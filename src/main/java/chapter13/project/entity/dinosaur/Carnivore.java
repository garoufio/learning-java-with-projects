package chapter13.project.entity.dinosaur;

/**
 * Represents a carnivorous dinosaur. Provides a default method to calculate the daily meat requirement based on the 
 * dinosaur's species and size.
 */
public interface Carnivore {
  
  double AVERAGE_DAILY_MEAT_AMOUNT_SMALL_SIZED = 30; // in kg
  double AVERAGE_DAILY_MEAT_AMOUNT_MEDIUM_SIZED = 50; // in kg
  double AVERAGE_DAILY_MEAT_AMOUNT_BIG_SIZED = 70; // in kg
  double SMALL_SIZED_FOOD_MULTIPLIER = 0.65;
  double BIG_SIZED_FOOD_MULTIPLIER = 1.35;
  
  //-------------------------------------------------------------------------------------------------------------------
 
  /**
   * Calculates the daily meat requirement for the carnivorous dinosaur based on its species and size.
   * @param species the species of the carnivorous dinosaur.
   * @param size the size of the carnivorous dinosaur.
   * @return the daily meat requirement for the carnivorous dinosaur.
   */
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
