import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Facade {

    private static HashMap<String, Integer> readHtml(BufferedReader br) {
        FileOutputStream fos;
        Splitter splitter = new Splitter();
        try {
            fos = new FileOutputStream("C:/info/new_html.txt");
            String line = br.readLine();
            while (line != null) {
                fos.write(line.getBytes(StandardCharsets.UTF_8));
                splitter.startReadingLine(line);
                line = br.readLine();
            }
            return splitter.getWords();
        } catch (IOException e) {
            System.err.println("Error while reading from file");
            return null;
        }
    }

    public static HashMap<String, Integer> getHtml(BufferedReader br) throws IOException {
        return readHtml(br);
    }
}
