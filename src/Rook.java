// import java.util.List;

public class Rook extends Piece {
    // final public static Piece B_Rook = new Rook("Rook", "black");
    // final public static Piece W_Rook = new Rook("Rook", "white");
    // private String name;
    // private String color;
    // private int x, y;
    // private int[] position = new int[2];
    // private String name;
    // public List<Move> moves;
    Type type;

    protected Rook(String color) {
        // this.color = color;
        // this.name = name;
        super(color);
        type = Type.ROOK;
        if (color.equals("white"))
            name = "wRok";
        else {
            name = "bRok";
        }
    }

    // @Override
    // public int[] getPosition() {
    //     position[0] = x;
    //     position[1] = y;
    //     return position;
    // }
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

    public void select() {
    }

    @Override
    public void move() {

    }

    /*
    **********Rook can move in any horizontally or vertically infinitely if not blocked  ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        int condition1 = Math.abs(endX - x);
        int condition2 = Math.abs(endY - y);
        if (condition1 == 0 || condition2 == 0) {
            System.out.println("Boogie boogie");
            return true;
        }
        return false;
    }

    // @Override
    // public List<Move> calculateMoves(String axis, String dir) {
    //     if (axis == "x") {
    //         x++;
    //     }
    //     int count = 1;
    //     int next = x + count;
    //     while (next < 8) {
    //         if (Board.squares[next][y].getPiece() != null) {
    //             if (Board.squares[next][y].getPiece().getColor() == this.color)
    //                 break;
    //             else {
    //                 Move move = new Move(next, y, true);
    //                 moves.add(move);
    //                 break;
    //             }
    //         }

    //     }

    // }

}