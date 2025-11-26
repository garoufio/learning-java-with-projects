package chapter10.project.entity.dinosaur;

import chapter10.project.entity.dinosaur.DinosaurSize;
import chapter10.project.entity.dinosaur.DinosaurSpecies;

public interface Omnivore {
  
  double AVERAGE_DAILY_FOOD_AMOUNT_SMALL_SIZED = 40; // in kg
  double AVERAGE_DAILY_FOOD_AMOUNT_MEDIUM_SIZED = 60; // in kg
  double AVERAGE_DAILY_FOOD_AMOUNT_BIG_SIZED = 80; // in kg
  double SMALL_SIZED_FOOD_MULTIPLIER = 0.75;
  double BIG_SIZED_FOOD_MULTIPLIER = 1.25;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  default double feed(DinosaurSpecies species, DinosaurSize size) {
    return switch (size) {
      case SMALL -> AVERAGE_DAILY_FOOD_AMOUNT_SMALL_SIZED * SMALL_SIZED_FOOD_MULTIPLIER * species.getFoodScaleFactor();
      case MEDIUM -> AVERAGE_DAILY_FOOD_AMOUNT_MEDIUM_SIZED  * species.getFoodScaleFactor();
      case BIG -> AVERAGE_DAILY_FOOD_AMOUNT_BIG_SIZED * BIG_SIZED_FOOD_MULTIPLIER * species.getFoodScaleFactor();
      default -> {
        System.out.println("Unknown size. Cannot determine food amount.");
        yield 0;
      }
    };
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  
}
