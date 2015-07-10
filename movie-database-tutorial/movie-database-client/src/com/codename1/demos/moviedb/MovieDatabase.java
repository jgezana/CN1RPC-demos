package com.codename1.demos.moviedb;


import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MovieDatabase {

    private Form current;
    
    // A stack to keep track of the navigation of our forms.
    private LinkedList<Form> formStack = new LinkedList<Form>();

    private Movie currentMovie, currentlyEditing;
    private ArrayList<Movie> foundSet = new ArrayList<Movie>();

    // The web service proxy
    private MovieServiceProxy movieService;

    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch(IOException e){
            e.printStackTrace();
        }
        
        movieService = new MovieServiceProxy("http://localhost:8080/movie-database-server");
        // Pro users - uncomment this code to get crash reports sent to you automatically
        /*Display.getInstance().addEdtErrorHandler(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                evt.consume();
                Log.p("Exception in AppName version " + Display.getInstance().getProperty("AppVersion", "Unknown"));
                Log.p("OS " + Display.getInstance().getPlatformName());
                Log.p("Error " + evt.getSource());
                Log.p("Current Form " + Display.getInstance().getCurrent().getName());
                Log.e((Throwable)evt.getSource());
                Log.sendLog();
            }
        });*/
    }
    
    public void start() {
        showMovieList();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
    
    public void showMovieList() {
        final Form f = new Form("Movies");
        formStack.push(f);
        f.setLayout(new BorderLayout());

        final TextField searchField = new TextField();

        searchField.setHint("Search for Movie");
        f.addComponent(BorderLayout.NORTH, searchField);

        final Container moviesPanel = new Container();
        moviesPanel.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        updateMoviesPanel(moviesPanel);

        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FindMoviesRequest request = movieService.create(FindMoviesRequest.class);
                    request.setPattern(searchField.getText());
                    FindMoviesResponse response = movieService.findMovies(request);
                    foundSet.clear();
                    foundSet.addAll(Arrays.asList(response.getResults()));
                    updateMoviesPanel(moviesPanel);
                } catch (IOException ex) {
                   Log.e(ex);
                }
            }
        });
        f.addComponent(BorderLayout.CENTER, moviesPanel);
        
        f.addCommand(new Command("New Movie") {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentlyEditing = movieService.create(Movie.class);
                showEditForm();
            }
        });
        
        f.show();
    }

    private void updateMoviesPanel(Container panel) {
        panel.removeAll();
        for (final Movie m : foundSet) {
            Button l = new Button(m.getName());
            l.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button clicked");
                    currentMovie = m;
                    showMovieDetails();
                }
            });
            panel.addComponent(l);
        }
        panel.revalidate();
    }
    
    public void showMovieDetails() {
        Form f = new Form(currentMovie.getName());
        formStack.push(f);
        f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        f.addComponent(new Label("Name"));
        f.addComponent(new SpanLabel(currentMovie.getName()));
        f.addComponent(new Label("Year"));
        f.addComponent(new Label(""+currentMovie.getYear()));
        f.addComponent(new Label("Synopsis:"));
        f.addComponent(new SpanLabel(currentMovie.getSynopsis()));

        f.setBackCommand(new Command("Movies") {
            public void actionPerformed(ActionEvent e) {
                Form self = formStack.pop();
                formStack.peek().showBack();
            }
        });

        f.addCommand(new Command("Edit") {
            public void actionPerformed(ActionEvent e) {
                currentlyEditing = currentMovie;
                showEditForm();
            }
        });

        f.show();
    }
    
    public void showEditForm() {
        String formName = currentlyEditing.getId() > 0
                ? ("Edit " + currentlyEditing.getName()) : "Insert New Movie";
        Form f = new Form(formName);
        formStack.push(f);
        f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        if (currentlyEditing.getId() > 0) {
            f.addComponent(new Label("Movie ID"));
            f.addComponent(new Label("" + currentlyEditing.getId()));
        }
        f.addComponent(new Label("Name"));
        final TextField nameField = new TextField();
        nameField.setText(currentlyEditing.getName());
        f.addComponent(nameField);

        f.addComponent(new Label("Year"));
        final TextField yearField = new TextField();
        yearField.setText(currentlyEditing.getYear() + "");
        f.addComponent(yearField);

        f.addComponent(new Label("Synopsis"));
        final TextArea synopsisField = new TextArea();
        synopsisField.setText(currentlyEditing.getSynopsis());
        f.addComponent(synopsisField);

        f.addComponent(new Button(new Command("Save") {
            public void actionPerformed(ActionEvent e) {
                currentlyEditing.setName(nameField.getText());
                currentlyEditing.setSynopsis(synopsisField.getText());
                try {
                    currentlyEditing.setYear(Integer.parseInt(yearField.getText()));
                } catch (Throwable t) {
                    Log.e(t);
                }
                if (currentlyEditing.getId() > 0) {
                    // Already have an ID. This is an update.
                    UpdateMovieRequest request = movieService.create(UpdateMovieRequest.class);
                    request.setMovie(currentlyEditing);
                    UpdateMovieResponse response = null;
                    try {
                        response = movieService.updateMovie(request);
                    } catch (IOException ex) {
                        Dialog.show("Save failed", "Failed to save movie due to a network error: " + ex.getMessage(), "OK", "Cancel");
                        return;
                    }
                    if (response.getCode() == UpdateMovieResponse.CODE_SUCCESS) {
                        currentlyEditing = response.getMovie();
                        Dialog.show("Saved", "Movie was saved successfully", "OK", "Cancel");
                        // Go back to the view form
                        formStack.pop();
                        formStack.peek().showBack();
                    } else {
                        Dialog.show("Failed", response.getMessage(), "OK", "Cancel");
                    }
                } else {
                    // This is an insert

                    InsertMovieRequest request = movieService.create(InsertMovieRequest.class);
                    request.setMovie(currentlyEditing);
                    InsertMovieResponse response = null;
                    try {
                        response = movieService.insertMovie(request);
                    } catch (IOException ex) {
                        Dialog.show("Save failed", "Failed to save movie due to a network error: " + ex.getMessage(), "OK", "Cancel");
                        return;
                    }
                    if (response.getCode() == InsertMovieResponse.CODE_SUCCESS) {
                        currentlyEditing = response.getMovie();
                        Dialog.show("Saved", "Movie was saved successfully", "OK", "Cancel");
                        // Go back to the view form
                        foundSet.add(currentlyEditing);
                        formStack.pop();
                        formStack.peek().showBack();
                    } else {
                        Dialog.show("Failed", response.getMessage(), "OK", "Cancel");
                    }
                }
            }
        }));
        f.show();
    }

}
