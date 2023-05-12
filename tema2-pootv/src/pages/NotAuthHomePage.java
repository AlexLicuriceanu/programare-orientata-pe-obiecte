package pages;

import internal.Constants;

import java.util.ArrayList;

public final class NotAuthHomePage implements Page {
    /*
     * Commands of type "change page" that can be executed on the unauthenticated homepage.
     * (In other words, pages a user can navigate to from the unauthenticated homepage)
     */
    private final ArrayList<String> changePageCommands;

    /*
     * Commands of type "on page" that can be executed on the unauthenticated homepage.
     */
    private final ArrayList<String> onPageCommands;
    private final String pageName = Constants.NOTAUTH_HOME_PAGE;

    public NotAuthHomePage() {
        /*
         * Allow users to access the login and register pages from the
         * unauthenticated homepage.
         */
        changePageCommands = new ArrayList<>();
        changePageCommands.add(Constants.LOGIN_PAGE);
        changePageCommands.add(Constants.REGISTER_PAGE);
        changePageCommands.add(Constants.NOTAUTH_HOME_PAGE);

        /*
         * No commands are allowed on the unauthenticated homepage.
         */
        onPageCommands = new ArrayList<>();
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
