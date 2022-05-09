/**
 * @author Gregory Elias
 * @version 1.0.0
 * */
public class RandomWord extends Words {
    public static String word;

    /**
     * Generates a random word from the Words.java arrayList and sets it to be a static variable.
     * */
    public static void setRandomWord() {
        word = list.get((int) (Math.random() * Words.list.size()));
    }
}