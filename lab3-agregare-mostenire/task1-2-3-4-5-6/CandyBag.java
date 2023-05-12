import java.util.ArrayList;

public class CandyBag {
    // Add ArrayList as field "candies"
    private ArrayList<CandyBox> candyBoxes = new ArrayList<>();

    // Constructor which populates the array list (the default gift)
    public CandyBag() {
        candyBoxes.add(new Lindt("cherry", "Austria", 20, 5.4f, 19.2f));
        candyBoxes.add(new Lindt("apricot", "Austria", 20, 5.4f, 19.2f));
        candyBoxes.add(new Lindt("strawberry", "Austria", 20, 5.4f, 19.2f));
        candyBoxes.add(new Baravelli("grape", "Italy", 6.7f, 8.7f));
        candyBoxes.add(new ChocAmor("coffee", "France", 5.5f));
        candyBoxes.add(new ChocAmor("vanilla", "France", 5.5f));
    }

    // Add getter and setter
    public ArrayList<CandyBox> getCandies() {
        return candyBoxes;
    }

    public void setCandies(CandyBox candyBox) {
        candyBoxes.add(candyBox);
    }
}