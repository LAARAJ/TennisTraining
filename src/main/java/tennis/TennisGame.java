package tennis;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import tennis.factory.StateFactory;
import tennis.observers.AssessorObserver;
import tennis.observers.BoardObserver;
import tennis.observers.RefereeObserver;
import tennis.states.GameState;


@AllArgsConstructor
@Getter
@Builder
public class TennisGame {
	
	private Player player1;
	private Player player2;
	private GameState gameState;


	private final List<AssessorObserver> assessorObservers = new ArrayList<>();
	private final List<RefereeObserver> refereeObservers = new ArrayList<>();
	private final List<BoardObserver> boardObservers = new ArrayList<>();

	public TennisGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.gameState = StateFactory.getGameState(player1, player2, this);
	}

	public void scorePoint(Player player){
		gameState.scorePoint(player);
		notifyReferee();
		notifyAssessor();
		notifyBoard();
	}

	public void addObserver(RefereeObserver observer) {
		refereeObservers.add(observer);
	}

	public void addObserver(BoardObserver observer) {
		boardObservers.add(observer);
	}

	public void addObserver(AssessorObserver observer) {
		assessorObservers.add(observer);
	}

	private void notifyReferee(){
		for(RefereeObserver obs: refereeObservers){
			obs.update(gameState);
		}
	}

	private void notifyAssessor(){
		for(AssessorObserver obs: assessorObservers){
			obs.update(gameState);
		}
	}

	private void notifyBoard() {
		for (BoardObserver observer : boardObservers) {
			observer.updateScore(player1, player2);
		}
	}

	public void changeGameState(GameState gameState){
		this.gameState = gameState;
	}

	public GameState getCurrentState(){
		return gameState;
	}

/**	private void checkSetEnd() {
		if (player1.getGamesWon() >= 6 && player1.getGamesWon() - player2.getGamesWon() >= 2) {
			// Player 1 wins the set
			// Reset game scores for the next set
			player1.resetGamesWon();
			player2.resetGamesWon();
		} else if (player2.getGamesWon() >= 6 && player2.getGamesWon() - player1.getGamesWon() >= 2) {
			// Player 2 wins the set
			// Reset game scores for the next set
			player1.resetGamesWon();
			player2.resetGamesWon();
		}
	}**/

}
