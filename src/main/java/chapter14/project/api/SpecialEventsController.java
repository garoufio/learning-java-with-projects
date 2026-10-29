package chapter14.project.api;

import chapter14.project.collections.EventComparator;
import chapter14.project.entity.event.EventType;
import chapter14.project.entity.event.SpecialEvent;
import chapter14.project.entity.ticket.Ticket;
import chapter14.project.entity.ticket.TicketType;
import chapter14.project.entity.ticket.Visitor;
import chapter14.project.service.SpecialEventsService;
import chapter14.project.service.TicketService;
import chapter14.project.exception.InputException;
import chapter14.project.exception.AppException;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

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
      try {
        System.out.print("""
            
            Special events service:
            1. Show all Special Events
            2. Add Event
            3. Find Event
            4. Remove Event
            5. Return to main menu
            Enter your choice:\t"""
        );
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> printSpecialEvents();
          case 2 -> addSpecialEvent();
          case 3 -> findSpecialEvent();
          case 4 -> removeSpecialEvent();
          case 5 -> System.out.println("Returning to main menu...");
          default -> System.out.println("Invalid choice. Please try again.");
        }
        if (choice == 5) {
          System.out.println();
          break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 5.");
        sc.nextLine(); // Consume the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error: %s
          Please try again or return to Main Menu...
        """, e.getMessage()
        );
      } catch (AppException e) {
        System.out.printf("""
          Error while managing Special Events: %s
          Please try again or return to Main Menu...
        """, e.getMessage()
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints the list of special events to the console. If the collection is null or empty, it informs the user that no 
   * special events were added.
   * @param specialEvents Collection of SpecialEvents to be printed.
   */
  private void printSpecialEvents(Collection<SpecialEvent> specialEvents) {
    if (specialEvents == null || specialEvents.isEmpty()) {
      System.out.println("No special events found.");
      return;
    }
    
    for (SpecialEvent se : specialEvents) {
      if (se != null) System.out.println(se);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Prints a submenu for sorting and displaying special events based on various criteria. The user can choose to sort
   * the events by ID, name, description, contact number, contact name, contact email, date, time, or event type, or
   * return to the special events' menu. The method handles user input and calls the appropriate sorting and printing
   * methods based on the user's choice.
   */
  public void printSpecialEvents() {
    for (;;) {
      try {
        System.out.print("""
            
            Print Special Events sorted by:
            1. No sorting
            2. Default
            3. ID
            4. Name
            5. Description
            6. Contact Number
            7. Contact Name
            8. Contact Email
            9. Date
            10. Time
            11. Event Type
            12. Return to Special Events Menu
            Enter your choice:\t"""
        );
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        
        switch (choice) {
          case 1 -> printSpecialEvents(specialEventsService.getAllSpecialEvents());
          case 2 ->
              printSpecialEvents(sortSpecialEvents(specialEventsService.getAllSpecialEvents(), null));
          case 3 ->
              printSpecialEvents(sortSpecialEvents(specialEventsService.getAllSpecialEvents(), EventComparator.BY_ID));
          case 4 ->
              printSpecialEvents(sortSpecialEvents(specialEventsService.getAllSpecialEvents(), EventComparator.BY_NAME));
          case 5 ->
              printSpecialEvents(sortSpecialEvents(specialEventsService.getAllSpecialEvents(), EventComparator.BY_DESCRIPTION));
          case 6 ->
              printSpecialEvents(sortSpecialEvents(specialEventsService.getAllSpecialEvents(), EventComparator.BY_CONTACT_NUMBER));
          case 7 ->
              printSpecialEvents(sortSpecialEvents(specialEventsService.getAllSpecialEvents(), EventComparator.BY_CONTACT_NAME));
          case 8 ->
              printSpecialEvents(sortSpecialEvents(specialEventsService.getAllSpecialEvents(), EventComparator.BY_CONTACT_EMAIL));
          case 9 ->
              printSpecialEvents(sortSpecialEvents(specialEventsService.getAllSpecialEvents(), EventComparator.BY_DATE));
          case 10 ->
              printSpecialEvents(sortSpecialEvents(specialEventsService.getAllSpecialEvents(), EventComparator.BY_TIME));
          case 11 ->
              printSpecialEvents(sortSpecialEvents(specialEventsService.getAllSpecialEvents(), EventComparator.BY_EVENT_TYPE));
          case 12 -> System.out.println("Returning to Special Events Menu...");
          default -> System.out.println("Invalid choice. Please try again.");
        }
        if (choice > 0 && choice < 13) break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 12.");
        sc.nextLine(); // Consume the invalid input
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Creates a new Ticket object for a visitor attending a special event on the specified date. It prompts the user for 
   * the visitor's information and ticket type.
   * @param sc Scanner for user input.
   * @param date Date of the special event.
   * @return A new Ticket for the visitor attending the special event.
   * @throws InputException if there is an error in the input provided by the user.
   */
  private Ticket createTicket(Scanner sc, LocalDate date) throws InputException {
    // read visitor's firstname, lastname and phone number
    Visitor visitor = Util.readVisitor(sc);
    // read ticket type
    TicketType ticketType = Util.readTicketType(sc);
    return new Ticket(ticketType, visitor, date);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds tickets for a special event by prompting the user for ticket information. It continues to prompt until the 
   * maximum number of visitors for the event is reached or the user chooses to stop.
   * @param sc Scanner for user input.
   * @param event SpecialEvent for which tickets are being added.
   * @return A set of tickets added for the special event.
   * @throws InputException if there is an error in the input provided by the user.
   */
  private Set<Ticket> addSpecialEventTickets(Scanner sc, SpecialEvent event) throws InputException {
    if (event == null) return null;
    
    Set<Ticket> tickets = new HashSet<>();
    for (int i = 0; i < event.getEventType().getNumberOfVisitors(); i++) {
      System.out.print("Add new ticket (Y/N)? ");
      char choice = Character.toUpperCase(sc.next().charAt(0));
      sc.nextLine(); // Consume the newline character after next()
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
   * @throws InputException if there is an error in the input provided by the user.
   */
  public void addSpecialEvent() throws InputException {
    // read name
    String name = Util.readSpecialEventField(sc, "name");
    // read description
    String description = Util.readSpecialEventField(sc, "description");
    // contact number
    String contactNumber = Util.readSpecialEventField(sc, "contactNumber");
    // contact name
    String contactName = Util.readSpecialEventField(sc, "contactName");
    // contact email
    String contactEmail = Util.readSpecialEventField(sc, "contactEmail");
    // date
    LocalDate date = Util.readSpecialEventDate(sc);
    // time
    LocalTime time = Util.readSpecialEventTime(sc);
    // event type
    EventType eventType = Util.readSpecialEventType(sc);
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
    System.out.printf("""
    
    %s by:
    1. Event id
    2. Event name
    3. Event contact phone
    4. Event date
    5. Event type
    6. Detailed search
    7. Return to event menu
    Enter your choice:\t""",
        action
    );
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Finds special events based on various criteria specified by the user through a submenu.
   */
  public void findSpecialEvent() {
    for (;;) {
      try {
        printSpecialEventSubmenuOptions("Find");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> { // UUID
            String id = Util.readSpecialEventField(sc, "id");
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
            // read description
            String description = Util.readSpecialEventField(sc, "description");
            // contact number
            String contactNumber = Util.readSpecialEventField(sc, "contactNumber");
            // contact name
            String contactName = Util.readSpecialEventField(sc, "contactName");
            // contact email
            String contactEmail = Util.readSpecialEventField(sc, "contactEmail");
            // date
            LocalDate date = Util.readSpecialEventDate(sc);
            // time
            LocalTime time = Util.readSpecialEventTime(sc);
            // event type
            EventType eventType = Util.readSpecialEventType(sc);
            SpecialEvent event = new SpecialEvent(
                name, description, contactNumber, contactName, contactEmail, eventType, date, time
            );
            SpecialEvent specialEvent = specialEventsService.getSpecialEvent(event);
            if (specialEvent == null) System.out.println("No event found!");
            else System.out.printf("Event found: '%s'\n", specialEvent);
          }
          case 7 -> {
          }
          default -> System.out.println("Invalid choice. Please try again.");
        }
        if (choice < 8) break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 7.");
        sc.nextLine(); // Consume the invalid input
      } catch (InputException e) {
        System.out.printf("""
          Error while finding Special Events: %s
          Please try again or return to Special Events Menu...
        """, e.getMessage()
        );
      }
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
      try {
        printSpecialEventSubmenuOptions("Remove");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()
        switch (choice) {
          case 1 -> { // UUID
            String id = Util.readSpecialEventField(sc, "id");
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
            if (events.isEmpty()) System.out.printf("No event(s) found for contact number '%s'\n", contactNumber);
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
            // read description
            String description = Util.readSpecialEventField(sc, "description");
            // contact number
            String contactNumber = Util.readSpecialEventField(sc, "contactNumber");
            // contact name
            String contactName = Util.readSpecialEventField(sc, "contactName");
            // contact email
            String contactEmail = Util.readSpecialEventField(sc, "contactEmail");
            // date
            LocalDate date = Util.readSpecialEventDate(sc);
            // time
            LocalTime time = Util.readSpecialEventTime(sc);
            // event type
            EventType eventType = Util.readSpecialEventType(sc);
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
          Error while removing Special Events: %s
          Please try again or return to Special Events Menu...
        """, e.getMessage()
        );
      }
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
    Collections.sort(sortedEvents, comparator == null ? EventComparator.DEFAULT : comparator);
    return sortedEvents;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
