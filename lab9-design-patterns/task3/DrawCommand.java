package lab9.task3;

interface DrawCommand {
    void execute();

    void undo();

    void redo();
}
