package chapter11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Exercise11_4 {
  
  public static void main(String[] args) {
    String content = readFile("src/main/resources/DinoDiet.txt");
    if (content != null) {
      System.out.println(content);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static String readFile(String fileName) {
    String content = null;
    try (
        FileReader reader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);
        ) {
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        sb.append(line).append("\n");
      }
      content = sb.toString();
    } catch (IOException e) {
      System.err.println("An error occurred while reading the file: " + e.getMessage());
    }
    return content;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
