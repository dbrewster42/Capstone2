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
}
