package chapter12;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Exercise12_1 {
  
  public static void main(String[] args) {
    Dinosaur dino = new Dinosaur("Azgor", LocalDate.of(1990, 5, 15));
    
    System.out.println(dino);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Dinosaur class
   */
  static class Dinosaur {
    
    private String name;
    private LocalDate birthDate;
    
    //-----------------------------------------------------------------------------------------------------------------
    
    Dinosaur(String name, LocalDate birthDate) {
      this.name = name;
      this.birthDate = birthDate;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    public String getName() {
      return name;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    public void setName(String name) {
      this.name = name;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    public LocalDate getBirthDate() {
      return birthDate;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    public void setBirthDate(LocalDate birthDate) {
      this.birthDate = birthDate;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    public int getAge() {
      return Period.between(birthDate, LocalDate.now()).getYears();
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    @Override
    public String toString() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      return "Dinosaur [" +
          "name='" + name +
          ", birthDate=" + birthDate.format(formatter) +
          ", age=" + getAge() +
          "]";
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
