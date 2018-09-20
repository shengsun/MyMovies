package com.sandy.mymovies.models.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Title implements Serializable {

  private String imdbid;
  private String title;
  private String releaseYear;
  private String imageUrl;
}
