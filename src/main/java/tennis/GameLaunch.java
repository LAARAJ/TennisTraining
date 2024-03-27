package tennis;

import tennis.observers.BoardObserver;
import tennis.observers.RefereeObserver;

public class GameLaunch {

    public static void main(String[] args) {
        Player sarahPlayer = new Player("sarah", new Score(0,6));

        Player bernardPlayer = new Player("bernard", new Score(0,5));

        TennisGame tennisGame = new TennisGame(sarahPlayer, bernardPlayer);


        BoardObserver boardObserver = new Board(sarahPlayer, bernardPlayer);
        RefereeObserver refereeObserver = new Referee();
        Assessor assessorObserver = new Assessor(tennisGame);

        tennisGame.addObserver(assessorObserver);
        tennisGame.addObserver(refereeObserver);
        tennisGame.addObserver(boardObserver);

        //SCENARIO

        tennisGame.scorePoint(sarahPlayer);
        tennisGame.scorePoint(sarahPlayer);
        tennisGame.scorePoint(sarahPlayer);

        tennisGame.scorePoint(bernardPlayer);
        tennisGame.scorePoint(bernardPlayer);
        tennisGame.scorePoint(bernardPlayer);   // Deuce

        tennisGame.scorePoint(sarahPlayer);     // Advantage
        tennisGame.scorePoint(bernardPlayer);   // Deuce

        tennisGame.scorePoint(bernardPlayer);   // Advantage
        tennisGame.scorePoint(sarahPlayer);     // Deuce

        tennisGame.scorePoint(bernardPlayer);   // Advantage
        tennisGame.scorePoint(bernardPlayer);   // Winner => Tiebreak 6 vs 6

        // Extra game
        tennisGame.scorePoint(sarahPlayer);
        tennisGame.scorePoint(sarahPlayer);
        tennisGame.scorePoint(sarahPlayer);
        tennisGame.scorePoint(sarahPlayer);     // 7 vs 6 => Set finished
    }
}
