package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class DeathStar extends StormTrooper {
    private Timer shotTimer;

    public DeathStar(int px, int py) {
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
            graphicsContext.setFill(Paint.valueOf("#808080"));
            graphicsContext.fillRect(getX(), getY(), getLargura(), getAltura());
    }
}
