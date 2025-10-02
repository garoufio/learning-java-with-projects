package chapter8.project;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class TicketController {
  
  private TicketService ticketService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public TicketController(Scanner sc, TicketService ticketService) {
    this.sc = sc;
    this.ticketService = ticketService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void manageTickets() {
    for (;;) {
      System.out.printf("\nTickets service:\n");
      System.out.println("1. Show all Tickets");
      System.out.println("2. Add Ticket");
      System.out.println("3. Find Ticket");
      System.out.println("4. Remove Ticket");
      System.out.println("5. Return to main menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          printTickets(ticketService.getAllTickets());
          break;
        case 2:
          addTicket();
          break;
        case 3:
          findTicket();
          break;
        case 4:
          removeTicket();
          break;
        case 5:
          System.out.println("Returning to main menu...");
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
      if (choice == 5) {
        System.out.println();
        break;
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void printTickets(Ticket... tickets) {
    if (tickets == null || tickets.length == 0) {
      System.out.println("No Tickets were added!");
      return;
    }
    
    for (Ticket ticket : tickets) {
      if (ticket == null) continue;
      
      System.out.println(ticket);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void addTicket() {
    // read visitor's firstname, lastname and phone number
    Visitor visitor = Util.readVisitor(sc);
    sc.nextLine();
    // read ticket type
    TicketType ticketType = Util.readTicketType(sc);
    // read date
    LocalDate date = Util.readTicketDate(sc);
    sc.nextLine();
    ticketService.addTickets(new Ticket(ticketType, visitor, date));
    System.out.println("Ticket added!");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void printTicketSubMenuOptions(String action) {
    System.out.printf("\n%s by:\n", action);
    System.out.println("1. Ticket ID");
    System.out.println("2. Ticket Name");
    System.out.println("3. Ticket Phone");
    System.out.println("4. Ticket Date");
    System.out.println("5. Ticket Type");
    System.out.println("6. Detail search");
    System.out.println("7. Return to ticket menu");
    System.out.print("Enter your choice: ");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void findTicket() {
    for (;;) {
      printTicketSubMenuOptions("Find");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          UUID id = Util.readTicketUUID(sc);
          sc.nextLine();
          Ticket ticket = ticketService.getTicket(id);
          if (ticket == null) System.out.printf("Ticket with ID '%s' not found!\n", id);
          else System.out.printf("Ticket found: '%s'\n", ticket);
        }
        case 2 -> {
          String firstname = Util.readVisitorField(sc, "firstname");
          String lastname = Util.readVisitorField(sc, "lastname");
          Visitor visitor = new Visitor(firstname, lastname, "");
          Ticket[] tickets = ticketService.getTickets(visitor);
          if (tickets == null) System.out.printf(
              "No ticket(s) found for visitor '%s'!\n",
              visitor.firstname() + " " + visitor.lastname()
          );
          else printTickets(tickets);
        }
        case 3 -> {
          String phone = Util.readVisitorField(sc, "phone number");
          Ticket[] tickets = ticketService.getTickets(phone);
          if (tickets == null) System.out.printf("No ticket(s) found for phone number '%s'!\n", phone);
          else printTickets(tickets);
        }
        case 4 -> {
          LocalDate date = Util.readTicketDate(sc);
          Ticket[] tickets = ticketService.getTickets(date);
          if (tickets == null) System.out.printf("No ticket(s) found on '%s'!\n", date.toString());
          else printTickets(tickets);
        }
        case 5 -> {
          TicketType ticketType = Util.readTicketType(sc);
          Ticket[] tickets = ticketService.getTickets(ticketType);
          if (tickets == null) System.out.printf("No ticket(s) found for ticket type '%s'!\n", ticketType.name());
          else printTickets(tickets);
        }
        case 6 -> {
          Visitor visitor = Util.readVisitor(sc);
          TicketType ticketType = Util.readTicketType(sc);
          sc.nextLine();
          LocalDate date = Util.readTicketDate(sc);
          Ticket ticket = ticketService.getTicket(new Ticket(ticketType, visitor, date));
          if (ticket == null) System.out.println("No ticket found!");
          else System.out.println("Ticket found: " + ticket);
        }
        case 7 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 8) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void removeTicket() {
    for (;;) {
      printTicketSubMenuOptions("Remove");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          UUID id = Util.readTicketUUID(sc);
          sc.nextLine();
          Ticket ticket = ticketService.getTicket(id);
          if (ticketService.removeTicket(ticket)) System.out.println("Ticket removed");
          else System.out.println("Ticket not found");
        }
        case 2 -> {
          String firstname = Util.readVisitorField(sc,"firstname");
          String lastname = Util.readVisitorField(sc,"lastname");
          Visitor visitor = new Visitor(firstname, lastname, "");
          if (ticketService.removeTicket(visitor)) System.out.println("Ticket(s) removed");
          else System.out.printf("Ticket not found for  visitor '%s'\n", visitor);
        }
        case 3 -> {
          String phone = Util.readVisitorField(sc, "phone number");
          Ticket[] tickets = ticketService.getTickets(phone);
          if (ticketService.removeTicket(tickets)) System.out.println("Ticket(s) removed");
          else System.out.printf("No ticket(s) found for phone number '%s'\n", phone);
        }
        case 4 -> {
          LocalDate date = Util.readTicketDate(sc);
          Ticket[] tickets = ticketService.getTickets(date);
          if (ticketService.removeTicket(tickets)) System.out.println("Ticket(s) removed");
          else System.out.printf("No ticket(s) found on '%s'\n", date.toString());
        }
        case 5 -> {
          TicketType ticketType = Util.readTicketType(sc);
          Ticket[] tickets = ticketService.getTickets(ticketType);
          if (ticketService.removeTicket(tickets)) System.out.println("Ticket(s) removed");
          else System.out.printf("No ticket(s) found for ticket type '%s'\n", ticketType.name());
        }
        case 6 -> {
          Visitor visitor = Util.readVisitor(sc);
          sc.nextLine();
          TicketType ticketType = Util.readTicketType(sc);
          sc.nextLine();
          LocalDate date = Util.readTicketDate(sc);
          sc.nextLine();
          Ticket ticket = ticketService.getTicket(new Ticket(ticketType, visitor, date));
          if (ticketService.removeTicket(ticket)) System.out.println("Ticket removed");
          else System.out.println("No ticket found\n");
        }
        case 7 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 8) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
