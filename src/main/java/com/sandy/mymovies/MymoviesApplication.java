package com.sandy.mymovies;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandy.mymovies.models.domain.Genres;
import com.sandy.mymovies.models.domain.Movie;
import com.sandy.mymovies.models.domain.Tag;
import com.sandy.mymovies.models.dto.Cast;
import com.sandy.mymovies.repositories.EpisodeRepo;
import com.sandy.mymovies.repositories.MovieRepository;
import com.sandy.mymovies.services.MyMoviesService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log
@SpringBootApplication
public class MymoviesApplication implements CommandLineRunner {

  @Autowired
  MyMoviesService movieService;
  @Autowired
  MovieRepository movieRepository;
  @Autowired
  EpisodeRepo episodeRepo;

  public static void main(String[] args) {
    SpringApplication.run(MymoviesApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    movieRepository.save(new Movie("0128442", "Rounders", "1998", "2:01", "R", "John Dahl",
        "0128442.jpg",
        "A young man is a reformed gambler who must return to playing big stakes poker to help a friend pay off loan sharks."));

    Optional<Movie> m = movieRepository.findById("0128442");
    if (m.isPresent()) {
      log.info("---- yay ----");
      log.info(m.toString());
    } else {
      log.info("Unable to load movie with id: " + "0128442");
    }
    // Load files, create movie beans
    // Load files, create Episode beans

  }

  static class MovieFromFile {

    private String imdbid, title, releaseYear, duration, rating, director, imageUrl, description;
    private Genres genres;
    private Tag tags;
    private Cast cast;


  }

  static class EpisodeFromFile {

    private String imdbid, season, episodeNumber, title, description;

    static List<EpisodeFromFile> importEpisodes() throws IOException {
      return new ObjectMapper()
          .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
          .readValue(EpisodeFromFile.class.getResourceAsStream("foo"),
              new TypeReference<List<EpisodeFromFile>>() {
              });
    }
  }
}
