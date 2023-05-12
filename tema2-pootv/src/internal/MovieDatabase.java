package internal;

import java.util.ArrayList;

/**
 * Thread-safe Singleton for the movie database.
 */
public final class MovieDatabase implements Database {
    private final static MovieDatabase MOVIE_DATABASE;

    private ArrayList<Movie> movies = new ArrayList<>();
    private int numMovies;

    static {
        MOVIE_DATABASE = new MovieDatabase();
    }

    private MovieDatabase() {
    }

    public static MovieDatabase getInstance() {
        return MOVIE_DATABASE;
    }

    /**
     * Adds a single movie to the database, also checking for duplicates.
     * @param movie Movie to be added.
     */
    public void addMovie(final Movie movie) {
        if (!this.movies.contains(movie)) {
            this.movies.add(movie);
            this.numMovies++;
        }
    }

    /**
     * Adds a list of movies to the database, also checking for duplicates.
     * @param movies list of movies.
     */
    public void addMovies(final ArrayList<Movie> movies) {
        for (Movie movie : movies) {
            addMovie(movie);
        }
    }

    /**
     * Checks if a movie with the given name exists in the database.
     * @param movieName Movie name to check.
     * @return true if the movie exists, false otherwise.
     */
    public boolean existsMovie(final String movieName) {
        for (Movie movie : this.movies) {
            if (movie.getName().equals(movieName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the movie object whose name matches the string passed as
     * a parameter or null if the movie with that name does not exist.
     * @param movieName The movie's name.
     * @return The movie's object or null if no name matches are found.
     */
    public Movie getMovie(final String movieName) {
        if (movieName == null) {
            return null;
        }

        for (Movie movie : this.movies) {
            if (movie.getName().equals(movieName)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Removes all elements from the database.
     */
    @Override
    public void dropDatabase() {
        this.movies = new ArrayList<>();
        this.numMovies = 0;
    }

    /*
     * Getters and setters.
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public int getNumMovies() {
        return numMovies;
    }

    public void setNumMovies(final int numMovies) {
        this.numMovies = numMovies;
    }
}
