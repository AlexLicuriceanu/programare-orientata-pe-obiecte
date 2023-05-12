package commands.gameplay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.Command;
import commands.CommandController;
import fileio.CardInput;
import gwentstone.GwentStone;
import gwentstone.Player;

import java.util.List;

public final class UseHeroAbility implements Command {
    private final int affectedRow;

    public UseHeroAbility(final int affectedRow) {
        this.affectedRow = affectedRow;
    }

    /**
     * Executa comanda de utilizare a abilitatii specifice fiecarui erou.
     * <p>Cazurile invalide testate sunt daca jucatorul nu are suficienta mana, daca
     * eroul deja si-a folosit abilitate in tura curenta, daca eroul este de tipul
     * "Lord Royce" sau "Empress Thorina", randul afectat trebuie sa fie al adversarului iar
     * daca eroul este de tipul "General Kocioraw" sau "King Mudface", randul afectat
     * trebuie sa fie al jucatorului curent.</p>
     * <p></p>
     * <p>Daca sunt indeplinite conditiile, se executa abilitatea eroului, se seteaza
     * flag-ul hasAttacked la true si se decrementeaza din mana.</p>
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        int playerTurn = gwentStone.getPlayerTurn();
        Player player = gwentStone.getPlayer(playerTurn);
        CardInput heroCard = gwentStone.getBoard().getPlayerHero(playerTurn);

        if (player.getMana() < heroCard.getMana()) {
            heroAbilityManaError(output);
            return;
        } else if (heroCard.getHasAttacked()) {
            heroAbilityCooldownError(output);
            return;
        } else if ((heroCard.getName().equals("Lord Royce")
                || heroCard.getName().equals("Empress Thorina"))
                && !CommandController.checkRow(playerTurn, affectedRow)) {
            heroAbilityEnemyRowError(output);
            return;
        } else if ((heroCard.getName().equals("General Kocioraw")
                || heroCard.getName().equals("King Mudface"))
                && CommandController.checkRow(playerTurn, affectedRow)) {
            heroAbilityOwnRowError(output);
            return;
        }

        heroCard.executeHeroAbility(gwentStone, affectedRow);
        heroCard.setHasAttacked(true);

        int mana = player.getMana();
        mana -= heroCard.getMana();
        player.setMana(mana);
    }

    /**
     * Functie care initializeaza mesajul de output in caz de eroare.
     * @param mapper mapper-ul json
     * @return un nod json
     */
    private ObjectNode createErrorNode(final ObjectMapper mapper) {
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "useHeroAbility");
        node1.put("affectedRow", affectedRow);
        return node1;
    }

    /**
     * Pune in json mesajul de eroare daca jucatorul nu are destula mana
     * pentru a folosi abilitatea.
     * @param output fisierul json
     */
    private void heroAbilityManaError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Not enough mana to use hero's ability.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare daca eroul deja a folosit abilitatea
     * in tura curenta.
     * @param output fisierul json
     */
    private void heroAbilityCooldownError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Hero has already attacked this turn.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare daca randul afectat nu apartine inamicului.
     * @param output fisierul json
     */
    private void heroAbilityEnemyRowError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Selected row does not belong to the enemy.");
        output.addAll(List.of(node1));
    }

    /**
     * Pune in json mesajul de eroare daca randul afectat nu apartine jucatorului curent.
     * @param output fisierul json
     */
    private void heroAbilityOwnRowError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = createErrorNode(mapper);
        node1.put("error", "Selected row does not belong to the current player.");
        output.addAll(List.of(node1));
    }
}
