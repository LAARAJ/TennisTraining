package tennis.observers;

import tennis.Player;

public interface BoardObserver {

    void updateScore(Player player1, Player player2);

}
