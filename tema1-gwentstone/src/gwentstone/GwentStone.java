package gwentstone;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Input;

import java.util.ArrayList;

public final class GwentStone {
    private Input inputFile;            // Input-ul.
    private ArrayNode outputFile;       // Output-ul.
    private ArrayList<ActionsInput> actions;    // Lista de comenzi.

    private Player playerOne;       // Jucatorul 1.
    private Player playerTwo;       // Jucatorul 2.
    private Board board;            // Masa de joc.

    private int startingPlayer;     // Numarul jucatorului care incepe.
    private int playerTurn;         // Index-ul jucatorului curent.
    private int roundNumber;        // Numarul rundei.
    private final int gameSeed;     // Seed-ul meciului.

    // Constante.
    private static final int MAXMANA = 10;      // Numarul pana la care se incrementeaza mana.
    private static final int MAXCARDSROW = 5;   // Numarul maxim de carti pe fiecare rand.
    private static final int MAXROWS = 4;       // Numarul maxim de randuri.
    private static final int MAXPLAYERS = 2;    // Numarul maxim de jucatori.

    public GwentStone(final Input inputFile,
                      final ArrayNode outputFile,
                      final int gameNr) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.actions = inputFile.getGames().get(gameNr).getActions();
        this.gameSeed =
                inputFile.getGames().get(gameNr).getStartGame().getShuffleSeed();

        // Se initializeaza jucatorii.
        this.playerOne = new Player(
                inputFile.getGames().get(gameNr).getStartGame().getPlayerOneDeckIdx(),
                inputFile.getPlayerOneDecks().getNrDecks(),
                inputFile.getPlayerOneDecks().getNrCardsInDeck(),
                inputFile.getPlayerOneDecks().getDecks(),
                getGameSeed()
        );

        this.playerTwo = new Player(
                inputFile.getGames().get(gameNr).getStartGame().getPlayerTwoDeckIdx(),
                inputFile.getPlayerTwoDecks().getNrDecks(),
                inputFile.getPlayerTwoDecks().getNrCardsInDeck(),
                inputFile.getPlayerTwoDecks().getDecks(),
                getGameSeed()
        );

        this.board = new Board(inputFile);

        this.startingPlayer =
                inputFile.getGames().get(gameNr).getStartGame().getStartingPlayer();
        this.playerTurn = startingPlayer;
        this.roundNumber = 1;
    }


    public ArrayList<ActionsInput> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<ActionsInput> actions) {
        this.actions = actions;
    }

    public int getGameSeed() {
        return gameSeed;
    }

    public void setRoundNumber(final int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setPlayerTurn(final int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setStartingPlayer(final int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    public int getStartingPlayer() {
        return startingPlayer;
    }

    public void setBoard(final Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public Input getInputFile() {
        return inputFile;
    }

    public void setInputFile(final Input inputFile) {
        this.inputFile = inputFile;
    }

    public ArrayNode getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(final ArrayNode outputFile) {
        this.outputFile = outputFile;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(final Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(final Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    /**
     * @param playerIdx indexul jucatorului
     * @return obiectul jucatorului
     */
    public Player getPlayer(final int playerIdx) {
        if (playerIdx == 1) {
            return getPlayerOne();
        } else if (playerIdx == 2) {
            return getPlayerTwo();
        } else {
            System.out.println("Invalid player number\n");
            return null;
        }
    }

    public static int getMAXMANA() {
        return MAXMANA;
    }

    public static int getMAXCARDSROW() {
        return MAXCARDSROW;
    }

    public static int getMAXROWS() {
        return MAXROWS;
    }

    public static int getMAXPLAYERS() {
        return MAXPLAYERS;
    }
}
