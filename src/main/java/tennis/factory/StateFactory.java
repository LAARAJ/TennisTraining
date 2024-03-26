package tennis.factory;

import tennis.states.AdvantageState;
import tennis.states.DeuceState;
import tennis.states.GameState;
import tennis.Player;
import tennis.TennisGame;
import tennis.states.NormalState;

public class StateFactory {

    public static GameState getGameState(Player player1, Player player2, TennisGame tennisGame){
        int player1Score = player1.getScore().getCurrentGameScore();
        int player2Score = player2.getScore().getCurrentGameScore();

        if(player1Score == player2Score && player2Score >= 3){
            return new DeuceState(player1, player2, tennisGame);
        }

        if(player1Score >= 4 || player2Score >= 4){
            if (Math.abs(player1Score - player2Score) == 1) {
                return new AdvantageState(player1, player2, tennisGame);
            }
        }

        return new NormalState(player1, player2, tennisGame);
    }

}
