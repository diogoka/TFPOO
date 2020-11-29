import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.LinkedList;

/**
 * Handles the game lifecycle and behavior
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Game {
    private static Game game = null;
    private Canhao canhao;
    private List<Character> activeChars;
    private int score = 0;

    private Game() {

    }

    public static Game getInstance(){
        if (game == null){
            game = new Game();
        }
        return(game);
    }

    public void addChar(Character c){
        activeChars.add(c);
        c.start();
    }

    public void eliminate(Character c){
        activeChars.remove(c);
    }

    public void onEnemyKilled() {
        score++;
        Manager.getInstance().setScore(score);
    }

    public void onPlayerDamage() {
        Manager.getInstance().setLifes(canhao.getLives());
    }

    public void Start() {
        // Repositório de personagens
        activeChars = new LinkedList();

        // Adiciona o canhao
        canhao = new Canhao();
        activeChars.add(canhao);

        for(int i=0; i<5; i++){
            activeChars.add(new Ball(100+(i*10),60+i*35));
        }
        for(int i=5; i<10; i++){
            activeChars.add(new DeathStar(100+(i*30),60+i*40));
        }

        for(Character c:activeChars){
            c.start();
        }
    }

    public void Update(long currentTime, long deltaTime) {
        if(canhao.getLives() == 0) {
            System.out.println("Game Over");
            //printar na tela o game over
        }

        for(int i=0;i<activeChars.size();i++) {
            Character este = activeChars.get(i);
            este.resetColliding();
        }
        for(int i=0;i<activeChars.size();i++){
            Character este = activeChars.get(i);
            for(int j =0; j<activeChars.size();j++){
                Character outro = activeChars.get(j);
                if ( este != outro){
                    este.testaColisao(outro);
                }
            }
        }

        for(int i=0;i<activeChars.size();i++){
            Character este = activeChars.get(i);
            este.Update();
        }
    }

    public void OnInput(KeyCode keyCode, boolean isPressed) {
        canhao.OnInput(keyCode, isPressed);
    }

    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Paint.valueOf("000000"));
        graphicsContext.fillRect(0, 0, Params.GAME_WIDTH, Params.GAME_HEIGHT);
        for(Character c:activeChars){
            c.Draw(graphicsContext);
        }
    }
}
