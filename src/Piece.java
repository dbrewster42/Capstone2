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
    // public boolean alive = true;
    // int x, y;

    Piece(String color) {
        this.color = color;
    }

    // Piece(String color, int x, int y) {
    //     this.color = color;
    //     this.x = x;
    //     this.y = y;
    // }

    // /*
    // **********Returns X ************
    // */
    // public abstract int getX();

    // /*
    // **********Returns Y ************
    // */
    // public abstract int getY();

    // /*
    // **********Sets X and Y ************
    // */
    // public abstract void setXY(int x, int y);

    /*
    **********Returns Team ************
    */
    public abstract String getColor();

    /*
    **********Returns Name(for printing) ************
    */
    public abstract String getName();

    /*
    **********Returns Type(for printing) ************
    */
    public abstract Type getType();

    /*
    **********Returns Type(for printing) ************
    */
    public abstract boolean canCheck(int x, int y, int kingX, int kingY);

    /*
    **********Returns Whether Move is Accepted Based on the piece's movement ability************
    */
    public abstract boolean isValidMove(int x, int y, int endX, int endY);

    @Override
    public boolean equals(Object comparator) {
        if (comparator == null) {
            return false;
        }
        if (Piece.class != comparator.getClass()) {
            return false;
        }
        Piece pieceComparator = (Piece) comparator;
        boolean equals = pieceComparator.getType().equals(this.getType());
        equals &= pieceComparator.getColor().equals(this.getColor());
        return equals;
    }

}
// public abstract int[] getPosition();

// public abstract List<Move> calculateMoves();

// public abstract int[][] drawPath();
// public abstract void select();
