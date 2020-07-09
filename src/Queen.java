public class Queen extends Piece {
    Type type;

    protected Queen(String color) {
        super(color);
        type = Type.QUEEN;
        if (color.equals("white"))
            name = "wQUE";
        else {
            name = "QUEb";
        }
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
    **********Queen can move in any 1 direction infinitely if not blocked, but only 1 direction  ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        System.out.println("MY QUEEN!");
        int condition1 = Math.abs(endX - x);
        int condition2 = Math.abs(endY - y);
        int checkX, checkY, betweenX, betweenY;
        if (condition1 == 0) {
            System.out.println("Travelling horizontally");
            int count = endY - y;
            if (count > 0) {
                checkY = 1;
            } else {
                checkY = -1;
            }
            betweenY = y;
            while (count != 1 || count != -1) {
                betweenY = betweenY + checkY;
                System.out.println("Checking Square " + x + betweenY + ". Count- " + count);
                if (Board.squares[x][betweenY].hasPiece()) {
                    return false;
                }
                count--;
            }
            return true;
            /////////////////nnnnnnnnnnnnneeds refactor IFFF Roook works
        } else if (condition2 == 0) {
            System.out.println("Travelling vertically");
            if (endX - x > 0) {
                checkX = 1;
            } else {
                checkX = -1;
            }
            betweenX = x;
            while (condition1 > 0) {
                betweenY = betweenY + checkY;
                System.out.println("Checking Square " + x + betweenY + ". Count- " + condition2);
                if (Board.squares[x][betweenY].hasPiece()) {
                    return false;
                }
                condition2--;
            }
            return true;

        } else if (condition1 == condition2) {
            System.out.println("Travelling diagonally");

        } else
            return false;
        ////must check to see if blocked

        return false;
    }
}
