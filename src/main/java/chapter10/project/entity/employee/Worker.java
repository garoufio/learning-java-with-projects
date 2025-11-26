package chapter10.project.entity.employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Worker {

  void work(LocalDateTime from, LocalDateTime to);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  void daysOff(LocalDate from, LocalDate to);
  
  //-------------------------------------------------------------------------------------------------------------------

}
