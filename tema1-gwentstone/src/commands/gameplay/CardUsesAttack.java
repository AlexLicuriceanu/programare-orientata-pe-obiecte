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

public final class CardUsesAttack implements Command {
    private final int attackerX;
    private final int attackerY;
    private final int attackedX;
    private final int attackedY;

    public CardUsesAttack(final Coordinates cardAttacker,
                          final Coordinates cardAttacked) {
        this.attackerX = cardAttacker.getX();
        this.attackerY = cardAttacker.getY();
        this.attackedX = cardAttacked.getX();
        this.attackedY = cardAttacked.getY();
    }

    /**
     * Executa comanda pentru utilizarea atacului unei carti.
     * <p>Se verifica cazurile invalide si se scriu in json erorile aferente.
     * In cazul in care cartea atacata nu este a inamicului, daca atacatorul a atacat sau a
     * folosit deja abilitatea in tura curenta si daca adversarul are minim un card "tank",
     * acesta sa fie atacat primul.</p>
     * <p></p>
     * <p>Daca sunt indeplinite conditiile, din viata cartii atacate este scazuta valoarea
     * atacului cartii atacatorului. Se seteaza flag-ul hasAttacked al cartii atacatorului
     * pe true si in cazul in care cartea atacata ajunge la sau sub 0 viata, este stearsa
     * de pe masa.</p>
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

        if (!CommandController.isEnemyCard(gwentStone.getPlayerTurn(),
                attackedX, attackedY)) {
            attackEnemyError(output);
            return;
        } else if (CommandController.hasAttacked(attackerCard)) {
            attackCooldownError(output);
            return;
        } else if (CommandController.isFrozen(attackerCard)) {
            attackFrozenError(output);
            return;
        } else if (CommandController.getFirstTankCard(board,
                gwentStone.getPlayerTurn()) != null
                && !CommandController.isTank(attackedCard)) {
            attackTankError(output);
            return;
        }

        attackedCard.setHealth(attackedCard.getHealth()
                - attackerCard.getAttackDamage());
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
        node1.put("command", "cardUsesAttack");

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
     * Pune in json mesajul de eroare in cazul in care cartea atacata
     * nu este a inamicului.
     * @param output nodul de output
     */
    private void attackEnemyError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacked card does not belong to the enemy.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare in cazul in care cartea atacatorului
     * a atacat deja in tura curenta.
     * @param output nodul de output
     */
    private void attackCooldownError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacker card has already attacked this turn.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare in cazul in care cartea atacatorului
     * este inghetata.
     * @param output nodul de output
     */
    private void attackFrozenError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacker card is frozen.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare in cazul in care exista una sau mai
     * multe carti de tipul "tank", insa niciuna nu este atacata.
     * @param output nodul de output
     */
    private void attackTankError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacked card is not of type 'Tank'.");
        output.addAll(List.of(node1));
    }
}
