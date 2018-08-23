package com.sandy.mymovies.models.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Episode implements Serializable {

  @Id
  @GeneratedValue
  private int id;

  @Column
  private String imdbid;

  @Column
  private String season;

  @Column
  private String episodeNumber;

  @Column
  private String title;

  @Column
  private String description;

  public Episode(String imdbid, String season, String episodeNumber, String title,
      String description) {
    this.imdbid = imdbid;
    this.season = season;
    this.episodeNumber = episodeNumber;
    this.title = title;
    this.description = description;
  }
  // @todo - consider compound key? imdbId, season, episode number
}
