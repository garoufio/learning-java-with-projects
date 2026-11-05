package chapter14.exercises;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class TestStaticMethodReferences {
  
  public static void main(String[] args) {
    List<Integer> numbersL = Arrays.asList(11, 2, 34, 4, 51);
    Consumer<List<Integer>> sortedNumbersL =list -> Collections.sort(list);
    sortedNumbersL.accept(numbersL);
    System.out.println("Lambda -> sortedNumbers: " + numbersL);
    
    List<Integer> numbersMR = Arrays.asList(10, -1, -3, 14, 5);
    Consumer<List<Integer>> sortedNumbersMR = Collections::sort;
    sortedNumbersMR.accept(numbersMR);
    System.out.println("Method Reference -> sortedNumbers: " + numbersMR);
  }
  
}
