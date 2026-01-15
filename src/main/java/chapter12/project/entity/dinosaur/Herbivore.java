package chapter12.project.entity.dinosaur;

public interface Herbivore {
  
  double AVERAGE_DAILY_GRASS_AMOUNT_SMALL_SIZED = 10; // in kg
  double AVERAGE_DAILY_GRASS_AMOUNT_MEDIUM_SIZED = 30; // in kg
  double AVERAGE_DAILY_GRASS_AMOUNT_BIG_SIZED = 50; // in kg
  double SMALL_SIZED_FOOD_MULTIPLIER = 0.75;
  double BIG_SIZED_FOOD_MULTIPLIER = 1.25;
  
  //-------------------------------------------------------------------------------------------------------------------
  
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
