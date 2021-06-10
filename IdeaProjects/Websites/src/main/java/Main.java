import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        String input;
        BufferedReader br;
        URL url;
        HttpURLConnection urlCon = null;
        do {
            System.out.print("Input web address, please\n>");
            br = new BufferedReader(new InputStreamReader(System.in));
            log.info("Input was read");
            input = br.readLine().trim();
            if (!input.equals("")) break;
        } while (true);
        try {
            url = new URL(input);
            urlCon = (HttpURLConnection) url.openConnection();
            System.out.println("Connected successfully");
            log.info("Connection was set");
            br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            HashMap<String, Integer> words = Facade.getHtml(br);
            log.info("Got words from Facade");
            words.keySet().stream()
                    .sorted((o1, o2) -> words.get(o2) - words.get(o1))
                    .map(s -> s + " - " + words.get(s))
                    .forEach(System.out::println);
        } catch (MalformedURLException mue) {
            System.out.println("Malformed URL has occurred or no legal protocol could be found");
            log.error("Incorrect input. MalformedURLException");
            main(null);
        } catch (IOException ioe) {
            System.out.println("Error while reading from source");
            log.error("IOException in main");
            main(null);
        } finally {
            if (urlCon != null) urlCon.disconnect();
            log.info("Disconnection");
        }
    }

}
