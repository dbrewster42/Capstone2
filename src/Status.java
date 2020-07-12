import java.util.Arrays;

public class Status {
    public static boolean active = true;
    public static boolean check = false;
    public static boolean checkMate = false;
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
        if (endX == attacker.x && endY == attacker.y) {
            Board.squares[endX][endY].setPiece(piece);
            return true;
        }

        else if (attacker.type == Type.KNIGHT) {
            if (piece.getType() != Type.KING) {
                return false;
            }
        }
        King king = player.getKing();
        int kingX = king.getX();
        int kingY = king.getY();
        Board.squares[endX][endY].setPiece(piece);
        if (attacker.piece.isValidMove(attacker.x, attacker.y, kingX, kingY)) {
            return false;
        } else {
            ////need to save prior state first
            Board.squares[endX][endY].setPiece(null);
            return true;
        }
    }

    public static boolean isCheckMate(Player player, Board gameboard) {
        // boolean isWhite = player.getColor();
        String color = "black";
        if (player.getColor()) {
            color = "white";
        }
        //     Arrays.stream(team).flatMap(b -> b.getArray().stream())
        //     .filter(s -> s.hasPiece())
        //     .filter(s -> s.getPiece().getColor() == color)
        //     .map(s -> s.getPiece().isValidMove())
        return true;
    }
    // public static void storeAttacker(Player player, Piece piece, int x, int y) {}

}