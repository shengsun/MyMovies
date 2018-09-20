package com.sandy.mymovies.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import com.sandy.mymovies.models.domain.Actor;
import com.sandy.mymovies.models.domain.Movie;
import com.sandy.mymovies.models.dto.Cast;
import com.sandy.mymovies.models.dto.Title;
import com.sandy.mymovies.repositories.ActorRepository;
import com.sandy.mymovies.repositories.MovieRepository;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyMoviesServiceTests {

  @Autowired
  MyMoviesService moviesService;

  @Autowired
  MovieRepository movieRepository;

  @Autowired
  ActorRepository actorRepository;

  private String imdbid;
  private String title;

  @Before
  public void createRoundersMovie() {
    this.imdbid = "0128442";
    this.title = "Rounders";
    movieRepository.save(new Movie(imdbid, title, "1998", "2:01", "R", "John Dahl",
        "0128442.jpg",
        "A young man is a reformed gambler who must return to playing big stakes poker to help a friend pay off loan sharks."));
  }

  @Test
  public void getMovie_withValidId_returnsMovie() {
    Movie movie = moviesService.getMovie(imdbid);
    assertThat(movie, is(notNullValue()));
    assertThat(movie.getTitle(), is(title));
  }

  @Test
  public void getMovie_withInvalidId_throwsException() {
    try {
      Movie movie = moviesService.getMovie("0");
      fail("Invalid imdbid should throw NoSuchElementException");
    } catch (NoSuchElementException ex) {
      assertThat(ex, instanceOf(NoSuchElementException.class));
    }
  }

  @Test
  public void getTitle_withValidId_returnsTitle() {
    Title title = moviesService.getTitle(imdbid);
    assertThat(title, is(notNullValue()));
    assertThat(title.getImdbid(), is(imdbid));
  }

  @Test
  public void getTitle_withInvalidId_throwsException() {
    try {
      Title title = moviesService.getTitle("0");
      fail("Invalid imdbid should throw NoSuchElementException");
    } catch (NoSuchElementException ex) {
      assertThat(ex, instanceOf(NoSuchElementException.class));
    }
  }

  @Test
  public void getTitle_withValidIdAndCast_returnsCast() {
    String name = "Matt Damon";
    actorRepository.save(new Actor(name, imdbid));
    Cast cast = moviesService.getCast(imdbid);
    assertThat(cast, is(notNullValue()));
    assertThat(cast.contains(name), is(true));
  }

  @Test
  public void getTitle_withValidId_throwsExceptionWhenNoCast() {
    try {
      Cast cast = moviesService.getCast(imdbid);
      fail("No cast should throw NoSuchElementException");
    } catch (NoSuchElementException ex) {
      assertThat(ex, instanceOf(NoSuchElementException.class));
    }
  }

}
