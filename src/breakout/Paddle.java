package breakout;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class Paddle {

    private Rectangle paddleShape;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private double speed;

    private double X;
    private double Y;
    private double maxX;
    private double width ;
    private double height ;
    


    public static final double PADDLE_SPEED = 10;
    private static final double buffer = 0.1;


    private Rectangle paddle;

    public Paddle(
        double x,
        double y,
        double width,
        double height,
        double maxX,
        double initialSpeed){

            this.paddle = new Rectangle(x, y, width, height);

            this.paddle.setFillColor(Color.black);

            this.speed = initialSpeed ;

            this.maxX = maxX;
            this.width =width;
            this.height = height;

    }

    public void Upadte(double dt) {
        double dx = 0;
        
        if (movingLeft) dx -= speed;
        if (movingRight) dx += speed;
        
        double newX = paddle.getX() + dx * dt;

        paddle.setX(newX);

        if (newX < 10) {
            newX = 10 + buffer;
        } else if (newX + this.width > maxX ) {
            newX = maxX - this.width;
        }
        
        // Set the new position
        paddle.setX(newX);
       

    }

    public void setMovingLeft(boolean moving) {
         movingLeft = moving; 
        }

    public void setMovingRight(boolean moving) { 
        movingRight = moving; 
    }
    
    public Rectangle getShape() { 
        return paddleShape; 
     }

    public double getX() {
        return X;
    }
    public double getY() {
        return Y;
    }
    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }
    
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(paddle);
    }

    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(paddle);
    }
    public Rectangle getPaddle() {
        return paddle;
    }
    public void setX(double x) {
        X = x;
    }
    


}
