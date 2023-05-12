package internal;

import internal.io.Action;
import actions.Command;
import actions.CommandFactory;
import internal.io.InputData;
import pages.*;

import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;

/**
 * Thread-safe Singleton that contains the input data, the current user
 * and the user and movie databases.
 */
public final class PooTV {
    private final static PooTV POO_TV;

    // Data
    private InputData pooTvData;          // Input data read from a file
    private User currentUser = null;      // The current user
    private UserDatabase userDatabase = UserDatabase.getInstance();
    private MovieDatabase movieDatabase = MovieDatabase.getInstance();
    private ArrayNode output;
    private Page currentPage = new NotAuthHomePage();   // The current page
    private ArrayList<Command> commands;
    private ArrayList<Movie> currentMoviesList = new ArrayList<>();



    static {
        POO_TV = new PooTV();
    }

    private PooTV() {
    }

    public static PooTV getInstance() {
        return POO_TV;
    }

    /**
     * Executes the actions from the input file.
     * @param output The command's success or error message.
     */
    public void executeActions(final ArrayNode output) {
        // Set the output file
        PooTV.getInstance().setOutput(output);

        // List of commands that will be executed
        this.commands = new ArrayList<>();

        // Get the list of actions from the input file
        ArrayList<Action> actions =
                PooTV.getInstance().getPooTvData().getActions();

        // Instantiate CommandFactory object
        CommandFactory commandFactory = new CommandFactory();

        // Iterate the list of actions
        for (Action action : actions) {
            // Add the appropriate command to the list of commands
            commands.add(commandFactory.getCommand(action));
        }


        // Execute the commands
        for (Command command : commands) {
            command.execute();
        }
    }

    /**
     * Initializes the platform's users and movies with the data from the
     * input file.
     * @param pooTvData The data set to be loaded.
     */
    public void setPooTvData(final InputData pooTvData) {
        this.pooTvData = pooTvData;
        this.userDatabase.addUsers(pooTvData.getUsers());
        this.movieDatabase.addMovies(pooTvData.getMovies());
    }

    /**
     * Clears the databases with the loaded data.
     * Called after each individual test set.
     */
    public void clearPooTvData() {
        this.pooTvData = null;
        this.userDatabase.dropDatabase();
        this.movieDatabase.dropDatabase();
        PooTV.getInstance().setOutput(null);
        PooTV.getInstance().setCurrentPage(new NotAuthHomePage());
    }

    /**
     * Checks if a movie exists in the currentMoviesList array.
     * @param movieName The movie's name.
     * @return true if the movie exists, false otherwise.
     */
    public boolean existsCurrentMovie(final String movieName) {
        for (Movie movie : this.currentMoviesList) {
            if (movie.getName().equals(movieName)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Getters and setters.
     */

    public InputData getPooTvData() {
        return pooTvData;
    }

    public void resetPooTvData() {
        pooTvData = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public UserDatabase getUserDatabase() {
        return userDatabase;
    }

    public void setUserDatabase(final UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public ArrayNode getOutput() {
        return output;
    }

    public void setOutputFile(final ArrayNode output) {
        this.output = output;
    }

    public MovieDatabase getMovieDatabase() {
        return movieDatabase;
    }

    public void setMovieDatabase(final MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
    }

    public void setOutput(final ArrayNode output) {
        this.output = output;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void setCommands(final ArrayList<Command> commands) {
        this.commands = commands;
    }

    public ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(final ArrayList<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }
}
