package commands.debug;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.Command;
import gwentstone.GwentStone;

import java.util.List;

public final class GetPlayerMana implements Command {
    private final int playerIdx;

    public GetPlayerMana(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    /**
     * Scrie in json cata mana mai are jucatorul.
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerMana");
        node1.put("playerIdx", this.playerIdx);
        node1.put("output", gwentStone.getPlayer(playerIdx).getMana());
        output.addAll(List.of(node1));
    }
}
