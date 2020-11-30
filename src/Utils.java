package src;

import javafx.geometry.Rectangle2D;

import java.util.Collection;

public final class Utils {
    public static <T extends Character> Rectangle2D getRectOfCharacterCollection(Collection<T> gos) {
        if (gos.size() == 0) return null;

        Character first = gos.iterator().next();
        int x1 = first.getX();
        int x2 = first.getX() + first.getLargura();
        int y1 = first.getY();
        int y2 = first.getY() + first.getAltura();

        for (Character go : gos) {
            if (go.getX() < x1) x1 = go.getX();
            if (go.getX() + go.getLargura() > x2) x2 = go.getX() + go.getLargura();
            if (go.getY() < y1) y1 = go.getY();
            if (go.getY() + go.getAltura() > y2) y2 = go.getY() + go.getAltura();
        }

        return new Rectangle2D(x1, y1, x2-x1, y2-y1);
    }
}
