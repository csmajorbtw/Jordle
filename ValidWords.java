import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Gregory Elias
 * @version 1.0.0
 * */
public class ValidWords {
    public static boolean isDictionary;

    /**
     * @param word Word to be checked.
     * @return If the word exists in 'validWords.txt'.
     * */
    public static boolean dictionary(String word) {
        try {
            FileReader fr = new FileReader("validWords.txt");
            BufferedReader bufferedReader = new BufferedReader(fr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (word.equals(line)) {
                    bufferedReader.close();
                    fr.close();
                    return true;
                }
            }
            fr.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}