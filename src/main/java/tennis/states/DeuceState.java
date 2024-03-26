package tennis.states;


import lombok.AllArgsConstructor;
import lombok.Getter;
import tennis.Player;
import tennis.TennisGame;

@AllArgsConstructor
@Getter
public class DeuceState implements GameState {

    private final Player player1;
    private final Player player2;
    private final TennisGame tennisGame;

    @Override
    public void scorePoint(Player player) {
        player.incrementScore();
        resetState();
    }

    @Override
    public String getScoreStatus() {
        return "Deuce";
    }

    private void resetState() {
        GameState normalState = new NormalState(player1, player2, tennisGame);
        this.tennisGame.changeGameState(normalState);
    }

}
