package pages;

import internal.Constants;

import java.util.ArrayList;

public final class SeeDetailsPage implements Page {
    /*
     * Commands of type "change page" that can be executed on the see details page.
     * (In other words, pages a user can navigate to from the see detaails page)
     */
    private final ArrayList<String> changePageCommands;

    /*
     * Commands of type "on page" that can be executed on the upgrades page.
     */
    private final ArrayList<String> onPageCommands;
    private final String pageName = Constants.SEE_DETAILS_PAGE;
    private String movie;   // Name of the movie

    public SeeDetailsPage() {
        /*
         * Allow the user to go to the authenticated homepage, the movies page,
         * the upgrades page and the logout page from the see details page.
         */
        changePageCommands = new ArrayList<>();
        changePageCommands.add(Constants.AUTH_HOME_PAGE);
        changePageCommands.add(Constants.MOVIES_PAGE);
        changePageCommands.add(Constants.UPGRADES_PAGE);
        changePageCommands.add(Constants.LOGOUT_PAGE);
        changePageCommands.add(Constants.SEE_DETAILS_PAGE);

        /*
         * Allow users to execute the purchase, watch, like
         * and rate commands while on the see details page.
         */
        onPageCommands = new ArrayList<>();
        onPageCommands.add(Constants.PURCHASE_COMMAND);
        onPageCommands.add(Constants.WATCH_COMMAND);
        onPageCommands.add(Constants.LIKE_COMMAND);
        onPageCommands.add(Constants.RATE_COMMAND);
    }

    // Movie setter
    public void setMovie(final String movie) {
        this.movie = movie;
    }


    /*
     * Getters
     */

    public ArrayList<String> getChangePageCommands() {
        return this.changePageCommands;
    }

    public ArrayList<String> getOnPageCommands() {
        return this.onPageCommands;
    }

    public String getPageName() {
        return this.pageName;
    }
}
