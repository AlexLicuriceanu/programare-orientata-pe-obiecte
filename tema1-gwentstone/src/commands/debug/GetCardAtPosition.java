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

public final class GetCardAtPosition implements Command {
    private final int x;
    private final int y;

    public GetCardAtPosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Scrie in json proprietatile cartii la coordonatele din obiect.
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    public void executeCommand(final ArrayNode output,
                               final GwentStone gwentStone) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getCardAtPosition");
        node1.put("x", this.x);
        node1.put("y", this.y);

        ArrayList<ArrayList<CardInput>> board =
                gwentStone.getBoard().getBoard();

        CardInput card;
        try {
            card = board.get(x).get(y);
        } catch (Exception ignored) {
            positionError(output);
            return;
        }

        ObjectNode node2 = CommandController.createCardNode(mapper, card);

        node1.put("output", node2);
        output.addAll(List.of(node1));
    }

    /**
     * Mesajul care va fi scris in json daca try...catch esueaza.
     * @param output fisierul json
     */
    private void positionError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();
        node1.put("command", "getCardAtPosition");
        node1.put("x", this.x);
        node1.put("y", this.y);
        node1.put("output", "No card available at that position.");
        output.addAll(List.of(node1));
    }
}
