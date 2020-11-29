import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
 */
public abstract class BasicElement implements Character{
    private int posX, posY;
    private int width, height;
    private int lminH,lmaxH;
    private int lminV,lmaxV;
    private int speed;
    private boolean active;
    private boolean collided;
    private boolean colliding;
    private int direction_horizontal, direction_vertical;

    public BasicElement() {
        this(0, 0);
    }

    public BasicElement(int startX,int startY){
        posX = startX;
        posY = startY;
        width = 32;
        height = 32;
        direction_horizontal = 0;
        direction_vertical = 0;
        active = true;
        collided = false;
        speed = 2;
        lminH = 0;
        lmaxH = Params.GAME_WIDTH;
        lminV = 0;
        lmaxV = Params.GAME_HEIGHT;
    }

    @Override
    public int getX(){
        return(posX);
    }

    @Override
    public int getY(){
        return(posY);
    }

    @Override
    public int getHeight(){
        return(height);
    }

    @Override
    public int getWidth(){
        return(width);
    }

    @Override
    public void testaColisao(Character outro){
        // Monta pontos
        int p1x = this.getX();
        int p1y = this.getY();
        int p2x = p1x+this.getWidth();
        int p2y = p1y+this.getHeight();

        int op1x = outro.getX();
        int op1y = outro.getY();
        int op2x = op1x+outro.getWidth();
        int op2y = op1y+outro.getHeight();

        // Verifica colisão
        if (p1x < op2x && p2x > op1x && p1y < op2y && p2y > op1y){
            colliding = true;
            collided = true;
            //outro.setCollided();
        }
        /*// Verifica colisão
        if ( ((p1x <= op1x && p2x >= op1x) && (p1y <= op1y && p2y >= op1y)) ||
             ((p1x <= op2x && p2x >= op2x) && (p1y <= op2y && p2y >= op2y)) ){
            collided = true;
            //outro.setCollided();
        }*/
    }

    public int getDirH(){
        return(direction_horizontal);
    }

    public int getDirV(){
        return(direction_vertical);
    }

    public int getLMinH(){
        return(lminH);
    }

    public int getLMaxH(){
        return(lmaxH);
    }

    public int getLMinV(){
        return(lminV);
    }

    public int getLMaxV(){
        return(lmaxV);
    }

    public int getSpeed(){
        return(speed);
    }

    public void setPosX(int p){
        posX = p;
    }

    public void setPosY(int p){
        posY = p;
    }

    public void setDimensions(int l,int a){
        width = l;
        height = a;
    }

    public void setDirH(int dirH){
        direction_horizontal = dirH;
    }

    public void setDirV(int dirV){
        direction_vertical = dirV;
    }

    public void setLimH(int min,int max){
        lminH = min;
        lmaxH = max;
    }

    public void setLimV(int min,int max){
        lminV = min;
        lmaxV = max;
    }

    public void setSpeed(int s){
        speed = s;
    }

    public void deactivate(){
        active = false;
        Game.getInstance().eliminate(this);
    }

    @Override
    public void resetColliding() {
        colliding = false;
    }

    @Override
    public boolean isColliding() {
        return colliding;
    }

    @Override
    public boolean isCollided(){
        return(collided);
    }

    @Override
    public void setCollided(){
        collided = true;
    }

    @Override
    public  boolean isActive(){
        return(active);
    }

    @Override
    public abstract void start();

    @Override
    public abstract void Update();

    @Override
    public abstract void Draw(GraphicsContext graphicsContext);
}
