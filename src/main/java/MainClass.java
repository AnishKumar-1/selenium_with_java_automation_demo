import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        try {
            // Specify the correct file path
            File myObj = new File("C:\\Users\\kumar\\Desktop\\temp.txt");

            // Create the file if it does not exist
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }

            // Check if the file exists before writing
            if (myObj.exists()) {
                // Writing to the file (overwrites existing content)
                FileWriter myWriter = new FileWriter(myObj);
                myWriter.write("Files in Java might be tricky, but it is fun enough!");
                myWriter.close(); // Ensure the writer is closed before reading
                
                // Reading from the file
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close(); // Close the scanner after use
            } else {
                System.out.println("File does not exist.");
            }

        } catch (IOException e) {
            // Print the error message to understand the issue
            System.out.println("An error occurred.");
            e.printStackTrace(); // This will help to see the actual exception message
        }
    }
}
