import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

/**
 * Represents the game Gun
 * @author Bernardo Copstein, Rafael Copstein
 */
public class Canhao extends BasicElement implements KeyboardCtrl{
    private boolean pressingLeft = false;
    private boolean pressingRight = false;
    private boolean firing = false;
    private int lives = 3;
    private int fireDelay = 100;
    private long lastFiredTime = System.currentTimeMillis();

    @Override
    public void start() {
        setDimensions(32, 48);
        setSpeed(10);
        setPosY(Params.GAME_HEIGHT-getHeight());
        setLimV(Params.GAME_HEIGHT-getHeight(),Params.GAME_HEIGHT);
    }

    private void move(int xDelta, int yDelta) {
        setPosX(Math.min(Math.max(getLMinH(), getX() + xDelta), getLMaxH() - getWidth()));
        setPosY(Math.min(Math.max(getLMinV(), getY() + yDelta), getLMaxV() - getHeight()));
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    @Override
    public void Update() {
        if(isColliding()) {
            setLives(lives-1);
            Game.getInstance().onPlayerDamage();
        }
        if (pressingLeft) move(-getSpeed(), 0);
        if (pressingRight) move(getSpeed(), 0);

        if (firing && System.currentTimeMillis() - lastFiredTime > fireDelay) {
            Game.getInstance().addChar(new Shot(getX() + 16, getY() - 36, -1, 0, 15));
            lastFiredTime = System.currentTimeMillis();
        }
    }

    @Override
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        switch (keyCode) {
            case LEFT:
                pressingLeft = isPressed;
                break;
            case RIGHT:
                pressingRight = isPressed;
                break;
            case SPACE:
                firing = isPressed;
                break;
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Paint.valueOf("#FF0000"));
        graphicsContext.fillRect(getX(), getY()+16, 32, 32);
        graphicsContext.fillRect(getX()+8, getY() -40, 16, 16);
    }
}
