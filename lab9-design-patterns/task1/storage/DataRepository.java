package lab9.task1.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Persists sensor data. Observable, its observers are notified when data is added it to.
 */
@SuppressWarnings("deprecation")
public class DataRepository extends Observable {
    private List<SensorData> data;
    
    public DataRepository() {
        this.data = new ArrayList<SensorData>();
    }

    public void addData(SensorData dataRecord){
        this.data.add(dataRecord);
        setChanged();
        notifyObservers(this.data);
        clearChanged();
    }

    public List<SensorData> getData() {
        return data;
    }

    public void setData(List<SensorData> data) {
        this.data = data;
    }
}

