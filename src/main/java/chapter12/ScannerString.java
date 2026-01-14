package chapter12;

import java.util.Scanner;

public class ScannerString {
  
  public static void main(String[] args) {
    String input = "Maaike     delim vandelim Putten delim Netherlands delim 22";
    
    Scanner sc = new Scanner(input);
    sc.useDelimiter("\\s*delim\\s*");
    System.out.println(sc.next());
    System.out.println(sc.next());
    System.out.println(sc.next());
    System.out.println(sc.next());
    System.out.println(sc.nextInt());
  }
  
}
