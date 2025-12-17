package chapter11;

import java.io.FileReader;
import java.io.IOException;

public class ReadFileExample {
  
  public static void main(String[] args) {
    System.out.println("Read File Example");
    
    try {
      FileReader reader = new FileReader("src/main/resources/input.txt");
      int character;
      while ((character = reader.read()) != -1) {
        System.out.print((char) character);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
}
