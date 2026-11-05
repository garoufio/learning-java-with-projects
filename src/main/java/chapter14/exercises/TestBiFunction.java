package chapter14.exercises;

import java.util.function.BiFunction;

public class TestBiFunction {
  
  public static void main(String[] args) {
    BiFunction<String, String, Integer> howMany =
        (haystack, needle) -> {
          return haystack.contains(needle) ?
            haystack.split(needle).length - 1 : 0;
        };
    
    System.out.println(howMany.apply("hello world", "o"));
    System.out.println(howMany.apply("hello world", "l"));
    System.out.println(howMany.apply("hello world", "a"));
  }
  
}
