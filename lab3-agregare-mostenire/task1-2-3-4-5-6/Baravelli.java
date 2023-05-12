public class Baravelli extends CandyBox {
    private float radius;
    private float height;

    public Baravelli() {
        radius = 0;
        height = 0;
    }

    // TODO[2]: Constructor which initialize all fields  (using super keyword)
    public Baravelli(String flavour, String origin, float radius, float height) {
        super.setFlavour(flavour);
        super.setOrigin(origin);
        this.radius = radius;
        this.height = height;
    }

    // TODO[3]: Override getVolume() method
    @Override
    public float getVolume() {
        return (float) (Math.PI * this.radius * this.radius * this.height);
    }

    @Override
    public String toString() {
        return "Baravelli: The " + super.getOrigin() + " " + super.getFlavour() + " chocolate has volume " + this.getVolume();
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public float getRadius() {
        return radius;
    }

    public void printBaravelliDim() {
        System.out.println("Baravelli: " + this.getRadius() + " " + this.getHeight());
    }
    public void printDim() {
        System.out.println("Baravelli: " + this.getRadius() + " " + this.getHeight());
    }
}
