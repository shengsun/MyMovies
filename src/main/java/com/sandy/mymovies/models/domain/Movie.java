package com.sandy.mymovies.models.domain;

import com.sandy.mymovies.models.dto.Cast;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;
import com.sandy.mymovies.models.dto.Genre;
import com.sandy.mymovies.models.dto.Tag;

@Entity
@Data
@AllArgsConstructor
public class Movie implements Serializable {

  @Id
  private String imdbid;

  @Column
  private String title;

  @Column
  private String releaseYear;

  @Column
  private String duration;

  @Column
  private String rating;

  @Column
  private String director;

  @Column
  private String imageUrl;

  @Column
  private String description;

  @Column
  @ManyToOne
  private Genre genre;

  @Column
  @ManyToOne
  private Tag tag;

  @Column
  private Cast cast;

  public Movie(String imdbid, String title, String releaseYear,String duration,
      String rating, String director, String imageUrl, String description) {
    this.imdbid = imdbid;
    this.title = title;
    this.releaseYear = releaseYear;
    this.duration = duration;;
    this.rating = rating;
    this.director = director;
    this.imageUrl = imageUrl;
    this.description = description;
  }
}
