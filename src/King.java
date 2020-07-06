
// package com.gamepieces;
// import java.util.List;

public class King extends Piece {
    // final public static Piece B_KING = new King("King", "black");
    // final public static Piece W_KING = new King("King", "white");
    // private String name;
    // private String color;
    Type type;

    protected King(String color) {
        super(color);
        type = Type.KING;
        name = "KING";
        // if (color.equals("white"))

        // else {
        //     name = "bPaw";
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

    @Override
    public void move() {
    }

    @Override
    public boolean isValidMove() {
        return true;
    }

    public void select() {
    }

    // private King(String name, String color) {
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