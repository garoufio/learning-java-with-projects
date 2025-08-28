package chapter5;

public class Exercise5_5 {
  
  public static void main(String[] args) {
    int tickets = 15;
    int ticketsSold = 0;
    
    while (ticketsSold < tickets) {
      ticketsSold++;
      System.out.println("Tickets sold: " + ticketsSold);
    }
    System.out.println("There are no available tickets.");
  }
  
}
