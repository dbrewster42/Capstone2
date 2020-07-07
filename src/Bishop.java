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

    /*
    **********Checks To Ensure Piece is moving Diaganally ************
    **********Checks To Ensure there are no pieces in between starting and ending spot ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        int width = x - endX;
        int length = y - endY;
        if (width != length)
            return false;
        //// Initalize the increment amounts to be used to find direction
        int checkX, checkY;
        if (width > 0) {
            checkX = 1;
        } else {
            checkX = -1;
        }
        if (length > 0) {
            checkY = 1;
        } else {
            checkY = -1;
        } ////////////////////problem!
        int count = Math.abs(width);
        int betweenX = x - checkX;
        int betweenY = y - checkY;
        while (count >= 0) {
            betweenX = betweenX - checkX;
            betweenY = betweenY - checkY;
            System.out.println("Checking Square " + betweenX + betweenY + " . Count- " + count);
            if (Board.squares[betweenX][betweenY].hasPiece()) {
                return false;
            }
            count--;
        }
        return true;
    }

    public void select() {
    }
    // public List<String> possibleMoves() {
    //     return moves;
    // }

}