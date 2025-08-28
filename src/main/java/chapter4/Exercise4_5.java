package chapter4;

public class Exercise4_5 {
  
  public static void main(String[] args) {
    String dinosaurSize = "L";
    
    switch (dinosaurSize) {
      case "XS":
        System.out.println("Assign dinosaur to an extra small enclosure");
        break;
      case "S":
        System.out.println("Assign dinosaur to a small enclosure");
        break;
      case "M":
        System.out.println("Assign dinosaur to a medium enclosure");
        break;
      case "L":
        System.out.println("Assign dinosaur to a large enclosure");
        break;
      case "XL":
        System.out.println("Assign dinosaur to an extra large enclosure");
         break;
      default:
        System.out.println("Invalid dinosaur size");
        break;
    }
  }
  
}
