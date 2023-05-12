package lab5.task1;

import java.util.Random;

public class RandomOutTask implements Task {
    private int randomInt;
    static Random random = new Random(12345);

    public RandomOutTask() {
        this.randomInt = random.nextInt();
    }

    @Override
    public void execute() {
        System.out.println(randomInt);
    }
}
