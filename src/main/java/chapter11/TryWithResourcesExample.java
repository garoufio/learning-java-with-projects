package chapter11;

import java.io.*;

public class TryWithResourcesExample {
  
  public static void main(String[] args) {
    System.out.println("Try With Resources Example");
    
    try (FileReader reader = new FileReader("src/main/resources/input.txt");
         BufferedReader bufferedReader = new BufferedReader(reader);
         FileWriter writer = new FileWriter("src/main/resources/output.txt");
         BufferedWriter bufferedWriter = new BufferedWriter(writer);
    ) {
      String line;
      
      while ((line = bufferedReader.readLine()) != null) {
        bufferedWriter.write(line.toUpperCase());
        bufferedWriter.newLine();
      }
    } catch (IOException e) {
      System.err.println("An error occurred: " + e.getMessage());
    }
  }
  
}
