package src;

public class Score {
    private Integer score;

    public Score() {
        this(0);
    }

    public Score(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
