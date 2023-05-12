package commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import gwentstone.GwentStone;

public interface Command {
    /**
     * Executa comanda
     * @param output fisierul json
     * @param gwentStone obiectul gwentStone
     */
    void executeCommand(ArrayNode output, GwentStone gwentStone);
}
