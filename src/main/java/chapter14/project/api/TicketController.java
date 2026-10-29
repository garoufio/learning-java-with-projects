package chapter14.project.api;

import chapter14.project.entity.ticket.*;
import chapter14.project.exception.InputException;
import chapter14.project.service.TicketService;
import com.sun.tools.javac.Main;

import java.time.LocalDate;
import java.util.*;

public class TicketController {
  
  private TicketService ticketService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new TicketController with the specified Scanner and TicketService.
   * @param sc the Scanner to read user input.
   * @param ticketService the service to manage ticket operations.
   */
  public TicketController(Scanner sc, TicketService ticketService) {
    this.sc = sc;
    this.ticketService = ticketService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Manages the ticket operations by providing a menu for various actions such as showing all tickets, adding a
   * ticket, finding a ticket, and removing a ticket. The method runs in an infinite loop until the user chooses to
   * return to the main menu. It reads the user's choice and performs the corresponding action by calling the
   * appropriate methods from the TicketService. The method also handles invalid choices by prompting the user to try
   * again.
   */
  public void manageTickets() {
    for (;;) {
      try {
        System.out.print("""
            
            Tickets service:
            1. Show all Tickets
            2. Add Ticket
            3. Find Ticket
            4. Remove Ticket
            5. Return to main menu
            Enter your choice:\t"""
        );
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> printTickets(ticketService.getAllTickets());
          case 2 -> addTicket();
          case 3 -> findTicket();
          case 4 -> removeTicket();
          case 5 -> System.out.println("Returning to main menu...");
          default -> System.out.println("Invalid choice. Please try again.");
        }
        if (choice == 5) {
          System.out.println();
          break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid number between 1 and 5.");
        sc.nextLine(); // Clear the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error while managing tickets: %s
          Please try again or return to Main Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints the details of the tickets in the provided collection. If the collection is null or empty, it prints a
   * message indicating that no tickets were added. Otherwise, it iterates through the collection and prints the
   * details of each ticket. If a ticket is null, it skips printing that ticket.
   * @param tickets the collection of tickets to be printed.
   */
  private void printTickets(Collection<Ticket> tickets) {
    if (tickets == null || tickets.isEmpty()) {
      System.out.println("No Tickets were added!");
      return;
    }
    
    for (Ticket ticket : tickets) {
      if (ticket != null) System.out.println(ticket);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds a new ticket by reading the visitor's information, ticket type, and date. Depending on the ticket type, it
   * creates either a SeasonTicket or a RegularTicket and adds it to the ticket service. If the ticket type is a
   * regular ticket, it also prompts the user to decide whether to reserve a parking spot.
   * @throws InputException if there is an error while reading the input for the ticket details.
   */
  private void addTicket() throws InputException {
    // read visitor's firstname, lastname and phone number
    Visitor visitor = Util.readVisitor(sc);
    // read ticket type
    TicketType ticketType = Util.readTicketType(sc);
    // read date
    LocalDate date = Util.readTicketDate(sc);
    switch (ticketType) {
      case SEASON_3, SEASON_6, SEASON_8, SEASON_12 ->
          ticketService.addTickets(new SeasonTicket(ticketType, visitor, date));
      default -> {
        boolean reserveParkingSpot = Util.reserveParkingSpot(sc);
        ticketService.addTickets(new RegularTicket(ticketType, visitor, date, reserveParkingSpot));
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints the submenu options for finding or removing tickets based on the specified action. The method displays a
   * list of criteria that the user can choose from to find or remove tickets, such as ticket ID, ticket name, phone
   * number, date, ticket type, or a detailed search. It also provides an option to return to the main ticket menu. The
   * action parameter is used to customize the prompt message to indicate whether the user is finding or removing
   * tickets.
   * @param action the action to be performed (e.g., "Find" or "Remove")
   */
  private void printTicketSubMenuOptions(String action) {
    System.out.printf("""
    
    %s by:
    1. Ticket ID
    2. Ticket Name
    3. Ticket Phone
    4. Ticket Date
    5. Ticket Type
    6. Detail search
    7. Return to ticket menu
    Enter your choice:\t""",
        action
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Finds tickets based on various criteria such as ticket ID, visitor's name, phone number, date, ticket type, or a
   * detailed search. The method displays a submenu with options for the user to choose from and prompts for the
   * necessary input based on the selected option. It then retrieves the corresponding tickets from the ticket service
   * and prints the results. If no tickets are found, it displays an appropriate message. The method handles invalid
   * choices and input exceptions by prompting the user to try again.
   */
  private void findTicket() {
    for (;;) {
      try {
        printTicketSubMenuOptions("Find");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> {
            UUID id = Util.readTicketUUID(sc);
            sc.nextLine();
            Ticket ticket = ticketService.getTicket(id);
            if (ticket == null) System.out.printf("Ticket with ID '%s' not found\n", id);
            else System.out.printf("Ticket found: '%s'\n", ticket);
          }
          case 2 -> {
            String firstname = Util.readVisitorField(sc, "firstname");
            String lastname = Util.readVisitorField(sc, "lastname");
            Set<Ticket> tickets = ticketService.getTickets(firstname, lastname);
            if (tickets.isEmpty()) System.out.printf(
                "No ticket(s) found for visitor '%s'\n",
                firstname + " " + lastname
            );
            else printTickets(tickets);
          }
          case 3 -> {
            String phone = Util.readVisitorField(sc, "phone number");
            Set<Ticket> tickets = ticketService.getTickets(phone);
            if (tickets.isEmpty()) System.out.printf("No ticket(s) found for phone number '%s'\n", phone);
            else printTickets(tickets);
          }
          case 4 -> {
            LocalDate date = Util.readTicketDate(sc);
            Set<Ticket> tickets = ticketService.getTickets(date);
            if (tickets.isEmpty()) System.out.printf("No ticket(s) found on '%s'\n", date.toString());
            else printTickets(tickets);
          }
          case 5 -> {
            TicketType ticketType = Util.readTicketType(sc);
            Set<Ticket> tickets = ticketService.getTickets(ticketType);
            if (tickets.isEmpty()) System.out.printf("No ticket(s) found for ticket type '%s'\n", ticketType.name());
            else printTickets(tickets);
          }
          case 6 -> {
            Visitor visitor = Util.readVisitor(sc);
            TicketType ticketType = Util.readTicketType(sc);
            LocalDate date = Util.readTicketDate(sc);
            Ticket ticket = ticketService.getTicket(new Ticket(ticketType, visitor, date));
            if (ticket == null) System.out.println("No ticket found");
            else System.out.println("Ticket found: " + ticket);
          }
          case 7 -> {
          }
          default -> System.out.println("Invalid choice. Please try again");
        }
        if (choice > 0 && choice < 8) break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid number between 1 and 7.");
        sc.nextLine(); // Clear the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error while finding tickets: %s
          Please try again or return to Ticket Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes a ticket based on user input. The method displays a submenu with options for the user to choose from and
   * prompts for the necessary input based on the selected option. It then removes the corresponding tickets from the
   * ticket service and prints the results. If no tickets are found, it displays an appropriate message. The method
   * handles invalid choices and input exceptions by prompting the user to try again.
   */
  private void removeTicket() {
    for (;;) {
      try {
        printTicketSubMenuOptions("Remove");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> {
            UUID id = Util.readTicketUUID(sc);
            Ticket ticket = ticketService.getTicket(id);
            if (ticketService.removeTicket(ticket)) System.out.println("Ticket removed");
            else System.out.println("Ticket not found");
          }
          case 2 -> {
            String firstname = Util.readVisitorField(sc, "firstname");
            String lastname = Util.readVisitorField(sc, "lastname");
            Visitor visitor = new Visitor(firstname, lastname, "");
            if (ticketService.removeTicket(visitor)) System.out.println("Ticket(s) removed");
            else System.out.printf("Ticket not found for  visitor '%s'\n", visitor);
          }
          case 3 -> {
            String phone = Util.readVisitorField(sc, "phone number");
            Set<Ticket> tickets = ticketService.getTickets(phone);
            if (ticketService.removeTicket(tickets)) System.out.println("Ticket(s) removed");
            else System.out.printf("No ticket(s) found for phone number '%s'\n", phone);
          }
          case 4 -> {
            LocalDate date = Util.readTicketDate(sc);
            Set<Ticket> tickets = ticketService.getTickets(date);
            if (ticketService.removeTicket(tickets)) System.out.println("Ticket(s) removed");
            else System.out.printf("No ticket(s) found on '%s'\n", date.toString());
          }
          case 5 -> {
            TicketType ticketType = Util.readTicketType(sc);
            Set<Ticket> tickets = ticketService.getTickets(ticketType);
            if (ticketService.removeTicket(tickets)) System.out.println("Ticket(s) removed");
            else System.out.printf("No ticket(s) found for ticket type '%s'\n", ticketType.name());
          }
          case 6 -> {
            Visitor visitor = Util.readVisitor(sc);
            TicketType ticketType = Util.readTicketType(sc);
            LocalDate date = Util.readTicketDate(sc);
            Ticket ticket = ticketService.getTicket(new Ticket(ticketType, visitor, date));
            if (ticketService.removeTicket(ticket)) System.out.println("Ticket removed");
            else System.out.println("No ticket found\n");
          }
          case 7 -> {
          }
          default -> System.out.println("Invalid choice. Please try again.");
        }
        if (choice > 0 && choice < 8) break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 7.");
        sc.nextLine(); // Consume the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error while removing tickets: %s
          Please try again or return to Ticket Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
