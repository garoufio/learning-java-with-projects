package chapter10.project.api;

import chapter10.project.entity.event.EventType;
import chapter10.project.entity.event.SpecialEvent;
import chapter10.project.entity.ticket.Ticket;
import chapter10.project.entity.ticket.TicketType;
import chapter10.project.entity.ticket.Visitor;
import chapter10.project.service.SpecialEventsService;
import chapter10.project.service.TicketService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpecialEventsController {
  
  SpecialEventsService specialEventsService;
  TicketService ticketsService;
  Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public SpecialEventsController(Scanner sc, SpecialEventsService specialEventsService,  TicketService ticketsService) {
    this.sc = sc;
    this.specialEventsService = specialEventsService;
    this.ticketsService = ticketsService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void handleSpecialEvents() {
    for (;;) {
      System.out.printf("\nSpecial events service:\n");
      System.out.println("1. Show all Special Events");
      System.out.println("2. Add Event");
      System.out.println("3. Find Event");
      System.out.println("4. Remove Event");
      System.out.println("5. Return to main menu");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        case 1:
          printSpecialEvents(specialEventsService.getAllSpecialEvents());
          break;
        case 2:
          addSpecialEvent();
          break;
        case 3:
          findSpecialEvent();
          break;
        case 4:
          removeSpecialEvent();
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
  
  public void printSpecialEvents(List<SpecialEvent> specialEvents) {
    if (specialEvents == null) {
      System.out.println("No special events were added");
      return;
    }
    
    for (SpecialEvent se : specialEvents) {
      if (se != null) System.out.println(se);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private Ticket createTicket(Scanner sc, LocalDate date) {
    // read visitor's firstname, lastname and phone number
    Visitor visitor = Util.readVisitor(sc);
    sc.nextLine();
    // read ticket type
    TicketType ticketType = Util.readTicketType(sc);
    sc.nextLine();
    return new Ticket(ticketType, visitor, date);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private List<Ticket> addSpecialEventTickets(Scanner sc, SpecialEvent event) {
    if (event == null) return null;
    
    List<Ticket> tickets = new ArrayList<>();
    for (int i = 0; i < event.getEventType().getNumberOfVisitors(); i++) {
      System.out.print("Add new ticket (Y/N)? ");
      char choice = Character.toUpperCase(sc.next().charAt(0));
      if (choice == 'N') break;
      
      tickets.add(createTicket(sc, event.getDate()));
    }
    ticketsService.addTickets(tickets);
    
    return tickets;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addSpecialEvent() {
    // read name
    String name = Util.readSpecialEventField(sc, "name");
    sc.nextLine();
    // read description
    String description = Util.readSpecialEventField(sc, "description");
    sc.nextLine();
    // contact number
    String contactNumber = Util.readSpecialEventField(sc, "contactNumber");
    sc.nextLine();
    // contact name
    String contactName = Util.readSpecialEventField(sc, "contactName");
    sc.nextLine();
    // contact email
    String contactEmail = Util.readSpecialEventField(sc, "contactEmail");
    sc.nextLine();
    // date
    LocalDate date = Util.readSpecialEventDate(sc);
    sc.nextLine();
    // time
    LocalTime time = Util.readSpecialEventTime(sc);
    sc.nextLine();
    // event type
    EventType eventType = Util.readSpecialEventType(sc);
    sc.nextLine();
    SpecialEvent event = new SpecialEvent(
        name, description, contactNumber, contactName, contactEmail, eventType, date, time
    );
    
    event.setTickets(addSpecialEventTickets(sc, event));
    specialEventsService.addSpecialEvent(event);
    System.out.println("Event added successfully.");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void printSpecialEventSubmenuOptions(String action) {
    System.out.printf("\n%s by:\n", action);
    System.out.println("1. Event id");
    System.out.println("2. Event name");
    System.out.println("3. Event contact phone");
    System.out.println("4. Event date");
    System.out.println("5. Event type");
    System.out.println("6. Detailed search");
    System.out.println("7. Return to event menu");
    System.out.print("Enter your choice: ");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void findSpecialEvent() {
    for (;;) {
      printSpecialEventSubmenuOptions("Find");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> { // UUID
          String id =  Util.readSpecialEventField(sc, "id");
          SpecialEvent event = specialEventsService.getSpecialEvent(id);
          if (event == null) System.out.printf("Special event with id '%s' not found\n", id);
          else System.out.println(event);
        }
        case 2 -> { // Name
          String name = Util.readSpecialEventField(sc, "name");
          List<SpecialEvent> events = specialEventsService.getSpecialEvents(name, false);
          if (events.isEmpty()) System.out.printf("No event(s) found with name '%s'\n", name);
          else printSpecialEvents(events);
        }
        case 3 -> { // Contact number
          String contactNumber = Util.readSpecialEventField(sc, "contact number");
          List<SpecialEvent> events = specialEventsService.getSpecialEvents(contactNumber, true);
          if (events.isEmpty()) System.out.printf("No event(s) found for contact number '%s'\n", contactNumber);
          else printSpecialEvents(events);
        }
        case 4 -> { // Date
          LocalDate date = Util.readSpecialEventDate(sc);
          List<SpecialEvent> events = specialEventsService.getSpecialEvents(date);
          if (events.isEmpty()) System.out.printf("No event(s) found for event date '%s'\n", date);
          else printSpecialEvents(events);
        }
        case 5 -> { // Event type
          EventType eventType = Util.readSpecialEventType(sc);
          List<SpecialEvent> events = specialEventsService.getSpecialEvents(eventType);
          if (events.isEmpty()) System.out.printf("No event(s) found for event type '%s'\n", eventType.name());
          else printSpecialEvents(events);
        }
        case 6 -> {
          // read name
          String name = Util.readSpecialEventField(sc, "name");
          sc.nextLine();
          // read description
          String description = Util.readSpecialEventField(sc, "description");
          sc.nextLine();
          // contact number
          String contactNumber = Util.readSpecialEventField(sc, "contactNumber");
          sc.nextLine();
          // contact name
          String contactName = Util.readSpecialEventField(sc, "contactName");
          sc.nextLine();
          // contact email
          String contactEmail = Util.readSpecialEventField(sc, "contactEmail");
          sc.nextLine();
          // date
          LocalDate date = Util.readSpecialEventDate(sc);
          sc.nextLine();
          // time
          LocalTime time = Util.readSpecialEventTime(sc);
          sc.nextLine();
          // event type
          EventType eventType = Util.readSpecialEventType(sc);
          sc.nextLine();
          SpecialEvent event = new SpecialEvent(
              name, description, contactNumber, contactName, contactEmail, eventType, date, time
          );
          SpecialEvent specialEvent = specialEventsService.getSpecialEvent(event);
          if (specialEvent == null) System.out.println("No event found!");
          else System.out.printf("Event found: '%s'\n", specialEvent);
        }
        case 7 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice < 8) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private boolean removeEvent(SpecialEvent event) {
    if (event == null) return false;
    
    List<Ticket> tickets = event.getTickets();
    if (specialEventsService.removeSpecialEvent(event)) {
      return ticketsService.removeTicket(tickets);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void removeEvents(List<SpecialEvent> events) {
    if (events == null || events.isEmpty()) {
      System.out.println("No events were removed");
      return;
    }
    
    for (SpecialEvent se : events) {
      if (se == null) continue;
      
      System.out.printf("Removing event '%s'", se);
      if (removeEvent(se)) {
        System.out.printf(" - Removed successfully\n");
      } else {
        System.out.printf(" - Failed\n");
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void removeSpecialEvent() {
    for (;;) {
      printSpecialEventSubmenuOptions("Remove");
      int choice = sc.nextInt();
      switch (choice) {
        case 1 -> { // UUID
          String id =  Util.readSpecialEventField(sc, "id");
          SpecialEvent event = specialEventsService.getSpecialEvent(id);
          if (event == null) System.out.printf("Special event with id '%s' not found\n", id);
          else {
            if (removeEvent(event)) System.out.println("Event removed!");
            else System.out.printf("Unable to remove event with id '%s'\n", id);
          }
        }
        case 2 -> { // Name
          String name = Util.readSpecialEventField(sc, "name");
          List<SpecialEvent> events = specialEventsService.getSpecialEvents(name, false);
          if (events.isEmpty()) System.out.printf("No event(s) found with name '%s'\n", name);
          else removeEvents(events);
        }
        case 3 -> { // Contact number
          String contactNumber = Util.readSpecialEventField(sc, "contact number");
          List<SpecialEvent> events = specialEventsService.getSpecialEvents(contactNumber, true);
          if (events .isEmpty()) System.out.printf("No event(s) found for contact number '%s'\n", contactNumber);
          else removeEvents(events);
        }
        case 4 -> { // Date
          LocalDate date = Util.readSpecialEventDate(sc);
          List<SpecialEvent> events = specialEventsService.getSpecialEvents(date);
          if (events.isEmpty()) System.out.printf("No event(s) found for event date '%s'\n", date);
          else removeEvents(events);
        }
        case 5 -> { // Event type
          EventType eventType = Util.readSpecialEventType(sc);
          List<SpecialEvent> events = specialEventsService.getSpecialEvents(eventType);
          if (events.isEmpty()) System.out.printf("No event(s) found for event type '%s'\n", eventType.name());
          else removeEvents(events);
        }
        case 6 -> {
          // read name
          String name = Util.readSpecialEventField(sc, "name");
          sc.nextLine();
          // read description
          String description = Util.readSpecialEventField(sc, "description");
          sc.nextLine();
          // contact number
          String contactNumber = Util.readSpecialEventField(sc, "contactNumber");
          sc.nextLine();
          // contact name
          String contactName = Util.readSpecialEventField(sc, "contactName");
          sc.nextLine();
          // contact email
          String contactEmail = Util.readSpecialEventField(sc, "contactEmail");
          sc.nextLine();
          // date
          LocalDate date = Util.readSpecialEventDate(sc);
          sc.nextLine();
          // time
          LocalTime time = Util.readSpecialEventTime(sc);
          sc.nextLine();
          // event type
          EventType eventType = Util.readSpecialEventType(sc);
          sc.nextLine();
          SpecialEvent event = new SpecialEvent(
              name, description, contactNumber, contactName, contactEmail, eventType, date, time
          );
          SpecialEvent specialEvent = specialEventsService.getSpecialEvent(event);
          if (specialEvent == null) System.out.println("No event found!");
          else {
            if (removeEvent(event)) System.out.println("Event removed!");
            else System.out.println("Unable to remove event");
          }
        }
        case 7 -> { }
        default -> System.out.println("Invalid choice. Please try again.");
      }
      if (choice > 0 && choice < 8) break;
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
