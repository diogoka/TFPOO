package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class DeathStar extends StormTrooper {
    private Timer shotTimer;

    public DeathStar(int px, int py) {
        super(px, py, 2);
    }

    @Override
    public void start() {
        shotTimer = new Timer(0.3f, true);
        shotTimer.addHandler(loop -> {
            Game.getInstance().addChar(new Shot(getX()+16, getY()+getAltura(), 1, 0, 8, this, "#854747"));
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
            graphicsContext.setFill(Paint.valueOf("#666666"));
            graphicsContext.fillRect(getX(), getY(), getLargura(), getAltura());
            graphicsContext.setFont(Font.font(20));
            graphicsContext.fillText(getLifes() + "♥", getX(), getY() + -5);
    }
}
