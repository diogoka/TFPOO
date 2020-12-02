package src;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class TimerStormTrooper extends StormTrooper {
    private int time;
    private Timer timer;

    // se o tempo acabar adiciona novas estrelas da morte
    public TimerStormTrooper(int px, int py, int secondsAlive, int lifes) {
        super(px, py, lifes);
        timer = new Timer(1f, true);
        timer.addHandler(loop -> {
            time--;
            if (time == 0) {
                deactivate();
                for(int y=0; y < 2; y++){
                    for(int x=0; x < 2; x++){
                        Game.getInstance().addChar(new DarthVader(getX() + x * 35 - 30, getY() + y * 35 - 30, 1));
                    }
                }
            }
        });
        time = secondsAlive;
    }

    @Override
    public void Update(long currentTime, long deltaTime) {
        super.Update(currentTime, deltaTime);
        timer.Update(deltaTime);
        setPosX(getX() + getDirH() * getSpeed());
        if (getX() >= getLMaxH()){
            setPosX(getLMinH());
            setPosY(getY() + getAltura());
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
            graphicsContext.setFill(Paint.valueOf("#ff0000"));
            graphicsContext.fillRect(getX(), getY(), getLargura(), getAltura());

            graphicsContext.setFont(Font.font(20));
            graphicsContext.fillText(time + "s", getX(), getY());

            if(getLifes() > 1) {
                graphicsContext.setFont(Font.font(20));
                graphicsContext.fillText(getLifes() + "♥", getX(), getY() + -20);
            }

    }
}
