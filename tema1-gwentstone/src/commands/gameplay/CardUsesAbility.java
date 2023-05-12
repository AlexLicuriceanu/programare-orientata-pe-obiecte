package commands.gameplay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.Command;
import commands.CommandController;
import fileio.CardInput;
import fileio.Coordinates;
import gwentstone.GwentStone;

import java.util.ArrayList;
import java.util.List;

public final class CardUsesAbility implements Command {
    private final int attackerX;
    private final int attackerY;
    private final int attackedX;
    private final int attackedY;

    public CardUsesAbility(final Coordinates cardAttacker,
                          final Coordinates cardAttacked) {
        this.attackerX = cardAttacker.getX();
        this.attackerY = cardAttacker.getY();
        this.attackedX = cardAttacked.getX();
        this.attackedY = cardAttacked.getY();
    }

    /**
     * Executa comanda pentru utilizarea abilitatii unei carti.
     * <p>Se verifica daca este inghetata cartea atacatorului sau daca a atacat deja in
     * tura curenta. In caz afirmativ, se scrie o eroare in output.</p>
     * <p></p>
     * <p>In cazul in care cartea este de tipul "Disciple", cartea atacata trebuie sa nu fie
     * a inamicului. In cazul in care cartea este "The Ripper", "Miraj" sau "The Cursed One",
     * cartea atacata trebuie sa fie a inamicului. De asemenea, se cauta daca exista minim o
     * carte de tipul "tank" si daca exista se verifica daca este sau nu atacata.</p>
     * <p></p>
     * <p>Daca sunt indeplinite toate conditiile, se executa abilitatea cartii si flag-ul
     * hasAttacked este setat cu valoarea true. Daca viata cartii atacate scade la sau
     * sub 0, aceasta este stearsa de pe masa de joc.</p>
     *
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        ArrayList<ArrayList<CardInput>> board =
                gwentStone.getBoard().getBoard();

        CardInput attackerCard = board.get(attackerX).get(attackerY);
        CardInput attackedCard = board.get(attackedX).get(attackedY);

        if (CommandController.isFrozen(attackerCard)) {
            abilityFrozenError(output);
            return;
        } else if (CommandController.hasAttacked(attackerCard)) {
            abilityCooldownError(output);
            return;
        } else if (attackerCard.getName().equals("Disciple")) {
            if (CommandController.isEnemyCard(gwentStone.getPlayerTurn(),
                    attackedX, attackedY)) {
                abilityPlayerError(output);
                return;
            }
        } else if (attackerCard.getName().equals("The Ripper")
                || attackerCard.getName().equals("Miraj")
                || attackerCard.getName().equals("The Cursed One")) {
            if (!CommandController.isEnemyCard(gwentStone.getPlayerTurn(),
                    attackedX, attackedY)) {
                abilityEnemyError(output);
                return;
            }

            if (CommandController.getFirstTankCard(board,
                    gwentStone.getPlayerTurn()) != null
                    && !CommandController.isTank(attackedCard)) {

                abilityTankError(output);
                return;
            }
        }

        attackerCard.executeMinionAbility(gwentStone, attackedCard, attackerCard);
        attackerCard.setHasAttacked(true);

        if (attackedCard.getHealth() <= 0) {
            board.get(attackedX).remove(attackedY);
        }
    }

    /**
     * Functie care initializeaza mesajul de output in caz de eroare.
     * @param mapper mapper-ul json
     * @return un nod json
     */
    private ObjectNode createErrorNode(final ObjectMapper mapper) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "cardUsesAbility");

        ObjectNode cardAttackerNode = mapper.createObjectNode();
        cardAttackerNode.put("x", this.attackerX);
        cardAttackerNode.put("y", this.attackerY);
        node1.put("cardAttacker", cardAttackerNode);

        ObjectNode cardAttackedNode = mapper.createObjectNode();
        cardAttackedNode.put("x", this.attackedX);
        cardAttackedNode.put("y", this.attackedY);
        node1.put("cardAttacked", cardAttackedNode);
        return node1;
    }

    /**
     * Pune in json mesajul de eroare in cazul in care cartea atacatorului
     * este inghetata.
     * @param output nodul de output
     */
    private void abilityFrozenError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacker card is frozen.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare in cazul in care cartea atacata nu este
     * a atacatorului.
     * @param output nodul de output
     */
    private void abilityPlayerError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacked card does not belong "
                + "to the current player.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare in cazul in care cartea atacata nu este
     * a inamicului.
     * @param output nodul de output
     */
    private void abilityEnemyError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacked card does not belong to the enemy.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare in cazul in care cartea atacatorului a folosit
     * deja abilitatea in tura curenta.
     * @param output nodul de output
     */
    private void abilityCooldownError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacker card has already attacked this turn.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare in cazul in care cartea atacata nu este
     * de tipul "tank".
     * @param output nodul de output
     */
    private void abilityTankError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacked card is not of type 'Tank'.");
        output.addAll(List.of(node1));
    }
}
