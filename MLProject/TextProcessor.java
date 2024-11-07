import java.util.Arrays;
import java.util.List;

public class TextProcessor {
    public static List<String> tokenize(String text) {
        return Arrays.asList(text.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+"));
    }
}
