// DO NOT MODIFY THIS FILE.  IT HAS BEEN AUTOMATICALLY GENERATED
// CHANGES MAY BE OVERWRITTEN WITHOUT NOTICE
package com.codename1.demos.moviedb;

import com.codename1.io.WebServiceProxyCall;
import com.codename1.io.WebServiceProxyCall.WSDefinition;
import java.io.IOException;
import java.lang.Class;
import java.lang.String;

public class MovieServiceProxy {
  private static boolean initialized;

  private final String url;

  private final WebServiceProxyCall.WSDefinition def_findMovies;

  private final WebServiceProxyCall.WSDefinition def_updateMovie;

  private final WebServiceProxyCall.WSDefinition def_insertMovie;

  public MovieServiceProxy(String url) {
    init();
    if (url.charAt(url.length()-1) != '/') {
      url = url + "/";
    }
    String urlPattern = "/MovieServiceServlet";
    if (urlPattern.charAt(0) == '/') {
      urlPattern = urlPattern.substring(1);
    }
    this.url=url+urlPattern;
    def_findMovies = WebServiceProxyCall.defineWebService(this.url, "findMovies", WebServiceProxyCall.TYPE_EXTERNALIABLE, WebServiceProxyCall.TYPE_EXTERNALIABLE);
    def_updateMovie = WebServiceProxyCall.defineWebService(this.url, "updateMovie", WebServiceProxyCall.TYPE_EXTERNALIABLE, WebServiceProxyCall.TYPE_EXTERNALIABLE);
    def_insertMovie = WebServiceProxyCall.defineWebService(this.url, "insertMovie", WebServiceProxyCall.TYPE_EXTERNALIABLE, WebServiceProxyCall.TYPE_EXTERNALIABLE);
  }

  private static void init() {
    if (!initialized) {
      initialized = true;
      new ExternalizableFactory().init();
    }
  }

  public <T> T create(Class<T> cls) {
    try {
      return new ExternalizableFactory().create(cls);
    }
    catch (Throwable t) {
    }
    throw new RuntimeException("No matching implementation found for class.");
  }

  public FindMoviesResponse findMovies(FindMoviesRequest arg0) throws IOException {
    return (FindMoviesResponse)WebServiceProxyCall.invokeWebserviceSync(def_findMovies, arg0);
  }

  public UpdateMovieResponse updateMovie(UpdateMovieRequest arg0) throws IOException {
    return (UpdateMovieResponse)WebServiceProxyCall.invokeWebserviceSync(def_updateMovie, arg0);
  }

  public InsertMovieResponse insertMovie(InsertMovieRequest arg0) throws IOException {
    return (InsertMovieResponse)WebServiceProxyCall.invokeWebserviceSync(def_insertMovie, arg0);
  }
}
