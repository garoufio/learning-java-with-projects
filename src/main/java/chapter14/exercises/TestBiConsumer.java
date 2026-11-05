package chapter14.exercises;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class TestBiConsumer {
  
  static void main() {
    BiConsumer<String, String> printFullName =
        (firstName, lastName) -> System.out.println(firstName + " " + lastName);
    printFullName.accept("Mike", "Ross");
    
    HashMap<String, String> mapCapitalCities = new HashMap<>();
    BiConsumer<String, String> addToMap = (key, value) -> mapCapitalCities.put(key, value);
    addToMap.accept("Athens", "Greece");
    addToMap.accept("Paris", "France");
    addToMap.accept("Berlin", "Germany");
    BiConsumer<String, String> printMap = (key, value) -> System.out.printf("%s is the capital of %s\n", key, value);
    mapCapitalCities.forEach(printMap);
  }
}
