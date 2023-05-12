package task3;

class Point {
    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void changeCoords(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void showPoint() {
        System.out.println("(" + this.x + ", " + this.y + ")");
    }
}
