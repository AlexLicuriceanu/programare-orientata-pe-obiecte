package lab9.task3;

public class Client {

    private final Invoker invoker;
    private DiagramCanvas diagramCanvas;

    Client() {
        invoker = new Invoker();
        diagramCanvas = new DiagramCanvas();
    }

    public void showDiagram() {
        if (this.diagramCanvas.getComponents().isEmpty()) {
            System.out.println("The canvas is empty!");
        }

        diagramCanvas.show();

        for (DiagramComponent diagramComponent : this.diagramCanvas.getComponents()) {
            System.out.println(diagramComponent.toString());
        }

    }

    public void newDiagram() {
        this.diagramCanvas = new DiagramCanvas();
        this.invoker.restart();
    }

    public void executeAction(String commandName, String... args) {
        DrawCommand command;
        try {
            CommandType commandType = CommandType.fromString(commandName);
            command = getCommand(commandType, args);
            if (command == null) {
                throw new IllegalArgumentException();
            }

        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid command: " + commandName);
            System.out.println("Available commands:");
            for (CommandType type : CommandType.values()) {
                System.out.println("\t- " + type.text);
            }
            return;
        }

        this.invoker.execute(command);
    }

    private DrawCommand getCommand(CommandType type, String... args) throws IllegalArgumentException {
        try {
            switch (type) {
                case DRAW_RECTANGLE:
                    return new DrawRectangle(diagramCanvas);
                case CHANGE_COLOR:
                    return new ChangeColor(diagramCanvas, args[0], args[1]);
                case RESIZE:
                    return new Resize(diagramCanvas, args[0], args[1]);
                case CONNECT:
                    return new Connect(diagramCanvas, args[0], args[1]);
                case CHANGE_TEXT:
                    return new ChangeText(diagramCanvas, args[0], args[1]);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

        return null;
    }

    public void undo() {
        this.invoker.undo();
    }

    public void redo() {
        this.invoker.redo();
    }
}
