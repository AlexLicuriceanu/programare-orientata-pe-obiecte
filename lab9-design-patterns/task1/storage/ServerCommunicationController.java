package lab9.task1.storage;

import lab9.task1.communication.ServerMessage;
import lab9.task1.main.Utils;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


@SuppressWarnings("deprecation")
public class ServerCommunicationController implements Observer {
    
    @SuppressWarnings("unchecked")
    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof List))
            return;

        List<SensorData> data = (List<SensorData>) arg;
        int last = data.size() - 1;
        System.out.println("Generated server message: " +
                new ServerMessage(data.get(last).getStepsCount(), Utils.getClientId(),
                                                        data.get(last).getTimestamp()));
    }
}
