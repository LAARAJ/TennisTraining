package tennis;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tennis.factory.StateFactory;
import tennis.observers.AssessorObserver;
import tennis.observers.BoardObserver;
import tennis.observers.RefereeObserver;
import tennis.states.GameState;


@AllArgsConstructor
@Getter
@Setter
public class TennisGame {
	
	private Player player1;
	private Player player2;
	private GameState gameState;

	private final List<AssessorObserver> assessorObservers = new ArrayList<>();
	private final List<RefereeObserver> refereeObservers = new ArrayList<>();
	private final List<BoardObserver> boardObservers = new ArrayList<>();

	private boolean isSetWon;

	public TennisGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.gameState = StateFactory.getGameState(player1, player2, this);
	}

	public void scorePoint(Player player){
		if (isSetWon()) {
			System.out.println("Set finished");
			return;
		}
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

}
