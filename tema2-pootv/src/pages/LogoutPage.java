package pages;

import internal.Constants;

import java.util.ArrayList;

public final class LogoutPage implements Page {
    /*
     * Commands of type "change page" that can be executed on the logout page.
     * (In other words, pages a user can navigate to from the logout page)
     */
    private final ArrayList<String> changePageCommands;

    /*
     * Commands of type "on page" that can be executed on the logout page.
     */
    private final ArrayList<String> onPageCommands;
    private final String pageName = Constants.LOGOUT_PAGE;

    public LogoutPage() {
        /*
         * User can't navigate to any page from the logout page.
         */
        changePageCommands = new ArrayList<>();
        changePageCommands.add(Constants.LOGOUT_PAGE);

        /*
         * No commands allowed on the logout page.
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
