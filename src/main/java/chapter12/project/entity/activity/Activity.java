package chapter12.project.entity.activity;

import chapter12.project.api.Util;
import chapter12.project.entity.dinosaur.Dinosaur;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

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
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Activity activity = (Activity) o;
    
    return Objects.equals(name, activity.name) &&
        Objects.equals(description, activity.description) &&
        Objects.equals(dinosaur, activity.dinosaur) &&
        tms.compareTo(activity.tms) == 0 &&
        duration.compareTo(activity.duration) == 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(name, description, dinosaur, tms, duration);
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
