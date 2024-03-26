package tennis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tennis.observers.AssessorObserver;
import tennis.states.GameOverState;
import tennis.states.GameState;
import tennis.states.NormalState;

@AllArgsConstructor
@Getter
public class Assessor implements AssessorObserver {

    private TennisGame tennisGame;

    @Override
    public void update(GameState gameState) {
        assessGameWon(gameState);
        assessTieBreak();
        assessSetWon();
    }

    private void assessGameWon(GameState gameState) {
        if (gameState instanceof GameOverState) {
            Player winner = ((GameOverState) gameState).getWinner();
            winner.incrementGamesWon();
            resetScores();

            GameState normalState = new NormalState(tennisGame.getPlayer1(),tennisGame.getPlayer2(), tennisGame);
            tennisGame.changeGameState(normalState);
            System.out.println("Assessor: Game Winner " + winner.getName());
        }
    }

    private void assessTieBreak() {
        int player1GamesWon = tennisGame.getPlayer1().getScore().getNumberOfGamesWon();
        int player2GamesWon = tennisGame.getPlayer2().getScore().getNumberOfGamesWon();

        if (player1GamesWon == 6 && player2GamesWon == 6) {
            System.out.println("Assessor: TieBreak");
        }
    }

    private void assessSetWon() {
        int player1GamesWon = tennisGame.getPlayer1().getScore().getNumberOfGamesWon();
        int player2GamesWon = tennisGame.getPlayer2().getScore().getNumberOfGamesWon();

        if (player1GamesWon == 6 && player2GamesWon <= 4) {
            System.out.println("Assessor: Set Winner " + tennisGame.getPlayer1().getName());
        }else if (player2GamesWon == 6 && player1GamesWon <= 4) {
            System.out.println("Assessor: Set Winner " + tennisGame.getPlayer2().getName());
        }
    }

 //   private boolean isExtraGame(int player1GamesWon, int player2GamesWon) {
 //       return (player1GamesWon == 5 || player1GamesWon == 6) && (player2GamesWon == 5 || player2GamesWon == 6);
 //   }

    private void resetScores(){
        tennisGame.getPlayer1().resetScore();
        tennisGame.getPlayer2().resetScore();
    }
}
