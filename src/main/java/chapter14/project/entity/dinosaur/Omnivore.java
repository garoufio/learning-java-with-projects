package chapter14.project.entity.dinosaur;

/**
 * Represents an omnivorous dinosaur that feeds on both plants and meat. Provides a default method to calculate the daily 
 * food requirement based on the dinosaur's size and species.
 */
public interface Omnivore {
  
  double AVERAGE_DAILY_FOOD_AMOUNT_SMALL_SIZED = 40; // in kg
  double AVERAGE_DAILY_FOOD_AMOUNT_MEDIUM_SIZED = 60; // in kg
  double AVERAGE_DAILY_FOOD_AMOUNT_BIG_SIZED = 80; // in kg
  double SMALL_SIZED_FOOD_MULTIPLIER = 0.5;
  double BIG_SIZED_FOOD_MULTIPLIER = 1.5;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Calculates the daily food requirement for the omnivorous dinosaur based on its species and size.
   * @param species the species of the omnivorous dinosaur.
   * @param size the size of the omnivorous dinosaur.
   * @return the amount of food required for the omnivorous dinosaur.
   */
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
