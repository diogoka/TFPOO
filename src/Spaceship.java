package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class Spaceship extends Enemy {
    private Timer shotTimer;

    public Spaceship(int px, int py) {
        super(px, py);
    }

    @Override
    public void start() {
        shotTimer = new Timer(0.6f, true);
        shotTimer.addHandler(loop -> {
            Game.getInstance().addChar(new Shot(getX()+16, getY()+getAltura(), 1, 0, 5, this));
        });
        super.start();
    }

    @Override
    public void Update(long currentTime, long deltaTime) {
        super.Update(currentTime, deltaTime);
        setPosX(getX() + getDirH() * getSpeed());
        shotTimer.Update(deltaTime);
        if (getX() >= getLMaxH()){
            setPosX(getLMinH());
            setSpeed(Params.getInstance().nextInt(5)+1);
            setPosY(getY() + getAltura());
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
            graphicsContext.setFill(Paint.valueOf("#007700"));
            graphicsContext.fillRect(getX(), getY(), getLargura(), getAltura());
    }
}
