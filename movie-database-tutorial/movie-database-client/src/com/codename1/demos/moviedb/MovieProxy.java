// DO NOT MODIFY THIS FILE.  IT HAS BEEN AUTOMATICALLY GENERATED
// CHANGES MAY BE OVERWRITTEN WITHOUT NOTICE
package com.codename1.demos.moviedb;

import java.lang.Class;
import java.lang.String;

public class MovieProxy {
  private static boolean initialized;

  private final String url;

  public MovieProxy(String url) {
    init();
    if (url.charAt(url.length()-1) != '/') {
      url = url + "/";
    }
    String urlPattern = "/MovieServlet";
    if (urlPattern.charAt(0) == '/') {
      urlPattern = urlPattern.substring(1);
    }
    this.url=url+urlPattern;
  }

  private static void init() {
    if (!initialized) {
      initialized = true;
    }
  }

  public <T> T create(Class<T> cls) {
    throw new RuntimeException("No matching implementation found for class.");
  }
}
