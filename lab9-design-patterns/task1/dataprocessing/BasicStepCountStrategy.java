package lab9.task1.dataprocessing;


import lab9.task1.main.Utils;
import lab9.task1.storage.SensorData;

import java.util.List;

public class BasicStepCountStrategy implements StepCountStrategy {

    @Override
    public int getTotalSteps(List<SensorData> data) {
        int steps = 0;
        for (SensorData sensorData : data) {
            steps += sensorData.getStepsCount();
        }
        
        return steps;
    }

    @Override
    public String getStrategyDescription() {
        return "returns the total number of steps since the app started running";
    }

    public String getStrategyName() {
        return Utils.BASIC_STRATEGY;
    }
}
