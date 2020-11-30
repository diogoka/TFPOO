package src;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
 */
public interface Character {
    int getX();
    int getY();
    int getAltura();
    int getLargura();
    
    void testaColisao(Character c);
    void resetColidindo();
    void setColidivel(boolean colidivel);
    boolean isColidivel();
    boolean isColliding();
    Character getCollidingChar();
    boolean IsCollided();
    void setCollided();
    
    void start();
    boolean isActive();
    void Update(long currentTime, long deltaTime);
    void Draw(GraphicsContext graphicsContext);
}
