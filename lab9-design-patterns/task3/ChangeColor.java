package lab9.task3;

public class ChangeColor implements DrawCommand {

    private final String newColor;
    private String oldColor;
    private final int id;
    private final DiagramCanvas diagramCanvas;

    public ChangeColor(DiagramCanvas diagramCanvas, String id, String newColor) {
        this.newColor = newColor;
        this.id = Integer.parseInt(id);
        this.diagramCanvas = diagramCanvas;
    }

    @Override
    public void execute() {
        DiagramComponent diagramComponent = this.diagramCanvas.getComponent(id);
        this.oldColor = diagramComponent.getColor();
        diagramComponent.setColor(newColor);
    }

    @Override
    public void undo() {
        this.diagramCanvas.getComponent(id).setColor(oldColor);
    }

    @Override
    public void redo() {
        this.diagramCanvas.getComponent(id).setColor(newColor);
    }

    @Override
    public String toString() {
        return "Change color from " + oldColor + " to " + newColor + " for component " + id;
    }

}
