package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Particle extends BasicElement {

    private Timer autoDestroyTimer;

    public Particle(int px, int py, int dirV, int dirH, int speed) {
        setPosX(px);
        setPosY(py);
        setDirV(dirV);
        setDirH(dirH);
        setSpeed(speed);
        autoDestroyTimer = new Timer(0.5f, false);
        autoDestroyTimer.addHandler(loop -> {
            deactivate();
        });
    }

    @Override
    public void start(){
        setLargAlt(2, 2);
        setColidivel(false);
    }

    @Override
    public void Update(long currentTime, long deltaTime) {
        setPosX(getX() + getSpeed() * getDirH());
        setPosY(getY() + getSpeed() * getDirV());
        autoDestroyTimer.Update(deltaTime);
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Paint.valueOf("#ffffff"));
        graphicsContext.fillRect(getX(), getY(), getLargura(), getAltura());
    }
}
