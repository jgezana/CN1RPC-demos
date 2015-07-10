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

public class FindMoviesResponseImpl extends FindMoviesResponse implements Externalizable {
  @Override
  public String getObjectId() {
    return "com.codename1.demos.moviedb.FindMoviesResponse";
  }

  @Override
  public int getVersion() {
    return 1;
  }

  @Override
  public void externalize(DataOutputStream out) throws IOException {
    out.writeInt(this.results==null?0:this.results.length);
    if (this.results != null) {
      for (int i=0; i<this.results.length; i++) {
        Util.writeObject(this.results[i], out);
      }
    }
  }

  @Override
  public void internalize(int version, DataInputStream in) throws IOException {
    int len = in.readInt();
    this.results = new Movie[len];
    if (len>0) {
      for (int i=0; i<len; i++) {
        this.results[i] = (com.codename1.demos.moviedb.Movie)Util.readObject(in);
      }
    }
  }
}
