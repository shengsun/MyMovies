package com.sandy.mymovies.services;

import com.sandy.mymovies.models.domain.Episode;
import com.sandy.mymovies.models.domain.Genres;
import com.sandy.mymovies.models.domain.Movie;
import com.sandy.mymovies.models.domain.Tag;
import com.sandy.mymovies.models.dto.Cast;
import com.sandy.mymovies.models.dto.Title;
import com.sandy.mymovies.repositories.ActorRepository;
import com.sandy.mymovies.repositories.EpisodeRepo;
import com.sandy.mymovies.repositories.MovieRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyMoviesService {

  private static final String MOVIE_NOT_FOUND = "Movie not found with id %s .";

  private MovieRepository movieRepository;
  private EpisodeRepo episodeRepository;
  private ActorRepository actorRepository;

  @Autowired
  public MyMoviesService(MovieRepository movieRepository, EpisodeRepo episodeRepository,
      ActorRepository actorRepository) {
    this.movieRepository = movieRepository;
    this.episodeRepository = episodeRepository;
    this.actorRepository = actorRepository;
  }

  /**
   * Creates a Movie, if one with provided imdbid does not exist
   *
   * @return created movie
   */
  public Movie createMovie(String imdbid, String title, String releaseYear,
      String duration, String rating, String director,
      String imageUrl, String description,
      Genres genres, Tag tags) {
    Optional<Movie> movie = movieRepository.findById(imdbid);
    if (movie.isPresent()) {
      return movie.get();
    }
    return movieRepository.save(
        new Movie(imdbid, title, releaseYear, duration,
            rating, director, imageUrl, description)//, genres, tags)
    );
  }

  /**
   * Creates an Episode, if one with provided imdbid does not exist
   *
   * @return created episode
   */
  public Episode createEpisode(String imdbid, String season,
      String episodeNumber, String title, String description) {
    Optional<Episode> episode = episodeRepository.findById(imdbid);
    if (episode.isPresent()) {
      return episode.get();
    }
    return episodeRepository.save(
        new Episode(imdbid, season, episodeNumber, title, description)
    );
  }

  /**
   * Verify and return a Movie given a imdbid
   *
   * @return the found movie
   */
  public Movie getMovie(String imdbid) {
    Optional<Movie> movie = movieRepository.findById(imdbid);
    return movie.get();
  }

  public Title getTitle(String imdbid) {
    Optional<Title> title = movieRepository.findTitleByImdbid(imdbid);
    return title.get();
  }

  public Cast getCast(String imdbid) {
    Optional<Movie> movie = movieRepository.findById(imdbid);
    if (!movie.isPresent()) {
      throw new NoSuchElementException(String.format(MOVIE_NOT_FOUND, imdbid));
    }

    List<String> actors = actorRepository.findAllByImdbid(imdbid);
    if (actors == null || actors.isEmpty()) {
      throw new NoSuchElementException(String.format("Cast for movie %s not found", imdbid));
    }
    Cast cast = new Cast();
    cast.addAll(actors);

    return cast;
  }

}
