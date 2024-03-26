package tennis.states;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tennis.Player;
import tennis.TennisGame;

@AllArgsConstructor
@Getter
@Setter
public class NormalState implements GameState{

	private Player player1;
	private Player player2;
	private TennisGame tennisGame;

	@Override
	public void scorePoint(Player player) {
		player.incrementScore();
	}

	@Override
	public String getScoreStatus() {
		List<String> scoreNames = List.of("love", "15", "30", "40");
		int player1Score = player1.getScore().getCurrentGameScore();
		int player2Score = player2.getScore().getCurrentGameScore();

		if (player1Score == player2Score) {
			if(player1Score >= 3){
				switchToDeuceState();
			}else{
				return scoreNames.get(player1Score) + ", all";
			}
		}else if(Math.abs(player1Score - player2Score) == 1 && (player1Score >= 4 || player2Score >= 4)){
			switchToAdvantageState(player1Score, player2Score);
		}else if (Math.abs(player1Score - player2Score) >= 2 && (player1Score >= 4 || player2Score >= 4)){
			switchToGameOverState(player1Score, player2Score);
		}else{
			return scoreNames.get(player1Score) + ", " + scoreNames.get(player2Score);
		}

		return tennisGame.getCurrentState().getScoreStatus();
	}

	private void switchToDeuceState(){
		GameState deuceState = new DeuceState(player1, player2, tennisGame);
		this.tennisGame.changeGameState(deuceState);
	}

	private void switchToAdvantageState(int player1Score, int player2Score){
		Player winner = getCurrentWinner(player1Score, player2Score);
		Player looser = getCurrentLooser(player1Score, player2Score);
		GameState advantageState = new AdvantageState(winner, looser, tennisGame);
		this.tennisGame.changeGameState(advantageState);
	}

	private void switchToGameOverState(int player1Score, int player2Score){
		Player winner = getCurrentWinner(player1Score, player2Score);
		Player looser = getCurrentLooser(player1Score, player2Score);
		GameState gameOverState = new GameOverState(winner,looser);
		this.tennisGame.changeGameState(gameOverState);
	}

	private Player getCurrentWinner(int player1Score, int player2Score){
		return player1Score > player2Score ? player1 : player2;
	}

	private Player getCurrentLooser(int player1Score, int player2Score){
		return player2Score < player1Score ? player2 : player1;
	}

}
