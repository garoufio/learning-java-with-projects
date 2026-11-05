package chapter14.exercises;

import java.util.List;
import java.util.function.*;

public class TestConsumer {
  
  public static void main(String[] args) {
    Consumer<String> printUpperCase = str -> System.out.println(str.toUpperCase());
    printUpperCase.accept("hello world");
    
    Consumer<String> print = str -> System.out.println(str);
    List<String> list = List.of("java", "python", "javascript");
    list.forEach(print);
    
    DoubleConsumer printDouble = d -> System.out.println(d);
    printDouble.accept(3.14);
    
    IntConsumer printInt = i -> System.out.println(i);
    printInt.accept(42);
    
    LongConsumer printLong = l -> System.out.println(l);
    printLong.accept(123456789L);
  }
  
}
