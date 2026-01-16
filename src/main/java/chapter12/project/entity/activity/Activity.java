package chapter12.project.entity.activity;

import chapter12.project.api.Util;
import chapter12.project.entity.dinosaur.Dinosaur;

import java.time.LocalDateTime;

public class Activity {
  
  private String name;
  private String description;
  private LocalDateTime tms;
  private Dinosaur dinosaur;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Activity(String name, String description, LocalDateTime tms, Dinosaur dinosaur) {
    this.name = name;
    this.description = description;
    this.tms = tms;
    this.dinosaur = dinosaur;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getName() {
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setName(String name) {
    this.name = name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getDescription() {
    return description;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public LocalDateTime getTms() {
    return tms;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setTms(LocalDateTime tms) {
    this.tms = tms;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur getDinosaur() {
    return dinosaur;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setDinosaur(Dinosaur dinosaur) {
    this.dinosaur = dinosaur;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Activity [" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", tms=" + tms.format(Util.DATE_TIME_FORMAT) +
        ", dinosaur=" + dinosaur +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
