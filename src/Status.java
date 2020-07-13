// import java.util.Arrays;

public class Status {
    public static boolean active = true;
    public static boolean check = false;
    public static boolean checkMate = false;
    public static boolean Stalemate = false;
    static Attacker[] attackers = new Attacker[2];

    /*
    ************** Checks for Check! After every move it scans the pieces to see if it is a check ****************
    */
    public static boolean didCheck(Player player, Piece piece, Board gameboard, int endX, int endY) {
        // Piece[] team = player.getTeam();
        // for (Piece i : team){
        //     if (i.canCheck(endX, endY))
        // }
        int x = endX;
        int y = endY;
        Player otherTeam = Game.getOtherTeam(player);
        King king = otherTeam.getKing();
        // King king = (King) theKing;
        int kingX = king.getX();
        int kingY = king.getY();
        if (piece.isValidMove(x, y, kingX, kingY)) {
            Attacker attacker = Attacker.createAttacker(player, piece, x, y);
            attackers[0] = attacker;
            // if (player.getColor()){

            // }
            return true;
        }
        return false;
    }

    public static boolean defeatCheck(Player player, Piece piece, Board gameboard, int endX, int endY) {
        Attacker attacker = attackers[0];
        //if attacking piece is captured, then check is defeated
        if (endX == attacker.x && endY == attacker.y) {
            Board.squares[endX][endY].setPiece(piece);
            return true;
        }
        //if knight then it can only be blocked by moving or capturing
        else if (attacker.type == Type.KNIGHT) {
            if (piece.getType() != Type.KING) {
                return false;
            }
        }
        King king = player.getKing();
        int kingX = king.getX();
        int kingY = king.getY();
        Piece oldPiece = null;
        if (Board.squares[endX][endY].hasPiece()) {
            oldPiece = Board.squares[endX][endY].getPiece();
        }
        /// makes attempted move and validates if out of check
        Board.squares[endX][endY].setPiece(piece);
        if (attacker.piece.isValidMove(attacker.x, attacker.y, kingX, kingY)) {
            return false;
        } else {
            Board.squares[endX][endY].setPiece(oldPiece);
            return true;
        }
    }

    public static boolean isCheckMate(Player player, Board gameboard) {
        String color = "black";
        if (player.isWhite()) {
            color = "white";
        }
        //// checks to see if King can move out of check
        /////
        Attacker attacker = attackers[0];
        King king = player.getKing();
        int kingX = king.getX();
        int kingY = king.getY();
        int xDirection = 0;
        int yDirection = 0;
        if (attacker.x - kingX > 0) {
            xDirection = -1;
        } else if (attacker.x - kingX < 0) {
            xDirection = 1;
        }
        if (attacker.y - kingY > 0) {
            yDirection = -1;
        } else if (attacker.y - kingY < 0) {
            yDirection = 1;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board.squares[i][j].hasPiece()) {
                    Piece piece = Board.squares[i][j].getPiece();
                    if (piece.getColor() == color) {
                        if (piece.getType() == Type.KING) {
                            continue;
                        }
                        int blockX = attacker.x;
                        int blockY = attacker.y;
                        while (blockX != i && blockY != j) {
                            System.out.println(
                                    "Can " + piece.getType() + " at " + i + "" + j + " reach " + blockX + "" + blockY);
                            if (piece.isValidMove(i, j, blockX, blockY)) {
                                System.out.println("Can be blocked by " + piece.getType() + " at " + i + "" + j
                                        + " to  " + blockX + "" + blockY);
                                System.out.println("Not checkmate");
                                return false; //is not checkmate
                            }
                            blockX += xDirection;
                            blockY += yDirection;
                        }

                    }
                }
            }
        }
        return true;
    }

    // Square[][] squares = gameboard.getSquares();
    // boolean mate = Arrays.stream(squares).flatMap(board -> b.getSquare().stream()).filter(s -> s.hasPiece())
    //         .filter(s -> s.getPiece().getColor() == color)
    //         .map(s -> s.getPiece().isValidMove(s.getX(), s.getY(), attack.x, attack.y)).getFirst();
    // while (attacker.x != i && attacker.y != j)
    // boolean second = Arrays.stream(Board.squares).flatMap(b -> b.getArray().stream()).filter(s -> s.hasPiece())
    //         .filter(s -> s.getPiece().getColor() == color)
    //         .map(s -> s.getPiece().isValidMove(s.getX(), s.getY(), attack.x - 1, attack.y + 1)).getFirst();
    // boolean third = Arrays.stream(gameboard).flatMap(b -> b.getArray().stream()).filter(s -> s.hasPiece())
    //         .filter(s -> s.getPiece().getColor() == color)
    //         .map(s -> s.getPiece().isValidMove(s.getX(), s.getY(), attack.x - 2, attack.y + 2)).getFirst();
    // return (mate || second || third);
    // return false;

    // public static void storeAttacker(Player player, Piece piece, int x, int y) {}

}