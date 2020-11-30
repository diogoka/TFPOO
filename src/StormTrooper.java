package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class StormTrooper extends BasicElement {
    public StormTrooper(int px, int py, int lifes) {
        setPosX(px);
        setPosY(py);
        setLifes(lifes);
    }

    @Override
    public void start(){
        setDirH(1);
    }

    @Override
    public void Update(long currentTime, long deltaTime){
        countLifes();
        if (getY() + getAltura() > Params.GAME_HEIGHT) {
            Game.getInstance().onEnemyReachEnd(this);
        }
    }

    public void countLifes() {
        if (isColliding()){
            Character collidingChar = getCollidingChar();

            if ( collidingChar instanceof Canhao ||
                    (collidingChar instanceof Shot &&
                            ((Shot)collidingChar).getOwner() instanceof Canhao)) {
                setLifes( getLifes() -1 );
                System.out.println(getLifes());
                if (getLifes() == 0 ) {
                    System.out.println("teste");
                    deactivate();
                    Game.getInstance().onEnemyKilled(this);
                }
            }
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(Paint.valueOf("#00ff00"));
        graphicsContext.fillRect(getX(), getY(), getLargura(), getAltura());
    }
}
