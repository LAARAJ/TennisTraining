package tennis.states;

import tennis.Player;

public interface GameState {
	
    void scorePoint(Player player);

    String getScoreStatus();

}
