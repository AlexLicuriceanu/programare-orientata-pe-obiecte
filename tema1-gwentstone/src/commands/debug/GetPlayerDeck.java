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

public final class GetPlayerDeck implements Command {
    private final int playerIdx;
    private final ArrayList<CardInput> deck;

    public GetPlayerDeck(final GwentStone gwentStone,
                         final int playerIdx) {
        this.playerIdx = playerIdx;
        this.deck = gwentStone.getPlayer(playerIdx).getDeck();
    }

    /**
     * Itereaza pachetul jucatorului si scrie proprietatile cartilor in json.
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerDeck");
        node1.put("playerIdx", this.playerIdx);

        ArrayNode outputNode = mapper.createArrayNode();
        for (CardInput card : deck) {
            ObjectNode cardNode =
                    CommandController.createCardNodeNoHealth(mapper, card);
            outputNode.add(cardNode);
        }
        node1.set("output", outputNode);
        output.addAll(List.of(node1));
    }
}
