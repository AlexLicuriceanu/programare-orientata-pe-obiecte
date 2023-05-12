package internal.io;

public class Action {
    private String type;                // Type of the action, either "on page" or "change page"
    private String page;                // Name of the page the command is executed on
    private String feature;             // Command
    private Credentials credentials;    // User's credentials
    private String startsWith;          // Used to search movies that start with the given string
    private Filters filters;            // Used to filter movies
    private String count;               //
    private String movie;               //
    private String objectType;          //
    private int rate;                // Rating given to the movie

    /*
     * Getters and setters
     */

    public final String getType() {
        return type;
    }

    public final void setType(final String type) {
        this.type = type;
    }

    public final String getPage() {
        return page;
    }

    public final void setPage(final String page) {
        this.page = page;
    }

    public final String getFeature() {
        return feature;
    }

    public final void setFeature(final String feature) {
        this.feature = feature;
    }

    public final Credentials getCredentials() {
        return credentials;
    }

    public final void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public final String getStartsWith() {
        return startsWith;
    }

    public final void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    public final Filters getFilters() {
        return filters;
    }

    public final void setFilters(final Filters filters) {
        this.filters = filters;
    }

    public final String getCount() {
        return count;
    }

    public final void setCount(final String count) {
        this.count = count;
    }

    public final String getMovie() {
        return movie;
    }

    public final void setMovie(final String movie) {
        this.movie = movie;
    }

    public final String getObjectType() {
        return objectType;
    }

    public final void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    public final int getRate() {
        return rate;
    }

    public final void setRate(final int rate) {
        this.rate = rate;
    }
}
