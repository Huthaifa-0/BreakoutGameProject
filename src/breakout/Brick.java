package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;
public class Brick extends GraphicsGroup{

    // private Color color;
    // private double x;
    // private double y;
    // private double height;
    // private double width;

    private Rectangle brick;

    private boolean isAlive;


    
    public Brick(double x, double y, double width, double height, Color color) {
        // this.x = x;
        // this.y = y;
        // this.width = width;
        // this.height = height;
        this.brick = new Rectangle(x, y, width, height);
        brick.setFillColor(color);
        this.isAlive = true;
    }


    // public double getHeight() {
    //     return height;
    // }
    // public double getWidth() {
    //     return width;
    // }
    // public double getX() {
    //     return x;
    // }
    // public double getY() {
    //     return y;
    // }
    public boolean isAlive() {
        return isAlive;
    }
    
    public void destroy(CanvasWindow canvas) {
        isAlive = false;
        this.removeFromCanvas(canvas);
        BrickManager.decrementBrickCount();
    }
    
    public void addToCanvas(CanvasWindow canvas) {
        if (isAlive) {
            canvas.add(brick);
        }
    }
    
    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(brick);
    }
    
    public Rectangle getShape() {
        return brick;
    }

}

