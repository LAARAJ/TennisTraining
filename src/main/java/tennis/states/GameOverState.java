package tennis.states;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tennis.Player;

@AllArgsConstructor
@Getter
public class GameOverState implements GameState{

    private Player winner;
    private Player looser;

    @Override
    public void scorePoint(Player player) {}

    @Override
    public String getScoreStatus() {
        return "Winner Player " + winner.getName();
    }

}
