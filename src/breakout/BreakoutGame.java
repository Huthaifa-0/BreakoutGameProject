package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The game of Breakout.
 */
public class BreakoutGame {
    
    protected static final int CANVAS_WIDTH = 600;
    protected static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private boolean isPaused;

    private Paddle paddle;
    private GraphicsText loosingText = new GraphicsText();
    private GraphicsText winninText = new GraphicsText();
    // private GraphicsText Lives = new GraphicsText();

    Random random = new Random();
    List<Double> angles = Arrays.asList(30.0, 45.0, 50.0, 60.0, 65.0, 75.0, 80.0, 115.0, 135.0, 165.0);

    public double getRandomAngle() {
        return angles.get(random.nextInt(angles.size()));
    }
    public BreakoutGame() {


        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        
        // canvas.add(Lives);
        // Lives.setPosition(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
    
        Ball ball = new Ball(
            CANVAS_WIDTH / 2, 
            CANVAS_HEIGHT /2, 
            200,  
            getRandomAngle(),  
            CANVAS_WIDTH,
            CANVAS_HEIGHT + 10
        );
        paddle = new Paddle(
            CANVAS_WIDTH/2 - 50,  // x position 
            CANVAS_HEIGHT - 40,   // y position 
            100, 15, // width, height
            CANVAS_WIDTH - 10,         // maxX boundary
            400      // speed
        );

        BrickManager brickManager = new BrickManager(CANVAS_WIDTH);

        ball.addToCanvas(canvas);
        paddle.addToCanvas(canvas);
        brickManager.addAllToCanvas(canvas);
        setupKeyListeners(paddle);
        gameLoop(ball, brickManager);
        

    }


    private void setupKeyListeners(Paddle paddle) {
        // Key press events
        canvas.onKeyDown(event -> {
            switch (event.getKey()) {
                case LEFT_ARROW:
                case A:
                    paddle.setMovingLeft(true);
                    break;
                case RIGHT_ARROW:
                case D:
                    paddle.setMovingRight(true);
                    break;
                default:
                    break;
            }
        });

        // Key release events
        canvas.onKeyUp(event -> {
            switch (event.getKey()) {
                case LEFT_ARROW:
                case A:
                    paddle.setMovingLeft(false);
                    break;
                case RIGHT_ARROW:
                case D:
                    paddle.setMovingRight(false);
                    break;
                default:
                    break;
            }
        });
    }
    
    private void gameLoop(Ball ball, BrickManager brickManager){
        canvas.animate(() -> {
            

            
            if (!isPaused) {

                if (!ball.updatePosition(0.025)){
                    restart(ball, paddle, canvas);
                    ball.health --;
                    System.out.println(ball.health);
                    if (ball.health <= 0){
                        canvas.removeAll();
                        loosingText.setText("You lost !! , Try Again!");
                        loosingText.setPosition(CANVAS_WIDTH/2 - 20, CANVAS_HEIGHT/2);
                        loosingText.setFontSize(20);
                        canvas.add(loosingText);
                        
                        Audio.endGameAudio.play();

                        Audio.endGameAudio.stop();

                    }
                    
                    if (isPaused){
                        canvas.pause(1000);
                        isPaused=false;
                    }
                    
                }
                if(BrickManager.BRICK_COUNT <= 0){
                    canvas.removeAll();
                    winninText.setText("Congrats !! , You won");
                    canvas.add(winninText);  // Use winninText, not loosingText
                    winninText.setPosition(CANVAS_WIDTH/2 - 100, CANVAS_HEIGHT/2);
                    winninText.setFontSize(20);
                    isPaused = true;  // Stop the game
                    }

                if (ball.detectPaddleCollision(paddle,canvas)){ball.handlePaddleCollisionResponse(paddle);}
                ball.handleBrickCollisions(brickManager.getBricks(),canvas);
                paddle.Upadte(0.025);
            } 
            
        });

    }
    public void restart(Ball ball ,Paddle paddle , CanvasWindow canvas) {
        isPaused = true; 
        ball.setCenterX(BreakoutGame.CANVAS_WIDTH / 2);
        ball.setCenterY(BreakoutGame.CANVAS_HEIGHT /2);
        // ball.setDefaultVelocity();
        ball.sethitBottom(false);
    


    
    }

    public static void main(String[] args){
        Menu menu = new Menu();

        menu.getQuitButton().onClick(()-> menu.getCanvas().closeWindow());

        menu.getStartButton().onClick(()-> {
            Audio.themeGameMusic.stop();
            menu.getCanvas().closeWindow();
            new BreakoutGame();
        });

        
    }
}
