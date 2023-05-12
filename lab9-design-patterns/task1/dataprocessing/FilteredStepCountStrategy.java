package lab9.task1.dataprocessing;

import lab9.task1.main.Utils;
import lab9.task1.storage.SensorData;

import java.util.List;

public class FilteredStepCountStrategy implements StepCountStrategy {

    @Override
    public int getTotalSteps(List<SensorData> data) {
        int nrSteps = 0;
        long lastTimestamp = 0;
        for (SensorData sensorData : data) {
            int stepCount = sensorData.getStepsCount();
            if (lastTimestamp == 0) {
                lastTimestamp = sensorData.getTimestamp();
                nrSteps += stepCount;
                continue;
            }

            if (stepCount > 0 && stepCount < Utils.MAX_STEP_COUNT) {
                nrSteps += stepCount;
            }
        }
        
        return nrSteps;
    }

    @Override
    public String getStrategyDescription() {
        return "returns the total number of steps, that satisfy the filter, since the app started running";
    }

    public String getStrategyName() {
        return Utils.FILTERED_STRATEGY;
    }
}
