package pages;

import internal.Constants;

import java.util.ArrayList;

public final class LoginPage implements Page {
    /*
     * Commands of type "change page" that can be executed on the login page.
     * (In other words, pages a user can navigate to from the login page)
     */
    private final ArrayList<String> changePageCommands;

    /*
     * Commands of type "on page" that can be executed on the login page.
     */
    private final ArrayList<String> onPageCommands;
    private final String pageName = Constants.LOGIN_PAGE;

    public LoginPage() {
        /*
         * Can't go on other pages from the login page
         */
        changePageCommands = new ArrayList<>();
        changePageCommands.add(Constants.LOGIN_PAGE);

        /*
         * Allow users to execute the login command while on the login page.
         */
        onPageCommands = new ArrayList<>();
        onPageCommands.add(Constants.LOGIN_COMMAND);
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
