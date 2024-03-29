package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Handles the game lifecycle and behavior
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Game {
    private static Game game = null;
    private ArrayList<Score> list =  new ArrayList<>();
    private Canhao canhao;
    private Timer particleSpawner;
    private List<Character> activeChars;
    private int score = 0;
    private boolean died = false;
    private long lastTimeDied = 0;
    private int wave = 0;
    private int dificulty = 0;
    private boolean paused = false;
    
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

    private void onAllWaveKilled() {
        System.out.println("New wave " + wave);
        spawnWave();
    }

    public void onEnemyKilled(StormTrooper stormTrooper) {
        score++;
        if (getChars(StormTrooper.class).size() == 0) {
            onAllWaveKilled();
        }
    }

    public void onEnemyReachEnd(StormTrooper stormTrooper) {
        if (died) return;
        eliminate(canhao);
        onDie();
    }

    public void onPlayerDamage() {
        if (died) return;
        if (canhao.getLives() == 0) {
            onDie();
        }
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    private void onDie() {
        died = true;
        lastTimeDied = System.currentTimeMillis();
        canhao.deactivate();

        //salva score do jogador
        list.add(new Score(getScore()));
        Manager.getInstance().setScores(list);
        
        Manager.getInstance().setGameOverVisible(true);
    }

    public void Start() {

        // Repositório de personagens
        activeChars = new LinkedList();

        particleSpawner = new Timer(0.2f, true);
        particleSpawner.addHandler(loop -> {
            for (int i = 0; i < 5; i++) {
                addChar(new Particle((int)Math.floor(Math.random() * Params.GAME_WIDTH), (int)Math.floor(Math.random() * Params.GAME_HEIGHT), 0, 1, 5));
            }
        });

        // Inicia o jogo
        resetGame();
    }

    public <T> List<T> getChars(Class<T> clazz) {
        return activeChars.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toList());
    }

    public int getScore() {
        return score;
    }

    public int getLifes() {
        if (canhao == null) return 0;
        return canhao.getLives();
    }

    private void spawnWave() {
        List<StormTrooper> enemies;

        if (dificulty == 0) {
            enemies = WavesEasy.getWaveStorm(wave);
        } else if (dificulty == 1) {
            enemies = WavesNormal.getWaveStorm(wave);
        } else {
             enemies = WavesHard.getWaveStorm(wave);
        }

        for (StormTrooper stormTrooper : enemies) {
            addChar(stormTrooper);
        }
        wave++;
    }

    public void changeDificulty(Integer dif) {
        dificulty = dif;
        resetGame();
    }

    public void resetGame() {
        activeChars.clear();
        score = 0;
        wave = 0;

        died = false;

        // Adiciona o canhao
        canhao = new Canhao();
        addChar(canhao);

        spawnWave();
    }

    public void Update(long currentTime, long deltaTime) {
        if (paused) return;

        particleSpawner.Update(deltaTime);
        for(int i=0;i<activeChars.size();i++) {
            Character este = activeChars.get(i);
            este.resetColidindo();
        }
        for(int i=0;i<activeChars.size();i++){
            Character este = activeChars.get(i);
            if (!este.isColidivel()) continue;
            for(int j =0; j<activeChars.size();j++){
                Character outro = activeChars.get(j);
                if (!outro.isColidivel()) continue;
                if ( este != outro){
                    este.testaColisao(outro);
                }
            }
        }

        for(int i=0;i<activeChars.size();i++){
            Character este = activeChars.get(i);
            este.Update(currentTime, deltaTime);
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
