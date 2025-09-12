package chapter8;

public class ComplexEnums {
  
  public static void main(String[] args) {
    WorkDay workDayMonday = WorkDay.MONDAY;
    WorkDay workDaySaturday = WorkDay.SATURDAY;
    
    System.out.println(workDayMonday.ordinal());
    System.out.println(workDayMonday.name());
    System.out.println(workDaySaturday.ordinal());
    System.out.println(workDaySaturday.name());
    
    for (WorkDay workDay : WorkDay.values()) {
      System.out.printf("Hours of work: %s, location: %s\n", workDay.getHoursOfWork(), workDay.getWorkLocation());
    }
  }
  
}
