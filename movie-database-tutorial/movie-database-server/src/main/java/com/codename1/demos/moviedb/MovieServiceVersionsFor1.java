package com.codename1.demos.moviedb;

import java.lang.Class;

class MovieServiceVersionsFor1 {
  static int getVersionFor(Class cls) {
    if (InsertMovieResponse.class.equals(cls)) {
      return 1;
    }
    else if (UpdateMovieRequest.class.equals(cls)) {
      return 1;
    }
    else if (FindMoviesRequest.class.equals(cls)) {
      return 1;
    }
    else if (Movie.class.equals(cls)) {
      return 1;
    }
    else if (UpdateMovieResponse.class.equals(cls)) {
      return 1;
    }
    else if (InsertMovieRequest.class.equals(cls)) {
      return 1;
    }
    else if (FindMoviesResponse.class.equals(cls)) {
      return 1;
    }
    throw new RuntimeException("Cannot find version for class "+cls);
  }
}
