package tennis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BoardTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Board board;

    @Mock
    private Player player1;

    @Mock
    private Player player2;

    @BeforeEach
    public void init() {
        board = new Board(player1,player2);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void should_show_dashboard_correctly() {
        //arrange
        Score player1Score = new Score(2, 2);
        Score player2Score = new Score(1, 1);
        when(player1.getScore()).thenReturn(player1Score);
        when(player1.getName()).thenReturn("Sarah");
        when(player2.getScore()).thenReturn(player2Score);
        when(player2.getName()).thenReturn("Bernard");

        //act
        board.updateScore(player1,player2);

        //verify
        String expectedOutput = "Player\tCurrent Score\tGames Won\r\n" +
                "---------------------------------------------\r\n" +
                "Sarah\t\t30\t\t\t2\r\n" +
                "Bernard\t\t15\t\t\t1\r\n" +
                "---------------------------------------------\n\r\n";

        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
