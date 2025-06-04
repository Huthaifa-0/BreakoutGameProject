package breakout;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.CanvasWindow;


public class BrickManager {
    private List<Brick> bricks;
    private static final int ROWS = 8;
    private static final int COLS = 10;
    private static final double BRICK_WIDTH = 50;
    private static final double BRICK_HEIGHT = 20;
    private static final double BRICK_PADDING = 4;
    private static final double BRICK_TOP_OFFSET = 40;
    public static  double BRICK_COUNT = ROWS * COLS;
    
    public BrickManager(double canvasWidth) {
        bricks = new ArrayList<>();
        createBrickLayout(canvasWidth);
    }
    
    private void createBrickLayout(double canvasWidth) {
        double startX = (canvasWidth - (COLS * (BRICK_WIDTH + BRICK_PADDING))) / 2;
        
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                // Color color = getColorForRow(row);
                double x = startX + col * (BRICK_WIDTH + BRICK_PADDING);
                double y = BRICK_TOP_OFFSET + row * (BRICK_HEIGHT + BRICK_PADDING);
                
                bricks.add(new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.black));
            }
        }
    }
    
    // private Color getColorForRow(int row) {
    //     switch (row) {
    //         case 0: return Color.RED;
    //         case 1: return Color.ORANGE;
    //         case 2: return Color.YELLOW;
    //         case 3: return Color.GREEN;
    //         case 4: return Color.BLUE;
    //         default: return Color.CYAN;
    //     }
    // }
    
    public void addAllToCanvas(CanvasWindow canvas) {
        for (Brick brick : bricks) {
            brick.addToCanvas(canvas);
        }
    }
    
    public List<Brick> getBricks() {
        return bricks;
    }
    public static void decrementBrickCount() {
        BRICK_COUNT--;
    }
}