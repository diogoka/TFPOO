package src;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.List;

public class GroupStormTrooper extends StormTrooper {
    public GroupStormTrooper(int px, int py, int lifes) {
        super(px, py, lifes);
    }

    @Override
    public void start() {
        setSpeed(1);
        super.start();
    }

    @Override
    public void Update(long currentTime, long deltaTime) {
        super.Update(currentTime, deltaTime);
        List<GroupStormTrooper> ges = Game.getInstance().getChars(GroupStormTrooper.class);
        if (ges.size() > 0) {
            if (ges.get(0) == this) {
                Rectangle2D rect = Utils.getRectOfCharacterCollection(ges);
                if (rect != null) {
                    if (rect.getMaxX() > Params.GAME_WIDTH) {
                        for (GroupStormTrooper ge : ges) {
                            ge.setDirH(-1);
                            ge.setPosY(ge.getY() + ge.getAltura());
                        }
                    } else if (rect.getMinX() < 0) {
                        for (GroupStormTrooper ge : ges) {
                            ge.setDirH(1);
                            ge.setPosY(ge.getY() + ge.getAltura());
                        }
                    }
                }
            }
        }
        setPosX(getX() + getSpeed() * getDirH());
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
            graphicsContext.setFill(Paint.valueOf("#007777"));
            graphicsContext.fillRect(getX(), getY(), getLargura(), getAltura());

        if(getLifes() > 1) {
            graphicsContext.setFont(Font.font(20));
            graphicsContext.fillText(getLifes() + "♥", getX(), getY() + -5);
        }
    }
}
