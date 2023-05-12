package internal.io;

import internal.Movie;
import internal.User;

import java.util.ArrayList;

public final class InputData {
    private ArrayList<User> users;          // List of registered users
    private ArrayList<Movie> movies;        // List of available movies
    private ArrayList<Action> actions;      // List of commands to execute

    /*
     * Getters and setters
     */

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<Action> actions) {
        this.actions = actions;
    }
}
