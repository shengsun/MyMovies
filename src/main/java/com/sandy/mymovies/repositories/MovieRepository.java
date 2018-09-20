package com.sandy.mymovies.repositories;

import com.sandy.mymovies.models.domain.Movie;
import com.sandy.mymovies.models.dto.Cast;
import com.sandy.mymovies.models.dto.Title;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

  //  /title/{imdbid}
  Optional<Title> findTitleByImdbid(String imdbid);

  //  /cast/{imdbid}
  Optional<Cast> findCastByImdbid(String imdbid);

  //	/movie/{imdbid} <- Optional<Movie>, findById()
}
