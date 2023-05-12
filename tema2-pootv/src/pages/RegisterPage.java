package pages;

import internal.Constants;

import java.util.ArrayList;

public final class RegisterPage implements Page {
    /*
     * Commands of type "change page" that can be executed on the login page.
     * (In other words, pages a user can navigate to from the login page)
     */
    private final ArrayList<String> changePageCommands;

    /*
     * Commands of type "on page" that can be executed on the login page.
     */
    private final ArrayList<String> onPageCommands;
    private final String pageName = Constants.REGISTER_PAGE;

    public RegisterPage() {
        /*
         * Can't go on other pages from the register page
         */
        changePageCommands = new ArrayList<>();
        changePageCommands.add(Constants.REGISTER_PAGE);

        /*
         * Allow users to execute the register command while on the register page.
         */
        onPageCommands = new ArrayList<>();
        onPageCommands.add(Constants.REGISTER_COMMAND);
    }

    /*
     * Getters
     */

    public ArrayList<String> getChangePageCommands() {
        return changePageCommands;
    }

    public ArrayList<String> getOnPageCommands() {
        return onPageCommands;
    }

    public String getPageName() {
        return pageName;
    }
}
