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

public class MovieImpl extends Movie implements Externalizable {
  @Override
  public String getObjectId() {
    return "com.codename1.demos.moviedb.Movie";
  }

  @Override
  public int getVersion() {
    return 1;
  }

  @Override
  public void externalize(DataOutputStream out) throws IOException {
    out.writeInt(this.id);
    Util.writeUTF(this.name, out);
    out.writeInt(this.year);
    Util.writeUTF(this.synopsis, out);
  }

  @Override
  public void internalize(int version, DataInputStream in) throws IOException {
    this.id = in.readInt();
    this.name = Util.readUTF(in);
    this.year = in.readInt();
    this.synopsis = Util.readUTF(in);
  }
}
