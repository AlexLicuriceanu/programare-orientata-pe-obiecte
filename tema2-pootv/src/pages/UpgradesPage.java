package pages;

import internal.Constants;

import java.util.ArrayList;

public final class UpgradesPage implements Page {
    /*
     * Commands of type "change page" that can be executed on the upgrades page.
     * (In other words, pages a user can navigate to from the upgrades page)
     */
    private final ArrayList<String> changePageCommands;

    /*
     * Commands of type "on page" that can be executed on the upgrades page.
     */
    private final ArrayList<String> onPageCommands;
    private final String pageName = Constants.UPGRADES_PAGE;

    public UpgradesPage() {
        /*
         * Allow the user to go to the authenticated homepage, the movies page
         * and the logout page from the upgrades page.
         */
        changePageCommands = new ArrayList<>();
        changePageCommands.add(Constants.AUTH_HOME_PAGE);
        changePageCommands.add(Constants.MOVIES_PAGE);
        changePageCommands.add(Constants.LOGOUT_PAGE);
        changePageCommands.add(Constants.UPGRADES_PAGE);

        /*
         * Allow users to execute the buy premium account and
         * buy tokens commands while on the upgrades page.
         */
        onPageCommands = new ArrayList<>();
        onPageCommands.add(Constants.BUY_PREMIUM_ACCOUNT_COMMAND);
        onPageCommands.add(Constants.BUY_TOKENS_COMMAND);
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
