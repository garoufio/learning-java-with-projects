package chapter14.exercises;

import java.util.function.UnaryOperator;

public class TestUnaryOperator {
  
  static void main() {
    UnaryOperator<String> toUpperCase = s -> s.toUpperCase();
    System.out.println(toUpperCase.apply("hello"));
  }
  
}
