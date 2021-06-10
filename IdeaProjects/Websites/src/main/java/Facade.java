import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Slf4j
public class Facade {

    private static HashMap<String, Integer> readHtml(BufferedReader br) {
        FileOutputStream fos;
        Splitter splitter = new Splitter();
        try {
            fos = new FileOutputStream("C:/info/new_html.txt");
            log.info("File successfully found and connected");
            String line = br.readLine();
            while (line != null) {
                fos.write(line.getBytes(StandardCharsets.UTF_8));
                splitter.startReadingLine(line);
                line = br.readLine();
            }
            return splitter.getWords();
        } catch (IOException e) {
            System.err.println("Error while reading from file");
            log.error("IOException while reading file in Facade");
            return null;
        }
    }

    public static HashMap<String, Integer> getHtml(BufferedReader br) throws IOException {
        return readHtml(br);
    }
}
