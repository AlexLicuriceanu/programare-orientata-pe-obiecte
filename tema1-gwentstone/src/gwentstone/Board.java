package gwentstone;

import cards.hero.EmpressThorina;
import cards.hero.GeneralKocioraw;
import cards.hero.KingMudface;
import cards.hero.LordRoyce;
import commands.StatController;
import fileio.CardInput;
import fileio.Input;
import fileio.StartGameInput;

import java.util.ArrayList;

public final class Board {
    private final ArrayList<ArrayList<CardInput>> board;
    private final CardInput playerOneHero;
    private final CardInput playerTwoHero;

    public Board(final Input inputFile) {
        // Se initializeaza masa de joc sub forma unui ArrayList de ArrayList-uri.
        this.board = new ArrayList<>();
        for (int i = 0; i < GwentStone.getMAXROWS(); i++) {
            board.add(new ArrayList<>());
        }

        // Extragem datele pentru a incepe jocul.
        StartGameInput startGame = inputFile.getGames().get(
                StatController.getInstance().getTotalGames()).getStartGame();

        // Se initializeaza erorii jucatorilor.
        this.playerOneHero =
                createHero(startGame.getPlayerOneHero());
        this.playerTwoHero =
                createHero(startGame.getPlayerTwoHero());
    }

    private CardInput createHero(final CardInput heroCard) {
        return switch (heroCard.getName()) {
            case "Lord Royce" -> new LordRoyce(heroCard);
            case "Empress Thorina" -> new EmpressThorina(heroCard);
            case "King Mudface" -> new KingMudface(heroCard);
            case "General Kocioraw" -> new GeneralKocioraw(heroCard);
            default -> null;
        };
    }

    public ArrayList<ArrayList<CardInput>> getBoard() {
        return board;
    }

    public CardInput getPlayerOneHero() {
        return playerOneHero;
    }

    public CardInput getPlayerTwoHero() {
        return playerTwoHero;
    }

    /**
     * Intoarce cartea erou a jucatorului cu index-ul pasat ca parametru.
     * @param playerIdx index-ul jucatorului.
     * @return cartea erou
     */
    public CardInput getPlayerHero(final int playerIdx) {
        if (playerIdx == 1) {
            return getPlayerOneHero();
        } else if (playerIdx == 2) {
            return getPlayerTwoHero();
        } else {
            System.out.println("Invalid player number\n");
            return null;
        }
    }
}
