package chapter14.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestConstructorMethodReferences {
  
  public static void main(String[] args) {
    Supplier<StringBuilder> stringBuilderL = () -> new StringBuilder();
    System.out.println("Lambda -> create StringBuilder: " + stringBuilderL.get().append("Hello, World!"));
    
    Supplier<StringBuilder> stringBuilderMR = StringBuilder::new;
    System.out.println("MethodReference -> create StringBuilder: " + stringBuilderMR.get().append("Hello, World!"));
    
    Function<Integer, List<Integer>> arrayToListL = (x) -> new ArrayList(x);
    System.out.println("Lambda -> create List from integer: " + arrayToListL.apply(10));
    
    Function<Integer, List<Integer>> arrayToListMR = ArrayList::new;
    System.out.println("MethodReference -> create List from integer: " + arrayToListMR.apply(15));
  }
  
}
