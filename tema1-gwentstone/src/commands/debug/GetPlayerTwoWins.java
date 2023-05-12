package commands.debug;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.Command;
import commands.StatController;
import gwentstone.GwentStone;

import java.util.List;

public final class GetPlayerTwoWins implements Command {
    /**
     * Scrie in json numarul meciurilor castigate de jucatorul 2.
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerTwoWins");

        StatController statController = StatController.getInstance();
        node1.put("output", statController.getPlayerTwoWins());
        output.addAll(List.of(node1));
    }
}
