package chapter12;

import java.util.List;

public class Exercise12_5 {
  
  public static void main(String[] args) {
    List<String> names = List.of("Bella", "Ellie", "Lucy");
    
    StringBuilder sb = new StringBuilder();
    for (String s : names) {
      sb.append(s).append(" ");
    }
    String result = sb.toString().trim();
    System.out.println(result);
  }
  
}
