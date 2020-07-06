public class Move {
    //maybe
    private Player player;
    public int x, y, endX, endY;
    public boolean capture = false;
    Type type;

    // public Move(int x, int y, int endX, int endY, boolean capture) {
    //     this(x, y, false);
    // }

    public Move(Player player, Type type, int x, int y, int endX, int endY) {
        this.player = player;
        this.type = type;
        this.x = x;
        this.y = y;
        this.endX = endX;
        this.endY = endY;
        if (Board.squares[endX][endY].hasPiece())
            capture = true;
    }
    // public void doesCapture(){
    //     capture = true;
    //     if (player.getTeam() == white){

    //     }
    // }
    // public boolean isCastlingMove() 
    // { 
    //     return this.castlingMove == true; 
    // } 

    // public void setCastlingMove(boolean castlingMove) 
    // { 
    //     this.castlingMove = castlingMove; 
    // } 
}