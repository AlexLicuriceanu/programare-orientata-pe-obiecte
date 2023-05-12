package lab9.task1.main;

import lab9.task1.storage.*;

import java.util.Scanner;

import static lab9.task1.factory.StepCountStrategyFactory.makeStrategy;

public class Main {
    public static void main(String[] args) {
        DataRepository dataRepository = new DataRepository();

        long baseTimestamp = 10000;

        Scanner scanner = new Scanner(System.in);
        int taskNum = scanner.nextInt();

        switch (taskNum) {
            case 0:
                System.out.println(0);
                return;
            case 1:
                // TODO: add one ConsoleLogger and one ServerCommunicationController instances as Observers
                ConsoleLogger consoleLogger = new ConsoleLogger();
                ServerCommunicationController serverCommunicationController = new ServerCommunicationController();
                dataRepository.addObserver(consoleLogger);
                dataRepository.addObserver(serverCommunicationController);
                break;
            case 2:
                // TODO: add two DataAggergator as Observers, with strategies provided by the
                // TODO: StepCountStrategyFactory, based on the String inputs given below
                String first_strategy_type = scanner.next();
                String second_strategy_type = scanner.next();
                DataAggregator dataAggregator1 = new DataAggregator(makeStrategy(first_strategy_type));
                DataAggregator dataAggregator2 = new DataAggregator(makeStrategy(second_strategy_type));
                dataRepository.addObserver(dataAggregator1);
                dataRepository.addObserver(dataAggregator2);
                break;
        }

        dataRepository.addData(new SensorData(10, baseTimestamp + 1));
        System.out.println();

        dataRepository.addData(new SensorData(20, baseTimestamp + 2));
        System.out.println();

        dataRepository.addData(new SensorData(30, baseTimestamp + 3));
        System.out.println();

        dataRepository.addData(new SensorData(4000, baseTimestamp + 4));
        System.out.println();

        dataRepository.addData(new SensorData(50, baseTimestamp + 5));
        System.out.println();

        dataRepository.addData(new SensorData(-100, baseTimestamp + 6));
        System.out.println();

        dataRepository.addData(new SensorData(500, baseTimestamp + 600));
        System.out.println();
    }
}
