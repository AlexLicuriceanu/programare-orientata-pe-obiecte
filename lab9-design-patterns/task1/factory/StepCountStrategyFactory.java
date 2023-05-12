package lab9.task1.factory;


import lab9.task1.dataprocessing.BasicStepCountStrategy;
import lab9.task1.dataprocessing.FilteredStepCountStrategy;
import lab9.task1.dataprocessing.StepCountStrategy;
import lab9.task1.main.Utils;

public class StepCountStrategyFactory {
    public static StepCountStrategy makeStrategy(String strategyName) {
        switch(strategyName) {
            case Utils.BASIC_STRATEGY: return new BasicStepCountStrategy();
            case Utils.FILTERED_STRATEGY: return new FilteredStepCountStrategy();
        }
        
        return null;
    }
}
