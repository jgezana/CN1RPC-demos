/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.demos;

import com.codename1.ws.annotations.WebService;

@WebService(exports={"../../../../adder-client-maven"})
public class Adder {
    public static int addInts(int a, int b) {
        return a+b;
    }
}
