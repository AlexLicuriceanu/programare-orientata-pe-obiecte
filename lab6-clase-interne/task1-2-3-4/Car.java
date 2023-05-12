public class Car {
    enum CarType {
        MERCEDES("Mercedes"),
        FIAT("Fiat"),
        SKODA("Skoda");

        private final String type;
        CarType(String type){
            this.type = type;
        }
        public String getType(){
            return type;
        }
    }
    private Integer price;
    private Integer year;
    private CarType CarType;

    public Car(Integer price, CarType type, Integer year) {
        this.price = price;
        this.CarType = type;
        this.year = year;
    }

    @Override
    public String toString() {
        return  "Car{price="
                + this.price
                + ", carType="
                + this.CarType
                + ", year="
                + this.year
                +"}";
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Car.CarType getCarType() {
        return CarType;
    }

    public void setCarType(Car.CarType carType) {
        CarType = carType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
