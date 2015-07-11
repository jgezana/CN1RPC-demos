package com.codename1.demos;

import java.lang.Class;

class AdderVersionsFor1 {
  static int getVersionFor(Class cls) {
    throw new RuntimeException("Cannot find version for class "+cls);
  }
}
