package guiarch;

public class And {

    private int x;
    private int y;
    private int result;

    public And(int x, int y) {
        this.x = x;
        this.y = y;
        result = (x & y);

    }

    public int getresult() {
        return result;
    }
}
