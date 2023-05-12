import java.time.LocalDate;

public class Dealership {
    private static final int UPPERBOUND = 1000;

    public static class BrandOffer implements Offer {
        public int getDiscount(Car car) {
            switch (car.getCarType().getType()) {
                case "Mercedes": return 5 * car.getPrice() / 100;
                case "Fiat": return 10 * car.getPrice() / 100;
                case "Skoda": return 15 * car.getPrice() / 100;
            }
            return 0;
        }
    }

    public static class DealerOffer implements Offer {
        public int getDiscount(Car car) {
            switch (car.getCarType().getType()) {
                case "Mercedes": return 300 *
                        (LocalDate.now().getYear() - car.getYear());
                case "Fiat": return 100 *
                        (LocalDate.now().getYear() - car.getYear());
                case "Skoda": return 150 *
                        (LocalDate.now().getYear() - car.getYear());
            }
            return 0;
        }
    }

    public static class SpecialOffer implements Offer {
        public int getDiscount(Car car) {
            return Main.rand.nextInt(getUPPERBOUND());
        }
    }

    public void getFinalPrice(Car car) {
        Dealership.BrandOffer brandOffer = new BrandOffer();
        Dealership.DealerOffer dealerOffer = new DealerOffer();
        Dealership.SpecialOffer specialOffer = new SpecialOffer();


        System.out.println("Applying Brand discount: "
                + brandOffer.getDiscount(car) + " euros");
        System.out.println("Applying Dealer discount: "
                + dealerOffer.getDiscount(car) + " euros");
        System.out.println("Applying Special discount: "
                + specialOffer.getDiscount(car) + " euros");
    }

    public void negotiate(Car car, Offer offer) {
        System.out.println("Applying Client discount: " + offer.getDiscount(car) + " euros");
    }

    public static int getUPPERBOUND() {
        return UPPERBOUND;
    }
}
