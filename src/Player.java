
// import java.util.stream.*;
import java.util.Arrays;

public class Player {
    private String name;
    // private Piece[] white;
    // private Piece[] black;
    Piece[] team;
    private boolean isWhite;

    public Player(String playerName, boolean isWhite) {
        name = playerName;
        this.isWhite = isWhite;
        if (isWhite) {
            team = createPieces(6);
        } else {
            team = createPieces(0);
        }
    }

    /*
    ************** Initalization of All Pieces ****************
    */
    public static Piece[] createPieces(int start) {
        Piece[] team = new Piece[16];
        int count = 0;
        int end = start + 2;
        for (int i = start; i < end; i++) {
            for (int j = 0; j < 8; j++) {
                // System.out.println(Board.squares[i][j].printPiece() + " :" + count + " " + start + " " + end);
                team[count] = Board.squares[i][j].getPiece();
                count++;
            }
        }
        return team;
        // count = 0;
        // for (int i = 6; i < 8; i++) {
        //     for (int j = 0; j < 8; j++) {
        //         white[count] = Board.squares[i][j].getPiece();
        //         count++;
        //     }
        // }
    }

    /*
    **********Returns whether White or Black ************
    */
    public boolean getColor() {
        return isWhite;
    }

    /*
    **********Returns player's name ************
    */
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
    **********Sets current team ************
    */
    public void setTeam(Piece[] updatedTeam) {
        team = updatedTeam;
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
    ************** Prints Your Team's Available Pieces by Type with a Count ****************  
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
        System.out.println("crickets");
        for (Piece i : team) {
            if (i == null)
                continue;
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
    // public void killPiece(Piece piece) {
    //     piece = null;
    // }
    public void killPiece(Piece piece) {
        System.out.println("HI GUY");
        int count = 0;
        for (Piece i : team) {
            if (i.getType() == piece.getType()) {
                break;
            }
            count++;
        }
        team[count] = null;
        // return team;
    }

    /*
    **********Changes Pawn to Queen/ Change Piece/ Add Piece ************
    */
    public Piece pawnPromotion(Piece piece) {
        // if (piece.getType() == Type.PAWN);
        String color;
        // if (isWhite) {
        //     color = "white";
        //     Queen queen = new Queen("white");
        //     piece = queen;
        // } else {
        //     Queen queen = new Queen("black");
        //     piece = queen;
        // }
        if (isWhite) {
            color = "white";
        } else {
            color = "black";
        }
        Queen queen = new Queen(color);
        int count = 0;
        for (Piece i : team) {
            if (i.getType() == piece.getType()) {
                break;
            }
            count++;
        }
        team[count] = queen;
        piece = queen;
        return piece;
    }

    interface lambdaContains {
        boolean doesHave(Piece[] team);
    }
}