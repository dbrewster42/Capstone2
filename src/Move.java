public class Move {
    //maybe
    public int x, y;
    public boolean capture;

    public Move(int x, int y) {
        this(x, y, false);
    }

    public Move(int x, int y, boolean capture) {
        this.x = x;
        this.y = y;
        this.capture = capture;
    }
}