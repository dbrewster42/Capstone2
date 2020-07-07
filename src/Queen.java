public class Queen extends Piece {
    // final public static Piece B_Queen = new Queen("Queen", "black");
    // final public static Piece W_Queen = new Queen("Queen", "white");
    Type type;

    protected Queen(String color) {
        // this.color = color;
        // this.name = name;
        super(color);
        type = Type.QUEEN;
        if (color.equals("white"))
            name = "wQUE";
        else {
            name = "bQUE";
        }
    }

    /*
    **********Returns Name ************
    */
    @Override
    public String getName() {
        return this.name;
    }

    /*
    **********Returns Team ************
    */
    @Override
    public String getColor() {
        return this.color;
    }

    /*
    **********Returns Type ************
    */
    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public void move() {
    }

    /*
    **********Queen can move in any 1 direction infinitely if not blocked, but only 1 direction  ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        int condition1 = Math.abs(endX - x);
        int condition2 = Math.abs(endY - y);
        if (condition1 == 0 || condition2 == 0 || condition1 == condition2)
            ////must check to see if blocked
            return true;
        return false;
    }
}
