import java.util.Objects;

public class CandyBox {
    private String flavour;
    private String origin;

    public CandyBox() {
        this.flavour = "sugar-free";
        this.origin = "Switzerland";
    }

    public CandyBox(String flavour, String origin) {
        this.flavour = flavour;
        this.origin = origin;
    }

    public float getVolume() {
        return 0;
    }

    public String toString() {
        return "The " + this.origin + " " + this.flavour + " chocolate";
    }

    public void setFlavour(String flavour) { this.flavour = flavour; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getFlavour() { return flavour; }
    public String getOrigin() { return origin; }

    public boolean equals(CandyBox candyBox) {
        if(Objects.equals(this.flavour, candyBox.flavour) && Objects.equals(this.origin, candyBox.origin) && this.getVolume() == candyBox.getVolume()) {
            return true;
        }
        return false;
    }

    public void printDim() {
        System.out.println();
    }
}
