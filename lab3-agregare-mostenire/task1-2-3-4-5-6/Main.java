import java.util.*;

public class Main {
    public static void main(String[] args) {
        /* TASK 1:
        Scanner scanner = new Scanner(System.in);
        String flavor = scanner.next();
        String origin = scanner.next();

        CandyBox candyBoxDefault = new CandyBox();
        CandyBox candyBox = new CandyBox(flavor, origin);

        System.out.println("Constructor with no parameters test:");
        System.out.println(candyBoxDefault + "\n");

        System.out.println("Constructor with parameters test:");
        System.out.println(candyBox + "\n");

        System.out.println("getVolume() test:");
        System.out.println(candyBox + " has volume " + candyBox.getVolume());

        // END TASK 1 */


        /* TASK 2:
        Scanner scanner = new Scanner(System.in);
        String flavor = scanner.next();
        String origin = scanner.next();
        float length = scanner.nextFloat();
        float width = scanner.nextFloat();
        float height = scanner.nextFloat();
        float radius = scanner.nextFloat();

        Lindt lindtBoxDefault = new Lindt();
        Lindt lindtCandyBox = new Lindt(flavor, origin, length, width, height);
        Baravelli baravelliCandyBox = new Baravelli(flavor, origin, radius, height);
        ChocAmor chocAmorCandyBox = new ChocAmor(flavor, origin, length);

        System.out.println(lindtBoxDefault);
        System.out.println(lindtCandyBox);
        System.out.println(baravelliCandyBox);
        System.out.println(chocAmorCandyBox);
        // END TASK 2 */


        /* TASK 3:
        Scanner scanner = new Scanner(System.in);
        String flavor = scanner.next();
        String origin = scanner.next();

        /*CandyBox candyBox = new CandyBox(flavor, origin);
        CandyBox candyBoxCopy = new CandyBox(flavor, origin);
        CandyBox candyBox2 = new CandyBox("vanilla", "Belgium");

        Lindt lindtBox = new Lindt();
        Lindt lindtBoxCopy = new Lindt(0.0f, 0.0f, 0.0f);
        Lindt lindtBox2 = new Lindt("vanilla", "Belgium", 2.0f, 2.0f, 4.0f);

        System.out.println(candyBox.equals(candyBoxCopy));
        System.out.println(candyBox.equals(candyBox2));

        System.out.println(lindtBox.equals(lindtBoxCopy));
        System.out.println(lindtBox.equals(lindtBox2));
        // END TASK 3 */


        /* TASK 4:
        CandyBag presentBag = new CandyBag();

		for (int i = 0; i < presentBag.getCandies().size(); i++) {
			System.out.println(presentBag.getCandies().get(i));
		}
        // END TASK 4 */


        /* TASK 5:
        Scanner scanner = new Scanner(System.in);
        String flavor = scanner.next();
        String origin = scanner.next();
        float length = scanner.nextFloat();
        float width = scanner.nextFloat();
        float height = scanner.nextFloat();
        float radius = scanner.nextFloat();

        /*CandyBox lindtBox = new Lindt(flavor, origin, length, width, height);
        CandyBox baravelliBox = new Baravelli(flavor, origin, radius, height;
        CandyBox chocAmorBox = new ChocAmor(flavor, origin, length);

        lindtBox.printLindtDim();
        baravelliBox.printBaravelliDim();
        chocAmorBox.printChocAmorDim();
        // END TASK 5 */

        // TASK 6:
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        String street = scanner.next();
        String message = scanner.next();

        // TODO: Uncomment the code after implementing the task.

        Area area = new Area(number, message, street, new CandyBag());

        System.out.println("With instanceof:");
        area.getBirthdayCard();
        System.out.println();
        System.out.println("Without instanceof:");
        area.getBirthdayCardv2();
        // END TASK 6
    }
}