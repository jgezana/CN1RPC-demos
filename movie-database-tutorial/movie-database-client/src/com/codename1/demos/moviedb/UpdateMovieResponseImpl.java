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

public class UpdateMovieResponseImpl extends UpdateMovieResponse implements Externalizable, ExternalizableFactory.Versioned {
  int __version = 1;

  public void setVersion(int version) {
    __version=version;
  }

  @Override
  public String getObjectId() {
    return "com.codename1.demos.moviedb.UpdateMovieResponse";
  }

  @Override
  public int getVersion() {
    return __version;
  }

  @Override
  public void externalize(DataOutputStream out) throws IOException {
    if (__version == 1) {
      out.writeInt(this.code);
      Util.writeUTF(this.message, out);
      Util.writeObject(this.movie, out);
    }
    else {
      throw new RuntimeException("Unsupported write version for entity "+getObjectId()+" version "+__version+"");
    }
  }

  @Override
  public void internalize(int version, DataInputStream in) throws IOException {
    if (version == 1) {
      __version = version;
      this.code = in.readInt();
      this.message = Util.readUTF(in);
      this.movie = (com.codename1.demos.moviedb.Movie)Util.readObject(in);
    }
    else {
      throw new RuntimeException("Unsupported read version for entity "+getObjectId()+" version "+version+"");
    }
  }
}
