package chapter2;

public class Exercise2 {
  
  public static void main(String[] args) {
    String breed = "Velosaurus";
    String name = "Lucy";
    double weight = 843;  // weight in Kg
    double height = 2.45; // height in m
    double length = 4.3;  // length in m
    int age = 7;
    boolean isCarnivore = true;
    
    System.out.printf(
        "Breed: %s\nName: %s\nWeight: %.2f Kg\nHeight: %.2f m\nLength: %.2f m\nAge: %d\nCarnivore: %b\n",
        breed, name, weight, height, length, age, isCarnivore
    );
  }
  
}
