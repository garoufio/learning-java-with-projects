package chapter13;

import java.text.DateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Exercise13_7 {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("How many ice creams would the guest like? ");
    int numIceCreams = sc.nextInt();
    
    List<Map<Flavor, Integer>> iceCreamOrders = new ArrayList<>();
    for (int i = 1; i <= numIceCreams; i++) {
      System.out.println("Which of these flavors do you want for ice cream #" + i + "?");
      printFlavors();
      System.out.print(": ");
      int iceCreamVal = sc.nextInt();
      Flavor flavor = getFlavor(iceCreamVal);
      
      System.out.print("How many scoops of this flavor would you like? ");
      int numberOfScoops = sc.nextInt();
      Map<Flavor, Integer> order = new HashMap<>();
      order.put(flavor, numberOfScoops);
      iceCreamOrders.add(order);
    }
    System.out.println("\nTotal ice cream orders:");
    printIceCreamOrders(iceCreamOrders);
    LocalTime readyTime = LocalTime.now().plusMinutes(10);
    System.out.println("\nYour ice cream will be ready at: " + readyTime.format(DateTimeFormatter.ofPattern("HH:mm")));
    
    sc.close();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private static void printFlavors() {
    Flavor[] flavors = Flavor.values();
    for (Flavor flavor : flavors) {
      System.out.printf("%d. %s (%s)\n", flavor.ordinal() + 1, flavor.getDinoTheme(), flavor.name());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private static void printIceCreamOrders(List<Map<Flavor, Integer>> list) {
    int orderNumber = 1;
    for (Map<Flavor, Integer> m : list) {
      for (Map.Entry<Flavor, Integer> entry : m.entrySet()) {
        System.out.printf(
            "Ice Cream order #%d: %d scoops of %s (%s)\n",
            orderNumber++, entry.getValue(), entry.getKey().getDinoTheme(), entry.getKey().name()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private static Flavor getFlavor(int choice) {
    return switch (choice) {
      case 1 -> Flavor.VANILLA;
      case 2 -> Flavor.CHOCOLATE;
      case 3-> Flavor.STRAWBERRY;
      case 4 -> Flavor.MINT;
      case 5 -> Flavor.COOKIE_DOUGH;
      default -> throw new IllegalArgumentException("Invalid flavor choice: " + choice);
    };
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  // Flavor ENUM
  //-------------------------------------------------------------------------------------------------------------------
  
  enum Flavor {
    VANILLA("Pterosaur sky"),
    CHOCOLATE("T-Rex mountain"),
    STRAWBERRY("Volcano valley"),
    MINT("Dino forest"),
    COOKIE_DOUGH("Raptor nest");
    
    //-----------------------------------------------------------------------------------------------------------------
    
    private String dinoTheme;
    
    //-----------------------------------------------------------------------------------------------------------------
    
    Flavor(String dinoTheme) {
      this.dinoTheme = dinoTheme;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    public String getDinoTheme() {
      return dinoTheme;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
