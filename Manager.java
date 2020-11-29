import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Manager {
    private static Manager instance;

    public static Manager getInstance() {
        if (instance == null) instance = new Manager();
        return instance;
    }

    private BorderPane borderPane;
    private BorderPane header;
    private Text scoreTxt;
    private Text lifesTxt;

    private Manager() {
        borderPane = new BorderPane();


        header = new BorderPane();
        header.setPadding(new Insets(0, 30, 0, 30));
        scoreTxt = new Text(0, 10, "Score: 0");
        scoreTxt.setFont(Font.font(30));
        lifesTxt = new Text(0, 10, "Lifes: 0");
        lifesTxt.setFont(Font.font(30));
        header.setLeft(scoreTxt);
        header.setRight(lifesTxt);
        borderPane.setTop(header);
    }

    public void setScore(int score) {
        scoreTxt.setText("Score: " + score);
    }

    public void setLifes(int lifes) {
        lifesTxt.setText("Lifes: " + lifes);
    }

    public void setRoot(Group root) {
        root.getChildren().add(borderPane);
    }

    public void setCanvas(Canvas canvas) {
        borderPane.setCenter(canvas);
    }
}
