package internal;

public final class Constants {
    private Constants() {
    }

    // Page names
    public static final String NOTAUTH_HOME_PAGE = "not_auth_home";
    public static final String LOGIN_PAGE = "login";
    public static final String REGISTER_PAGE = "register";
    public static final String AUTH_HOME_PAGE = "auth_home";
    public static final String MOVIES_PAGE = "movies";
    public static final String SEE_DETAILS_PAGE = "see details";
    public static final String UPGRADES_PAGE = "upgrades";
    public static final String LOGOUT_PAGE = "logout";

    // Command names
    public static final String LOGIN_COMMAND = "login";
    public static final String REGISTER_COMMAND = "register";
    public static final String SEARCH_COMMAND = "search";
    public static final String FILTER_COMMAND = "filter";
    public static final String PURCHASE_COMMAND = "purchase";
    public static final String WATCH_COMMAND = "watch";
    public static final String LIKE_COMMAND = "like";
    public static final String RATE_COMMAND = "rate";
    public static final String BUY_PREMIUM_ACCOUNT_COMMAND = "buy premium account";
    public static final String BUY_TOKENS_COMMAND = "buy tokens";

    // Command types
    public static final String ON_PAGE_COMMAND = "on page";
    public static final String CHANGE_PAGE_COMMAND = "change page";

    // Numeric constants
    public static final int NUM_FREE_PREMIUM_MOVIES = 15;
    public static final int PREMIUM_ACCOUNT_TOKENS_PRICE = 10;
    public static final int MOVIE_TOKENS_PRICE = 2;
    public static final int MAX_RATING = 5;

    // Name/label constants
    public static final String PREMIUM_ACCOUNT_LABEL = "premium";

}
