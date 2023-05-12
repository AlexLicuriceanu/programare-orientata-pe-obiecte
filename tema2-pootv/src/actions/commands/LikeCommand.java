package actions.commands;

import internal.io.Action;
import actions.Command;
import actions.CommandFactory;
import internal.Movie;
import internal.OutputMessages;
import internal.PooTV;
import internal.User;

public class LikeCommand implements Command {
    private final String feature;

    public LikeCommand(final Action action) {
        this.feature = action.getFeature();
    }

    /**
     * Executes the like command. Likes a movie if it has been watched, outputs
     * an error otherwise.
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
         * Check if the movie is already liked in order to avoid
         * adding duplicates to the likedMovies array.
         */
        boolean alreadyLiked = false;
        for (Movie movie : currentUser.getLikedMovies()) {
            if (currentMovie.getName().equals(movie.getName())) {
                alreadyLiked = true;
                break;
            }
        }

        if (!alreadyLiked) {
            // Increment number of likes
            currentMovie.setNumLikes(currentMovie.getNumLikes() + 1);
            // Movie not liked, add it to the array
            currentUser.getLikedMovies().add(currentMovie);
            // Output success
            OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
            // Exit
            return;
        }

        // Output success
        OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
    }
}
