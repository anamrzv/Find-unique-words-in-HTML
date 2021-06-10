import java.util.HashMap;

public class Splitter {
    private String regex = "\\s|\\.|,|!|\\?|:|;|\\.\\.\\.|\\t|\\n|\\r|\\(|\\)|\\[|]|\"|'|>|/>|<!|</|<|=|-|_|/|#|@|\\d|%|&|}|\\{|\\+|\\\\|\\|\\*|$";
    private HashMap<String, Integer> words = new HashMap<>();

    private void readLine(String line) {
        String[] wordsArray = line.split(regex);
        for (String s : wordsArray) {
            if (s.equals("")) continue;
            Integer wordVal = words.get(s.toLowerCase());
            if (wordVal != null) words.replace(s.toLowerCase(), wordVal + 1);
            else words.put(s.toLowerCase(), 1);
        }
    }

    public void startReadingLine(String line) {
        readLine(line);
    }

    public HashMap<String, Integer> getWords() {
        return words;
    }
}
