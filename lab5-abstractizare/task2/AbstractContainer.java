package lab5.task2;

import lab5.task1.Task;

import java.util.ArrayList;

public abstract class AbstractContainer implements Container {
    protected final ArrayList<Task> storage = new ArrayList<>();

    @Override
    public void push(Task task) {
        storage.add(task);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void transferFrom(Container container) {
        while(container.size() != 0) {
            push(container.pop());
        }
    }

    @Override
    public ArrayList<Task> getTasks() {
        return storage;
    }
}
