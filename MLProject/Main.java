import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Message> data = DataReader.readData("/Users/om-college/Desktop/PDEU/ML/MLProject/text.txt");
        NaiveBayesClassifier classifier = new NaiveBayesClassifier();
        classifier.train(data);
        System.out.println("Classifying predefined test messages:");
        String[] testMessages = {
            "Win a new Iphone",
            "Hey, Can we met for coffee",
            "Cheap watches for sale",
            "How have you been?"
        };

        for (String message : testMessages) {
            System.out.printf("Message: \"%s\" - Classified as: %s%n", message, classifier.classify(message));
        }

        // Allow user to input their own messages for classification
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter your own message to classify as 'spam' or 'ham' (type 'exit' to quit):");

        while (true) {
            System.out.print("Enter message: ");
            String userInput = scanner.nextLine();

            // Exit loop if user types 'exit'
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            // Classify the user input
            String result = classifier.classify(userInput);
            System.out.printf("Your message was classified as: %s%n", result);
        }

        // Close the scanner
        scanner.close();
        System.out.println("Program terminated.");
    }
}
