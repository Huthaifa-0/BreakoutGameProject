package breakout;


import java.awt.Color;
import java.util.List;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;

public class Ball {

    private static final double buffer = 0.1 ;

    public static final double BALL_RADIUS = 10;

    private double centerX;
    private double centerY;
    private double xVelocity;
    private double yVelocity;
    private double maxX;
    private double maxY;



    int health = 3;

    private Ellipse ball;
    protected boolean hitBottom;

    public Ball(
            double centerX,
            double centerY,
            double initialSpeed,
            double initialAngle,
            double maxX,
            double maxY) {

        this.ball = new Ellipse(centerX, centerY, BALL_RADIUS,BALL_RADIUS);
        this.ball.setFillColor(Color.black);
        
        this.centerX = centerX;
        this.centerY = centerY;
        this.maxX = maxX;
        this.maxY = maxY;
        
        double initialAngleRadians = Math.toRadians(initialAngle);

        this.xVelocity = initialSpeed * Math.cos(initialAngleRadians);

        this.yVelocity =  initialSpeed * Math.sin(initialAngleRadians);

    }
   
    private String checkWallCollision(){

        boolean hitLeft = centerX - BALL_RADIUS <= 0;
        boolean hitRight = centerX + BALL_RADIUS >= maxX;
        boolean hitTop = centerY - BALL_RADIUS <= 0;
        boolean hitBottom = centerY + BALL_RADIUS >= maxY;
        
        if (hitLeft) return "left";
        else if (hitRight) return "right";
        else if (hitTop) return "top";
        else if (hitBottom) return "bottom";
        else return "none";
    }
    private void bounceOffWall(String collision){
        switch (collision) {
            case "left":
                centerX = BALL_RADIUS + buffer; 

                xVelocity = Math.abs(xVelocity); // bounce right
                break;
            case "right":
                centerX = maxX - BALL_RADIUS - buffer; 
         
                xVelocity = - Math.abs(xVelocity); // bounce left
                break;
            case "top":
                centerY = BALL_RADIUS + buffer; 
              
                yVelocity = Math.abs(yVelocity); // bounce down 
                break;
            case "bottom" :
                // centerY = maxY - BALL_RADIUS - buffer; 
                yVelocity = -Math.abs(yVelocity); // bounce up 
                // health -- ;
                // System.out.println(health);
                hitBottom = true;
                
        }
    }
    public Rectangle getBallHitbox(){

        double diameter = BALL_RADIUS * 2;

    return new Rectangle(
        centerX - BALL_RADIUS,  // Adjust for center-to-corner
        centerY - BALL_RADIUS, 
        diameter, 
        diameter
    );
    }

    public boolean updatePosition(double dt) {

        String collision = checkWallCollision();

        this.centerX += (this.xVelocity * dt);
        this.centerY += (this.yVelocity * dt);
    
        boolean xWithinBounds = maxX > centerX && centerX > 0;
        boolean yWithinBounds = maxY > centerY && centerY > 0;
    

        if (xWithinBounds && yWithinBounds) {
            ball.setCenter(centerX, centerY);
            return true;
        } 
        else if (!"none".equals(collision) && !"bottom".equals(collision)){
              bounceOffWall(collision);
              ball.setCenter(centerX, centerY);
              return true;
            }
        else{ 
            return false;
        }
    }
    private void calculateBrickCollision(CanvasWindow canvas , Brick brick) {
        GraphicsObject topObject = canvas.getElementAt(centerX, centerY - BALL_RADIUS);
        GraphicsObject rightObject = canvas.getElementAt(centerX + BALL_RADIUS, centerY);
        GraphicsObject bottomObject = canvas.getElementAt(centerX, centerY + BALL_RADIUS);
        GraphicsObject leftObject = canvas.getElementAt(centerX - BALL_RADIUS, centerY);
        
        // Determine which side collided and respond accordingly
        if (topObject instanceof Rectangle ) {
            // yVelocity += 1.01 * yVelocity;
            yVelocity = Math.abs(yVelocity); // Bounce down
            // Handle brick destruction
            brick.destroy(canvas);
        }
        else if (bottomObject instanceof Rectangle) {
            // yVelocity += 1.01 * yVelocity;

            yVelocity = -Math.abs(yVelocity); // Bounce up
            // Handle brick destruction
            brick.destroy(canvas);

        }
        else if (rightObject instanceof Rectangle) {
            // yVelocity += 1.01 * yVelocity;
            xVelocity = -Math.abs(xVelocity); // Bounce up
            // Handle brick destruction
            brick.destroy(canvas);

        }
        else if (leftObject instanceof Rectangle) {
            // yVelocity += 1.01 * yVelocity;
            xVelocity = -Math.abs(xVelocity); // Bounce up
            // Handle brick destruction
            brick.destroy(canvas);

        }
            }
    
    public static boolean isOverlapping(Rectangle ballHitBox,Rectangle brick){

        return 
           ballHitBox.getX() < brick.getX() + brick.getWidth() &&
           ballHitBox.getX() + ballHitBox.getWidth() > brick.getX() &&
           ballHitBox.getY() < brick.getY() + brick.getHeight() &&
           ballHitBox.getY() + ballHitBox.getHeight() > brick.getY();
    }
    

    public void handleBrickCollisions(List<Brick> bricks,CanvasWindow canvas) {
        Rectangle ballHitbox = getBallHitbox();
        
        for (Brick brick : bricks) {

            Rectangle brickRect = brick.getShape();

            if (!brick.isAlive()) continue;

            else if (isOverlapping(ballHitbox, brickRect)) {
                calculateBrickCollision(canvas,brick);
                
                Audio.destroyEffect.stop();
                Audio.destroyEffect.play();
                
                // Add one to the Score
                // Score.playerScore++; 

            }


        }
    }
    
    
    public boolean detectPaddleCollision(Paddle paddle, CanvasWindow canvas) {
        // Check the bottom center of the ball
        GraphicsObject objectBelow = canvas.getElementAt(centerX, centerY + BALL_RADIUS);
        
        // check bottom left and bottom right for better detection
        GraphicsObject objectBottomLeft = canvas.getElementAt(centerX - BALL_RADIUS * 0.7, centerY + BALL_RADIUS * 0.7);
        GraphicsObject objectBottomRight = canvas.getElementAt(centerX + BALL_RADIUS * 0.7, centerY + BALL_RADIUS * 0.7);
        
        // Return true if any of these points touch the paddle
        return (objectBelow == paddle.getPaddle() || 
                objectBottomLeft == paddle.getPaddle() || 
                objectBottomRight == paddle.getPaddle());
    }
    public void handlePaddleCollisionResponse(Paddle paddle) {
        GraphicsObject paddleObj = paddle.getPaddle();
        if (paddleObj == null) {
            return; // Safety check
        }
        
        //  Reposition the ball to prevent sticking
        centerY = paddleObj.getY() - BALL_RADIUS - buffer;
        ball.setCenter(centerX, centerY);
        
        //  Calculate bounce angle based on where ball hit the paddle
        double paddleX = paddleObj.getX();
        double paddleWidth = paddleObj.getWidth();
        
        // Calculate relative hit position (0 = left edge, 1 = right edge)
        double hitPosition = (centerX - paddleX) / paddleWidth;
        
        // Limit the hit position to avoid extreme angles
        hitPosition = Math.max(0.1, Math.min(0.9, hitPosition));
        
        //  Calculate new velocity
        // Maintain overall speed but change direction
        double speed = Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
        speed = Math.max(5.0, speed); // Ensure minimum speed
        
        // Map hit position to angle (-60 to +60 degrees)
        double angleInDegrees = (hitPosition - 0.5) * 120;
        double angleInRadians = Math.toRadians(angleInDegrees);
        
        // Set new velocities
        xVelocity = speed * Math.sin(angleInRadians);
        yVelocity = -speed * Math.cos(angleInRadians); // Negative for upward motion
        
        // System.out.println("Ball bounced off paddle at position: " + hitPosition);
    }

    
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(ball);
    }

    
    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(ball);
    }
    public double getCenterX() {
        return centerX;
    }
    public double getCenterY() {
        return centerY;
    }
    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }
    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }
    public boolean gethitBottom(){
        return hitBottom;
    }
    public void sethitBottom(boolean value){
        this.hitBottom = value;
    }

    
}
