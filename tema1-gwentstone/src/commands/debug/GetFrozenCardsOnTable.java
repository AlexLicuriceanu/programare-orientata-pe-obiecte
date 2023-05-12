package commands.debug;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.Command;
import commands.CommandController;
import fileio.CardInput;
import gwentstone.GwentStone;

import java.util.ArrayList;
import java.util.List;

public final class GetFrozenCardsOnTable implements Command {
    /**
     * Itereaza toata masa de joc si afiseaza in json toate cartile
     * care au flag-ul isFrozen setat cu true.
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getFrozenCardsOnTable");

        ArrayList<ArrayList<CardInput>> board =
                gwentStone.getBoard().getBoard();

        ArrayNode outputNode = mapper.createArrayNode();
        for (ArrayList<CardInput> cards : board) {
            for (CardInput card : cards) {
                if (card.getFreeze()) {
                    ObjectNode cardNode = CommandController.createCardNode(mapper, card);
                    outputNode.add(cardNode);
                }
            }
        }

        output.addAll(List.of(node1));
        node1.set("output", outputNode);
    }
}
