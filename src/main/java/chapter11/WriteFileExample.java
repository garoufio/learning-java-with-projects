package chapter11;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFileExample {
  
  public static void main(String[] args) {
    System.out.println("Write File Example");
    
    try {
      FileWriter writer = new FileWriter("src/main/resources/output.txt");
      String content = "This is an example of writing to a file using FileWriter in Java.\n";
      writer.write(content);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
}
