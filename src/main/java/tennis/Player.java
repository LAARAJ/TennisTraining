package tennis;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Player {

    private String name;
    private Score score;

    public void incrementScore() {
    	this.score = new Score(this.score.getCurrentGameScore()+1, this.score.getNumberOfGamesWon());
    }
    
    public void incrementGamesWon() {
    	this.score = new Score(0, this.score.getNumberOfGamesWon()+1);
    }

    public void resetScore(){
        this.score = new Score(0, this.score.getNumberOfGamesWon());
    }

}
