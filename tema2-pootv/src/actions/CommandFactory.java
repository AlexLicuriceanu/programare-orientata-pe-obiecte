package actions;

import actions.commands.*;
import internal.Constants;
import internal.PooTV;
import internal.io.Action;
import pages.Page;

public final class CommandFactory {
    public Command getCommand(final Action action) {

        /*
         * Factory for commands of type "change page"
         */
        if (action.getType().equals(Constants.CHANGE_PAGE_COMMAND)) {
            return new ChangePageCommand(action);
        }

        /*
         * Factory for commands of type "on page"
         */
        if (action.getType().equals(Constants.ON_PAGE_COMMAND)) {
            switch (action.getFeature()) {
                case Constants.LOGIN_COMMAND -> {
                    return new LoginCommand(action);
                }
                case Constants.REGISTER_COMMAND -> {
                    return new RegisterCommand(action);
                }
                case Constants.SEARCH_COMMAND -> {
                    return new SearchCommand(action);
                }
                case Constants.FILTER_COMMAND -> {
                    return new FilterCommand(action);
                }
                case Constants.PURCHASE_COMMAND -> {
                    return new PurchaseCommand(action);
                }
                case Constants.WATCH_COMMAND -> {
                    return new WatchCommand(action);
                }
                case Constants.LIKE_COMMAND -> {
                    return new LikeCommand(action);
                }
                case Constants.RATE_COMMAND -> {
                    return new RateCommand(action);
                }
                case Constants.BUY_PREMIUM_ACCOUNT_COMMAND -> {
                    return new BuyPremiumAccountCommand(action);
                }
                case Constants.BUY_TOKENS_COMMAND -> {
                    return new BuyTokensCommand(action);
                }
                default -> {
                    return null;
                }
            }
        }

        return null;
    }

    /**
     * Verifies if a command can run on the current page and also checks
     * * if the page on which the command tries to run is the same as the
     * page that the user is currently on.
     * @param command Command to run.
     * @return true if the command is valid for the page, false if the command
     * can't run on the page
     */
    public static boolean verifyExecute(final String command) {
        // The page the user is currently on
        Page currentPage = PooTV.getInstance().getCurrentPage();

        /*
         * Verify if the user can execute the action on the page
         * that he is on
         */
        return currentPage.getOnPageCommands().contains(command);
    }
}
