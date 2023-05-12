package commands.debug;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.Command;
import commands.CommandController;
import fileio.CardInput;
import gwentstone.GwentStone;

import java.util.List;

public final class GetPlayerHero implements Command {
    private final int playerIdx;
    private final CardInput heroCard;

    public GetPlayerHero(final GwentStone gwentStone,
                         final int playerIdx) {
        this.playerIdx = playerIdx;
        this.heroCard = gwentStone.getBoard().getPlayerHero(playerIdx);
    }

    /**
     * Scrie in json proprietatile cartii eroului.
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerHero");
        node1.put("playerIdx", this.playerIdx);

        ObjectNode node2 =
                CommandController.createHeroCardNode(mapper, heroCard);
        node1.put("output", node2);
        output.addAll(List.of(node1));
    }
}
