package tennis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tennis.observers.BoardObserver;

@AllArgsConstructor
@Getter
public class Board implements BoardObserver {
    private Player player1;
    private Player player2;

    @Override
    public void updateScore(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        display(player1, player2);
    }

    public void display(Player player1, Player player2) {
        System.out.println("Player\tCurrent Score\tGames Won");
        System.out.println("---------------------------------------------");
        displayPlayerScore(player1);
        displayPlayerScore(player2);
        System.out.println("---------------------------------------------\n");

    }

    private void displayPlayerScore(Player player) {
        Score playerScore = player.getScore();
        System.out.println(player.getName() + "\t\t" + playerScore.getCurrentGameScore() + "\t\t\t" + playerScore.getNumberOfGamesWon());
    }
}
