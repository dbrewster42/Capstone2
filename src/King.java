public class King extends Piece {
    private Type type;
    private String color;
    private String name;
    private int x;
    private int y;

    protected King(String color, int x, int y) {
        // protected King(String color) {
        this.color = color;
        this.x = x;
        this.y = y;
        type = Type.KING;
        name = "KING";
    }

    /*
    **********Returns X ************
    */
    // @Override
    public int getX() {
        return this.x;
    }

    /*
    **********Returns Y ************
    */
    // @Override
    public int getY() {
        return this.y;
    }

    /*
    **********Sets X and Y ************
    */
    // @Override
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
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
    public boolean canMakeMove(int x, int y, int kingX, int kingY) {
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