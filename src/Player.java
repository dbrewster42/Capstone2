public class Player {
    private String name;
    private Piece[] team;

    public Player(String playerName, Piece[] team) {
        name = playerName;
        team = this.team;

    }

    public String getName() {
        return name;
    }

    public Piece[] getPieces() {
        for (Piece i : team) {
            System.out.print(i.getName());
        }
        return this.team;
    }
}