package actions.commands;

import internal.io.Action;
import actions.Command;
import internal.*;
import pages.Page;
import pages.PageFactory;
import pages.SeeDetailsPage;

import java.util.ArrayList;

public class ChangePageCommand implements Command {
    private String page;
    private final String movie;

    public ChangePageCommand(final Action action) {
        this.page = action.getPage();
        this.movie = action.getMovie();
    }

    /**
     * Executes the change page command. Checks if the change from the current
     * page to the requested page is possible and outputs an error if the change
     * cannot be completed or changes the page otherwise.
     */
    @Override
    public void execute() {
        PageFactory pageFactory = new PageFactory();

        // The page the user is currently on
        Page currentPage = PooTV.getInstance().getCurrentPage();

        /*
         * If the user can't go to the requested page from the current page
         * output an error.
         */
        try {
            if (!currentPage.getChangePageCommands().contains(this.page)) {
                // Output error
                OutputMessages.writeError(PooTV.getInstance().getOutput());
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        boolean seeDetails = false;
        /*
         * If the user wants to change the page to the movie details' page,
         * create the "see details" page and navigate to it. Also update
         * the currentMovieList.
         */
        if (Constants.SEE_DETAILS_PAGE.equals(this.page)
                && PooTV.getInstance().existsCurrentMovie(this.movie)) {

            // Create the page
            SeeDetailsPage detailsPage = new SeeDetailsPage();

            // Set the movie for which the details will be displayed
            detailsPage.setMovie(this.movie);

            // Update currentMovieList
            ArrayList<Movie> movieList = new ArrayList<>();
            for (Movie movie : PooTV.getInstance().getCurrentMoviesList()) {
                if (movie.getName().equals(this.movie)) {
                    movieList.add(movie);
                    break;
                }
            }
            PooTV.getInstance().setCurrentMoviesList(movieList);

            // Navigate to the page
            PooTV.getInstance().setCurrentPage(detailsPage);
            // Output success
            OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
            return;
        } else {
            /*
             * If the movie for which the user wants to see the details
             * does not exist, stay on the movies page.
             */
            if (PooTV.getInstance().getCurrentUser() != null) {
                PooTV.getInstance().setCurrentMoviesList(new ArrayList<>());

                if (Constants.SEE_DETAILS_PAGE.equals(this.page)) {
                    seeDetails = true;
                    OutputMessages.writeError(PooTV.getInstance().getOutput());
                    this.page = Constants.MOVIES_PAGE;
                }
            }
        }

        /*
         * If the user navigates to the logout page, log him out and return
         * to the unauthenticated homepage.
         */
        if (Constants.LOGOUT_PAGE.equals(this.page)) {
            // Go to unauthenticated homepage
            PooTV.getInstance().setCurrentPage(pageFactory
                    .getPage(Constants.NOTAUTH_HOME_PAGE));

            // Set the current user to null
            PooTV.getInstance().setCurrentUser(null);

            // Reset currentMoviesList
            PooTV.getInstance().setCurrentMoviesList(new ArrayList<>());
            return;
        }

        /*
         * If the requested page is "movies", update the currentMoviesList
         * or clear it otherwise.
         */
        if (Constants.MOVIES_PAGE.equals(this.page)) {
            // Get the current user's country
            String country = PooTV.getInstance().getCurrentUser()
                    .getCredentials().getCountry();

            // Filter the movies list for banned countries
            ArrayList<Movie> displayedMovies = new ArrayList<>();

            // Copy
            displayedMovies
                    .addAll(PooTV.getInstance().getMovieDatabase().getMovies());

            // Remove the movies banned in the user's country
            displayedMovies.removeIf(movie ->
                    (movie.getCountriesBanned().contains(country)));

            // Set
            PooTV.getInstance().setCurrentMoviesList(displayedMovies);

            // Output success
            if (!seeDetails) {
                OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
            }

        } else {
            if (PooTV.getInstance().getCurrentUser() != null) {
                PooTV.getInstance().setCurrentMoviesList(new ArrayList<>());
            }
        }

        // Navigate to the requested page
        PooTV.getInstance().setCurrentPage(pageFactory.getPage(this.page));
    }
}
