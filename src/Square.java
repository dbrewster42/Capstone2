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
    public boolean hasPiece() {
        if (this.piece == null) {
            return false;
        }
        return true;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    /*
    **********For Printing Entire Board ************
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

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
