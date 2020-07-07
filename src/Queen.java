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
    **********Sets What Movements Piece is Capable Of ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        return true;
    }
}
