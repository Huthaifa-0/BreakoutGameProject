package breakout;

public class Score {
    
    private int playerScore = 0 ;

    public Score(){
        playerScore = 0;
    }
    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void checkWinningScore(){
        if (playerScore == BrickManager.BRICK_COUNT){
            //Exit to the menu and display "Congrats you Won!!" 
            // with an option to start the game again
            //DisplayEndGameAnimation();
        }
    }

}
