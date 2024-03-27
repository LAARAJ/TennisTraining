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

            GameState normalState = new NormalState(tennisGame.getPlayer1(), tennisGame.getPlayer2(), tennisGame);
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
        Player winner = getPlayerWinner();
        Player looser = getPlayerLooser();

        if((winner.getScore().getNumberOfGamesWon() == 6 && looser.getScore().getNumberOfGamesWon() <= 4)
                || (winner.getScore().getNumberOfGamesWon() == 7 && looser.getScore().getNumberOfGamesWon() == 5)
                || (winner.getScore().getNumberOfGamesWon() == 7 && looser.getScore().getNumberOfGamesWon() == 6)) {
            tennisGame.setSetWon(true);
            System.out.println("Assessor: Set Winner " + winner.getName());
        }
    }

    private Player getPlayerWinner(){
        Player player1 = tennisGame.getPlayer1();
        Player player2 = tennisGame.getPlayer2();
        return player1.getScore().getNumberOfGamesWon() > player2.getScore().getNumberOfGamesWon() ? player1 : player2;

    }

    private Player getPlayerLooser(){
        Player player1 = tennisGame.getPlayer1();
        Player player2 = tennisGame.getPlayer2();
        return player1.getScore().getNumberOfGamesWon() < player2.getScore().getNumberOfGamesWon() ? player1 : player2;
    }

    private void resetScores(){
        tennisGame.getPlayer1().resetScore();
        tennisGame.getPlayer2().resetScore();
    }
}
