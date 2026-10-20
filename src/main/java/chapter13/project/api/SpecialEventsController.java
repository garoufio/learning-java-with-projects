package chapter13.project.api;

import chapter13.project.collections.EventComparator;
import chapter13.project.entity.event.EventType;
import chapter13.project.entity.event.SpecialEvent;
import chapter13.project.entity.ticket.Ticket;
import chapter13.project.entity.ticket.TicketType;
import chapter13.project.entity.ticket.Visitor;
import chapter13.project.service.SpecialEventsService;
import chapter13.project.service.TicketService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Controller class for managing special events in the DinosaurCareSystem. Provides methods to add, find, remove, and 
 * list special events, as well as manage tickets for these events. This controller interacts with the SpecialEventsService
 * and TicketService to perform operations related to special events.
 */
public class SpecialEventsController {
  
  private SpecialEventsService specialEventsService;
  private TicketService ticketsService;
  private Scanner sc;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a SpecialEventsController object. It initializes the controller with the specified Scanner, 
   * SpecialEventsService, and TicketService.
   * @param sc the Scanner for reading user input.
   * @param specialEventsService the service for managing special events.
   * @param ticketsService the service for managing tickets.
   */
  public SpecialEventsController(Scanner sc, SpecialEventsService specialEventsService,  TicketService ticketsService) {
    this.sc = sc;
    this.specialEventsService = specialEventsService;
    this.ticketsService = ticketsService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Handles the special events menu, providing options to show all special events, add a new event, find an event,
   * remove an event, or return to the main menu. It continuously prompts the user for input until they choose to
   * return to the main menu and calls the appropriate methods based on the user's choice.
   */
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
          printSpecialEvents(sortSpecialEvents(specialEventsService.getAllSpecialEvents(), null));
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
  
  /**
   * Prints the list of special events to the console. If the collection is null or empty, it informs the user that no 
   * special events were added.
   * @param specialEvents Collection of SpecialEvents to be printed.
   */
  public void printSpecialEvents(Collection<SpecialEvent> specialEvents) {
    if (specialEvents == null) {
      System.out.println("No special events were added");
      return;
    }
    
    for (SpecialEvent se : specialEvents) {
      if (se != null) System.out.println(se);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Creates a new Ticket object for a visitor attending a special event on the specified date. It prompts the user for 
   * the visitor's information and ticket type.
   * @param sc Scanner for user input.
   * @param date Date of the special event.
   * @return A new Ticket for the visitor attending the special event.
   */
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
  
  /**
   * Adds tickets for a special event by prompting the user for ticket information. It continues to prompt until the 
   * maximum number of visitors for the event is reached or the user chooses to stop.
   * @param sc Scanner for user input.
   * @param event SpecialEvent for which tickets are being added.
   * @return A set of tickets added for the special event.
   */
  private Set<Ticket> addSpecialEventTickets(Scanner sc, SpecialEvent event) {
    if (event == null) return null;
    
    Set<Ticket> tickets = new HashSet<>();
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
  
  /**
   * Adds a new special event to the system by prompting the user for event details, including name, description,
   * contact information, date, time, and event type. It also allows adding tickets for the event.
   */
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
  
  /**
   * Prints the submenu options for finding special events based on the specified action.
   * @param action the action for which the submenu options are being printed (e.g., "Find", "Delete").
   */
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
  
  /**
   * Finds special events based on various criteria specified by the user through a submenu.
   */
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
          Set<SpecialEvent> events = specialEventsService.getSpecialEvents(name, false);
          if (events.isEmpty()) System.out.printf("No event(s) found with name '%s'\n", name);
          else printSpecialEvents(events);
        }
        case 3 -> { // Contact number
          String contactNumber = Util.readSpecialEventField(sc, "contact number");
          Set<SpecialEvent> events = specialEventsService.getSpecialEvents(contactNumber, true);
          if (events.isEmpty()) System.out.printf("No event(s) found for contact number '%s'\n", contactNumber);
          else printSpecialEvents(events);
        }
        case 4 -> { // Date
          LocalDate date = Util.readSpecialEventDate(sc);
          Set<SpecialEvent> events = specialEventsService.getSpecialEvents(date);
          if (events.isEmpty()) System.out.printf("No event(s) found for event date '%s'\n", date);
          else printSpecialEvents(events);
        }
        case 5 -> { // Event type
          EventType eventType = Util.readSpecialEventType(sc);
          Set<SpecialEvent> events = specialEventsService.getSpecialEvents(eventType);
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
  
  /**
   * Removes the specified special event along with its associated tickets.
   * @param event the special event to be removed.
   * @return true if the event and its tickets were successfully removed, false otherwise.
   */
  private boolean removeEvent(SpecialEvent event) {
    if (event == null) return false;
    
    Set<Ticket> tickets = event.getTickets();
    if (specialEventsService.removeSpecialEvent(event)) {
      return ticketsService.removeTicket(tickets);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes the specified set of special events along with their associated tickets. If any of the events do not exist
   * or the set is null, they are ignored.
   * @param events the set of special events to be removed.
   */
  public void removeEvents(Set<SpecialEvent> events) {
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
  
  /**
   * Removes a special event based on user input through a submenu.
   */
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
          Set<SpecialEvent> events = specialEventsService.getSpecialEvents(name, false);
          if (events.isEmpty()) System.out.printf("No event(s) found with name '%s'\n", name);
          else removeEvents(events);
        }
        case 3 -> { // Contact number
          String contactNumber = Util.readSpecialEventField(sc, "contact number");
          Set<SpecialEvent> events = specialEventsService.getSpecialEvents(contactNumber, true);
          if (events .isEmpty()) System.out.printf("No event(s) found for contact number '%s'\n", contactNumber);
          else removeEvents(events);
        }
        case 4 -> { // Date
          LocalDate date = Util.readSpecialEventDate(sc);
          Set<SpecialEvent> events = specialEventsService.getSpecialEvents(date);
          if (events.isEmpty()) System.out.printf("No event(s) found for event date '%s'\n", date);
          else removeEvents(events);
        }
        case 5 -> { // Event type
          EventType eventType = Util.readSpecialEventType(sc);
          Set<SpecialEvent> events = specialEventsService.getSpecialEvents(eventType);
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
  
  /**
   * Sorts the given collection of special events using the specified comparator. If the comparator is null, a default
   * comparator defined in the EventComparator class is used.
   * @param events the collection of special events to be sorted.
   * @param comparator the comparator to determine the order of the events. If null, a default comparator is used.
   * @return a list of sorted special events, or an empty list if the input collection is null or empty.
   */
  public List<SpecialEvent> sortSpecialEvents(Collection<SpecialEvent> events, Comparator<SpecialEvent> comparator) {
    if (events == null || events.isEmpty()) return List.of();
    
    List<SpecialEvent> sortedEvents = new ArrayList<>(events);
    Collections.sort(sortedEvents, comparator == null ? EventComparator.getDefaultComparator() : comparator);
    return sortedEvents;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
