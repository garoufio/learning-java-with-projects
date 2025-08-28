package chapter4;

public class Exercise4_2 {
  
  public static void main(String[] args) {
    String species = "Velociraptor";
    
    switch (species) {
      case "Stegosaurus":
        System.out.printf("%s is herbivore and weights from 500Kg up to 2000Kg", species);
        break;
      case "Triceraptor":
        System.out.printf("%s is a ");
      case "T-Rex":
        System.out.printf(
            "%s is a large and powerful dinosaur. It is carnivore and weights up to 1500Kg\n",
            species
        );
        break;
      case "Velociraptor":
        System.out.printf(
            "%s is an agile and intelligent dinosaur. It is carnivore and weights up to 600Kg",
            species
        );
        break;
      default:
        System.out.println("Invalid species");
        break;
    }
  }
  
}
