import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Game {
    // static private Board gameboard;
    // private Player player1;
    // private Player player2;
    private static Scanner scanner = new Scanner(System.in);
    static List<String> moves = new ArrayList<String>();
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
                white[count] = Board.squares[i][j].getPiece();
                count++;
            }
        }
        count = 0;
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                black[count] = Board.squares[i][j].getPiece();
                count++;
            }
        }
    }

    /*
    ************** Prints Your Team's Available Pieces ****************
    //// I could jazz it up so that it is a count of each Piece Type
    *********duplicate*************
    */
    // public static void getPieces(Piece[] team) {
    //     for (Piece i : team) {
    //         System.out.print(i.getType() + ", ");
    //     }
    //     System.out.println("");
    // }

    /*
    ************** Get other Team ****************
    */
    public static Player getOtherTeam(Player player) {
        if (player == player1) {
            return player2;
        } else {
            return player1;
        }
    }

    /*
    ************** Breaking up the Select Piece function in the hope of making it less buggy ****************
    */
    public static void preSelect(Player player, Board gameboard) {
        System.out.println(player.getName()
                + ", it is your turn. Type in 999 to display a list of all your pieces or 888 to display the detailed board");
        System.out.println("Or type in any other number to select your piece");
        try {
            int action = scanner.nextInt();
            scanner.nextLine();
            if (action == 999) {
                // Piece[] yourTeam = player.getTeam();
                // getPieces(yourTeam);
                player.getPieces();
                // selectPiece(player, gameboard);
            } else if (action == 888) {
                gameboard.showDetailedBoard();
                // selectPiece(player, gameboard);
            }
            // else {                    
            // }
            System.out.println("Select the piece you wish to move");
            selectPiece(player, gameboard);
        } catch (Exception e) {
            System.out.println("You must enter a number");
        }
    }

    /*
    ************** Select a Piece ****************
    */
    public static void selectPiece(Player player, Board gameboard) {

        // System.out.println(player.getName()
        //         + ", it is your turn. Select the piece you wish to move or type in 999 to display a list of all your pieces or 888 to display the detailed board");
        try {
            int action = scanner.nextInt();
            scanner.nextLine();
            // if (action == 999) {
            //     // Piece[] yourTeam = player.getTeam();
            //     // getPieces(yourTeam);
            //     player.getPieces();
            //     selectPiece(player, gameboard);
            // }
            // if (action == 888) {
            //     gameboard.showDetailedBoard();
            //     selectPiece(player, gameboard);
            // } else if (action > 77 || action < 0) {
            if (action > 77 || action < 0) {
                System.out.println("That selection is not a part of the board. Get in the game!");
                selectPiece(player, gameboard);
            }
            int x = action / 10;
            int y = action % 10;
            System.out.println("X: " + x + " , Y: " + y);
            // try {
            Square chosen = Board.squares[x][y];
            if (chosen.hasPiece()) {
                Piece piece = chosen.getPiece();
                if (player.hasPiece(piece)) {
                    System.out.println("Invalid choice. That is not your piece!");
                    selectPiece(player, gameboard);
                }
                System.out.println("You have selected a " + piece.getType() + " at " + x + ", " + y);
                preMove(player, x, y, gameboard);
            } else {
                System.out.println("You do not have a piece at " + x + ", " + y + ". Please try again");
                selectPiece(player, gameboard);
            }
        } catch (Exception e) {
            System.out.println(
                    "You must enter 999 or a double digit number. The first digit is the piece's height, the second is the width");
            selectPiece(player, gameboard);
        }

        // } catch (Exception e) {
        //     selectPiece(player, gameboard);
        // }        

    }

    /*
    ************** Breaking up the MovePiece function in the hope of making it less buggy ****************
    */
    public static void preMove(Player player, int x, int y, Board gameboard) {
        System.out.println(player.getName()
                + ", it is still your turn. Type in 999 to select a different piece or 888 to display the detailed board");
        System.out.println(
                "If you have selected a Rook and are in a valid Castling Condition, you may select 777 to castle");
        System.out.println("Or type in any other number to move your piece");
        try {
            int action = scanner.nextInt();
            scanner.nextLine();
            if (action == 999) {
                selectPiece(player, gameboard);
            } else if (action == 888) {
                gameboard.showDetailedBoard();
                // selectPiece(player, gameboard);
            } else if (action == 777) {
                System.out.println("CASTLE!");
            }
            System.out.println("Select the square you wish to move to");
            movePiece(player, x, y, gameboard);
        } catch (Exception e) {
            System.out.println("You must enter a number");
            preMove(player, x, y, gameboard);
        }

    }

    /*
    ************** Move Your Piece ****************
    */
    public static void movePiece(Player player, int x, int y, Board gameboard) {
        // int action;
        Square initial = Board.squares[x][y];
        Piece piece = initial.getPiece();
        // System.out.println("If you wish to select a different piece, press 999.");
        // System.out.println("Otherwise, select the tile where you wish to move your piece");
        try {
            int action = scanner.nextInt();
            scanner.nextLine();
            System.out.println(action);
            // if (action == 999) {
            //     selectPiece(player, gameboard);
            // } else 
            if (action > 77 || action < 0) {
                System.out.println(
                        "You must enter 999 or a double digit number. The first digit is the piece's height, the second is the width");
                System.out.println("Hello");
                movePiece(player, x, y, gameboard);
            }
            int endX = action / 10;
            int endY = action % 10;

            // if (gameboard.getSquare(endX, endY) == null) {
            //     System.out.println("Get in the game! That selection is not a part of the board" + endX + "" + endY);
            //     movePiece(player, x, y, gameboard);
            // }
            if (piece.isValidMove(x, y, endX, endY)) {

                Type type = piece.getType();
                System.out.println(type + " whoopee ------------");
                // Move move = new Move(player, type, x, y, endX, endY);
                String move = player.getName() + "'s " + type + " has moved from " + x + "" + y + " to " + endX + ""
                        + endY;
                moves.add(move);
                Board.squares[x][y].setPiece(null);
                /*
                ****** checks if a capture took place ******
                */
                /////////should refactor to check if end has own piece before move is added
                if (Board.squares[endX][endY].hasPiece()) {
                    if (player.hasPiece(piece)) {
                        System.out.println("Invalid choice. You already have a piece there!");
                        movePiece(player, x, y, gameboard);
                    }
                    Piece capturedPiece = Board.squares[endX][endY].getPiece();
                    Type capturedType = capturedPiece.getType();
                    Player otherPlayer = getOtherTeam(player);
                    move = move + " and has captured " + otherPlayer.getName() + "'s " + capturedType + "!";
                    // System.out.println(player.getName() + "'s " + type + " has captured " + otherPlayer.getName()
                    //         + "'s " + capturedType + "!");
                    otherPlayer.killPiece(capturedPiece);
                    // Piece[] pieces = otherPlayer.getTeam();
                    // for (Piece i : pieces) {
                    //     if (i.getType() == capturedType) {
                    //         i = null;
                    //         break;
                    //     }
                    // }
                }
                System.out.println(move);
                gameboard.getSquare(endX, endY).setPiece(piece);
            } else {
                System.out.println("Invalid move");
                movePiece(player, x, y, gameboard);
            }
        } catch (Exception e) {
            System.out.println(
                    "You must enter 999 or a double digit number. The first digit is the piece's height, the second is the width");
            System.out.println("Oh Goodness");
            movePiece(player, x, y, gameboard);
        }

    }

    public static void main(String[] args) {
        Board gameboard = Board.boardConstructor();
        createPieces();
        boolean active = true;
        player1 = createPlayer1();
        player2 = createPlayer2();
        System.out.println();
        System.out.println(
                "Select your piece by typing in a double digit number. The first digit is the vertical coordinate, the second digit is the horizontal like so-");
        gameboard.showDetailedBoard();
        // getPieces(black);      
        System.out.println("You can type 888 into the console at any time to see this detailed board");
        while (active) {
            // System.out.println("WE BE THE GG");

            gameboard.showBoard();
            selectPiece(player2, gameboard);
            gameboard.showBoard();
        }

    }
}