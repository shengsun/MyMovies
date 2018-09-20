package com.sandy.mymovies.models.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

//  @Column
//  @ManyToOne
//  private Genres genres;
//
//  @Column
//  @ManyToOne
//  private Tag tags;

  public enum Index {
    TAG("tag"),
    ACTOR("actor"),
    GENRE("genre"),
    DIRECTOR("director"),
    RATING("rating"),
    TITLE("title"),
    YEAR("year");

    private String value;

    private Index(String value) {
      this.value = value;
    }
  }
}
