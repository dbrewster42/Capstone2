public class Knight extends Piece {
    // final public static Piece B_Knight = new Knight("Knight", "black");
    // final public static Piece W_Knight = new Knight("Knight", "white");
    // private String name;
    // private String color;
    Type type;

    protected Knight(String color) {
        // this.color = color;
        // this.name = name;
        super(color);
        type = Type.KNIGHT;
        if (color.equals("white"))
            name = "wKni";
        else {
            name = "bKni";
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
    **********Checks To Ensure Piece is moving 1 spot vertically and 2 spots horizontally or vise versa ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        int condition1 = Math.abs(x - endX);
        int condition2 = Math.abs(y - endY);
        if (condition1 == 1) {
            if (condition2 == 2)
                return true;
        } else if (condition1 == 2) {
            if (condition2 == 1)
                return true;
        }
        return false;
    }

    // private Knight(String name, String color) {
    //     this.color = color;
    //     this.name = name;
    // }

    // public String getName() {
    //     return this.name;
    // }

    // public String getColor() {
    //     return this.color;
    // }

    // public void select() {
    // }

    // public void move() {
    // }

    // public List<String> possibleMoves() {
    //     return moves;
    // }

}