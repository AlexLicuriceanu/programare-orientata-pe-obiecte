package pages;

import internal.Constants;

import java.util.ArrayList;

public final class AuthHomePage implements Page {
    /*
     * Commands of type "change page" that can be executed on the authenticated homepage.
     * (In other words, pages a user can navigate to from the authenticated homepage)
     */
    private final ArrayList<String> changePageCommands;

    /*
     * Commands of type "on page" that can be executed on the authenticated homepage.
     */
    private final ArrayList<String> onPageCommands;
    private final String pageName = Constants.AUTH_HOME_PAGE;

    public AuthHomePage() {
        /*
         * Allow users to navigate to the movies, upgrades or logout page
         * when on the authenticated homepage.
         */
        changePageCommands = new ArrayList<>();
        changePageCommands.add(Constants.MOVIES_PAGE);
        changePageCommands.add(Constants.UPGRADES_PAGE);
        changePageCommands.add(Constants.LOGOUT_PAGE);
        changePageCommands.add(Constants.AUTH_HOME_PAGE);

        /*
         * No commands are allowed on the authenticated homepage.
         */
        onPageCommands = new ArrayList<>();
    }

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
