package lab9.task1.storage;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class ConsoleLogger implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof List)) {
            return;
        }

        List<SensorData> data = (List<SensorData>) arg;
        int last = data.size() - 1;
        System.out.println("New sensor data: " + data.get(last).toString());
    }

}