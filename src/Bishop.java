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
            name = "BisB";
        }
        // if (color.equals("white"))
        //     name = "\u2657";
        // else {
        //     name = "\u265D";
        // }
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

    /*
    **********For cycling through all pieces to prevent King from moving into check or out of checkmate ************
    */
    @Override
    public boolean canCheck() {
        return false;
    }

    /*
    **********Checks To Ensure Piece is moving Diaganally ************
    **********Checks To Ensure there are no pieces in between starting and ending spot ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        //doesn't work with-1 up +1 right or down and left
        //counts 1 time too many
        int width = x - endX;
        int length = y - endY;
        if (Math.abs(width) != Math.abs(length)) {
            System.out.println("Oh you Bishop " + Math.abs(width) + "" + Math.abs(length));
            return false;
        }

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
        int betweenX = x;
        int betweenY = y;
        while (count > 0) {
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