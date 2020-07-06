import java.util.Scanner;

public class Game {
    static private Board gameboard;
    // private Player player1;
    // private Player player2;
    private static Scanner scanner = new Scanner(System.in);
    static Piece[] white = new Piece[16];
    static Piece[] black = new Piece[16];
    static Player player1, player2;

    /*
    ************** One Time Initalization of All Players ****************
    */
    public static void createPlayers() {
        System.out.println("Player1, please enter your name");
        String name = scanner.nextLine();
        Player player1 = new Player(name, white);
        System.out.println("It's nice to meet you " + name);
        System.out.println("Player2, please enter your name");
        String name2 = scanner.nextLine();
        Player player2 = new Player(name2, black);
        System.out.println("I have heard of you " + name2
                + ", they say you do not treat your electronics with care. I hope you lose, jerk.");

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
    */
    public static void getPieces(Piece[] team) {
        for (Piece i : team) {
            System.out.print(i.getName() + ", ");
        }
        System.out.print("");
    }

    /*
    ************** Select a Piece ****************
    */
    public static void selectPiece(Player player) {
        System.out.println(player.getName() + ", it is your turn. Select the piece you wish to move");
        int action = 0;
        try {
            action = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(
                    "You must enter a double digit number. The first digit is the x coordinate, the second is the y coordinate.");
            selectPiece(player);
        }
        int x = action / 10;
        int y = action % 10;
        Square chosen = gameboard.getSquare(x, y);
        //////////////
        //////////////////add logic to make sure piece is on the right team
        if (chosen.hasPiece()) {
            Piece piece = chosen.getPiece();
            System.out.println("You have selected a " + piece.getType() + " at " + x + ", " + y);
            movePiece(player, x, y);
        } else {
            System.out.println("You do not have a piece at " + x + ", " + y + ". Please try again");
            selectPiece(player);
        }

    }

    /*
    ************** Move Your Piece ****************
    */
    public static void movePiece(Player player, int x, int y) {
        int action = 888;
        System.out.println("If you wish to select a different piece, press 999.");
        System.out.println("Otherwise, select the tile you wish to move your");
        try {
            action = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(
                    "You must enter 999 or a double digit number. The first digit is the x coordinate, the second is the y coordinate.");
            movePiece(player, x, y);
        }
        if (action == 999) {
            selectPiece(player);
        } else {
            int endX = action / 10;
            int endY = action % 10;
        }
        ///logic for moving piece, probably piece.isValidMove
    }

    public static void main(String[] args) {
        // createPieces("black");
        Board gameboard = new Board();
        createPieces();
        boolean active = true;
        createPlayers();
        getPieces(black);
        while (active) {
            selectPiece(player1);
            gameboard.showBoard();
            selectPiece(player2);
            gameboard.showBoard();
        }

    }
}