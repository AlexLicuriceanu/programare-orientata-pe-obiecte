package actions.commands;

import internal.io.Action;
import actions.Command;
import actions.CommandFactory;
import internal.OutputMessages;
import internal.PooTV;
import internal.User;

public class BuyTokensCommand implements Command {
    private String count;
    private String feature;

    public BuyTokensCommand(final Action action) {
        this.count = action.getCount();
        this.feature = action.getFeature();
    }

    /**
     * Executes the buy tokens command. Checks the validity of the request,
     * outputs an error if the command cannot be executed on the current page
     * or if the user does not have enough money to buy the requested number
     * of tokens.
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

        // Convert the string to int
        int tokensToBuy = Integer.MAX_VALUE;
        int userBalance = Integer.MIN_VALUE;
        try {
            tokensToBuy = Integer.parseInt(count);
            userBalance =
                    Integer.parseInt(currentUser.getCredentials().getBalance());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        // Verify if the user has enough money to buy tokens
        if (userBalance >= tokensToBuy) {
            // Subtract from user's balance
            int newUserBalance = userBalance - tokensToBuy;
            // Set the tokens
            currentUser.setTokensCount(tokensToBuy);
            // Set the new balance
            currentUser.getCredentials()
                    .setBalance(Integer.toString(newUserBalance));
        } else {
            // Not enough money, output error
            OutputMessages.writeError(PooTV.getInstance().getOutput());
        }
    }
}
