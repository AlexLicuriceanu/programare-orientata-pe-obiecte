package lab5.task3;

public class Operation implements Div, Minus, Mult, Plus {
    private float value;

    public Operation(float value) {
        this.value = value;
    }

    public float getNumber() {
        return value;
    }

    @Override
    public void div(float value) {
        if (value == 0) {
            System.out.println("Division by zero is not possible");
            return;
        }
        this.value /= value;
    }

    @Override
    public void minus(float value) {
        this.value -= value;
    }

    @Override
    public void mult(float value) {
        this.value *= value;
    }

    @Override
    public void plus(float value) {
        this.value += value;
    }
}
