import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<Message> readData(String filePath) {
        List<Message> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ", 2);
                String label = parts[0];
                String text = parts[1];
                data.add(new Message(label, text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

