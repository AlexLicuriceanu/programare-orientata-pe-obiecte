package actions.commands;

import internal.io.Action;
import actions.Command;
import actions.CommandFactory;
import internal.Constants;
import internal.OutputMessages;
import internal.PooTV;
import internal.User;

public class BuyPremiumAccountCommand implements Command {
    private final String feature;

    public BuyPremiumAccountCommand(final Action action) {
        this.feature = action.getFeature();
    }

    /**
     * Executes the buy premium account command. Checks the validity of
     * the request, outputs an error if the command cannot be executed
     * on the current page or if the user does not have enough money to
     * buy a premium account.
     */
    @Override
    public void execute() {
        // Verify if the current page allows the action
        if (!CommandFactory.verifyExecute(feature)) {
            // Output error
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            return;
        }

        // Get the current user
        User currentUser = PooTV.getInstance().getCurrentUser();

        // Check if the user has enough tokens to buy a premium account
        if (currentUser.getTokensCount()
                >= Constants.PREMIUM_ACCOUNT_TOKENS_PRICE) {
            // Subtract from user's tokens
            int newTokensCount =
                    currentUser.getTokensCount()
                            - Constants.PREMIUM_ACCOUNT_TOKENS_PRICE;
            // Set the new tokens balance
            currentUser.setTokensCount(newTokensCount);
            // Set the account type
            currentUser.getCredentials()
                    .setAccountType(Constants.PREMIUM_ACCOUNT_LABEL);
        } else {
            // Not enough tokens, output error
            OutputMessages.writeError(PooTV.getInstance().getOutput());
        }
    }
}
