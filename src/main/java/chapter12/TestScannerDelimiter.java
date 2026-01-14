package chapter12;

import java.util.Scanner;

public class TestScannerDelimiter {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.print("Enter your name: ");
    String name = sc.next();
    System.out.println("Name: " + name);
    sc.nextLine(); // Clear the buffer
    
    System.out.println("Setting delimiter to semicolon (;)");
    sc.useDelimiter("\\s*;\\s*");
    System.out.print("Enter your name: ");
    name = sc.next();
    System.out.println("Name: " + name);
    
  }
  
}
