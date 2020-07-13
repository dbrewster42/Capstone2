class Move {
    private int x, y, endX, endY;
    public boolean capture = false;
    public boolean promoted = false;
    public boolean checking = false;
    public boolean castle;
    private String message;
    private Player player;
    private Piece piece, capturedPiece;

    public Move(Player player, Piece piece, int x, int y, int endX, int endY) {
        this(player, piece, x, y, endX, endY, false);
        message = makeMessage();
    }

    public Move(Player player, Piece piece, int x, int y, int endX, int endY, boolean castle) {
        this.player = player;
        this.piece = piece;
        this.x = x;
        this.y = y;
        this.endX = endX;
        this.endY = endY;
        this.castle = castle;
        if (y == 0) {
            message = player.getName() + " has performed a long side castle";
        } else {
            message = player.getName() + " has performed a short side castle";
        }

    }

    public void addPromoted() {
        promoted = true;
        message += " and is promoted to a QUEEN";
    }

    public void addCapture(Piece capturedPiece) {
        capture = true;
        this.capturedPiece = capturedPiece;
        message = message + " and has captured a " + capturedPiece.getType() + "!";
    }

    public void addCheck() {
        checking = true;
        Player otherPlayer = Game.getOtherTeam(player);
        message += ". " + player.getName() + " has put " + otherPlayer.getName() + " in check!";
    }

    public String makeMessage() {
        String message = player.getName() + "'s " + piece.getType() + " has moved from " + x + "" + y + " to " + endX
                + "" + endY;
        return message;
    }

    public String getMessage() {
        return message;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getEndX() {
        return this.endX;
    }

    public int getEndY() {
        return this.endY;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Piece getCapturedPiece() {
        return this.capturedPiece;
    }
    // /*
    // ************** Saves State for Option to undo****************
    // */
    // public Memento saveToMemento(Move move) {
    //     // System.out.println("Saving Board to Memento");
    //     return new Memento(move);
    // }

    // /*
    // ************** Restores State (undoes) ****************
    // */
    // public Board restoreFromMemento(Memento memento) {
    //     Board gameboard = memento.getSavedBoard();
    //     System.out.println("Board restored from Memento.");
    //     return gameboard;
    // }

    // /*
    // ************** Memento Design Patterns Saves Each Turn to enable an Undo ****************
    // */
    // public class Memento {
    //     private Board gameboard;

    //     public Memento(Board gameboard) {
    //         this.gameboard = gameboard;
    //     }

    //     public Board getSavedBoard() {
    //         return gameboard;
    //     }

    // }

}