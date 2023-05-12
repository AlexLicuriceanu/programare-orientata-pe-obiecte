import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import internal.*;
import internal.io.InputData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {
    /**
     * Driver function executed for each test set.
     * @param args Input and output files.
     * @throws IOException In case of exceptions while reading or writing.
     */
    public static void main(final String[] args) throws IOException {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);

        // Create the output file.
        Files.createFile(Path.of(outputFile.getPath()));
        // Start the execution.
        action(inputFile, outputFile);
    }

    /**
     * Starts the execution of commands.
     * @param inputFile The input filepath.
     * @param outputFile The output filepath.
     * @throws IOException In case of exceptions while reading or writing.
     */
    public static void action(final File inputFile,
                              final File outputFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load data from json files.
        InputData inputData = objectMapper.readValue(inputFile, InputData.class);

        // Set the loaded data.
        PooTV POO_TV = PooTV.getInstance();
        POO_TV.setPooTvData(inputData);

        ArrayNode output = objectMapper.createArrayNode();

        // Execute the commands.
        POO_TV.executeActions(output);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(outputFile, output);

        // Clear the databases for the next test.
        POO_TV.clearPooTvData();
    }
}
