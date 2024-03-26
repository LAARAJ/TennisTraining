package tennis.states;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tennis.Player;
import tennis.TennisGame;

@AllArgsConstructor
@Getter
public class AdvantageState implements GameState{

    private final Player advantagedPlayer;
    private final Player opponentPlayer;
    private TennisGame tennisGame;

    @Override
    public void scorePoint(Player player) {
        player.incrementScore();
        resetState();
    }

    @Override
    public String getScoreStatus() {
        return "Advantage Player " + advantagedPlayer.getName();
    }

    private void resetState() {
        GameState normalState = new NormalState(advantagedPlayer, opponentPlayer, tennisGame);
        this.tennisGame.changeGameState(normalState);
    }
}
