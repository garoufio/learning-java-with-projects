package chapter14.exercises;

import java.util.function.Predicate;
import java.util.function.Supplier;
import static java.lang.IO.println;

public class TestBoundMethodReferences {
  
  static void main() {
    String name = "Mr. Pete Ford";
    
    Supplier<String> toLowerL = () -> name.toLowerCase();
    Supplier<String> toLowerMR = name::toLowerCase;
    println("Lambda -> toLowerCase: " + toLowerL.get());
    println("Method Reference -> toLowerCase: " + toLowerMR.get());
    
    Predicate<String> isMaleL = (title) -> name.startsWith(title);
    Predicate<String> isMaleMR = name::startsWith;
    println("Lambda -> startsWith 'Mr.': " + isMaleL.test("Mr."));
    println("Method Reference -> startsWith 'Ms.': " + isMaleMR.test("Ms."));
  }
  
}
