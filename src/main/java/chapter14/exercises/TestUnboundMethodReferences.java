package chapter14.exercises;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static java.lang.IO.println;

public class TestUnboundMethodReferences {
  
  static void main() {
    Function <String, String> toUpperL = (name) -> name.toLowerCase();
    Function<String, String> toUpperMR = String::toUpperCase;
    println("Lambda -> toUpperCase: " + toUpperL.apply("Mr. Pete Ford"));
    println("Method Reference -> toUpperCase: " + toUpperMR.apply("Mr. Pete Ford"));
    
    BiFunction<String, String, String> concatL = (str1, str2) -> str1.concat(str2);
    BiFunction<String, String, String> concatMR = String::concat;
    println("Lambda -> concat: " + concatL.apply("Mr. ", "Pete Ford"));
    println("Method Reference -> concat: " + concatMR.apply("Mr. ", "Pete Ford"));
    
    BiPredicate<String, Integer> isLengthLongerThanL = (str, len) -> str.length() > len;
    println("Lambda -> isLengthLongerThan 5 characters: " + isLengthLongerThanL.test("Mr. Pete Ford", 5));
  }
  
}
