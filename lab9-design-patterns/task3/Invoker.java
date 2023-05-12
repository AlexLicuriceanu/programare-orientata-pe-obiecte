package lab9.task3;

import java.util.LinkedList;

public class Invoker {

    private LinkedList<DrawCommand> usedCommands;
    private LinkedList<DrawCommand> undidCommands;

    public Invoker() {
        this.restart();
    }

    /**
     * Clear up all the used resources, start fresh :D
     */
    public void restart() {
        this.usedCommands = new LinkedList<DrawCommand>();
        this.undidCommands = new LinkedList<DrawCommand>();
    }

    /**
     * Executes a given command
     *
     * @param command
     */
    public void execute(DrawCommand command) {
        command.execute();
        this.usedCommands.add(command);
    }

    /**
     * Undo the latest command
     */
    public void undo() {
        if (this.usedCommands.isEmpty()) {
            return;
        }

        DrawCommand command = this.usedCommands.removeLast();
        command.undo();
        this.undidCommands.add(command);
    }

    /**
     * Redo command previously undone. Cannot perform a redo after an execute, only after at least one undo.
     */
    public void redo() {
        if (this.undidCommands.isEmpty()) {
            return;
        }

        DrawCommand command = this.undidCommands.removeLast();
        command.redo();
        this.usedCommands.add(command);
    }

}
