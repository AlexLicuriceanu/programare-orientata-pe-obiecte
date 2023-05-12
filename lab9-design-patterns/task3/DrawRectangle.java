package lab9.task3;

public class DrawRectangle implements DrawCommand {

    private final DiagramCanvas diagramCanvas;
    private DiagramComponent diagramComponent;

    public DrawRectangle(DiagramCanvas diagramCanvas) {
        this.diagramCanvas = diagramCanvas;
    }

    @Override
    public void execute() {
        this.diagramComponent = new DiagramComponent();
        this.diagramCanvas.addComponent(diagramComponent);
    }

    @Override
    public void undo() {
        this.diagramCanvas.removeComponent(diagramComponent);
    }

    @Override
    public void redo() {
        this.diagramCanvas.addComponent(diagramComponent);
    }

    @Override
    public String toString() {
        return "Add component " + diagramCanvas.getComponentId(diagramComponent);
    }

}
