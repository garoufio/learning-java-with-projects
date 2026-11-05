package chapter14.exercises;

import java.util.function.UnaryOperator;

public class TestUnaryOperator {
  
  public static void main(String[] args) {
    UnaryOperator<String> toUpperCase = s -> s.toUpperCase();
    System.out.println(toUpperCase.apply("hello"));
  }
  
}
