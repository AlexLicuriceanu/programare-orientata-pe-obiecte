package actions.commands;

import internal.io.Action;
import actions.Command;
import actions.CommandFactory;
import internal.Movie;
import internal.OutputMessages;
import internal.PooTV;
import internal.User;

public class WatchCommand implements Command {
    private final String feature;

    public WatchCommand(final Action action) {
        this.feature = action.getFeature();
    }


    /**
     * Executes the watch command, verifies if the request is valid, otherwise
     * outputs an error. No duplicates will be added to the watchedMovies list.
     * If the movie is not purchased, output an error.
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

        // Check if the movie is purchased
        boolean purchased = false;

        for (Movie movie : currentUser.getPurchasedMovies()) {
            if (currentMovie.getName().equals(movie.getName())) {
                purchased = true;
                break;
            }
        }

        if (!purchased) {
            // Output error, movie not purchased
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            // Exit
            return;
        }

        // Check for duplicates
        boolean isWatched = false;
        for (Movie movie : currentUser.getWatchedMovies()) {
            if (currentMovie.getName().equals(movie.getName())) {
                isWatched = true;
                break;
            }
        }

        if (!isWatched) {
            currentUser.getWatchedMovies().add(currentMovie);
        }

        // Output success
        OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
    }
}
