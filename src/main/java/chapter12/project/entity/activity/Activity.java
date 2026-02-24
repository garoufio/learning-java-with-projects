package chapter12.project.entity.activity;

import chapter12.project.api.Util;
import chapter12.project.entity.dinosaur.Dinosaur;

import java.time.Duration;
import java.time.LocalDateTime;

public class Activity {
  
  private String name;
  private String description;
  private Dinosaur dinosaur;
  private LocalDateTime tms;
  private Duration duration;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Activity(String name, String description, Dinosaur dinosaur, LocalDateTime tms, Duration duration) {
    this.name = name;
    this.description = description;
    this.dinosaur = dinosaur;
    this.tms = tms;
    this.duration = duration;
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
  
  public Duration getDuration() {
    return duration;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setDuration(Duration duration) {
    this.duration = duration;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Activity [" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", dinosaur=" + dinosaur +
        ", fromTms=" + tms.format(Util.DATE_TIME_FORMAT) +
        ", toTms=" + tms.plus(duration).format(Util.DATE_TIME_FORMAT) +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
