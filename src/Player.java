
// import java.util.stream.*;
import java.util.Arrays;

public class Player {
    private String name;
    private Piece[] team;

    public Player(String playerName, Piece[] myTeam) {
        name = playerName;
        team = myTeam;
    }

    public String getName() {
        return name;
    }

    /*
    **********Returns current team ************
    */
    public Piece[] getTeam() {
        return team;
    }

    /*
    **********lambda stream that checks to see if the selected piece belongs to the current team ************
    */
    public boolean hasPiece(Piece piece) {
        // return Arrays.stream(team).anyMatch(p -> Objects.equals(piece, p));
        lambdaContains doIt = team -> {
            boolean result = Arrays.stream(team).anyMatch(x -> x.getName() == piece.getName());
            // System.out.println(result);
            return result;
        };
        return doIt.doesHave(team);
    }

    /*
    ************** Prints Your Team's Available Pieces ****************
    //// I could jazz it up so that it is a count of each Piece Type  
    */
    public void getPieces() {
        int pawnCount = 0;
        int queenCount = 0;
        int rookCount = 0;
        int knightCount = 0;
        int bishopCount = 0;
        // for (Piece i : team) {
        //     System.out.print(i.getType() + ", ");
        // }
        // System.out.println("");
        for (Piece i : team) {
            if (i.getType() == Type.BISHOP)
                bishopCount++;
            if (i.getType() == Type.QUEEN)
                queenCount++;
            if (i.getType() == Type.ROOK)
                rookCount++;
            if (i.getType() == Type.KNIGHT)
                knightCount++;
            if (i.getType() == Type.PAWN)
                pawnCount++;
        }
        System.out.println(name + " has the following pieces-");
        System.out.println("Queen: " + queenCount);
        System.out.println("Rook: " + rookCount);
        System.out.println("Knight: " + knightCount);
        System.out.println("Bishop: " + bishopCount);
        System.out.println("Pawn: " + pawnCount);

    }

    /*
    **********Removes captured piece from team ************
    */
    public Piece[] killPiece(Piece piece) {
        for (Piece i : team) {
            if (i == piece) {
                i = null;
                break;
            }
        }
        return team;
    }

    interface lambdaContains {
        boolean doesHave(Piece[] team);
    }
}