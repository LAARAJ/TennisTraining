package tennis;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tennis.states.GameState;
import tennis.states.NormalState;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ExtendWith(MockitoExtension.class)
public class RefereeTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    private GameState gameState;
    private TennisGame tennisGame;
    private Referee referee;

    @BeforeEach
    public void init() {
        referee = new Referee();
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
    }


    @Test
    public void should_handler_normal_state_with_diff_scores(){
        //arrange
        Player sarahPlayer = new Player("sarah", new Score(1,0));
        Player bernardPlayer = new Player("bernard", new Score(0,0));
        tennisGame = new TennisGame(sarahPlayer, bernardPlayer);
        gameState = new NormalState(sarahPlayer, bernardPlayer, tennisGame);

        //act
        referee.update(gameState);

        //assert
        String expectedOutput = "Referee: 15, love\r\n";

        Assertions.assertEquals(expectedOutput , outputStream.toString());
    }


    @Test
    public void should_handler_normal_state_with_same_score(){
        //arrange
        Player sarahPlayer = new Player("sarah", new Score(2,0));
        Player bernardPlayer = new Player("bernard", new Score(2,0));
        tennisGame = new TennisGame(sarahPlayer, bernardPlayer);
        gameState = new NormalState(sarahPlayer, bernardPlayer, tennisGame);

        //act
        referee.update(gameState);

        //assert
        String expectedOutput = "Referee: 30, all\r\n";
        Assertions.assertEquals(expectedOutput , outputStream.toString());
    }

    @Test
    public void should_handler_deuce_state(){
        //arrange
        Player sarahPlayer = new Player("sarah", new Score(3,0));
        Player bernardPlayer = new Player("bernard", new Score(3,0));
        tennisGame = new TennisGame(sarahPlayer, bernardPlayer);
        gameState = new NormalState(sarahPlayer, bernardPlayer, tennisGame);

        //act
        referee.update(gameState);

        //assert
        String expectedOutput = "Referee: Deuce\r\n";
        Assertions.assertEquals(expectedOutput , outputStream.toString());
    }

    @Test
    public void should_handler_advantage_state(){
        //arrange
        Player sarahPlayer = new Player("sarah", new Score(4,0));
        Player bernardPlayer = new Player("bernard", new Score(3,0));
        tennisGame = new TennisGame(sarahPlayer, bernardPlayer);
        gameState = new NormalState(sarahPlayer, bernardPlayer, tennisGame);

        //act
        referee.update(gameState);

        //assert
        String expectedOutput = "Referee: Advantage Player sarah\r\n";
        Assertions.assertEquals(expectedOutput , outputStream.toString());
    }

    @Test
    public void should_handler_game_over_state(){
        //arrange
        Player sarahPlayer = new Player("sarah", new Score(4,0));
        Player bernardPlayer = new Player("bernard", new Score(2,0));
        tennisGame = new TennisGame(sarahPlayer, bernardPlayer);
        gameState = new NormalState(sarahPlayer, bernardPlayer, tennisGame);

        //act
        referee.update(gameState);

        //assert
        String expectedOutput = "Referee: Winner Player sarah\r\n";
        Assertions.assertEquals(expectedOutput , outputStream.toString());
    }

    @Test
    public void should_state_deuce_after_advantage_state(){
        //arrange
        Player sarahPlayer = new Player("sarah", new Score(2,0));
        Player bernardPlayer = new Player("bernard", new Score(2,0));
        tennisGame = new TennisGame(sarahPlayer, bernardPlayer);
        gameState = new NormalState(sarahPlayer, bernardPlayer, tennisGame);

        //act
        tennisGame.scorePoint(sarahPlayer);
        tennisGame.scorePoint(bernardPlayer);
        tennisGame.scorePoint(sarahPlayer);
        referee.update(gameState);

        //assert
        String expectedOutput_adv = "Referee: Advantage Player sarah\r\n";
        Assertions.assertEquals(expectedOutput_adv , outputStream.toString());

        //act
        tennisGame.scorePoint(bernardPlayer);
        referee.update(gameState);

        //assert
        String expectedOutput_deuce = "Referee: Deuce\r\n";
        Assertions.assertEquals(expectedOutput_adv + expectedOutput_deuce , outputStream.toString());
    }

    @Test
    public void should_state_winner_after_advantage_state(){
        //arrange
        Player sarahPlayer = new Player("sarah", new Score(2,0));
        Player bernardPlayer = new Player("bernard", new Score(2,0));
        tennisGame = new TennisGame(sarahPlayer, bernardPlayer);
        gameState = new NormalState(sarahPlayer, bernardPlayer, tennisGame);

        //act
        tennisGame.scorePoint(sarahPlayer);
        tennisGame.scorePoint(bernardPlayer);
        tennisGame.scorePoint(sarahPlayer);
        referee.update(gameState);

        //assert
        String expectedOutput_adv = "Referee: Advantage Player sarah\r\n";
        Assertions.assertEquals(expectedOutput_adv , outputStream.toString());

        //act
        tennisGame.scorePoint(sarahPlayer);
        referee.update(gameState);

        //assert
        String expectedOutput_deuce = "Referee: Winner Player sarah\r\n";
        Assertions.assertEquals(expectedOutput_adv + expectedOutput_deuce , outputStream.toString());
    }

    @Test
    public void should_reset_new_game_on_game_won(){
        //arrange
        Player sarahPlayer = new Player("sarah", new Score(4,0));
        Player bernardPlayer = new Player("bernard", new Score(2,0));
        tennisGame = new TennisGame(sarahPlayer, bernardPlayer);
        gameState = new NormalState(sarahPlayer, bernardPlayer, tennisGame);

        //act
        tennisGame.scorePoint(sarahPlayer);
        tennisGame.scorePoint(sarahPlayer);
        referee.update(gameState);

        //assert
        String expectedOutput = "Referee: Winner Player sarah\r\n";
        Assertions.assertEquals(expectedOutput , outputStream.toString());
    }
}
