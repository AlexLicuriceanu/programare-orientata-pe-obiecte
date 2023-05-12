package commands;

public final class StatController {
    // Singleton.
    private static final StatController STATCONTROLLER = new StatController();
    private int playerOneWins;
    private int playerTwoWins;
    private int totalGames;

    private StatController() {
        this.playerOneWins = 0;
        this.playerTwoWins = 0;
        this.totalGames = 0;
    }

    public static StatController getInstance() {
        return STATCONTROLLER;
    }

    public int getPlayerOneWins() {
        return playerOneWins;
    }

    public void setPlayerOneWins(final int playerOneWins) {
        this.playerOneWins = playerOneWins;
    }

    public int getPlayerTwoWins() {
        return playerTwoWins;
    }

    public void setPlayerTwoWins(final int playerTwoWins) {
        this.playerTwoWins = playerTwoWins;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(final int totalGames) {
        this.totalGames = totalGames;
    }

    /**
     * Reseteaza toate campurile obiectului.
     */
    public void resetStatController() {
        this.totalGames = 0;
        this.playerOneWins = 0;
        this.playerTwoWins = 0;
    }
}
