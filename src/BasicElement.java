package src;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
 */
public abstract class BasicElement implements Character{
    private int posX, posY;
    private int largura, altura;
    private int lminH,lmaxH;
    private int lminV,lmaxV;
    private int speed;
    private boolean active;
    private boolean colidivel;
    private boolean colidiu;
    private Character colidindoChar;
    private boolean colidindo;
    private int direction_horizontal, direction_vertical;

    public BasicElement() {
        this(0, 0);
    }

    public BasicElement(int startX,int startY){
        posX = startX;
        posY = startY;
        largura = 32;
        altura = 32;
        direction_horizontal = 0;
        direction_vertical = 0;
        active = true;
        colidiu = false;
        colidindo = false;
        colidindoChar = null;
        colidivel = true;
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
    public int getAltura(){
        return(altura);
    }
    
    @Override
    public int getLargura(){
        return(largura);
    }

    @Override
    public void testaColisao(Character outro){
        // Monta pontos
        int p1x = this.getX();
        int p1y = this.getY();
        int p2x = p1x+this.getLargura();
        int p2y = p1y+this.getAltura();
        
        int op1x = outro.getX();
        int op1y = outro.getY();
        int op2x = op1x+outro.getLargura();
        int op2y = op1y+outro.getAltura();

        // Verifica colisão
        if (p1x < op2x && p2x > op1x && p1y < op2y && p2y > op1y){
            colidindo = true;
            colidindoChar = outro;
            colidiu = true;
            //outro.setColidiu();
        }
        /*// Verifica colisão
        if ( ((p1x <= op1x && p2x >= op1x) && (p1y <= op1y && p2y >= op1y)) ||
             ((p1x <= op2x && p2x >= op2x) && (p1y <= op2y && p2y >= op2y)) ){
            colidiu = true;
            //outro.setColidiu();
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
    
    public void setLargAlt(int l,int a){
        largura = l;
        altura = a;
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
    public void resetColidindo() {
        colidindo = false;
        colidindoChar = null;
    }

    @Override
    public boolean isColidindo() {
        return colidindo;
    }

    @Override
    public Character getColidindoChar() {
        return colidindoChar;
    }

    @Override
    public boolean isColidivel() {
        return colidivel;
    }

    @Override
    public void setColidivel(boolean colidivel) {
        this.colidivel = colidivel;
    }

    @Override
    public boolean jaColidiu(){
        return(colidiu);
    }
    
    @Override
    public void setColidiu(){
        colidiu = true;
    }
    
    @Override
    public  boolean isActive(){
        return(active);
    }
    
    @Override
    public abstract void start();    
        
    @Override
    public abstract void Update(long currentTime, long deltaTime);
        
    @Override
    public abstract void Draw(GraphicsContext graphicsContext);
}
