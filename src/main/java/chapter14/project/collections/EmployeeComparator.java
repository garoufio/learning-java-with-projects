package chapter14.project.collections;

import chapter14.project.entity.employee.Employee;
import chapter14.project.entity.employee.JobTitle;

import java.util.Comparator;

/**
 * Comparator for Employee objects based on name, birthdate, and job title. EmployeeComparator implements the
 * Comparator interface for Employee objects.
 */
public class EmployeeComparator implements Comparator<Employee> {
  
  private static final Comparator<JobTitle> JOB_TITLE_BY_TITLE =
      Comparator.comparing(JobTitle::getTitle, String::compareToIgnoreCase);
  public static final Comparator<Employee> BY_NAME =
      Comparator.comparing(Employee::getName, String::compareToIgnoreCase);
  public static final Comparator<Employee> BY_BIRTHDATE = Comparator.comparing(Employee::getBirthDate);
  public static final Comparator<Employee> BY_AGE = Comparator.comparingInt(Employee::getAge);
  public static final Comparator<Employee> BY_JOB_TITLE =
      Comparator.comparing(Employee::getJobTitle, JOB_TITLE_BY_TITLE);
  public static final Comparator<Employee> DEFAULT = Comparator.nullsFirst(
      Comparator.comparing(Employee::getName)
          .thenComparing(Employee::getBirthDate)
          .thenComparing(Employee::getJobTitle, JOB_TITLE_BY_TITLE)
  );
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares two Employee objects based on name, birthdate, and job title.
   * @param e1 the first object to be compared.
   * @param e2 the second object to be compared.
   * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater
   * than the second.
   */
  @Override
  public int compare(Employee e1, Employee e2) {
    return DEFAULT.compare(e1, e2);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
