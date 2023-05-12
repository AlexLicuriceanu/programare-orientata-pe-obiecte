package actions.commands;

import internal.io.Action;
import actions.Command;
import actions.CommandFactory;
import internal.*;
import pages.Page;
import pages.PageFactory;

public class LoginCommand implements Command {
    private String name;
    private String password;
    private final String page;
    private final String feature;

    public LoginCommand(final Action action) {
        this.name = action.getCredentials().getName();
        this.password = action.getCredentials().getPassword();
        this.page = action.getPage();
        this.feature = action.getFeature();
    }

    /**
     * Executes the login command. Checks the validity of the login
     * request, outputs an error if the username does not exist in the
     * database or if the password is wrong, or logs the user in otherwise.
     */
    @Override
    public void execute() {
        PageFactory pageFactory = new PageFactory();

        // The page on which the user will be sent on successful login
        Page redirectPageSuccess =
                pageFactory.getPage(Constants.AUTH_HOME_PAGE);

        // The page on which the user will be sent on failed login
        Page redirectPageFailure =
                pageFactory.getPage(Constants.NOTAUTH_HOME_PAGE);

        // User database
        UserDatabase userDatabase = PooTV.getInstance().getUserDatabase();

        // Verify if the current page allows the action
        if (!CommandFactory.verifyExecute(feature)) {
            // Output error
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            return;
        }

        /*
         * Verify if the username exists in the database
         */
        boolean exists = false;
        User foundUser = null;
        for (User user : userDatabase.getUsers()) {
            if (user.getCredentials().getName().equals(name)) {
                exists = true;
                foundUser = user;
                break;
            }
        }

        /*
         * User doesn't exist
         */
        if (!exists) {
            // Redirect
            PooTV.getInstance().setCurrentPage(redirectPageFailure);
            // Output error
            OutputMessages.writeError(PooTV.getInstance().getOutput());
            return;
        }

        /*
         * User exists, try the password
         */
        if (foundUser.getCredentials().getPassword().equals(password)) {
            // Set the current user
            PooTV.getInstance().setCurrentUser(foundUser);
            //Redirect
            PooTV.getInstance().setCurrentPage(redirectPageSuccess);
            // Output success
            OutputMessages.writeSuccess(PooTV.getInstance().getOutput());
            return;
        }

        /*
         * User exists, but wrong password was entered
         */
        // Redirect
        PooTV.getInstance().setCurrentPage(redirectPageFailure);
        // Output error
        OutputMessages.writeError(PooTV.getInstance().getOutput());
    }

    /*
     * Getters and setters
     */

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(final String password) {
        this.password = password;
    }
}

