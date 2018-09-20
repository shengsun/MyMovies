package com.sandy.mymovies.controllers;

import com.sandy.mymovies.models.domain.Movie;
import com.sandy.mymovies.models.dto.Title;
import com.sandy.mymovies.services.MyMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyMoviesController {

  private MyMoviesService service;

  // -- Basic crud endpoints --
  //		/title/{imdbid}
  //		/titles/{index}/{key}
  // 		/movies/{imdbid}
  //		/movies/{index}
  // 		/cast/{imdbid}
  // 		/episodes/{imdbid}/{season}
  // 		/episodes/{imdbid}
  // 		/season/{imdbid}
  // -- Index endpoints --
  //		/index/keys/{name}
  //		/index/{name}
  //		/index/{name}/{key}
  //    /search/{index}
  //    /count/{index}

  @Autowired
  public MyMoviesController(MyMoviesService service) {
    this.service = service;
  }

  /**
   * @return Individual movie
   */
  @RequestMapping(method = RequestMethod.GET, path = "/movie/{imdbid}")
  @ResponseStatus(HttpStatus.OK)
  public Movie getMovieByImdbId(@PathVariable String imdbid) {
    return service.getMovie(imdbid);
  }

  /**
   * @return Individual movie title
   */
  @RequestMapping(method = RequestMethod.GET, path = "/title/{imdbid}")
  @ResponseStatus(HttpStatus.OK)
  public Title getTitleByImdbId(@PathVariable String imdbid) {
    return service.getTitle(imdbid);
  }

}
