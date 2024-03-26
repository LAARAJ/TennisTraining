package tennis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class Score {
	private int currentGameScore;
    private int numberOfGamesWon;
}
