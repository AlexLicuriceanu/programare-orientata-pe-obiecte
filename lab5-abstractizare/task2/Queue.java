package lab5.task2;

import lab5.task1.Task;

public class Queue extends AbstractContainer {
    @Override
    public Task pop() {
        return storage.remove(0);
    }
}
