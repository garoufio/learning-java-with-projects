package chapter7;

public class Exercise7_2 {
  
  public static void main(String[] args) {
    System.out.printf("A dinosaur weighting 150Kg should eat '%.2fKg' of food daily\n", getFoodAmountInKg(150));
    System.out.printf("A dinosaur weighting 450Kg should eat '%.2fKg' of food daily\n", getFoodAmountInKg(450));
    System.out.printf("A dinosaur weighting 800Kg should eat '%.2fKg' of food daily\n", getFoodAmountInKg(800));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static double getFoodAmountInKg(double weight) {
    if (weight < 0) {
      return 0.0;
    }
    else if (weight < 300) {
      return 0.1 * weight;
    }
    else if (weight < 600) {
      return 0.2 * weight;
    }
    else if (weight < 900) {
      return 0.3 * weight;
    }
    else {
      return 0.4 * weight;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}