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

public final class GetCardsOnTable implements Command {

    /**
     * Itereaza masa de joc de sus-jos, stanga-dreapta si scrie proprietatile
     * cartilor in fisierul json.
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getCardsOnTable");

        ArrayList<ArrayList<CardInput>> board =
                gwentStone.getBoard().getBoard();

        // Se itereaza masa de joc, iar pentru fiecare carte se scrie in json.
        ArrayNode outputNode = mapper.createArrayNode();
        for (ArrayList<CardInput> cards : board) {
            ArrayNode rowNode = mapper.createArrayNode();

            for (CardInput card : cards) {
                ObjectNode cardNode =
                        CommandController.createCardNodeNoHealth(mapper, card);
                rowNode.add(cardNode);
            }
            outputNode.add(rowNode);
        }

        output.addAll(List.of(node1));
        node1.set("output", outputNode);
    }
}
