public class Lindt extends CandyBox {
    private float length;
    private float width;
    private float height;

    public Lindt() {
        length = 0;
        width = 0;
        height = 0;
    }

    // TODO[2]: Constructor which initialize all fields (using super keyword)
    public Lindt(String flavour, String origin, float length, float width, float height) {
        super.setFlavour(flavour);
        super.setOrigin(origin);
        this.height = height;
        this.width = width;
        this.length = length;

    }

    public Lindt(float length, float width, float height) {
        this.height = height;
        this.width = width;
        this.length = length;

    }

    // TODO[3]: Override getVolume() method
    @Override
    public float getVolume() {
        return this.height * this.length * this.width;
    }
    // TODO[4]: Override toString() method - "Lindt: The [origin] [flavor] has volume [volume]"
    @Override
    public String toString() {
        return "Lindt: The " + super.getOrigin() + " " + super.getFlavour() + " chocolate has volume " + this.getVolume();
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

    public void printLindtDim() {
        System.out.println("Lindt: " + this.getLength() + " " + this.getWidth() + " " + this.getHeight());
    }
    public void printDim() {
        System.out.println("Lindt: " + this.getLength() + " " + this.getWidth() + " " + this.getHeight());
    }
}
