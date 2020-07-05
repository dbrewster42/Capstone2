// import java.util.List;
// import java.util.ArrayList;

public class Board {
    // List<String> ids = Arrays.asList("A", "B", "C", "D", "E", "F", "G","H" );
    String[] ids = { "A", "B", "C", "D", "E", "F", "G", "H" };
    // List<Square> squares = new ArrayList<Square>();
    // Square[] squares = new Square[64];
    final public static Square[][] squares = new Square[8][8];

    public Board() {
        //dynamically creates board composed of 64 squares        
        generateBoard();
        // for (int[] : squares) {
        //     for (Square s : int[]){
        //         System.out.println(s.getID());
        //     }
        // } 
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 7)
                    System.out.println(squares[i][j].printPiece());
                else
                    System.out.print(squares[i][j].printPiece() + ", ");
            }
        }
    }
    // public static Square getSquare(String id){

    // }
    public void showBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(squares[i][j].getPiece() + ", ");
            }
        }
    }

    public Square getSquare(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            // throw new Exception("Index out of bound"); 
            System.out.println("This square does not exist. Please try again");
            return null;
        }

        return squares[x][y];
    }

    public Square[][] generateBoard() {
        // int count = 0;

        squares[0][0] = new Square(0, 0, new Rook("white"));
        squares[0][1] = new Square(0, 1, new Knight("white"));
        squares[0][2] = new Square(0, 2, new Bishop("white"));
        squares[0][3] = new Square(0, 3, new Queen("white"));
        squares[0][4] = new Square(0, 4, new King("white"));
        squares[0][5] = new Square(0, 5, new Bishop("white"));
        squares[0][6] = new Square(0, 6, new Knight("white"));
        squares[0][7] = new Square(0, 7, new Rook("white"));
        /*
        creates the board, using nested arrays to dynamically make 8 rows columns 1-6
        */
        for (int i = 1; i < 7; i++) {
            // String firstID = ids[i];
            for (int j = 0; j < 8; j++) {
                // String id = firstID + j;
                if (i == 1)
                    squares[i][j] = new Square(i, j, new Pawn("white"));
                else if (i == 6)
                    squares[i][j] = new Square(i, j, new Pawn("black"));
                else {
                    Square square = new Square(i, j, null);
                    squares[i][j] = square;
                }
                // squares.add(square);
                // count++;
            }
        }
        squares[7][0] = new Square(7, 0, new Rook("black"));
        squares[7][1] = new Square(7, 1, new Knight("black"));
        squares[7][2] = new Square(7, 2, new Bishop("black"));
        squares[7][3] = new Square(7, 3, new Queen("black"));
        squares[7][4] = new Square(7, 4, new King("black"));
        squares[7][5] = new Square(7, 5, new Bishop("black"));
        squares[7][6] = new Square(7, 6, new Knight("black"));
        squares[7][7] = new Square(7, 7, new Rook("black"));

        return squares;
    }

}