public class Area {
    // TODO[0]: Add fields
    private CandyBag candyBag = new CandyBag();
    private int number;
    private String street;
    private String message;

    // TODO[1]: Constructor without parameters
    public Area() {
        this.number = 0;
        this.street = "nowhere";
        this.message = "";
    }

    // TODO[2]: Constructor which initialize all the fields
    public Area(int number, String message, String street, CandyBag candyBag) {
        this.candyBag = candyBag;
        this.number = number;
        this.street = street;
        this.message = message;
    }

    // TODO[3]: Add getBirthdayCard() method with instanceof
    public void getBirthdayCard() {
        System.out.println("Street " + this.street + ", number " + this.number + "\n");
        System.out.println(this.message + "\n");

        for(CandyBox candyBox : candyBag.getCandies()) {
            if(candyBox instanceof Lindt) {
                ((Lindt) candyBox).printLindtDim();
            }
            else if(candyBox instanceof Baravelli) {
                ((Baravelli) candyBox).printBaravelliDim();
            }
            else {
                ((ChocAmor) candyBox).printChocAmorDim();
            }
        }
    }

    // TODO[4]: Add getBirthdayCardv2() method without instanceof
    public void getBirthdayCardv2() {
        System.out.println("Street " + this.street + ", number " + this.number + "\n");
        System.out.println(this.message + "\n");

        for(CandyBox candyBox : candyBag.getCandies()) {
            candyBox.printDim();
        }
    }

    // TODO[0]: Add getters and setters
    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getMessage() {
        return message;
    }

    public CandyBag getCandyBag() {
        return candyBag;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCandyBag(CandyBag candyBag) {
        this.candyBag = candyBag;
    }
}
