package lab9.task2;

class Main {
    private static String spacerSymbols = new String(new char[40]).replace("\0", "-");

    public static void main(String[] args) {
        String spacerSymbols = new String(new char[40]).replace("\0", "-");

        House house = new House.HouseBuilder("Piata Unirii", 3, 10)
                .pool(true)
                .securityCompany("POO_Security")
                .build();

        printOutputSpacerFor("testHouse");
        testHouse(house);

    }

    private static void printOutputSpacerFor(String test) {
        System.out.println(spacerSymbols + test + spacerSymbols);
    }

    private static void testHouse(House house) {
        System.out.println(house.getLocation());
        System.out.println(house.getNumFloors());
        System.out.println(house.getNumRooms());
        System.out.println(house.isPool());
        System.out.println(house.getSecurityCompany());
        System.out.println(house.isAppliances());
        System.out.println(house.isSolarPanels());
    }
}

