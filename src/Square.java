// package com.gameboard;

public class Square {
    // private String id;
    private Piece piece;
    private int x, y;

    // protected Square(int x, int y, String id) {
    //     this(x, y, id, null);
    // }
    protected Square(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        // this.id = id;
        this.piece = piece;
    }

    // public String getID() {
    //     return this.id;
    // }
    // public Square getSquare(String id){
    //     return this;
    // }
    /*
    **********Checks if the Square has a Piece ************
    */
    public boolean hasPiece() {
        if (this.piece == null) {
            return false;
        }
        return true;
    }

    /*
    **********Returns Piece (if there) Use AFTER hasPiece() ************
    */
    public Piece getPiece() {
        return this.piece;
    }

    /*
    **********Returns Y coordinate ************
    */
    public int getY() {
        return y;
    }

    /*
    **********Returns X coordinate ************
    */
    public int getX() {
        return x;
    }

    /*
    **********For Printing Square's piece(no hasPiece() check needed) ************
    */
    public String printPiece() {
        // String team;
        // if (this.piece.color == "white") {
        //     team = "W_";
        // } else {
        //     team = "B_";
        // }
        // team += instanceOf(this.piece);
        if (this.piece != null)
            return this.piece.getName();
        else
            return null;
    }

    /*
    **********Sets new Piece ************
    */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
