public class Game {
    static Player player1, player2;
    static boolean isFirst = true;

    /*
    ************** Get other Team ****************
    */
    public static Player getOtherTeam(Player player) {
        if (player.isWhite()) {
            return player2;
        } else {
            return player1;
        }
    }

    /*
    ************** Undo side effects in tandem with Memento ****************
    */
    public static void undo(int i) {
        Move lastMove = Move.moves.get(Move.moves.size() - i);
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
                InputReader.preMove(player, x, y, gameboard);
                return;
            } else {
                System.out.println("Invalid choice. That is not your piece at " + x + ", " + y);
                InputReader.preSelect(player, gameboard);
            }

        } else {
            System.out.println("There is no piece at " + x + ", " + y + ". Please try again");
            InputReader.preSelect(player, gameboard);
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
                    InputReader.preMove(player, x, y, gameboard);
                    return;
                }
            }
            //// Must move out of check if in check
            if (Status.check) {
                if (Status.defeatCheck(player, piece, gameboard, endX, endY)) {
                    System.out.println(player.getName() + " has moved out of check!");
                    Status.check = false;
                } else {
                    System.out.println("Invalid move. You must move out of check!");
                    InputReader.preMove(player, x, y, gameboard);
                    return;
                }
            }
            Type type = piece.getType();
            Move move = new Move(player, piece, x, y, endX, endY);
            ///checks if Pawn Promotion is applicable
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
            InputReader.preMove(player, x, y, gameboard);
        }
    }

    public void run() {
        Board gameboard = Board.boardConstructor();
        player1 = InputReader.getPlayer(1, true);
        player2 = InputReader.getPlayer(2, false);
        System.out.println();
        System.out.println(
                "Select your piece by typing in a double digit number. The first digit is the vertical coordinate, the second digit is the horizontal like so-");
        gameboard.showDetailedBoard();
        System.out.println("You can type 888 into the console at any time to see this detailed board");
        Player player = player1;
        while (Status.active) {
            InputReader.preSelect(player, gameboard);
            gameboard.showBoard();
            player = getOtherTeam(player);
        }
        System.out.println("");
        if (Status.checkMate == true) {
            System.out.println("CHECKMATE!!!!!!!!!!!!");
            player = getOtherTeam(player);
            System.out.println(player.getName() + " wins! Congratulations");
        } else if (Status.draw == true) {
            System.out.println("Both teams are out of pieces and unable to checkmate. It is a Draw! Good game "
                    + player1.getName() + " and " + player2.getName());
        } else if (Status.forfeit == true) {
            Player otherPlayer = getOtherTeam(player);
            System.out.println(otherPlayer.getName() + " has forfeited! Game Over!");
            System.out.println(player.getName() + " wins! Congratulations on your victory!");

        } else {
            System.out.println(
                    "We have stalemate! It is a tie! Good game " + player1.getName() + " and " + player2.getName());
        }
        System.out.println("");

    }

}