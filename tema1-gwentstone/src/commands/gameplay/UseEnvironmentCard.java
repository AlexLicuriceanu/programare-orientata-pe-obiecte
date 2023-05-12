package commands.gameplay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.Command;
import commands.CommandController;
import fileio.CardInput;
import gwentstone.GwentStone;
import gwentstone.Player;

import java.util.ArrayList;
import java.util.List;

public final class UseEnvironmentCard implements Command {
    private final int handIdx;
    private final int affectedRow;

    public UseEnvironmentCard(final int handIdx,
                              final int affectedRow) {
        this.affectedRow = affectedRow;
        this.handIdx = handIdx;
    }

    /**
     * Executa comanda pentru a utiliza o carte de tip "environment".
     * <p>Se verifica daca este de tipul "environment" cartea, daca jucatorul are
     * destula mana sa o foloseasca, daca randul afectat este al inamicului, iar
     * in cazul cartii Heart Hound, daca mai este spatiu pe randul oglindit cartii
     * care trebuie furate.</p>
     * <p></p>
     * <p>Daca se verifica conditiile, se executa abilitatea specifica fiecarei carti
     * environment, se sterge cartea din mana jucatorului si se decrementeaza mana.</p>
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        int playerTurn = gwentStone.getPlayerTurn();
        Player player = gwentStone.getPlayer(playerTurn);

        ArrayList<CardInput> hand =
                gwentStone.getPlayer(playerTurn).getHand();
        CardInput card = hand.get(this.handIdx);

        if (!card.getCardType().equals("environment")) {
            environmentTypeError(output);
            return;
        } else if (player.getMana() < card.getMana()) {
            environmentManaError(output);
            return;
        } else if (!CommandController.checkRow(playerTurn, this.affectedRow)) {
            environmentRowError(output);
            return;
        } else if (card.getName().equals("Heart Hound")) {
            if (!checkRowSpace(this.affectedRow, gwentStone)) {
                environmentHeartHoundError(output);
                return;
            }
        }

        card.executeEnvironmentAbility(gwentStone, affectedRow);
        int mana = player.getMana();
        mana -= card.getMana();
        player.setMana(mana);
        hand.remove(card);

    }

    /**
     * Functie care initializeaza mesajul de output in caz de eroare.
     * @param mapper mapper-ul json
     * @return un nod json
     */
    private ObjectNode createErrorNode(final ObjectMapper mapper) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "useEnvironmentCard");
        node1.put("handIdx", this.handIdx);
        node1.put("affectedRow", this.affectedRow);
        return node1;
    }

    /**
     * Pune in json mesajul de eroare daca atacatorul nu utilizeaza o
     * carte de tipul "environment".
     * @param output fisierul json
     */
    private void environmentTypeError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Chosen card is not of type environment.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare daca jucatorul nu are destula mana
     * pentru a folosi cartea.
     * @param output fisierul json
     */
    private void environmentManaError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Not enough mana to use environment card.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare daca randul afectat nu este al inamicului.
     * @param output fisierul json
     */
    private void environmentRowError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Chosen row does not belong to the enemy.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare daca randul oglindit celui afectat este plin.
     * @param output fisierul json
     */
    private void environmentHeartHoundError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Cannot steal enemy card since the player's "
                + "row is full.");
        output.addAll(List.of(node1));
    }

    /**
     * Verifica daca mai este spatiu pe randul dat ca parametru.
     * @param affectedRow randul de verificat
     * @param gwentStone obiectul gwentStone
     * @return true daca randul nu este plin, false in caz contrar
     */
    private boolean checkRowSpace(final int affectedRow,
                                  final GwentStone gwentStone) {
        ArrayList<ArrayList<CardInput>> board = gwentStone.getBoard().getBoard();

        int row = GwentStone.getMAXROWS() - affectedRow - 1;
        if (board.get(row).size() < GwentStone.getMAXCARDSROW()) {
            return true;
        }
        return false;
    }
}
