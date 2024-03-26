package tennis;

import lombok.ToString;
import tennis.observers.RefereeObserver;
import tennis.states.*;

@ToString
public class Referee implements RefereeObserver {

    @Override
    public void update(GameState gameState) {
        if (gameState instanceof NormalState) {
            handleNormalState((NormalState) gameState);

        } else if (gameState instanceof DeuceState) {
            handleDeuceState((DeuceState)gameState);

        } else if (gameState instanceof AdvantageState) {
            handleAdvantageState((AdvantageState) gameState);

        } else if (gameState instanceof GameOverState) {
            handleGameOverState((GameOverState) gameState);
        }
    }

    private void handleNormalState(NormalState state) {
        System.out.println("Referee: " + state.getScoreStatus());
    }

    private void handleDeuceState(DeuceState state) {
        System.out.println("Referee: "+state.getScoreStatus());
    }

    private void handleAdvantageState(AdvantageState state) {
        System.out.println("Referee: Advantage " + state.getAdvantagedPlayer().getName());
    }

    private void handleGameOverState(GameOverState state) {
        System.out.println("Referee: Winner Player " + state.getWinner().getName());
    }

}
