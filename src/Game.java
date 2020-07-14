import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Game {
    private static Scanner scanner = new Scanner(System.in);
    static List<Move> moves = new ArrayList<Move>();
    // static Piece[] white = new Piece[16];
    // static Piece[] black = new Piece[16];
    static Player player1, player2;
    static boolean isFirst = true;
    static List<Board.Memento> savedBoards = new ArrayList<Board.Memento>();

    /*
    ************** Get other Team ****************
    */
    public static Player getOtherTeam(Player player) {
        // if (player == player1) {
        if (player.isWhite()) {
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
        for (Move i : moves) {
            System.out.println(count + ". " + i.getMessage());
            count++;
        }
        System.out.println(" ");
    }

    /*
    ************** Undo side effects in tandem with Memento ****************
    */
    public static void undo(int i) {
        Move lastMove = moves.get(moves.size() - i);
        int x = lastMove.getEndX();
        int y = lastMove.getEndY();
        int prevX = lastMove.getX();
        int prevY = lastMove.getY();
        Board.squares[prevX][prevY].setPiece(lastMove.getPiece());
        if (lastMove.capture) {
            Piece piece = lastMove.getCapturedPiece();
            Board.squares[x][y].setPiece(piece);
            if (piece.getColor().equals("white")) {
                player1.restorePiece(piece);
            } else {
                player2.restorePiece(piece);
            }
        } else {
            Board.squares[x][y].setPiece(null);
        }

    }

    /*
    ************** Breaking up the Select Piece function in the hope of making it less buggy ****************
    */
    public static void preSelect(Player player, Board gameboard) {
        if (moves.size() > 20) {
            int whiteCount = player1.pieceCount();
            int blackCount = player2.pieceCount();
            // System.out.println(whiteCount + " " + blackCount);
            if (whiteCount == 1 && blackCount == 1) {
                Status.draw = true;
                Status.active = false;

            }
        }
        System.out.println();
        System.out.println(player.getName() + ", it is your turn.");
        System.out.println("Enter 999 to display a list of all the remaining pieces");
        System.out.println("Enter 888 to display the detailed board");
        System.out.println("Enter 777 to display a list of all moves");
        System.out.println("Enter 444 to undo a move");
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
                    Status.forfeit = true;
                    Status.active = false;
                    return;
                }
                preSelect(player, gameboard);
                return;
            } else if (action == 444) {
                System.out.println("Are you sure that you wish to undo a move?");
                System.out.println("Type yes or hit any other key to continue with the game");
                String answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    int mementoSize = savedBoards.size();
                    int travel = mementoSize - 2;
                    // System.out.println(mementoSize + "travel: " + travel);
                    gameboard = gameboard.restoreFromMemento(savedBoards.get(travel));
                    undo(1);
                    undo(2);
                    gameboard.showDetailedBoard();
                }
                preSelect(player, gameboard);
                return;
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
        System.out.println("Enter 111 to perform a passant if you have selected a Pawn and the conditions are valid");
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
                if (SpecialMoves.isValidCastle(player, x, y, gameboard)) {
                    // System.out.println("CASTLE!");
                    SpecialMoves.doCastle(player, x, y, gameboard, moves);
                    return;
                }
            } else if (action == 111) {
                if (SpecialMoves.isValidPassant(player, x, y, gameboard, moves)) {
                    // System.out.println("Passant!");
                    SpecialMoves.doPassant(player, x, y, gameboard, moves);
                    return;
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
        Square initial = Board.squares[x][y];
        Piece piece = initial.getPiece();
        int action = pieceSelection;
        // System.out.println(action + " " + piece.getName() + " " + initial.getX());
        int endX = action / 10;
        int endY = action % 10;
        //// Validates that the specific piece can move in manner intended
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
                    System.out.println(player.getName() + " has moved out of check!");
                    Status.check = false;
                } else {
                    System.out.println("Invalid move. You must move out of check!");
                    preMove(player, x, y, gameboard);
                    return;
                }
            }
            Type type = piece.getType();
            Move move = new Move(player, piece, x, y, endX, endY);
            moves.add(move);
            if (type == Type.PAWN) {
                if (endX == 0 || endX == 7) {
                    piece = player.pawnPromotion(piece, endX, endY);
                    move.addPromoted();
                }
            }
            /*
            ****** checks if a capture took place and if so, sets enemy piece to null ******
            */
            Player otherPlayer = getOtherTeam(player);
            if (Board.squares[endX][endY].hasPiece()) {
                Piece capturedPiece = Board.squares[endX][endY].getPiece();
                move.addCapture(capturedPiece);
                otherPlayer.killPiece(capturedPiece);
            }
            //moves from old spot ///moves to new spot
            Board.squares[x][y].setPiece(null);
            gameboard.getSquare(endX, endY).setPiece(piece);
            //updates King's location if King moved
            if (piece.getType().equals(Type.KING)) {
                King king = (King) piece;
                king.setXY(endX, endY);
            }
            ///checks to see if the move has put the opposing King in check
            if (Status.didCheck(player, piece, gameboard, endX, endY)) {
                move.addCheck();
                Status.check = true;
                if (Status.isCheckMate(otherPlayer, gameboard)) {
                    Status.checkMate = true;
                    Status.active = false;
                }
            }
            System.out.println(move.getMessage());
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
        Player player = player1;
        while (Status.active) {
            preSelect(player, gameboard);
            gameboard.set(gameboard);
            savedBoards.add(gameboard.saveToMemento());
            gameboard.showBoard();
            player = getOtherTeam(player);
        }
        if (Status.checkMate == true) {
            System.out.println("");
            System.out.println("CHECKMATE!!!!!!!!!!!!");
            player = getOtherTeam(player);
            System.out.println(player.getName() + " wins! Congratulations");
            System.out.println("");
        } else if (Status.draw == true) {
            System.out.println("");
            System.out.println("Both teams are out of pieces and unable to checkmate. It is a Draw! Good game "
                    + player1.getName() + " and " + player2.getName());
            System.out.println("");
        } else if (Status.forfeit == true) {
            Player otherPlayer = getOtherTeam(player);
            System.out.println("");
            System.out.println(otherPlayer.getName() + " has forfeited! Game Over!");
            System.out.println(player.getName() + " wins! Congratulations on your victory!");
            System.out.println("");

        }

    }

    private Player getPlayer(int number, boolean isWhite) {
        System.out.println(String.format("Player %d, please enter your name", number));
        String name = scanner.nextLine();
        return Player.createPlayer(name, isWhite);
    }
}