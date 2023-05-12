package actions.commands;

import internal.io.Action;
import actions.Command;
import actions.CommandFactory;
import internal.Movie;
import internal.OutputMessages;
import internal.PooTV;

import java.util.ArrayList;

public class SearchCommand implements Command {
    private final String page;
    private final String startsWith;
    private final String feature;

    public SearchCommand(final Action action) {
        this.page = action.getPage();
        this.startsWith = action.getStartsWith();
        this.feature = action.getFeature();
    }

    /**
     * Executes the search command. Filters the movie names, showing
     * only the movies that start with the startsWith string.
     */
    @Override
    public void execute() {
        // Verify if the current page allows the action
        if (!CommandFactory.verifyExecute(feature)) {
            // Output error
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            return;
        }

        // Get the movies available in the user's country
        ArrayList<Movie> currentMovies =
                PooTV.getInstance().getCurrentMoviesList();

        // Remove all movies that don't start with the given string
        currentMovies.removeIf(movie ->
                !movie.getName().startsWith(this.startsWith));
        PooTV.getInstance().setCurrentMoviesList(currentMovies);

        // Output success
        OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
    }

    /*
     * Getters
     */

    public final String getPage() {
        return page;
    }

    public final String getStartsWith() {
        return startsWith;
    }

    public final String getFeature() {
        return feature;
    }
}
