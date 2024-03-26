package tennis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tennis.states.GameState;
import tennis.states.GameOverState;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ExtendWith(MockitoExtension.class)
public class AssessorTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    private GameState gameState;
    private TennisGame tennisGame;
    private Assessor assessor;

    @BeforeEach
    public void init() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
    }

    @Test
    public void should_assess_game_won(){
        //arrange
        Player winner = new Player("sarah", new Score(4,1));
        Player looser = new Player("bernard", new Score(2,1));
        tennisGame = new TennisGame(winner, looser);
        gameState = new GameOverState(winner, looser);
        assessor = new Assessor(tennisGame);

        //act
        assessor.update(gameState);

        //assert
        Assertions.assertEquals(2, winner.getScore().getNumberOfGamesWon());
        Assertions.assertEquals(1, looser.getScore().getNumberOfGamesWon());
        Assertions.assertEquals("Assessor: Game Winner sarah\r\n", outputStream.toString());
    }

    @Test
    public void should_assess_set_won(){
        //arrange
        Player sarahPlayer = new Player("sarah", new Score(0,6));
        Player bernardPlayer = new Player("bernard", new Score(0,4));
        tennisGame = new TennisGame(sarahPlayer, bernardPlayer);
        assessor = new Assessor(tennisGame);

        //act
        assessor.update(gameState);

        //assert
        Assertions.assertEquals("Assessor: Set Winner sarah\r\n", outputStream.toString());
    }

    @Test
    public void should_assess_tie_break(){
        //arrange
        Player sarahPlayer = new Player("sarah", new Score(0,6));
        Player bernardPlayer = new Player("bernard", new Score(0,6));
        tennisGame = new TennisGame(sarahPlayer, bernardPlayer);
        assessor = new Assessor(tennisGame);

        //act
        assessor.update(null);

        //assert
        String expectedOutput = "Assessor: TieBreak\r\n";
        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }
}
