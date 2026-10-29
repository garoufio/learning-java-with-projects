package chapter14.project.entity.activity;

import chapter14.project.api.Util;
import chapter14.project.entity.dinosaur.Dinosaur;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Activity implements Comparable<Activity> {
  
  private String name;
  private String description;
  private Set<Dinosaur> dinosaurs;
  private LocalDateTime tms;
  private Duration duration;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Activity(String name, String description, Set<Dinosaur> dinosaurs, LocalDateTime tms, Duration duration) {
    this.name = name;
    this.description = description;
    this.dinosaurs = dinosaurs;
    this.tms = tms;
    this.duration = duration;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Activity(String name, String description, Dinosaur dinosaur, LocalDateTime tms, Duration duration) {
    this(name, description, Set.of(dinosaur), tms, duration);
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
  
  public Set<Dinosaur> getDinosaurs() {
    return dinosaurs;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setDinosaur(Set<Dinosaur> dinosaurs) {
    this.dinosaurs = dinosaurs;
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
  
  private boolean compareDinosaurs(Set<Dinosaur> otherDinosaurs) {
    if (this.dinosaurs == null && otherDinosaurs == null) return true;
    if (this.dinosaurs == null || otherDinosaurs == null) return false;
    if (this.dinosaurs.size() != otherDinosaurs.size()) return false;
    
    for (Dinosaur dinosaur : this.dinosaurs) {
      if (!otherDinosaurs.contains(dinosaur)) return false;
    }
    return true;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Activity activity = (Activity) o;
    
    return Objects.equals(name, activity.name) &&
        Objects.equals(description, activity.description) &&
        compareDinosaurs(activity.dinosaurs) &&
        tms.compareTo(activity.tms) == 0 &&
        duration.compareTo(activity.duration) == 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(name, description, dinosaurs, tms, duration);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private String formatDinosaurs() {
    if (dinosaurs == null || dinosaurs.isEmpty()) return "[]";
    
    StringBuilder sb = new StringBuilder("[");
    for (Dinosaur d : dinosaurs) {
      sb.append(d.toString()).append(", ");
    }
    // Remove the last comma and space
    sb.setLength(sb.length() - 2);
    sb.append("]");
    
    return sb.toString();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Activity [" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", dinosaur(s)=" + formatDinosaurs() +
        ", fromTms=" + tms.format(Util.DATE_TIME_FORMAT) +
        ", toTms=" + tms.plus(duration).format(Util.DATE_TIME_FORMAT) +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int compareTo(Activity other) {
    if (other == null) return 1;
    
    return this.name.compareTo(other.name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
