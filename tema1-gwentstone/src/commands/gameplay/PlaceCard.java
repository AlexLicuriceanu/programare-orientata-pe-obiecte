package commands.gameplay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.Command;
import fileio.CardInput;
import gwentstone.Board;
import gwentstone.GwentStone;

import java.util.List;

public final class PlaceCard implements Command {
    private int handIdx;

    public PlaceCard(final int handIdx) {
        this.handIdx = handIdx;
    }

    /**
     * Executa comanda de plasare a unei carti pe masa.
     * <p>Cazurile invalide sunt daca tipul cartii de pus pe masa este "environment",
     * daca jucatorul nu are suficienta mana sau daca randul pe care se doreste
     * plasarea cartii este plin.</p>
     * <p></p>
     * <p>Daca sunt indeplinite conditiile, se stabileste in functie de tipul cartii
     * minion pe care dintre randuri trebuie pusa, se sterge din mana jucatorului si
     * se pune pe masa, iar mana jucatorului este decrementata cu costul cartii plasate.</p>
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output, final GwentStone gwentStone) {
        Board board = gwentStone.getBoard();
        int playerTurn = gwentStone.getPlayerTurn();

        if (gwentStone.getPlayer(playerTurn).getHand().size() > handIdx) {
            CardInput card = gwentStone.getPlayer(playerTurn).getHand().get(handIdx);

            if (card.getCardType().equals("environment")) {
                environmentCardError(output);
                return;
            } else if (card.getMana()
                    > gwentStone.getPlayer(playerTurn).getMana()) {
                manaCardError(output);
                return;
            } else if (!checkRow(card, board, playerTurn)) {
                rowFullError(output);
                return;
            }

            // Se calculeaza randul pe care trebuie pusa cartea.
            int rowIdx = -1;
            if (playerTurn == 2) {
                if (card.getRow().equals("front")) {
                    rowIdx = 1;
                } else if (card.getRow().equals("back")) {
                    rowIdx = 0;
                }
            } else if (playerTurn == 1) {
                if (card.getRow().equals("front")) {
                    rowIdx = 2;
                } else if (card.getRow().equals("back")) {
                    rowIdx = 3;
                }
            }

            board.getBoard().get(rowIdx).add(card);
            gwentStone.getPlayer(playerTurn).getHand().remove(handIdx);

            int mana = gwentStone.getPlayer(playerTurn).getMana();
            mana -= card.getMana();
            gwentStone.getPlayer(playerTurn).setMana(mana);
        }
    }

    /**
     * Functie care initializeaza mesajul de output in caz de eroare.
     * @param mapper mapper-ul json
     * @return un nod json
     */
    private ObjectNode createErrorNode(final ObjectMapper mapper) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "placeCard");
        node1.put("handIdx", this.handIdx);
        return node1;
    }

    /**
     * Pune in json mesajul de eroare daca jucatorul incearca sa puna pe
     * masa o carte de tip "environment".
     * @param output fisierul json
     */
    private void environmentCardError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Cannot place environment card on table.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare daca jucatorul nu are destula mana
     * pentru a plasa cartea.
     * @param output fisierul json
     */
    private void manaCardError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Not enough mana to place card on table.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare daca jucatorul incearca sa plaseze
     * cartea pe un rand care este plin.
     * @param output fisierul json
     */
    private void rowFullError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Cannot place card on table since row is full.");
        output.addAll(List.of(node1));
    }

    /**
     * Verifica daca randul pe care trebuie plasata cartea este plin sau nu.
     * @param card cartea de plasat
     * @param board masa de joc
     * @param playerIdx index-ul jucatorului
     * @return true daca randul nu este plin, false in caz contrar
     */
    private boolean checkRow(final CardInput card,
                             final Board board,
                             final int playerIdx) {
        int rowIdx = -1;
        if (playerIdx == 2) {
            if (card.getRow().equals("front")) {
                rowIdx = 1;
            } else if (card.getRow().equals("back")) {
                rowIdx = 0;
            }
        } else if (playerIdx == 1) {
            if (card.getRow().equals("front")) {
                rowIdx = 2;
            } else if (card.getRow().equals("back")) {
                rowIdx = 3;
            }
        } else {
            return false;
        }

        return board.getBoard().get(rowIdx).size()
                < GwentStone.getMAXCARDSROW();
    }

    public int getHandIdx() {
        return handIdx;
    }

    public void setHandIdx(final int handIdx) {
        this.handIdx = handIdx;
    }
}
