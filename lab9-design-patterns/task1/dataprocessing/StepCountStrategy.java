package lab9.task1.dataprocessing;

import lab9.task1.storage.SensorData;

import java.util.List;

/**
 * Defines the operations that can be performed on step data. Each implementation uses its own algorithm.
 */
public interface StepCountStrategy {
    /**
     * Computes and returns the total number of steps since the app started running.
     */
    int getTotalSteps(List<SensorData> data);

    /**
     * Returns a string describing this strategy, to be used for printing purposes.
     */
    String getStrategyDescription();

    String getStrategyName();
}
