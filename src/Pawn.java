// import java.util.List;

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
        else {
            name = "bPaw";
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String getColor() {
        return this.color;
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