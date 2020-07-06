public class Bishop extends Piece {
    // final public static Piece B_Bishop = new Bishop("Bishop", "black");
    // final public static Piece W_Bishop = new Bishop("Bishop", "white");
    // private String name;
    // private String color;
    Type type;

    protected Bishop(String color) {
        super(color);
        type = Type.BISHOP;
        if (color.equals("white"))
            name = "wBis";
        else {
            name = "bBis";
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

    @Override
    public boolean isValidMove() {
        return true;
    }

    public void select() {
    }
    // public List<String> possibleMoves() {
    //     return moves;
    // }

}