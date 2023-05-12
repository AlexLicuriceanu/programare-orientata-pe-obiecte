public class ChocAmor extends CandyBox{
    private float length;

    public ChocAmor() {
        this.length = 0;
    }

    // TODO[2]: Constructor which initialize all fields  (using super keyword)
    public ChocAmor(String flavour, String origin, float length) {
        super.setFlavour(flavour);
        super.setOrigin(origin);
        this.length = length;
    }
    @Override
    public float getVolume() {
        return this.length * this.length * this.length;
    }

    @Override
    public String toString() {
        return "ChocAmor: The " + super.getOrigin() + " " + super.getFlavour() + " chocolate has volume " + this.getVolume();
    }

    public void setLength(float length) {
        this.length = length;
    }
    public float getLength() {
        return this.length;
    }

    public void printChocAmorDim() {
        System.out.println("ChocAmor: " + this.getLength());
    }

    public void printDim() {
        System.out.println("ChocAmor: " + this.getLength());
    }
}
