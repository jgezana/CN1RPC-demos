// DO NOT MODIFY THIS FILE.  IT HAS BEEN AUTOMATICALLY GENERATED
// CHANGES MAY BE OVERWRITTEN WITHOUT NOTICE
package com.codename1.demos.adder;

import com.codename1.io.WebServiceProxyCall;
import com.codename1.io.WebServiceProxyCall.WSDefinition;
import java.io.IOException;
import java.lang.Class;
import java.lang.Integer;
import java.lang.String;

public class SimpleAdderProxy {
  private static boolean initialized;

  private final String url;

  private final WebServiceProxyCall.WSDefinition def_addInts;

  public SimpleAdderProxy(String url) {
    init();
    if (url.charAt(url.length()-1) != '/') {
      url = url + "/";
    }
    String urlPattern = "/SimpleAdderServlet";
    if (urlPattern.charAt(0) == '/') {
      urlPattern = urlPattern.substring(1);
    }
    this.url=url+urlPattern;
    def_addInts = WebServiceProxyCall.defineWebService(this.url, "addInts", WebServiceProxyCall.TYPE_INT, WebServiceProxyCall.TYPE_INT, WebServiceProxyCall.TYPE_INT);
  }

  private static void init() {
    if (!initialized) {
      initialized = true;
    }
  }

  public <T> T create(Class<T> cls) {
    throw new RuntimeException("No matching implementation found for class.");
  }

  public int addInts(int arg0, int arg1) throws IOException {
    return (Integer)WebServiceProxyCall.invokeWebserviceSync(def_addInts, arg0, arg1);
  }
}
