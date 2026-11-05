package chapter14.exercises;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class TestUnboundMethodReferences {
  
  public static void main() {
    Function <String, String> toUpperL = (name) -> name.toLowerCase();
    Function<String, String> toUpperMR = String::toUpperCase;
    System.out.println("Lambda -> toUpperCase: " + toUpperL.apply("Mr. Pete Ford"));
    System.out.println("Method Reference -> toUpperCase: " + toUpperMR.apply("Mr. Pete Ford"));
    
    BiFunction<String, String, String> concatL = (str1, str2) -> str1.concat(str2);
    BiFunction<String, String, String> concatMR = String::concat;
    System.out.println("Lambda -> concat: " + concatL.apply("Mr. ", "Pete Ford"));
    System.out.println("Method Reference -> concat: " + concatMR.apply("Mr. ", "Pete Ford"));
    
    BiPredicate<String, Integer> isLengthLongerThanL = (str, len) -> str.length() > len;
    System.out.println("Lambda -> isLengthLongerThan 5 characters: " + isLengthLongerThanL.test("Mr. Pete Ford", 5));
  }
  
}
