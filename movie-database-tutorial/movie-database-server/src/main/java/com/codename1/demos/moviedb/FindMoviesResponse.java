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
public class FindMoviesResponse {
    Movie[] results;

    // setters and getters

    /**
     * @return the results
     */
    public Movie[] getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(Movie[] results) {
        this.results = results;
    }
}
