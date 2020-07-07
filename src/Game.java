import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Game {
    static private Board gameboard;
    // private Player player1;
    // private Player player2;
    private static Scanner scanner = new Scanner(System.in);
    static List<Move> moves = new ArrayList<Move>();
    static Piece[] white = new Piece[16];
    static Piece[] black = new Piece[16];
    static Player player1, player2;

    /*
    ************** One Time Initalization of First Player ****************
    */
    public static Player createPlayer1() {
        System.out.println("Player1, please enter your name");
        String name = scanner.nextLine();
        Player player1 = new Player(name, white);
        System.out.println("It's nice to meet you " + name);
        return player1;
    }

    /*
    ************** One Time Initalization of Second Player ****************
    */
    public static Player createPlayer2() {
        System.out.println("Player2, please enter your name");
        String name2 = scanner.nextLine();
        Player player2 = new Player(name2, black);
        System.out.println("I have heard of you " + name2
                + ", they say you do not treat your electronics with care. I hope you lose, jerk.");
        return player2;
    }

    /*
    ************** One Time Initalization of All Pieces ****************
    */
    public static void createPieces() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                // System.out.println(Board.squares[i][j].printPiece());
                black[count] = Board.squares[i][j].getPiece();
                count++;
            }
        }
        count = 0;
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                white[count] = Board.squares[i][j].getPiece();
                count++;
            }
        }
    }

    /*
    ************** Prints Your Team's Available Pieces ****************
    //// I could jazz it up so that it is a count of each Piece Type
    */
    public static void getPieces(Piece[] team) {
        for (Piece i : team) {
            System.out.print(i.getType() + ", ");
        }
        System.out.println("");
    }

    /*
    ************** Select a Piece ****************
    */
    public static void selectPiece(Player player, Board gameboard) {
        System.out.println(player.getName()
                + ", it is your turn. Select the piece you wish to move or type in 999 to display a list of all your pieces or 888 to display the board");
        int action = 0;
        try {
            action = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(
                    "You must enter a double digit number. The first digit is the x coordinate, the second is the y coordinate.");
            selectPiece(player, gameboard);
        }
        if (action == 999) {
            Piece[] yourTeam = player.getTeam();
            getPieces(yourTeam);
            selectPiece(player, gameboard);
        }
        if (action == 888) {
            gameboard.showBoard();
            selectPiece(player, gameboard);
        }
        int x = action / 10;
        int y = action % 10;
        System.out.println("X: " + x + " , Y: " + y);
        // try {
        Square chosen = gameboard.getSquare(x, y);
        if (chosen.hasPiece()) {
            Piece piece = chosen.getPiece();
            System.out.println("You have selected a " + piece.getType() + " at " + x + ", " + y);
            movePiece(player, x, y, gameboard);
        } else {
            System.out.println("You do not have a piece at " + x + ", " + y + ". Please try again");
            selectPiece(player, gameboard);
        }
        // } catch (Exception e) {
        //     selectPiece(player, gameboard);
        // }
        //////////////
        //////////////////add logic to make sure piece is on the right team
        //////////////////////////////*************************((((((((***************************************)))))))) */

    }

    /*
    ************** Move Your Piece ****************
    */
    public static void movePiece(Player player, int x, int y, Board gameboard) {
        int action = 888;
        Square initial = gameboard.getSquare(x, y);
        Piece piece = initial.getPiece();
        System.out.println("If you wish to select a different piece, press 999.");
        System.out.println("Otherwise, select the tile where you wish to move your piece");
        try {
            action = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(
                    "You must enter 999 or a double digit number. The first digit is the x coordinate, the second is the y coordinate.");
            movePiece(player, x, y, gameboard);
        }
        if (action == 999) {
            selectPiece(player, gameboard);
        }
        int endX = action / 10;
        int endY = action % 10;
        if (gameboard.getSquare(endX, endY) == null) {
            movePiece(player, x, y, gameboard);
        }
        if (piece.isValidMove(x, y, endX, endY)) {
            Type type = piece.getType();
            Move move = new Move(player, type, x, y, endX, endY);
            moves.add(move);
            gameboard.getSquare(x, y).setPiece(null);
            if (Board.squares[endX][endY].hasPiece()) {
                ///get opposite team
                //set type to null in piece[]
            }
            gameboard.getSquare(endX, endY).setPiece(piece);
        } else {
            System.out.println("Invalid move");
        }
    }

    public static void main(String[] args) {
        Board gameboard = Board.boardConstructor();
        createPieces();
        boolean active = true;
        player1 = createPlayer1();
        player2 = createPlayer2();
        // getPieces(black);
        System.out.println();
        //try/catch a timeout
        System.out.println(
                "Select your piece by typing in a double digit number. The first digit is the vertical coordinate, the second digit is the horizontal.");
        System.out.println(
                "For example, typing in 11 will select your piece on the 2nd row and 2nd column. Then you will select it's destination in the same fashion");
        while (active) {
            gameboard.showBoard();
            selectPiece(player1, gameboard);
            gameboard.showBoard();
            selectPiece(player2, gameboard);
        }

    }
}