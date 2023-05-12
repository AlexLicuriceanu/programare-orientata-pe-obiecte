package commands.gameplay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.Command;
import commands.CommandController;
import commands.StatController;
import fileio.CardInput;
import fileio.Coordinates;
import gwentstone.GwentStone;

import java.util.ArrayList;
import java.util.List;

public final class UseAttackHero implements Command {
    private final int attackerX;
    private final int attackerY;

    public UseAttackHero(final Coordinates attackerCard) {
        this.attackerX = attackerCard.getX();
        this.attackerY = attackerCard.getY();
    }

    /**
     * Executa comanda de atacare a eroului inamic.
     * <p>Cazurile invalide sunt cand eroul are flag-ul isFrozen sau hasAttacked true sau
     * daca exista minim un card "tank" al inamicului pe masa si acesta nu este atacat.</p>
     * <p></p>
     * <p>Daca se verifica conditiile, viata eroului atacat este decrementata, flag-ul
     * hasAttacked al atacatorului este setat la true si se verifica daca viata eroului
     * inamic este mai mare ca 0.</p>
     * <p></p>
     * <p>Daca viata eroului inamic este la sau sub 0, acesta se considera invins,
     * numarul victoriilor jucatorului castigator este incrementat, iar jocul se opreste.</p>
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        ArrayList<ArrayList<CardInput>> board =
                gwentStone.getBoard().getBoard();

        CardInput attackerCard;
        CardInput attackedHeroCard = gwentStone.getBoard().getPlayerHero(
                GwentStone.getMAXPLAYERS() / gwentStone.getPlayerTurn());

        try {
            attackerCard = board.get(attackerX).get(attackerY);
        } catch (Exception ignored) {
            return;
        }

        if (CommandController.isFrozen(attackerCard)) {
            heroAttackFrozenError(output);
            return;
        } else if (CommandController.hasAttacked(attackerCard)) {
            heroAttackCooldownError(output);
            return;
        } else if (CommandController.getFirstTankCard(board,
                gwentStone.getPlayerTurn()) != null
                && !CommandController.isTank(attackerCard)) {
            heroAttackTankError(output);
            return;
        }

        int health = attackedHeroCard.getHealth();
        attackedHeroCard.setHealth(health - attackerCard.getAttackDamage());

        attackerCard.setHasAttacked(true);

        if (attackedHeroCard.getHealth() <= 0) {
            gameEndedMessage(output, gwentStone.getPlayerTurn());
        }
    }

    /**
     * Functie care initializeaza mesajul de output in caz de eroare.
     * @param mapper mapper-ul json
     * @return un nod json
     */
    private ObjectNode createErrorNode(final ObjectMapper mapper) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "useAttackHero");
        ObjectNode cardAttackerNode = mapper.createObjectNode();
        cardAttackerNode.put("x", this.attackerX);
        cardAttackerNode.put("y", this.attackerY);
        node1.put("cardAttacker", cardAttackerNode);
        return node1;
    }

    /**
     * Pune in json mesajul de eroare daca flag-ul isFrozen al
     * cartii atacatorului este true.
     * @param output fisierul json
     */
    private void heroAttackFrozenError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacker card is frozen.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare daca inamicul are pe masa
     * minim un card tank, dar se incearca atacul asupra eroului.
     * @param output fisierul json
     */
    private void heroAttackTankError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacked card is not of type 'Tank'.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare daca flag-ul hasAttacked al
     * cartii atacatorului este true.
     * @param output fisierul json
     */
    private void heroAttackCooldownError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Attacker card has already attacked this turn.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul cu numarul jucatorului castigator si se incrementeaza
     * numarul de victorii ale acestuia.
     * @param output fisierul json
     * @param winnerIdx index-ul castigatorului
     */
    private void gameEndedMessage(final ArrayNode output, final int winnerIdx) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        StatController statController = StatController.getInstance();
        statController.setTotalGames(statController.getTotalGames() + 1);

        if (winnerIdx == 1) {
            node1.put("gameEnded", "Player one killed the enemy hero.");

            statController.setPlayerOneWins(
                    statController.getPlayerOneWins() + 1);
        } else if (winnerIdx == 2) {
            node1.put("gameEnded", "Player two killed the enemy hero.");

            statController.setPlayerTwoWins(
                    statController.getPlayerTwoWins() + 1);
        }
        output.addAll(List.of(node1));
    }
}
