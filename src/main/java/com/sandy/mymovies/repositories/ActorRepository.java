package com.sandy.mymovies.repositories;

import com.sandy.mymovies.models.domain.Actor;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA interface to Actor table
 */
@Repository
public interface ActorRepository extends CrudRepository<Actor, String> {

  @Query("SELECT name FROM Actor WHERE imdbId=?1 ORDER BY name")
  List<String> findAllByImdbid(String imdbid);
}
