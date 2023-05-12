package pages;

import internal.Constants;

import java.util.ArrayList;

public final class MoviesPage implements Page {
    /*
     * Commands of type "change page" that can be executed on the movies page.
     * (In other words, pages a user can navigate to from the movies page)
     */
    private final ArrayList<String> changePageCommands;

    /*
     * Commands of type "on page" that can be executed on the movies page.
     */
    private final ArrayList<String> onPageCommands;
    private final String pageName = Constants.MOVIES_PAGE;

    public MoviesPage() {
        /*
         * Allow a user to go to the authenticated homepage, see details page and logout page
         * from the movies page
         */
        changePageCommands = new ArrayList<>();
        changePageCommands.add(Constants.AUTH_HOME_PAGE);
        changePageCommands.add(Constants.SEE_DETAILS_PAGE);
        changePageCommands.add(Constants.LOGOUT_PAGE);
        changePageCommands.add(Constants.MOVIES_PAGE);

        /*
         * Allow users to execute the search and filter commands while on the movies page.
         */
        onPageCommands = new ArrayList<>();
        onPageCommands.add(Constants.SEARCH_COMMAND);
        onPageCommands.add(Constants.FILTER_COMMAND);
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
