package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class StormTrooper extends BasicElement {
    public StormTrooper(int px, int py) {
        setPosX(px);
        setPosY(py);
    }

    @Override
    public void start(){
        setDirH(1);
    }

    @Override
    public void Update(long currentTime, long deltaTime){
        if (isColliding()){
            Character collidingChar = getCollidingChar();

            if ( collidingChar instanceof Canhao ||
                (collidingChar instanceof Shot &&
                ((Shot)collidingChar).getOwner() instanceof Canhao)) {
                deactivate();
                Game.getInstance().onEnemyKilled(this);
            }
        }
        if (getY() + getAltura() > Params.GAME_HEIGHT) {
            Game.getInstance().onEnemyReachEnd(this);
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(Paint.valueOf("#00ff00"));
        graphicsContext.fillRect(getX(), getY(), getLargura(), getAltura());
    }
}
