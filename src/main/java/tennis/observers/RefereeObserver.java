package tennis.observers;

import tennis.states.GameState;

public interface RefereeObserver {

	void update(GameState gameState);

}
