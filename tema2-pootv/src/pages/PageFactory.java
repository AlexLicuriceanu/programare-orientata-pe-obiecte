package pages;

import internal.Constants;

public final class PageFactory {
    /*
     * Page factory
     */
    public Page getPage(final String pageName) {
        switch (pageName) {
            case Constants.NOTAUTH_HOME_PAGE -> {
                return new NotAuthHomePage();
            }
            case Constants.LOGIN_PAGE -> {
                return new LoginPage();
            }
            case Constants.REGISTER_PAGE -> {
                return new RegisterPage();
            }
            case Constants.AUTH_HOME_PAGE -> {
                return new AuthHomePage();
            }
            case Constants.MOVIES_PAGE -> {
                return new MoviesPage();
            }
            case Constants.SEE_DETAILS_PAGE -> {
                return new SeeDetailsPage();
            }
            case Constants.UPGRADES_PAGE -> {
                return new UpgradesPage();
            }
            case Constants.LOGOUT_PAGE -> {
                return new LogoutPage();
            }
            default -> {
                return null;
            }
        }
    }
}
