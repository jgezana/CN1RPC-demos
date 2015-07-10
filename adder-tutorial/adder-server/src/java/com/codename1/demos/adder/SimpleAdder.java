package com.codename1.demos.adder;

import com.codename1.ws.annotations.WebService;

/**
 *
 * @author shannah
 */
@WebService(exports="../../../adder-client")
public class SimpleAdder {
    public static int addInts(int a, int b) {
        return a+b;
    }
}
