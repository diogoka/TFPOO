package src;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Timer {
    private int loops = 0;
    private long count = 0;
    private int interval;
    private List<Consumer<Integer>> handlers;
    private boolean loop;
    private boolean alreadyCounted;

    public Timer(float interval, boolean loop) {
        handlers = new LinkedList<>();
        this.interval = (int)Math.floor(interval * 1000);
        this.loop = loop;
        alreadyCounted = false;
    }

    public void addHandler(Consumer<Integer> handler) {
        handlers.add(handler);
    }

    public void removeHandler(Consumer<Integer> handler) {
        handlers.remove(handler);
    }

    public void Update(long deltaTime) {
        if (!loop) {
            if (!alreadyCounted) {
            } else {
                return;
            }
        }
        count += deltaTime / 1000000;
        if (count > interval) {
            count -= interval;
            if (count > interval) count = 0;
            loops++;
            for(Consumer<Integer> handler : handlers) {
                handler.accept(loops);
            }
            if (!alreadyCounted) alreadyCounted = true;
        }
    }
}
