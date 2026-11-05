package chapter14.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.IO.println;

public class TestConstructorMethodReferences {
  
  static void main() {
    Supplier<StringBuilder> stringBuilderL = () -> new StringBuilder();
    println("Lambda -> create StringBuilder: " + stringBuilderL.get().append("Hello, World!"));
    
    Supplier<StringBuilder> stringBuilderMR = StringBuilder::new;
    println("MethodReference -> create StringBuilder: " + stringBuilderMR.get().append("Hello, World!"));
    
    Function<Integer, List<Integer>> arrayToListL = (x) -> new ArrayList(x);
    println("Lambda -> create List from integer: " + arrayToListL.apply(10));
    
    Function<Integer, List<Integer>> arrayToListMR = ArrayList::new;
    println("MethodReference -> create List from integer: " + arrayToListMR.apply(15));
  }
  
}
