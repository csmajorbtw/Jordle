/**
 * @author Gregory Elias
 * @version 1.0.0
 * */
public class Gamer implements Comparable<Gamer> {
    public String name;
    public int score;

    /**
     * @param name Inputs name for gamer.
     * @param score Inputs score for gamer.
     * */
    public Gamer(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Gamer gamer) {
        return this.score - gamer.score;
    }

    @Override
    public String toString() {
        return String.format("%s,%d", getName(), getScore());
    }

    /**
     * @return Returns score.
     * */
    public int getScore() {
        return score;
    }

    /**
     * @return Returns name.
     * */
    public String getName() {
        return name;
    }
}