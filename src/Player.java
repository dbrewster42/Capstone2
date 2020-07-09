
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
        lambdaContains doIt = arr -> {
            boolean result = Arrays.stream(arr).anyMatch(x -> x == piece);
            return result;
        };
        return doIt.doesHave(team);
    }

    /*
    ************** Prints Your Team's Available Pieces ****************
    //// I could jazz it up so that it is a count of each Piece Type  
    */
    public void getPieces() {
        // for (Piece i : team) {
        //     System.out.print(i.getName());
        // }
        // return team;
        for (Piece i : team) {
            System.out.print(i.getType() + ", ");
        }
        System.out.println("");
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
        boolean doesHave(Piece[] arr);
    }
}