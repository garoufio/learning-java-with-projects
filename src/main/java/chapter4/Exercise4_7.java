package chapter4;

public class Exercise4_7 {
  
  public static void main(String[] args) {
    String jobTitle = "Marketing manager";
    
    switch (jobTitle) {
      case "Cleaner":
        System.out.println("Keeps the Mesozoic Eden clean");
        break;
      case "Marketing manager":
        System.out.println("Advertises the Mesozoic Eden to the world");
        break;
      case "Parking manager":
        System.out.println("Assigns the parking space to the visitors");
        break;
      case "Safety manager":
        System.out.println("The Safety manager is responsible for the Mesozoic's Eden safety");
        break;
      case "Tickets manager":
        System.out.println("The Ticket manager is responsible for the tickets");
        break;
      default:
        System.out.println("Invalid job title");
        break;
    }
  }
  
}
