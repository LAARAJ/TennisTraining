package tennis;

import tennis.observers.BoardObserver;
import tennis.observers.RefereeObserver;

public class GameLaunch {

    public static void main(String[] args) {
        Player sarahPlayer = new Player("sarah", new Score(0,0));

        Player bernardPlayer = new Player("bernard", new Score(0,0));

        TennisGame game = new TennisGame(sarahPlayer, bernardPlayer);

        BoardObserver boardObserver = new Board(sarahPlayer, bernardPlayer);

        RefereeObserver refereeObserver = new Referee();

        Assessor assessorObserver = new Assessor(game);

        game.addObserver(assessorObserver);
        game.addObserver(refereeObserver);
        game.addObserver(boardObserver);

        //SCENARIO
        game.scorePoint(sarahPlayer);
        game.scorePoint(sarahPlayer);
        game.scorePoint(sarahPlayer);

        game.scorePoint(bernardPlayer);
        game.scorePoint(bernardPlayer);
        game.scorePoint(bernardPlayer);

        game.scorePoint(sarahPlayer);
        game.scorePoint(bernardPlayer);

        game.scorePoint(bernardPlayer);
        game.scorePoint(sarahPlayer);

        game.scorePoint(sarahPlayer);
        game.scorePoint(sarahPlayer);

        game.scorePoint(sarahPlayer);
        game.scorePoint(sarahPlayer);
        game.scorePoint(bernardPlayer);
    }
}
