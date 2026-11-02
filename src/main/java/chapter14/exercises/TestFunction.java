package chapter14.exercises;

import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

public class TestFunction {
  
  static void main() {
    Function<String, Integer> len = s -> s.length();
    System.out.println(len.apply("Hello"));
    
    DoubleFunction<Double> square = d -> d * d;
    System.out.println(square.apply(5.0));
    
    IntFunction<String> intToString = i -> Integer.toString(i);
    System.out.println(intToString.apply(123));
    
    DoubleToIntFunction doubleToInt = d -> (int) Math.round(d);
    System.out.println(doubleToInt.applyAsInt(3.7));
  }
  
}
