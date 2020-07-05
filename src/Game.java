import java.util.Scanner;

public class Game {
    private Board gameboard;
    // private Player player1;
    // private Player player2;
    private static Scanner scanner = new Scanner(System.in);
    static Piece[] white = new Piece[16];
    static Piece[] black = new Piece[16];

    public static void createPlayers() {
        System.out.println("Player1, please enter your name");
        String name = scanner.nextLine();
        Player player1 = new Player(name, white);
        System.out.println("It's nice to meet you " + name);
        System.out.println("Player2, please enter your name");
        String name2 = scanner.nextLine();
        Player player2 = new Player(name2, black);
        System.out.println("I have heard of you " + name2
                + ", they say you do not treat your electronics with care. I hope you lose, jerk.");

    }

    public static void createPieces() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                // System.out.println(Board.squares[i][j].printPiece());
                white[count] = Board.squares[i][j].getPiece();
                count++;
            }
        }
        // for (Piece i : white) {
        //     System.out.print(i.getName() + ", ");
        // }
        count = 0;
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                black[count] = Board.squares[i][j].getPiece();
                count++;
            }
        }
        // return white;
    }

    public static void getPieces(Piece[] team) {
        for (Piece i : team) {
            System.out.print(i.getName() + ", ");
        }
        System.out.print("");
    }

    public static void main(String[] args) {
        // createPieces("black");
        Board gameboard = new Board();
        createPieces();
        boolean active = true;
        createPlayers();
        getPieces(black);
        // while (active) {
        //     createPlayers();

        // }

    }
}