package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Shot extends BasicElement{
    private Character owner;
    private Timer autoDestroyTimer;
    private String shotColor;

    public Shot(int px,int py, int dirV, int dirH, int speed, Character owner, String shotColor){
        super(px,py);

        autoDestroyTimer = new Timer(5f, false);
        autoDestroyTimer.addHandler(loop -> {
            deactivate();
        });
        setDirV(dirV);
        setDirH(dirH);
        setSpeed(speed);
        setLargAlt(3, 10);
        this.owner = owner;
        this.shotColor = shotColor;
    }

    public Character getOwner() {
        return owner;
    }

    @Override
    public void start(){ }
            
    @Override
    public void testaColisao(Character outro){
        if (outro instanceof Shot){
            return;
        }

        super.testaColisao(outro);
    }
                
    @Override
    public void Update(long currentTime, long deltaTime){
        autoDestroyTimer.Update(deltaTime);
        if (IsCollided()) {
            deactivate();
        }
        setPosY(getY() + getDirV() * getSpeed());
        if (getY() <= getLMinV()){
            deactivate();
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(Paint.valueOf(shotColor));
        graphicsContext.fillRect(getX(), getY(), getLargura(), getAltura());
    }
}

