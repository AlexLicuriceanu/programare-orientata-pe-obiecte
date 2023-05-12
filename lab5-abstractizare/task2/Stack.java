package lab5.task2;

import lab5.task1.Task;

public class Stack extends AbstractContainer {
    @Override
    public Task pop() {
        return  storage.remove(storage.size() - 1);
    }
}
