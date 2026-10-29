package chapter14.project.entity.dinosaur;

/**
 * Represents a herbivorous dinosaur that primarily feeds on plants. Provides a default method to calculate the daily 
 * food requirement based on the dinosaur's size and species.
 */
public interface Herbivore {
  
  double AVERAGE_DAILY_GRASS_AMOUNT_SMALL_SIZED = 10; // in kg
  double AVERAGE_DAILY_GRASS_AMOUNT_MEDIUM_SIZED = 30; // in kg
  double AVERAGE_DAILY_GRASS_AMOUNT_BIG_SIZED = 50; // in kg
  double SMALL_SIZED_FOOD_MULTIPLIER = 0.75;
  double BIG_SIZED_FOOD_MULTIPLIER = 1.25;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Calculates the daily food requirement for the herbivorous dinosaur based on its species and size.
   * @param species the species of the herbivorous dinosaur.
   * @param size the size of the herbivorous dinosaur.
   * @return the amount of food required for the herbivorous dinosaur.
   */
  default double feed(DinosaurSpecies species, DinosaurSize size) {
    return switch (size) {
      case SMALL -> AVERAGE_DAILY_GRASS_AMOUNT_SMALL_SIZED * SMALL_SIZED_FOOD_MULTIPLIER * species.getFoodScaleFactor();
      case MEDIUM -> AVERAGE_DAILY_GRASS_AMOUNT_MEDIUM_SIZED  * species.getFoodScaleFactor();
      case BIG -> AVERAGE_DAILY_GRASS_AMOUNT_BIG_SIZED * BIG_SIZED_FOOD_MULTIPLIER * species.getFoodScaleFactor();
      default -> {
        System.out.println("Unknown size. Cannot determine food amount.");
        yield 0;
      }
    };
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  
}
