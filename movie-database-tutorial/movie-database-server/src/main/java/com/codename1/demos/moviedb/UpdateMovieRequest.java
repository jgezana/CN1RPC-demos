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
public class UpdateMovieRequest {
    Movie movie;

    /**
     * @return the movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * @param movie the movie to set
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
