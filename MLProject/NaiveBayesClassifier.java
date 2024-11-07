import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NaiveBayesClassifier {
    private Map<String, Integer> spamWordCounts;
    private Map<String, Integer> hamWordCounts;
    private int spamMessagesCount;
    private int hamMessagesCount;
    private int spamWordTotal;
    private int hamWordTotal;
    private double totalMessages;

    public NaiveBayesClassifier() {
        this.spamWordCounts = new HashMap<>();
        this.hamWordCounts = new HashMap<>();
        this.spamMessagesCount = 0;
        this.hamMessagesCount = 0;
        this.spamWordTotal = 0;
        this.hamWordTotal = 0;
        this.totalMessages = 0;
    }

    public void train(List<Message> data) {
        for (Message message : data) {
            List<String> words = TextProcessor.tokenize(message.text);
            if (message.label.equals("spam")) {
                spamMessagesCount++;
                for (String word : words) {
                    spamWordCounts.put(word, spamWordCounts.getOrDefault(word, 0) + 1);
                    spamWordTotal++;
                }
            } else {
                hamMessagesCount++;
                for (String word : words) {
                    hamWordCounts.put(word, hamWordCounts.getOrDefault(word, 0) + 1);
                    hamWordTotal++;
                }
            }
            totalMessages++;
        }
    }

    public String classify(String text) {
        List<String> words = TextProcessor.tokenize(text);
        double spamProbability = Math.log(spamMessagesCount / totalMessages);
        double hamProbability = Math.log(hamMessagesCount / totalMessages);

        for (String word : words) {
            spamProbability += Math.log((spamWordCounts.getOrDefault(word, 0) + 1.0) / (spamWordTotal + spamWordCounts.size()));
            hamProbability += Math.log((hamWordCounts.getOrDefault(word, 0) + 1.0) / (hamWordTotal + hamWordCounts.size()));
        }

        return spamProbability > hamProbability ? "spam" : "ham";
    }
}
