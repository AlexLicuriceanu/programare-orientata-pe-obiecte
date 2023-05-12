package internal;

import internal.io.Credentials;

import java.util.ArrayList;

public final class User {
    // Credentials object
    private Credentials credentials;
    // Number of the user's tokensCount
    private int tokensCount;
    // List of purchased movies
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    // List of liked movies
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    // List of rated movies
    private ArrayList<Movie> ratedMovies = new ArrayList<>();
    // List of watched movies
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private int numFreePremiumMovies = Constants.NUM_FREE_PREMIUM_MOVIES;

    public User() {
    }

    public User(final Credentials credentials) {
        super();
        this.credentials = credentials;
    }


    /*
     * Getters and setters
     */

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }
}
