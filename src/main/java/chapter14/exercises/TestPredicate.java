package chapter14.exercises;

import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import static java.lang.IO.println;

public class TestPredicate {
  
  static void main() {
    Predicate<String> isFromGmail = email -> email.endsWith("@gmail.com");
    println("Does 'example@gmail.com' belongs to gmail? " + isFromGmail.test("example@gmail.com"));
    println("Does 'example@otenet.com' belongs to gmail? " + isFromGmail.test("example@otenet.com"));
    
    DoublePredicate isDoublePositive = num -> num > 0;
    println("Is 5.0 positive? " + isDoublePositive.test(5.0));
    println("Is -3.0 positive? " + isDoublePositive.test(-3.0));
    
    IntPredicate isEven = num -> num % 2 == 0;
    println("Is 4 even? " + isEven.test(4));
    println("Is 7 even? " + isEven.test(7));
    
    LongPredicate isLongPositive = num -> num > 0;
    println("Is 10000000000 positive? " + isLongPositive.test(10000000000L));
    println("Is -5000000000 positive? " + isLongPositive.test(-5000000000L));
  }
  
}
