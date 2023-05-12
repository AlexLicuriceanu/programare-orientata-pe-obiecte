package actions.commands;

import internal.io.Action;
import actions.Command;
import actions.CommandFactory;
import internal.io.Filters;
import internal.Movie;
import internal.OutputMessages;
import internal.PooTV;
import internal.User;

import java.util.ArrayList;
import java.util.Comparator;

public class FilterCommand implements Command {
    private final String page;
    private final Filters filters;
    private final String feature;

    public FilterCommand(final Action action) {
        this.page = action.getPage();
        this.filters = action.getFilters();
        this.feature = action.getFeature();
    }


    /**
     * Executes the filter command. Checks the validity of the request then
     * filters the list of movies based on the input filters.
     */
    @Override
    public void execute() {
        // Verify if the current page allows the action
        if (!CommandFactory.verifyExecute(feature)) {
            // Output error
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            return;
        }

        /*
         * Get the movies available in the user's country
         */
        ArrayList<Movie> currentMovies = new ArrayList<>();
        currentMovies
                .addAll(PooTV.getInstance().getMovieDatabase().getMovies());
        // Get the current user
        User currentUser = PooTV.getInstance().getCurrentUser();
        // Remove movies banned in the user's country
        currentMovies.removeIf(movie -> (movie.getCountriesBanned()
                .contains(currentUser.getCredentials().getCountry())));


        /*
         * Filter by the contains filter first.
         * Iterate the filter actors and for each check if the actor
         * is playing in the movie. If not, remove the movie.
         */
        if (filters.getContains() != null) {
            if (filters.getContains().getActors() != null) {
                for (String actor : this.filters.getContains().getActors()) {
                    currentMovies.removeIf(movie ->
                            !movie.getActors().contains(actor));
                }
            }

            if (filters.getContains().getGenre() != null) {
                for (String genre : this.filters.getContains().getGenre()) {
                    currentMovies.removeIf(movie ->
                            !movie.getGenres().contains(genre));
                }
            }
        }

        if (filters.getSort() == null) {
            // Set the list of sorted movies
            PooTV.getInstance().setCurrentMoviesList(currentMovies);
            // Output success
            OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
            return;
        }

        // Sort by rating first
        if (filters.getSort().getRating() != null) {
            if (filters.getSort().getRating().equals("decreasing")) {
                currentMovies.sort(Comparator
                        .comparing(Movie::getRating).reversed());
            } else if (filters.getSort().getRating().equals("increasing")) {
                currentMovies.sort(Comparator.comparing(Movie::getRating));
            }
        }

        // Then sort by duration
        if (filters.getSort().getDuration() != null) {
            if (filters.getSort().getDuration().equals("decreasing")) {
                currentMovies.sort(Comparator
                        .comparing(Movie::getDuration).reversed());
            } else if (filters.getSort().getDuration().equals("increasing")) {
                currentMovies.sort(Comparator.comparing(Movie::getRating));
            }
        }

        // Set the list of sorted movies
        PooTV.getInstance().setCurrentMoviesList(currentMovies);
        // Output success
        OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
    }
}
