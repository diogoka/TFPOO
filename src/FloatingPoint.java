package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class FloatingPoint extends BasicElement {

    private int points;
    private boolean isVisible;
    private Timer blinkTimer;
    private Timer autoDestroyTimer;

    public FloatingPoint(int px, int py, int points) {
        setPosX(px);
        setPosY(py);
        this.points = points;
        isVisible = true;
        blinkTimer = new Timer(0.1f, true);
        blinkTimer.addHandler(loop -> {
            isVisible = !isVisible;
        });

        autoDestroyTimer = new Timer(0.5f, false);
        autoDestroyTimer.addHandler(loop -> {
            deactivate();
        });
    }

    @Override
    public void start(){
        setDirH(0);
        setDirV(-1);
        setSpeed(2);
        setColidivel(false);
    }

    @Override
    public void Update(long currentTime, long deltaTime) {
        setPosX(getX() + getSpeed() * getDirH());
        setPosY(getY() + getSpeed() * getDirV());
        autoDestroyTimer.Update(deltaTime);
        blinkTimer.Update(deltaTime);
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        if (isVisible) {
            graphicsContext.setFill(Paint.valueOf("#ffffff"));
            graphicsContext.setFont(Font.font(20));
            graphicsContext.fillText("+ " + points, getX(), getY());
        }
    }
}
