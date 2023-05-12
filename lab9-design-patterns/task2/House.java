package lab9.task2;

public class House {

    private final String location; // mandatory
    private final int numFloors; // mandatory
    private final int numRooms; // mandatory
    private final boolean pool; // optional
    private final boolean appliances; // optional
    private final boolean solarPanels; // optional
    private final String securityCompany; // optional

    private House(HouseBuilder builder) {
        this.location = builder.location;
        this.numFloors = builder.numFloors;
        this.numRooms = builder.numRooms;
        this.pool = builder.pool;
        this.appliances = builder.appliances;
        this.solarPanels = builder.solarPanels;
        this.securityCompany = builder.securityCompany;
    }

    public String getLocation() {
        return location;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public boolean isPool() {
        return pool;
    }

    public boolean isAppliances() {
        return appliances;
    }

    public boolean isSolarPanels() {
        return solarPanels;
    }

    public String getSecurityCompany() {
        return securityCompany;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    static class HouseBuilder {

        private final String location; // mandatory
        private final int numFloors; // mandatory
        private final int numRooms; // mandatory
        private boolean pool; // optional
        private boolean appliances; // optional
        private boolean solarPanels; // optional
        private String securityCompany; // optional

        public HouseBuilder(String location, int numFloors, int numRooms) {
            this.location = location;
            this.numFloors = numFloors;
            this.numRooms = numRooms;
        }

        public HouseBuilder pool(boolean val) {
            this.pool = val;
            return this;
        }

        public HouseBuilder appliances(boolean val) {
            this.appliances = val;
            return this;
        }

        public HouseBuilder solarPanels(boolean val) {
            this.solarPanels = val;
            return this;
        }

        public HouseBuilder securityCompany(String securityCompany) {
            this.securityCompany = securityCompany;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }
}