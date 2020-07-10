public class King extends Piece {

    Type type;

    protected King(String color, int x, int y) {
        // protected King(String color) {
        super(color);
        type = Type.KING;
        name = "KING";
    }

    /*
    **********Returns Name ************
    */
    @Override
    public String getName() {
        return this.name;
    }

    /*
    **********Returns Team ************
    */
    @Override
    public String getColor() {
        return this.color;
    }

    /*
    **********Returns Type ************
    */
    @Override
    public Type getType() {
        return this.type;
    }

    /*
    **********For cycling through all pieces to prevent King from moving into check or out of checkmate ************
    */
    @Override
    public boolean canCheck() {
        return false;
    }

    /*
    **********King can move 1 spot in any direction ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        if (Math.abs(endX - x) < 2 && Math.abs(endY - y) < 2) {
            return true;
        }
        return false;
    }/////////////////must prevent moving into check  

}