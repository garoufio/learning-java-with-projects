package chapter13.project.collections;

import chapter13.project.entity.employee.Employee;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {
  
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
  
  public static Comparator<Employee> getDefaultComparator() {
    return new EmployeeComparator();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
