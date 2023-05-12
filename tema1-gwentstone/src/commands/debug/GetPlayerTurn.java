package commands.debug;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.Command;
import gwentstone.GwentStone;

import java.util.List;

public final class GetPlayerTurn implements Command {
    /**
     * Scrie in json numarul jucatorului curent.
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerTurn");
        node1.put("output", gwentStone.getPlayerTurn());
        output.addAll(List.of(node1));
    }
}
