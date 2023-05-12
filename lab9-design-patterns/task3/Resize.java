package lab9.task3;

import java.util.ArrayList;
import java.util.List;

public class Resize implements DrawCommand {

    private final List<Integer> newDimensions;
    private final List<Integer> oldDimensions;
    private final float percentage;
    private final int id;
    private final DiagramCanvas diagramCanvas;

    public Resize(DiagramCanvas diagramCanvas, String id, String percentage) {
        this.percentage = (float) Integer.parseInt(percentage) / 100;
        this.id = Integer.parseInt(id);
        this.diagramCanvas = diagramCanvas;
        this.newDimensions = new ArrayList<Integer>();
        this.oldDimensions = new ArrayList<Integer>();
    }

    @Override
    public void execute() {
        DiagramComponent diagramComponent = this.diagramCanvas.getComponent(id);
        this.oldDimensions.add(diagramComponent.getWeight());
        this.oldDimensions.add(diagramComponent.getHeight());

        this.newDimensions.add(Math.round(diagramComponent.getWeight() * percentage));
        this.newDimensions.add(Math.round(diagramComponent.getHeight() * percentage));
        diagramComponent.setWeight(newDimensions.get(0)).setHeight(newDimensions.get(1));
    }

    @Override
    public void undo() {
        this.diagramCanvas.getComponent(id).setWeight(oldDimensions.get(0))
                .setHeight(oldDimensions.get(1));
    }

    @Override
    public void redo() {
        this.diagramCanvas.getComponent(id).setWeight(newDimensions.get(0))
                .setHeight(newDimensions.get(1));
    }

    @Override
    public String toString() {
        return "Change dimensions from " + oldDimensions + " to " + newDimensions +
                " for component " + id;
    }
}
