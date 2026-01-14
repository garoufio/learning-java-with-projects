package chapter12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFile {
  
  public static void main(String[] args) {
    readFile();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static void readFile() {
    try (Scanner sc = new Scanner(new File("src/main/resources/age.txt"))) {
      if (sc.hasNextInt()) {
        int age = sc.nextInt();
        System.out.println("Age: " + age);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
