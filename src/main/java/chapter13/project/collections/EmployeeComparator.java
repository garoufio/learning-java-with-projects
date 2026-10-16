package chapter13.project.collections;

import chapter13.project.entity.employee.Employee;

import java.util.Comparator;

/**
 * Comparator for Employee objects based on name, birthdate, and job title. EmployeeComparator implements the
 * Comparator interface for Employee objects.
 */
public class EmployeeComparator implements Comparator<Employee> {
  
  /**
   * Compares two Employee objects based on name, birthdate, and job title.
   * @param e1 the first object to be compared.
   * @param e2 the second object to be compared.
   * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater
   * than the second.
   */
  @Override
  public int compare(Employee e1, Employee e2) {
    if (e1 == null && e2 == null) return 0;
    if (e1 == null) return -1;
    if (e2 == null) return 1;

    // compare by name
    int cmp = e1.getName().compareTo(e2.getName());
    if (cmp != 0) {
      return cmp;
    }

    // compare by birthdate
    cmp = e1.getBirthDate().compareTo(e2.getBirthDate());
    if (cmp != 0) {
      return cmp;
    }
    
    // compare by job title
    return e1.getJobTitle().compareTo(e2.getJobTitle());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a default comparator for Employee objects.
   * @return a Comparator that compares Employee objects by their name, birthdate, and job title.
   */
  public static Comparator<Employee> getDefaultComparator() {
    return new EmployeeComparator();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
