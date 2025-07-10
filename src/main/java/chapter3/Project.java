package chapter3;

public class Project {
  
  public static void main(String[] args) {
    double weight = 843;
    double percentageOfFood = 0.05; // percentage per dinosaur's weight
    int numberOfDailyMeals = 2;
    
    double amountOfFood = weight * percentageOfFood;
    System.out.printf(
        "Our %.2f Kg dinosaur needs to eat %.2f kg daily, which means we need to serve %.2f kg per feeding.\n",
        weight, amountOfFood, amountOfFood / numberOfDailyMeals
    );
  }
  
}
