// DO NOT MODIFY THIS FILE.  IT HAS BEEN AUTOMATICALLY GENERATED
// CHANGES MAY BE OVERWRITTEN WITHOUT NOTICE
package com.codename1.demos.moviedb;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;

public class UpdateMovieRequestImpl extends UpdateMovieRequest implements Externalizable {
  @Override
  public String getObjectId() {
    return "com.codename1.demos.moviedb.UpdateMovieRequest";
  }

  @Override
  public int getVersion() {
    return 1;
  }

  @Override
  public void externalize(DataOutputStream out) throws IOException {
    Util.writeObject(this.movie, out);
  }

  @Override
  public void internalize(int version, DataInputStream in) throws IOException {
    this.movie = (com.codename1.demos.moviedb.Movie)Util.readObject(in);
  }
}
