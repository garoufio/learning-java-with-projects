package chapter10.project.entity.dinosaur;

import chapter10.project.entity.dinosaur.DinosaurSize;
import chapter10.project.entity.dinosaur.DinosaurSpecies;

public interface Carnivore {
  
  double AVERAGE_DAILY_MEAT_AMOUNT_SMALL_SIZED = 30; // in kg
  double AVERAGE_DAILY_MEAT_AMOUNT_MEDIUM_SIZED = 50; // in kg
  double AVERAGE_DAILY_MEAT_AMOUNT_BIG_SIZED = 70; // in kg
  double SMALL_SIZED_FOOD_MULTIPLIER = 0.65;
  double BIG_SIZED_FOOD_MULTIPLIER = 1.35;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  default double feed(DinosaurSpecies species, DinosaurSize size) {
    return switch (size) {
      case SMALL -> AVERAGE_DAILY_MEAT_AMOUNT_SMALL_SIZED * SMALL_SIZED_FOOD_MULTIPLIER * species.getFoodScaleFactor();
      case MEDIUM -> AVERAGE_DAILY_MEAT_AMOUNT_MEDIUM_SIZED  * species.getFoodScaleFactor();
      case BIG -> AVERAGE_DAILY_MEAT_AMOUNT_BIG_SIZED * BIG_SIZED_FOOD_MULTIPLIER * species.getFoodScaleFactor();
      default -> {
        System.out.println("Unknown size. Cannot determine food amount.");
        yield 0;
      }
    };
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
