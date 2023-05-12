package internal.io;

public class Credentials {
    private String name;            // Username
    private String password;        // Account password
    private String accountType;     // Account type
    private String country;         // User's country
    private String balance;         // User's balance


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

    public final String getAccountType() {
        return accountType;
    }

    public final void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public final String getCountry() {
        return country;
    }

    public final void setCountry(final String country) {
        this.country = country;
    }

    public final String getBalance() {
        return balance;
    }

    public final void setBalance(final String balance) {
        this.balance = balance;
    }
}
