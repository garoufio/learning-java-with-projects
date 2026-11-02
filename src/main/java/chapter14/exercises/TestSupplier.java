package chapter14.exercises;

import java.time.LocalDate;
import java.util.List;
import java.util.function.*;

public class TestSupplier {
  
  static void main() {
    Supplier<List<Integer>> randomIntegerList = () -> List.of(
        (int) (Math.random() * 100),
        (int) (Math.random() * 100),
        (int) (Math.random() * 100)
    );
    System.out.println("Random integer list: " + randomIntegerList.get());
    
    BooleanSupplier isLeapYear = () -> LocalDate.now().isLeapYear();
    System.out.println("Is current year a leap year? " + isLeapYear.getAsBoolean());
    
    DoubleSupplier randomDouble = () -> Math.random() * 100.0;
    System.out.println("Random double: " + randomDouble.getAsDouble());
    
    IntSupplier randomInt = () -> (int) (Math.random() * 100);
    System.out.println("Random integer: " + randomInt.getAsInt());
    
    LongSupplier  randomLong = () -> (long) (Math.random() * 100);
    System.out.println("Random long: " + randomLong.getAsLong());
  }
  
}
