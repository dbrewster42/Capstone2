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