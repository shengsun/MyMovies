package com.sandy.mymovies.models.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Actor {

  @Id
  @GeneratedValue
  private Long id;

  /**
   * The name of the actor
   */
  private String name;

  /**
   * The imdbid of the movie associated with this actor
   */
  private String imdbid;

  public Actor(final String name, final String imdbid) {
    this.name = name;
    this.imdbid = imdbid;
  }

}
