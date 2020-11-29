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
    private int fireDelay = 100;
    private long lastFiredTime = System.currentTimeMillis();
    public Canhao(int px,int py){
        super(px,py);
    }    
    
    @Override
    public void start() {
        setLimH(20,Params.WINDOW_WIDTH-20);
        setLimV(Params.WINDOW_HEIGHT-100,Params.WINDOW_HEIGHT);
    }
    
    @Override
    public void Update() {
        if (pressingLeft) setPosX(getX() + -getSpeed());
        if (pressingRight) setPosX(getX() + getSpeed());
        if (firing && System.currentTimeMillis() - lastFiredTime > fireDelay) {
            Game.getInstance().addChar(new Shot(getX() + 16, getY() - 32, -1, 0, 15));
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
        graphicsContext.fillRect(getX()+8, getY()-16, 16, 48);        
    }   
}
