public class Player {
    private String name;
    private Piece[] team;

    public Player(String playerName, Piece[] team) {
        name = playerName;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public Piece[] getTeam() {
        return this.team;
    }

    public Piece[] getPieces() {
        for (Piece i : team) {
            System.out.print(i.getName());
        }
        return this.team;
    }
}