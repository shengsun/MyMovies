package com.sandy.mymovies.models.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Tag {

  @Id
  @GeneratedValue
  private Long id;

  /**
   * The name of the tag
   */
  private String tag;

  /**
   * The imdbid of movie associated with this tag
   */
  private String imdbid;

  public Tag(final String tag, String imdbid) {
    this.tag = tag;
    this.imdbid = imdbid;
  }

}
