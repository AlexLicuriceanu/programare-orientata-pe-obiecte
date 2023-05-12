package lab9.task3;

import java.util.ArrayList;
import java.util.List;

public class DiagramCanvas {
    private final List<DiagramComponent> components = new ArrayList<>();

    public void addComponent(DiagramComponent diagramComponent) {
        components.add(diagramComponent);
    }

    public void removeComponent(DiagramComponent diagramComponent) {
        components.remove(diagramComponent);
    }

    public DiagramComponent getComponent(int id) {
        return components.get(id);
    }

    public int getComponentId(DiagramComponent diagramComponent) {
        return components.indexOf(diagramComponent);
    }

    public List<DiagramComponent> getComponents() {
        return components;
    }

    public void show() {
        System.out.println("Diagram:");
    }
}
