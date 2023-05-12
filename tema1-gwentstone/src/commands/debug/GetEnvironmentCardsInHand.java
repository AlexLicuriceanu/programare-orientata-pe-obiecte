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

public final class GetEnvironmentCardsInHand implements Command {
    private final int playerIdx;
    private final ArrayList<CardInput> hand;

    public GetEnvironmentCardsInHand(final GwentStone gwentStone, final int playerIdx) {
        this.playerIdx = playerIdx;
        this.hand = gwentStone.getPlayer(playerIdx).getHand();
    }

    /**
     * Itereaza toate cartile din mana jucatorului si le afiseaza in json
     * doar pe cele de tipul "environment".
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getEnvironmentCardsInHand");
        node1.put("playerIdx", this.playerIdx);

        ArrayNode outputNode = mapper.createArrayNode();
        for (CardInput card : hand) {
            if (card.getCardType().equals("environment")) {
                ObjectNode cardNode =
                        CommandController.createCardNodeNoHealth(mapper, card);
                outputNode.add(cardNode);
            }
        }
        node1.set("output", outputNode);
        output.addAll(List.of(node1));
    }
}
