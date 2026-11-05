package chapter14.exercises;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestBoundMethodReferences {
  
  public static void main(String[] args) {
    String name = "Mr. Pete Ford";
    
    Supplier<String> toLowerL = () -> name.toLowerCase();
    Supplier<String> toLowerMR = name::toLowerCase;
    System.out.println("Lambda -> toLowerCase: " + toLowerL.get());
    System.out.println("Method Reference -> toLowerCase: " + toLowerMR.get());
    
    Predicate<String> isMaleL = (title) -> name.startsWith(title);
    Predicate<String> isMaleMR = name::startsWith;
    System.out.println("Lambda -> startsWith 'Mr.': " + isMaleL.test("Mr."));
    System.out.println("Method Reference -> startsWith 'Ms.': " + isMaleMR.test("Ms."));
  }
  
}
