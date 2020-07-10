import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Game {
    // static private Board gameboard;
    // private Player player1;
    // private Player player2;
    private static Scanner scanner = new Scanner(System.in);
    static List<String> moves = new ArrayList<String>();
    // static Piece[] white = new Piece[16];
    // static Piece[] black = new Piece[16];
    static Player player1, player2;
    static boolean isFirst = true;
    static boolean active = true;

    /*
    ************** Initalization of Players ****************
    */
    public static Player createPlayer() {

        System.out.println("Player, please enter your name");
        String name = scanner.nextLine();
        Player player = new Player(name, isFirst);
        if (isFirst) {
            System.out.println("It's nice to meet you " + name);
        } else {
            // Player player = new Player(name, false);
            System.out.println("I have heard of you " + name
                    + ", they say you do not treat your electronics with care. I hope you lose, jerk.");
        }
        isFirst = false;
        return player;
    }

    /*
    ************** Get other Team ****************
    */
    public static Player getOtherTeam(Player player) {
        // if (player == player1) {
        if (player.getColor()) {
            return player2;
        } else {
            return player1;
        }
    }

    /*
    ************** Prints All Moves ****************
    */
    public static void printMoves() {
        int count = 1;
        for (String i : moves) {
            System.out.println(count + ". " + i);
            count++;
        }
        System.out.println(" ");
    }

    /*
    ************** Breaking up the Select Piece function in the hope of making it less buggy ****************
    */
    public static void preSelect(Player player, Board gameboard) {
        System.out.println();
        System.out.println(player.getName() + ", it is your turn.");
        System.out.println("Enter 999 to display a list of all the remaining pieces");
        System.out.println("Enter 888 to display the detailed board");
        System.out.println("Enter 777 to display a list of all moves");
        System.out.println("Or enter in a double digit number to select your piece");
        System.out.println();
        try {
            int action = scanner.nextInt();
            scanner.nextLine();
            if (action == 999) {
                player.getPieces();
                Player otherPlayer = getOtherTeam(player);
                otherPlayer.getPieces();
            } else if (action == 888) {
                gameboard.showDetailedBoard();
            } else if (action == 777) {
                printMoves();
            } else {
                int pieceSelection = action;
                selectPiece(player, gameboard, pieceSelection);
                return;
            }
            preSelect(player, gameboard);
        } catch (Exception e) {
            System.out.println("You must enter a number");
            preSelect(player, gameboard);
        }
    }

    /*
    ************** Select a Piece ****************
    */
    public static void selectPiece(Player player, Board gameboard, int pieceSelection) {
        int action = pieceSelection;
        if (action > 77 || action < 0) {
            System.out.println("That selection is not a part of the board. Get in the game!");
            preSelect(player, gameboard);
        }
        int x = action / 10;
        int y = action % 10;
        System.out.println("X: " + x + " , Y: " + y);
        Square chosen = Board.squares[x][y];
        if (chosen.hasPiece()) {
            Piece piece = chosen.getPiece();
            if (player.hasPiece(piece)) {
                System.out.println("You have selected a " + piece.getType() + " at " + x + ", " + y);
                preMove(player, x, y, gameboard);
                return;
            } else {
                System.out.println("Invalid choice. That is not your piece at " + x + ", " + y);
                preSelect(player, gameboard);
            }

        } else {
            System.out.println("There is no piece at " + x + ", " + y + ". Please try again");
            preSelect(player, gameboard);
        }
    }

    /*
    ************** Checks whether Castling Conditions are Valid ****************
    */
    public static boolean canCastle(Player player, int x, int y, Board gameboard) {
        Piece piece = Board.squares[x][y].getPiece();
        if (piece.getType() == Type.ROOK) {
            if (x == 7 || x == 0) {
                if (Board.squares[x][4].getPiece().getType() == Type.KING) {
                    if (y == 0) {
                        int count = 3;
                        int betweenY = y;
                        while (count > 0) {
                            betweenY = betweenY + 1;
                            if (Board.squares[x][betweenY].hasPiece()) {
                                return false;
                            }
                            count = count - 1;
                        }
                        return true;
                    } else if (y == 7) {
                        int count = 2;
                        int betweenY = y;
                        while (count > 0) {
                            betweenY = betweenY - 1;
                            if (Board.squares[x][betweenY].hasPiece()) {
                                return false;
                            }
                            count = count - 1;
                        }
                        return true;
                    } else {
                        return false;
                    }

                }
            } else {
                System.out.println("This is not a valid castle");
                return false;
            }
        } else {
            System.out.println("You must select your Rook to initiate a castle");
            // preSelect(player, gameboard);
            return false;
        }
        return false;
    }

    /*
    ************** Performs Special Castle Move ****************
    */
    public static void doCastle(Player player, int x, int y, Board gameboard) {
        Piece piece = Board.squares[x][y].getPiece();
        Piece theKing = Board.squares[x][4].getPiece();
        if (y == 0) {
            Board.squares[x][3].setPiece(piece);
            Board.squares[x][2].setPiece(theKing);
            Board.squares[x][0].setPiece(null);
            Board.squares[x][4].setPiece(null);
            System.out.println("Long Side Castle Completed");
        } else if (y == 7) {
            Board.squares[x][5].setPiece(piece);
            Board.squares[x][6].setPiece(theKing);
            Board.squares[x][7].setPiece(null);
            Board.squares[x][4].setPiece(null);
            System.out.println("Short Side Castle Completed");
        } else {
            System.out.println("Wow you are tricky");
        }
    }

    /*
    ************** Breaks up the MovePiece function for smoother design ****************
    */
    public static void preMove(Player player, int x, int y, Board gameboard) {
        // Square initial = Board.squares[x][y];
        System.out.println();
        Piece piece = Board.squares[x][y].getPiece();
        System.out.println(player.getName() + ", you have selected your " + piece.getType());
        System.out.println("Enter 999 to select a different piece");
        System.out.println("Enter 888 to display the detailed board");
        System.out.println("Enter 777 to display a list of all moves");
        System.out.println("Enter 333 to castle if you have selected a Rook and are in a valid Castling Condition");
        System.out.println("Or type in the double digit number tile of your piece's destination");
        System.out.println();
        try {
            int action = scanner.nextInt();
            scanner.nextLine();
            if (action == 999) {
                preSelect(player, gameboard);
            } else if (action == 888) {
                gameboard.showDetailedBoard();
            } else if (action == 777) {
                printMoves();
            } else if (action == 333) {
                if (canCastle(player, x, y, gameboard)) {
                    System.out.println("CASTLE!");
                    doCastle(player, x, y, gameboard);
                } else {
                    System.out.println("Valid castling conditions were not met");
                }
            } else {
                int pieceSelection = action;
                movePiece(player, x, y, gameboard, pieceSelection);
                return;
            }
            preMove(player, x, y, gameboard);
        } catch (Exception e) {
            System.out.println("You must enter a number dummy");
            preMove(player, x, y, gameboard);
        }

    }

    /*
    ************** Move Your Piece ****************
    */
    public static void movePiece(Player player, int x, int y, Board gameboard, int pieceSelection) {
        // int action;
        Square initial = Board.squares[x][y];
        Piece piece = initial.getPiece();
        int action = pieceSelection;
        System.out.println(action);
        if (action > 77 || action < 0) {
            System.out.println(
                    "You must enter a double digit number. The first digit is the piece's height, the second is the width");
            System.out.println("Hello");
            preMove(player, x, y, gameboard);
        }
        int endX = action / 10;
        int endY = action % 10;
        if (piece.isValidMove(x, y, endX, endY)) {
            if (Board.squares[endX][endY].hasPiece()) {
                if (player.hasPiece(Board.squares[endX][endY].getPiece())) {
                    System.out.println("Invalid choice. You already have a piece there!");
                    preMove(player, x, y, gameboard);
                }
            }
            Type type = piece.getType();
            System.out.println(type + " whoopee ------------");
            String move = player.getName() + "'s " + type + " has moved from " + x + "" + y + " to " + endX + "" + endY;
            if (type == Type.PAWN) {
                if (endX == 0 || endX == 7) {
                    move += " and is promoted to a QUEEN";
                    piece = player.pawnPromotion(piece);
                }
            }
            //moves from old spot
            Board.squares[x][y].setPiece(null);
            /*
            ****** checks if a capture took place and if so, sets enemy piece to null ******
            */
            if (Board.squares[endX][endY].hasPiece()) {
                Piece capturedPiece = Board.squares[endX][endY].getPiece();
                Player otherPlayer = getOtherTeam(player);
                move = move + " and has captured " + otherPlayer.getName() + "'s " + capturedPiece.getType() + "!";
                // otherPlayer.team = otherPlayer.killPiece(capturedPiece);
                otherPlayer.killPiece(capturedPiece);
            }
            moves.add(move);
            System.out.println(move);
            ///moves to new spot
            gameboard.getSquare(endX, endY).setPiece(piece);
            return;
        } else {
            System.out.println("Invalid move");
            preMove(player, x, y, gameboard);
        }
    }

    public static void main(String[] args) {
        Board gameboard = Board.boardConstructor();
        player1 = createPlayer();
        player2 = createPlayer();
        System.out.println();
        System.out.println(
                "Select your piece by typing in a double digit number. The first digit is the vertical coordinate, the second digit is the horizontal like so-");
        gameboard.showDetailedBoard();
        System.out.println("You can type 888 into the console at any time to see this detailed board");
        while (active) {
            preSelect(player1, gameboard);
            gameboard.showBoard();
            preSelect(player2, gameboard);
            gameboard.showBoard();
        }

    }
}