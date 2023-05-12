package lab9.task1.storage;

import lab9.task1.dataprocessing.StepCountStrategy;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


@SuppressWarnings("deprecation")
public class DataAggregator implements Observer{
    private final StepCountStrategy strategy;

    public DataAggregator(StepCountStrategy strategy) {
        this.strategy = strategy;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof List))
            return;
        
        System.out.println(strategy.getStrategyName().substring(0, 1).toUpperCase()
                + strategy.getStrategyName().substring(1)
                + " strategy, total step count: : "
                + this.strategy.getTotalSteps((List<SensorData>) arg));
    }
}
