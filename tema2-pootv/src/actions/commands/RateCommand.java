package actions.commands;

import internal.io.Action;
import actions.Command;
import actions.CommandFactory;
import internal.*;

public class RateCommand implements Command {
    private final String feature;
    private final int rating;

    public RateCommand(final Action action) {
        this.feature = action.getFeature();
        this.rating = action.getRate();
    }

    /**
     * Executes the rate command, verifies if the request is valid, otherwise
     * outputs an error. Duplicates will not be added to the rated movies array.
     * If the movie is not watched, outputs an error.
     */
    @Override
    public void execute() {
        // Get the current user
        User currentUser = PooTV.getInstance().getCurrentUser();
        // Get the current movie
        Movie currentMovie = null;
        try {
            currentMovie = PooTV.getInstance().getCurrentMoviesList().get(0);
        } catch (Exception ignored) {

        }

        // Verify if the current page allows the action
        if (!CommandFactory.verifyExecute(feature)
                || currentUser == null || currentMovie == null) {
            // Output error
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            // Exit
            return;
        }

        // Check the rating
        if (this.rating > Constants.MAX_RATING || this.rating < 0) {
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            return;
        }

        // Check if the movie is watched
        boolean watched = false;
        for (Movie movie : currentUser.getWatchedMovies()) {
            if (currentMovie.getName().equals(movie.getName())) {
                watched = true;
                break;
            }
        }

        if (!watched) {
            // Output error, movie not watched
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            // Exit
            return;
        }

        /*
         * Check if the movie is already watched in order to avoid
         * adding duplicates to the ratedMovies array.
         */
        boolean alreadyRated = false;
        for (Movie movie : currentUser.getRatedMovies()) {
            if (currentMovie.getName().equals(movie.getName())) {
                alreadyRated = true;
                break;
            }
        }

        if (!alreadyRated) {
            // Calculate new rating
            currentMovie.setRating(calculateRating(currentMovie, this.rating));
            // Increment ratings
            currentMovie.setNumRatings(currentMovie.getNumRatings() + 1);
            // Movie not rated, add it to the array
            currentUser.getRatedMovies().add(currentMovie);
            // Output success
            OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
            // Exit
            return;
        }

        // Add to rated movies
        currentUser.getRatedMovies().add(currentMovie);
        // Output success
        OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
    }

    /**
     * Calculates the new rating of a movie from the given values.
     * @param movie The movie's object.
     * @param userScore The score a user gave to the movie.
     * @return Recalculated mean value of the scores.
     */
    private double calculateRating(final Movie movie, final int userScore) {
        // Calculate the old sum of scores
        double sumRating = movie.getRating() * movie.getNumRatings();
        // Add the new score
        sumRating += userScore;
        // Divide by number of ratings + 1
        return sumRating / (movie.getNumRatings() + 1);
    }
}
