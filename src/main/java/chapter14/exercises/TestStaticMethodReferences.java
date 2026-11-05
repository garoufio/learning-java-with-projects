package chapter14.exercises;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import static java.lang.IO.println;

public class TestStaticMethodReferences {
  
  static void main() {
    List<Integer> numbersL = Arrays.asList(11, 2, 34, 4, 51);
    Consumer<List<Integer>> sortedNumbersL =list -> Collections.sort(list);
    sortedNumbersL.accept(numbersL);
    println("Lambda -> sortedNumbers: " + numbersL);
    
    List<Integer> numbersMR = Arrays.asList(10, -1, -3, 14, 5);
    Consumer<List<Integer>> sortedNumbersMR = Collections::sort;
    sortedNumbersMR.accept(numbersMR);
    println("Method Reference -> sortedNumbers: " + numbersMR);
  }
  
}
