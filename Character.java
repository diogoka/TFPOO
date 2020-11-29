import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
 */
public interface Character {
    int getX();
    int getY();
    int getHeight();
    int getWidth();

    void testaColisao(Character c);
    void resetColliding();
    boolean isColliding();
    boolean isCollided();
    void setCollided();

    void start();
    boolean isActive();
    void Update();
    void Draw(GraphicsContext graphicsContext);
}
