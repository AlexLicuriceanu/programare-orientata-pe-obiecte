package internal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

public final class OutputMessages {

    private OutputMessages() {
    }
    /**
     * Writes a general error to the output file.
     * @param output Output file.
     */
    public static void writeError(final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();

        // Add the "error" node
        node1.put("error", "Error");

        /*
         * Add the "currentMoviesList" node
         */
        ArrayList<Movie> dummy = new ArrayList<>();
        JsonNode currentMoviesListNode =
                mapper.valueToTree(dummy);
        node1.set("currentMoviesList", currentMoviesListNode);

        /*
         * Add the "currentUser" node
         */
        node1.put("currentUser", (JsonNode) null);

        output.addAll(List.of(node1));
    }


    /**
     * Writes a success message to the output file.
     * @param output Output file.
     */
    public static void writeSuccess(final ArrayNode output) {
        User currentUser = PooTV.getInstance().getCurrentUser();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node1 = mapper.createObjectNode();

        // Add the "error" node
        node1.put("error", (JsonNode) null);

        /*
         * Add the "currentMoviesList" node
         */
        JsonNode currentMoviesListNode =
                mapper.valueToTree(PooTV.getInstance().getCurrentMoviesList());
        node1.set("currentMoviesList", currentMoviesListNode);

        /*
         * Add the "currentUser" node
         */
        JsonNode currentUserNode =
                mapper.valueToTree(currentUser);
        node1.put("currentUser", currentUserNode);

        output.addAll(List.of(node1));
    }
}
