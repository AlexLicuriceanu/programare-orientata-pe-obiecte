package actions.commands;

import internal.io.Action;
import actions.Command;
import actions.CommandFactory;
import internal.*;

public class PurchaseCommand implements Command {
    private final String feature;

    public PurchaseCommand(final Action action) {
        this.feature = action.getFeature();
    }

    /**
     * Executes the purchase command. Tests the following error cases and
     * outputs an error if any of them are true:
     * If the movie has already been purchased, if the user has enough tokens
     * to buy the movie or if the user is premium and still has free movies
     * left.
     * Otherwise, it buys the movie.
     */
    @Override
    public void execute() {
        // Verify if the current page allows the action
        if (!CommandFactory.verifyExecute(feature)) {
            // Output error
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            return;
        }

        // Get the current movie
        Movie currentMovie = null;
        try {
            currentMovie = PooTV.getInstance().getCurrentMoviesList().get(0);
        } catch (Exception ignored) {

        }

        // Get the current user
        User currentUser = PooTV.getInstance().getCurrentUser();
        // Verify the user is not null
        if (currentUser == null || currentMovie == null) {
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            return;
        }


        // Verify if the user has already purchased the movie
        for (Movie movie : currentUser.getPurchasedMovies()) {
            if (movie.getName().equals(currentMovie.getName())) {
                // Movie is already purchased, output error
                OutputMessages.writeError(PooTV.getInstance().getOutput());
                // Exit
                return;
            }
        }

        // Check if the user is premium
        if (currentUser.getCredentials().getAccountType()
                .equals(Constants.PREMIUM_ACCOUNT_LABEL)) {

            // Check if the user still has any free premium movies left
            if (currentUser.getNumFreePremiumMovies() > 0) {
                int newNumFreePremiumMovies =
                        currentUser.getNumFreePremiumMovies();
                // Decrement
                newNumFreePremiumMovies--;
                // Set number of free premium movies left
                currentUser.setNumFreePremiumMovies(newNumFreePremiumMovies);
                // Set the purchased movies array
                currentUser.getPurchasedMovies().add(currentMovie);
                // Output success
                OutputMessages.writeSuccess(PooTV.getInstance().getOutput());

            } else {
                // User has to pay for the movie, check if he has the tokens
                if (currentUser.getTokensCount()
                        >= Constants.MOVIE_TOKENS_PRICE) {
                    int newTokensCount = currentUser.getTokensCount();
                    // Decrement
                    newTokensCount -= Constants.MOVIE_TOKENS_PRICE;
                    // Set the new token count
                    currentUser.setTokensCount(newTokensCount);
                    // Add the movie to the purchased list
                    currentUser.getPurchasedMovies().add(currentMovie);
                    // Output success
                    OutputMessages
                            .writeSuccess(PooTV.getInstance().getOutput());
                } else {
                    // Not enough tokens, output error
                    OutputMessages.writeError(PooTV.getInstance().getOutput());
                }
            }
        } else {
            // If the user is non-premium
            if (currentUser.getTokensCount() >= Constants.MOVIE_TOKENS_PRICE) {
                int newTokensCount = currentUser.getTokensCount();
                // Decrement
                newTokensCount -= Constants.MOVIE_TOKENS_PRICE;
                // Set the new token count
                currentUser.setTokensCount(newTokensCount);
                // Add the movie to the purchased list
                currentUser.getPurchasedMovies().add(currentMovie);
                // Output success
                OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
                // Exit
            } else {
                // Not enough tokens, output error
                OutputMessages.writeError(PooTV.getInstance().getOutput());
            }
        }
    }
}
