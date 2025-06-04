package breakout;


import java.awt.Color;
import java.awt.Font;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;

public class Menu {

    protected static final int CANVAS_WIDTH = 600;
    protected static final int CANVAS_HEIGHT = 800;


    private CanvasWindow canvas;
    private GraphicsText Title ;
    private GraphicsText TitleShade; 
    private Button startButton ;
    private Button quitButton ;

    public Menu(){

        Audio.themeGameMusic.play();

        canvas = new CanvasWindow("Starting Menu", CANVAS_WIDTH, CANVAS_HEIGHT);

        Title = new GraphicsText("BreakOut !", CANVAS_WIDTH/3.5, CANVAS_HEIGHT/4);
        TitleShade = new GraphicsText("BreakOut !", CANVAS_WIDTH/3.42, CANVAS_HEIGHT/3.95);

        Title.setFont(Font.SANS_SERIF,FontStyle.PLAIN,50);
        TitleShade.setFont(Font.SANS_SERIF,FontStyle.PLAIN,50);
        TitleShade.setFillColor(Color.WHITE);

        canvas.add(TitleShade);
        canvas.add(Title);
        canvas.setBackground(Color.GRAY);

        startButton = new Button("Start Game");
        startButton.setPosition(CANVAS_WIDTH/2.82, CANVAS_HEIGHT/3);
        quitButton = new Button("Quit Game");
        quitButton.setPosition(CANVAS_WIDTH/2.82, CANVAS_HEIGHT/2.5);

        canvas.add(startButton);
        canvas.add(quitButton);

    }

    public Button getQuitButton() {
        return quitButton;
    }
    public Button getStartButton() {
        return startButton;
    }
    public CanvasWindow getCanvas() {
        return canvas;
    }


}
