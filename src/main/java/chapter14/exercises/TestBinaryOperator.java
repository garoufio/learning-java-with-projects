package chapter14.exercises;

import java.util.function.BinaryOperator;

public class TestBinaryOperator {
  
  static void main() {
    BinaryOperator<String> toUpperCase = (s1, s2) -> (s1 + " " + s2).toUpperCase();
    System.out.println(toUpperCase.apply("hello", "world"));
  }
  
}
