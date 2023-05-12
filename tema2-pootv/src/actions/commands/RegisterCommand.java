package actions.commands;

import internal.io.Action;
import actions.Command;
import actions.CommandFactory;
import internal.io.Credentials;
import internal.*;
import pages.Page;
import pages.PageFactory;

public class RegisterCommand implements Command {
    private Credentials credentials;
    private final String page;
    private final String feature;

    public RegisterCommand(final Action action) {
        this.credentials = action.getCredentials();
        this.page = action.getPage();
        this.feature = action.getFeature();
    }

    /**
     * Executes the register command. Checks the validity of the register
     * request, outputs an error if a user is trying to register with an
     * already registered name or creates a new user otherwise.
     */
    @Override
    public void execute() {
        PageFactory pageFactory = new PageFactory();

        // The page on which the user will be sent on failed login
        Page redirectPageFailure =
                pageFactory.getPage(Constants.NOTAUTH_HOME_PAGE);

        // The page on which the user will be sent on successful login
        Page redirectPageSuccess =
                pageFactory.getPage(Constants.AUTH_HOME_PAGE);

        // User database
        UserDatabase userDatabase = PooTV.getInstance().getUserDatabase();

        // Verify if the action is allowed
        if (!CommandFactory.verifyExecute(feature)) {
            // Output error
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            return;
        }

        // Check if the name already exists in the database
        boolean exists = false;
        for (User user : userDatabase.getUsers()) {
            if (user.getCredentials().getName().equals(credentials.getName())) {
                exists = true;
                break;
            }
        }

        if (exists) {
            /*
             * User exists in the database, output an error message and
             * redirect to unauthenticated homepage
             */
            PooTV.getInstance().setCurrentPage(redirectPageFailure);
            OutputMessages.writeError(PooTV.getInstance().getOutput());
        } else {
            /*
             * User doesn't exist, register him
             */
            User newUser = new User(credentials);

            // Add him to the database
            PooTV.getInstance().getUserDatabase().addUser(newUser);

            // Log in and redirect to authenticated homepage
            PooTV.getInstance().setCurrentUser(newUser);
            PooTV.getInstance().setCurrentPage(redirectPageSuccess);

            // Output success
            OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
        }
    }

    /*
     * Getters and setters
     */

    public final Credentials getCredentials() {
        return credentials;
    }

    public final void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public final String getPage() {
        return page;
    }

    public final String getFeature() {
        return feature;
    }
}
