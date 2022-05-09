import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;

/**
 * @author Gregory Elias
 * @version 1.0.0
 * */
public class WordCheck {
    public static String wordCheck = "";

    /**
     * @param stackPanes Inputted list of stackPanes.
     * @return Returns an arrayList of strings that resemble the labels of each node in the panes.
     * */
    public static ArrayList<String> getWord(ArrayList<StackPane> stackPanes) {
        for (StackPane stackPane : stackPanes) {
            ObservableList<Node> children = stackPane.getChildren();
            for (Node node : children) {
                if (node instanceof Label) {
                    wordCheck = wordCheck.concat(((Label) node).getText());
                }
            }
        }
        ArrayList<String> stringArrayList = new ArrayList<>(5);
        ArrayList<String> guess = stringList(wordCheck);
        String temp = wordCheck;
        wordCheck = "";
        ArrayList<String> answer = stringList(RandomWord.word);

        ArrayList<String> tempAnswers = new ArrayList<>(answer);

        for (int i = 0; i < guess.size(); i++) {
            if (guess.get(i).equals(answer.get(i).toUpperCase())) {
                tempAnswers.remove(guess.get(i).toLowerCase());
            } else if (!answer.contains(guess.get(i).toLowerCase())) {
                tempAnswers.remove(guess.get(i).toLowerCase());
            }
        }

        for (int i = 0; i < guess.size(); i++) {
            if (guess.size() != 5 || guess.get(i).equals("")
                    || (!ValidWords.dictionary(temp.toLowerCase()) && ValidWords.isDictionary)) {
                return null;
            }
            if (guess.get(i).equals(answer.get(i).toUpperCase())) {
                stringArrayList.add("GREEN");
            } else if (!answer.contains(guess.get(i).toLowerCase())) {
                stringArrayList.add("GRAY");
            } else {
                if (tempAnswers.contains(guess.get(i).toLowerCase())) {
                    stringArrayList.add("YELLOW");
                    tempAnswers.remove(guess.get(i).toLowerCase());
                } else {
                    stringArrayList.add("GRAY");
                }
            }
        }
        return stringArrayList;
    }

    /**
     * @param word Word to be sorted
     * @return Converts word into arrayList of 1 character strings.
     * */
    public static ArrayList<String> stringList(String word) {
        if (word.length() == 0) {
            return new ArrayList<>();
        }
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            strings.add(String.valueOf(word.charAt(i)));
        }
        return strings;
    }

}