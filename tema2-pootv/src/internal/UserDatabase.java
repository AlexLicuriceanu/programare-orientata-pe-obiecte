package internal;

import java.util.ArrayList;

/**
 * Thread safe Singleton for the user database.
 */
public final class UserDatabase implements Database {
    private final static UserDatabase USER_DATABASE;        // Database object

    private ArrayList<User> users = new ArrayList<>();      // List of users
    private int numUsers;                            // Total number of users

    static {
        USER_DATABASE = new UserDatabase();
    }

    private UserDatabase() {
    }

    /**
     * Adds a single user to the database, without checking for duplicates.
     * @param user The user to be added.
     */
    public void addUser(final User user) {
        this.users.add(user);
        this.numUsers++;
    }

    /**
     * Adds a list of users to the database, without checking for duplicates.
     * @param users The list of users to be added.
     */
    public void addUsers(final ArrayList<User> users) {
        this.users.addAll(users);
        this.numUsers += users.size();
    }

    /**
     * Removes all elements from the database.
     */
    @Override
    public void dropDatabase() {
        this.users = new ArrayList<>();
        this.numUsers = 0;
    }

    /*
     * Getters and setters.
     */

    public static UserDatabase getInstance() {
        return USER_DATABASE;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(final int numUsers) {
        this.numUsers = numUsers;
    }
}
