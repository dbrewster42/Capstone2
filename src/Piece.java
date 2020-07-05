
// import com.gameboard.*;
// import java.util.List;

public abstract class Piece {
    // Square square;    
    // String name;
    // List<String> moves;
    // final public static Piece B_Rook = new Rook("Rook", "black");
    // final public static Piece W_Rook = new Rook("Rook", "white");
    // final public static Piece B_Queen = new Queen("Queen", "black");
    // final public static Piece W_Queen = new Queen("Queen", "white");
    public String name;
    public String color;
    public boolean alive = true;

    // int x, y;
    Piece(String color) {
        this.color = color;
    }

    // Piece(int x, int y, String color) {
    //     this.x = x;
    //     this.y = y;
    //     this.color = color;
    // }
    // public void setAlive() {
    // }

    public abstract String getColor();

    public abstract String getName();
    // public abstract void select();

    public abstract void move();

    public abstract boolean isValidMove();

    // public abstract int[] getPosition();

    // public abstract List<Move> calculateMoves();

    // public abstract int[][] drawPath();

}