package chapter7;

public class Exercise7_1 {
  
  public static void main(String[] args) {
    System.out.printf("A dinosaur of 1 years old is a '%s' dinosaur\n", getAgeCategory(1));
    System.out.printf("A dinosaur of 3 years old is a '%s' dinosaur\n", getAgeCategory(3));
    System.out.printf("A dinosaur of 7 years old is a '%s' dinosaur\n", getAgeCategory(7));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static String getAgeCategory(int age) {
    if (age < 0) {
      return "Invalid age";
    }
    else if (age < 2) {
      return "Hatchling";
    }
    else if (age < 5) {
      return "Juvenile";
    }
    else return "Adult";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
