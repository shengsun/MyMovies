package com.sandy.mymovies;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandy.mymovies.models.dto.Cast;
import com.sandy.mymovies.models.dto.Genre;
import com.sandy.mymovies.models.dto.Tag;
import com.sandy.mymovies.services.MyMoviesService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MymoviesApplication {

	@Autowired
	MyMoviesService movieService;

	public static void main(String[] args) {
		SpringApplication.run(MymoviesApplication.class, args);
	}

	public void run(String... args) throws Exception {
		// Load files, create movie beans
		// Load files, create Episode beans
	}

	static class MovieFromFile {
		private String imdbid, title, releaseYear, duration, rating, director, imageUrl, description;
		private Genre genre;
		private Tag tags;
		private Cast cast;

	}

	static class EpisodeFromFile {
		private String imdbid, season, episodeNumber, title, description;
		static List<EpisodeFromFile> importEpisodes() throws IOException {
			return new ObjectMapper()
					.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
					.readValue(EpisodeFromFile.class.getResourceAsStream("foo")),
		}
	}
}
