package lab9.task3;

import java.util.ArrayList;
import java.util.List;

// DIAGRAM CLASSES
public class DiagramComponent {
    private String text = "text";
    private String color = "WHITE";
    private int height = 40;
    private int weight = 100;

    @Override
    public String toString() {
        return "[" +
                "text='" + text + '\'' +
                ", color='" + color + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", connectedComponents=" + connectedComponents +
                ']';
    }

    private final List<String> connectedComponents = new ArrayList<>();

    public String getText() {
        return text;
    }

    public DiagramComponent setText(String text) {
        this.text = text;
        return this;
    }

    public String getColor() {
        return color;
    }

    public DiagramComponent setColor(String color) {
        this.color = color;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public DiagramComponent setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public DiagramComponent setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public void connectTo(String componentId) {
        connectedComponents.add(componentId);
    }

    public void removeConnection(String componentId) {
        connectedComponents.remove(componentId);
    }
}
