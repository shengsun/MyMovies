package com.sandy.mymovies.services;

import com.sandy.mymovies.models.domain.Episode;
import com.sandy.mymovies.models.domain.Movie;
import com.sandy.mymovies.models.dto.Genre;
import com.sandy.mymovies.models.dto.Tag;
import com.sandy.mymovies.models.dto.Title;
import com.sandy.mymovies.repositories.EpisodeRepo;
import com.sandy.mymovies.repositories.MovieRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyMoviesService {

	private MovieRepository movieRepository;
	private EpisodeRepo episodeRepository;

	@Autowired
	public MyMoviesService(MovieRepository movieRepository, EpisodeRepo episodeRepository) {
		this.movieRepository = movieRepository;
		this.episodeRepository = episodeRepository;
	}

	/**
	 * Creates a Movie, if one with provided imdbid does not exist
	 * @param imdbid
	 * @param title
	 * @param releaseYear
	 * @param duration
	 * @param rating
	 * @param director
	 * @param imageUrl
	 * @param description
	 * @param genres
	 * @param tags
	 * @return created movie
	 */
	public Movie createMovie(String imdbid, String title, String releaseYear,
						String duration, String rating, String director,
						String imageUrl, String description,
						Genre genres, Tag tags) {
		Optional<Movie> movie = movieRepository.findById(imdbid);
		if (movie.isPresent()) {
			return movie.get();
		}
		return movieRepository.save(
				new Movie(imdbid, title, releaseYear, duration,
					rating, director, imageUrl, description)
					// genres, tags) <-- only need to create objs?
		);
	}

	/**
	 * Creates an Episode, if one with provided imdbid does not exist
	 * @param imdbid
	 * @param season
	 * @param episodeNumber
	 * @param title
	 * @param description
	 * @return created episode
	 */
	public Episode createEpisode(String imdbid, String season,
						 String episodeNumber, String title, String description) {
//		@todo- use episodeRepo to see if imdbid,season,episode already exists
//		if (episode.isPresent()) {
//    	return episode;
//		}
		return episodeRepository.save(
				new Episode(imdbid, season, episodeNumber, title, description)
		);
	}

	/**
	 * Verify and return a Movie given a imdbid
 	 * @param imdbid
	 * @return the found movie
	 */
	public Movie verifyMovie(String imdbid) {
		Optional<Movie> movie = movieRepository.findById(imdbid);
		if (!movie.isPresent()) {
			// throw exception?
		}
		return movie.get();
	}

	public Title verifyTitle(String imdbid) {
		Optional<Title> title = movieRepository.findTitleByImdbid(imdbid);
		if (!title.isPresent()) {
			// throw exception?
		}
		return title.get();
	}

//	public Cast verifyCast(String imdbid) {
//		Optional<Cast> cast = movieRepository.findCastByImdbid(imdbid);
//		if (!cast.isPresent()) {
//			// throw exception?
//		}
//		return cast;
//	}

	public List<Episode> verifyEpisodes(String imdbid) {
		return episodeRepository.findAllByImdbid(imdbid);
	}

	public List<Episode> verifyEpisodes(String imdbid, String seasonNumber) {
		return episodeRepository.findAllByImdbidAndSeason(imdbid, seasonNumber);
	}
	
	public List<String> verifyImdbAndReturnSeasons(String imdbid) {
		return episodeRepository.findDistinctSeasonsByImdbid(imdbid);
	}
	// Remaining methods:
	// verifyMoviesByIndex(index)
	// verifyTitlesByIndexAndKey(index, key)
}
