package chapter4;

public class Project {

//  Design a simple program that assigns tasks to the Mesozoic Eden employees based on their roles (for
//  example, feeding, cleaning, security, and tour guiding). The program should decide tasks based on
//  time, the employee’s role, and other factors, such as the park’s safety rating.
  
  public static void main(String[] args) {
    String jobTitle = "Safety manager";
    int currentTime = 1725;
    double safetyThreshold = 8.0;
    double safetyRating = 7.2;
    
    switch (jobTitle) {
      case "Cleaner":
        if (currentTime > 1930) {
          System.out.println("Clean the floor and throw the garbage");
        }
        else if (currentTime >= 1400 && currentTime < 1730) {
          System.out.println("Throw the garbage");
        }
        break;
      case "Marketing manager":
        System.out.println("Advertises the Mesozoic Eden to the world");
        break;
      case "Parking manager":
        if (currentTime >= 1000 && currentTime < 1900) {
          System.out.println("Assigns the parking space to the visitors");
        }
        break;
      case "Safety manager":
        if (safetyRating < safetyThreshold) {
          System.out.println(
              "Closes the Mesozoic Eden and takes safety measures in order to increase the safety of the zoo"
          );
        }
        break;
      case "Tickets manager":
        if (currentTime >= 1000 && currentTime < 1900) {
          System.out.println("The Ticket manager is responsible for the tickets");
        }
        break;
      default:
        System.out.println("Invalid job title");
        break;
    }
  }
  
}
