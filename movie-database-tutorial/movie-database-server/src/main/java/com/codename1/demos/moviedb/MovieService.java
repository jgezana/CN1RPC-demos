/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.demos.moviedb;

import com.codename1.ws.annotations.WebService;
import java.util.ArrayList;

/**
 *
 * @author shannah
 */
@WebService(exports={"../../../../movie-database-client"})
public class MovieService {
    
    private static ExternalizableFactory factory = new ExternalizableFactory();
    
    private static ArrayList<Movie> db;
    
    private static int idCounter = 1;

    private static ArrayList<Movie> db() {
        if (db == null) {
            db = new ArrayList<Movie>();

            Object[] vals = new Object[]{
                "Top Gun", 1986, "A fighter pilot goes to school",
                "Rocky", 1977, "A boxer gets his shot at the title",
                "Lethal Weapon", 1987, "Two cops fight bad guys",
                "The Fast and the Furious",  1997, "A cop goes under cover with car racers",
                "Point Break", 1989, "A cop goes under cover with surfers"
            };

            for (int i=0; i<vals.length; i+=3) {
                Movie m = factory.create(Movie.class);
                m.setId(idCounter++);
                m.setName((String)vals[i]);
                m.setYear((int)vals[i+1]);
                m.setSynopsis((String)vals[i+2]);
                db.add(m);
            }
        }
        return db;
    }
    
    public static FindMoviesResponse findMovies(FindMoviesRequest request) {
        ArrayList<Movie> out = new ArrayList<Movie>();
        for (Movie m : db()) {
            if (m.getName().contains(request.getPattern())) {
                out.add(m);
            }
        }
        FindMoviesResponse response = factory.create(FindMoviesResponse.class);
        response.setResults(out.toArray(new Movie[out.size()]));
        return response;
    }
    
    public static UpdateMovieResponse updateMovie(UpdateMovieRequest request) {
        int id = request.getMovie().getId();
        UpdateMovieResponse response = factory.create(UpdateMovieResponse.class);
        synchronized(db()) {
            int len = db().size();
            for (int i=0; i<len; i++) {
                Movie m = db().get(i);
                if (m.getId() == id) {
                    db().set(i, request.getMovie());
                    response.setCode(UpdateMovieResponse.CODE_SUCCESS);
                    response.setMovie(request.getMovie());
                    response.setMessage("Successfully updated database");
                    return response;
                }
            }
        }

        response.setCode(UpdateMovieResponse.CODE_ERROR);
        response.setMessage("Failed to update movie.  Could not find movie with same ID");
        return response;
    }
    
    public static InsertMovieResponse insertMovie(InsertMovieRequest request) {
        Movie movie = request.getMovie();
        synchronized(db()) {
            movie.setId(idCounter++);
            db().add(movie);
        }
        InsertMovieResponse response = factory.create(InsertMovieResponse.class);
        response.setCode(UpdateMovieResponse.CODE_SUCCESS);
        response.setMovie(movie);
        response.setMessage("Movie updated successfully");
        return response;
    }
}
