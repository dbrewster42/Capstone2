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
    // static boolean active = true;
    // static boolean check = false;
    static List<Board.Memento> savedBoards = new ArrayList<Board.Memento>();

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
    ************** Checks whether Castling Conditions are Valid ****************
    */
    public static boolean isValidCastle(Player player, int x, int y, Board gameboard) {
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
        King king = (King) theKing;
        if (y == 0) {
            Board.squares[x][3].setPiece(piece);
            Board.squares[x][2].setPiece(theKing);
            king.setXY(x, y);
            Board.squares[x][0].setPiece(null);
            Board.squares[x][4].setPiece(null);
            String move = player.getName() + " has performed a long side castle";
            moves.add(move);
        } else if (y == 7) {
            Board.squares[x][5].setPiece(piece);
            Board.squares[x][6].setPiece(theKing);
            king.setXY(x, y);
            Board.squares[x][7].setPiece(null);
            Board.squares[x][4].setPiece(null);
            String move = player.getName() + " has performed a short side castle";
            moves.add(move);
        } else {
            System.out.println("Wow you are tricky");
        }
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
        System.out.println("Enter 333 to time travel (undo a move)");
        System.out.println("Enter 1111 to forfeit");
        System.out.println("Or enter in a double digit number to select your piece");
        System.out.println();
        if (Status.check) {
            System.out.println(player.getName() + ", you have been put into check! You must move out of check");
            System.out.println();
        }
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
            } else if (action == 1111) {
                System.out.println("Are you sure that you wish to forfeit?");
                System.out.println("Type yes or hit any other key to continue with the game");
                String answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    System.out.println("Forfeiting...");
                    Status.active = false;
                    return;
                }
                preSelect(player, gameboard);
                return;
            } else if (action == 333) {
                System.out.println("Enter how many turns you wish to undo");
                try {
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    num *= 2;
                    int mementoSize = savedBoards.size();
                    int travel = mementoSize - num;
                    System.out.println(mementoSize + "travel: " + travel);
                    Board newBoard = gameboard.restoreFromMemento(savedBoards.get(travel));
                    newBoard.showDetailedBoard();
                    preSelect(player, newBoard);
                    return;
                } catch (Exception e) {
                    System.out.println("You must enter a number. No time travel for you!" + e);
                    preSelect(player, gameboard);
                    return;
                }
            } else {
                if (action > 77 || action < 0) {
                    System.out.println("That selection is not a part of the board. Get in the game!");
                    preSelect(player, gameboard);
                    return;
                }
                // int pieceSelection = action;
                selectPiece(player, gameboard, action);
                return;
            }
            preSelect(player, gameboard);
            return;
        } catch (Exception e) {
            System.out.println("You must enter a number " + e);
            e.printStackTrace();
            preSelect(player, gameboard);
            return;
        }
    }

    /*
    ************** Select a Piece ****************
    */
    public static void selectPiece(Player player, Board gameboard, int pieceSelection) {
        int action = pieceSelection;
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
                return;
            } else if (action == 888) {
                gameboard.showDetailedBoard();
                // return;
            } else if (action == 777) {
                printMoves();
                // return;
            } else if (action == 333) {
                if (isValidCastle(player, x, y, gameboard)) {
                    // System.out.println("CASTLE!");
                    doCastle(player, x, y, gameboard);
                    return;
                } else {
                    System.out.println("Valid castling conditions were not met");
                }
            } else {
                // System.out.println("We're here and we're hustling");
                if (action > 77 || action < 0) {
                    System.out.println(
                            "You must enter a double digit number. The first digit is the piece's height, the second is the width");
                    // System.out.println("Hello");
                    preMove(player, x, y, gameboard);
                    return;
                }
                int pieceSelection = action;
                // System.out.println(player.getName() + " " + x + " " + y + " " + action);
                movePiece(player, x, y, gameboard, pieceSelection);
                return;
            }
            preMove(player, x, y, gameboard);
            return;
        } catch (Exception e) {
            System.out.println("You must enter a number dummy ");
            e.printStackTrace();
            preMove(player, x, y, gameboard);
            return;
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
        // System.out.println(action + " " + piece.getName() + " " + initial.getX());
        int endX = action / 10;
        int endY = action % 10;
        if (piece.isValidMove(x, y, endX, endY)) {
            if (Board.squares[endX][endY].hasPiece()) {
                if (player.hasPiece(Board.squares[endX][endY].getPiece())) {
                    System.out.println("Invalid choice. You already have a piece there!");
                    preMove(player, x, y, gameboard);
                    return;
                }
            }
            if (Status.check) {
                if (Status.defeatCheck(player, piece, gameboard, endX, endY)) {
                    System.out.println(player.getName() + "has moved out of check!");
                    Status.check = false;
                } else {
                    System.out.println("Invalid move. You must move out of check!");
                    preMove(player, x, y, gameboard);
                    return;
                }
            }
            Type type = piece.getType();
            // System.out.println(type + " whoopee ------------");
            String move = player.getName() + "'s " + type + " has moved from " + x + "" + y + " to " + endX + "" + endY;
            if (type == Type.PAWN) {
                if (endX == 0 || endX == 7) {
                    move += " and is promoted to a QUEEN";
                    piece = player.pawnPromotion(piece, endX, endY);
                }
            }
            /*
            ****** checks if a capture took place and if so, sets enemy piece to null ******
            */
            Player otherPlayer = getOtherTeam(player);
            if (Board.squares[endX][endY].hasPiece()) {
                Piece capturedPiece = Board.squares[endX][endY].getPiece();
                move = move + " and has captured " + otherPlayer.getName() + "'s " + capturedPiece.getType() + "!";
                // otherPlayer.team = otherPlayer.killPiece(capturedPiece);
                otherPlayer.killPiece(capturedPiece);
            }
            moves.add(move);
            System.out.println(move);
            //moves from old spot
            Board.squares[x][y].setPiece(null);
            ///moves to new spot
            gameboard.getSquare(endX, endY).setPiece(piece);
            //updates King's location if King moved
            if (piece.getType().equals(Type.KING)) {
                King king = (King) piece;
                king.setXY(endX, endY);
            }
            if (Status.didCheck(player, piece, gameboard, endX, endY)) {
                move += otherPlayer.getName() + " is now in check!";
                System.out.println(otherPlayer.getName() + ", you must move out of check.");
                Status.check = true;
            }
            return;
        } else {
            System.out.println("Invalid move");
            preMove(player, x, y, gameboard);
        }
    }

    public void run() {
        Board gameboard = Board.boardConstructor();
        player1 = getPlayer(1, true);
        player2 = getPlayer(2, false);
        System.out.println();
        System.out.println(
                "Select your piece by typing in a double digit number. The first digit is the vertical coordinate, the second digit is the horizontal like so-");
        gameboard.showDetailedBoard();
        System.out.println("You can type 888 into the console at any time to see this detailed board");
        while (Status.active) {
            // try {
            //     preSelect(player1, gameboard);
            // } catch (Throwable e) {
            //     e.printStackTrace();
            // }
            preSelect(player1, gameboard);
            savedBoards.add(gameboard.saveToMemento(gameboard));
            gameboard.showBoard();
            preSelect(player2, gameboard);
            savedBoards.add(gameboard.saveToMemento(gameboard));
            gameboard.showBoard();
        }

    }

    private Player getPlayer(int number, boolean isWhite) {
        System.out.println(String.format("Player %d, please enter your name", number));
        String name = scanner.nextLine();
        return Player.createPlayer(name, isWhite);
    }
}