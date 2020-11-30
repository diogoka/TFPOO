package src;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Handles window initialization and primary game setup
 * @author Bernardo Copstein, Rafael Copstein
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLE);
        stage.setResizable(false);
        stage.setWidth(Params.WINDOW_WIDTH);
        stage.setHeight(Params.WINDOW_HEIGHT);
 
        Group root = new Group();
        Scene scene = new Scene( root );
        stage.setScene( scene );
        try {
            scene.getStylesheets().add("src/styles.css");
        }
        catch (Exception e) {
            System.out.println("Nao foi possivel carregar o css " + e.getMessage());
        }

        Canvas canvas = new Canvas(Params.GAME_WIDTH, Params.GAME_HEIGHT);

        UIManager uiManager = UIManager.getInstance();
        uiManager.setCanvas(canvas);
        uiManager.setRoot(root);

        // Setup Game object
        Game.getInstance().Start();

        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });
        
        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });
        
        // Register Game Loop       
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime)
            {
                long deltaTime = currentNanoTime - lastNanoTime;

                Game.getInstance().Update(currentNanoTime, deltaTime);
                gc.clearRect(0, 0, Params.GAME_WIDTH, Params.GAME_HEIGHT);
                Game.getInstance().Draw(gc);
                UIManager.getInstance().Update(currentNanoTime, deltaTime);

                lastNanoTime = currentNanoTime;
            }

        }.start();

        // Show window
        stage.show();
    }
    
    public static void main(String args[]) {
        launch();
    }
}
