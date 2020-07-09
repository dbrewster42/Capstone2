public class Pawn extends Piece {
    // final public static Piece B_Pawn = new Pawn("Pawn", "black");
    // final public static Piece W_Pawn = new Pawn("Pawn", "white");
    // private String name;   
    // private String color;    
    // private int x, y;
    // private int[] position = new int[2];
    // List<String> moves;    
    // public String name;
    Type type;

    protected Pawn(String color) {
        // this.color = color;
        // this.name = name;
        super(color);
        type = Type.PAWN;
        if (color.equals("white"))
            name = "wPaw";
        // name = "\u2659";
        else {
            name = "PawB";
            // name = "\u265F";
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

    /*
    **********For cycling through all pieces to prevent King from moving into check or out of checkmate ************
    */
    @Override
    public boolean canCheck() {
        return false;
    }

    /*
    **********Checks To Ensure Piece is moving Forward 1 spot, unless first move in which case it can move 2, or capturing ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        if (y - endY != 0) {
            if (Math.abs(y - endY) == 1) {
                if (Math.abs(x - endX) == 1)
                    ///capture move
                    if (Board.squares[endX][endY].hasPiece()) {
                        return true;
                    }
            }
            return false;
        }
        if (Math.abs(x - endX) != 1) {
            if (Math.abs(x - endX) == 2) {
                if (x == 1 && endX == 3 || x == 6 && endX == 4) {
                    if (Board.squares[endX][endY].hasPiece()) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }
        if (Math.abs(x - endX) == 1) {
            if (Board.squares[endX][endY].hasPiece()) {
                return false;
            }
            return true;
        }
        return true;
    }

    // public void select() {
    // }
    // @Override    
    // public int[] getPosition() {
    //     position[0] = x;
    //     position[1] = y;
    //     return position;
    // }

    // @Override
    // public List<String> possibleMoves() {
    //     return moves;
    // }

    // @Override
    // public int[][] drawPath() {
    // }

}