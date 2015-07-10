/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.demos.moviedb;

import com.codename1.ws.annotations.Externalizable;

/**
 *
 * @author shannah
 */
@Externalizable
public class FindMoviesRequest {
    String pattern;
    // getters and setters

    /**
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @param pattern the pattern to set
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
