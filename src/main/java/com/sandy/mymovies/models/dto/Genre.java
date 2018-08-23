package com.sandy.mymovies.models.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class Genre extends ArrayList<String> implements Serializable {

}
