package chapter12.project.entity.park;

import chapter12.project.entity.activity.Activity;
import chapter12.project.entity.dinosaur.Dinosaur;
import chapter12.project.entity.enclosure.Enclosure;
import chapter12.project.exception.DinosaurIllException;
import chapter12.project.exception.EnclosureBreachException;

import java.util.ArrayList;
import java.util.List;

public class DinosaurCareSystem {
  
  public static final int DINOSAUR_HEALTH_THRESHOLD = 50;
  public static final int ENCLOSURE_SECURITY_THRESHOLD = 70;
  
  private List<Dinosaur> dinosaurs;
  private List<Enclosure> enclosures;
  private List<Activity> activities;

  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurCareSystem() {
    this.dinosaurs = new ArrayList<>();
    this.enclosures = new ArrayList<>();
    this.activities = new ArrayList<>();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void checkDinosaursHealth() throws DinosaurIllException {
    if (dinosaurs == null || dinosaurs.isEmpty()) return;
    
    for (Dinosaur dinosaur : dinosaurs) {
      if (dinosaur == null) continue;
      if (dinosaur.getHealthScore() < DINOSAUR_HEALTH_THRESHOLD) {
        throw new DinosaurIllException(
            String.format(
                "Dinosaur '%s' of species '%s' is ill with health score: %d",
                dinosaur.getName(),
                dinosaur.getSpecies().name(),
                dinosaur.getHealthScore())
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void checkEnclosuresSecurity() throws EnclosureBreachException {
    if (enclosures == null || enclosures.isEmpty()) return;
    
    for (Enclosure enclosure : enclosures) {
      if (enclosure == null) continue;
      if (enclosure.getSecurityLevel() < ENCLOSURE_SECURITY_THRESHOLD) {
        throw new EnclosureBreachException(
            String.format("Enclosure of type '%s' has reached security level: %d",
                enclosure.getEnclosureType(),
                enclosure.getSecurityLevel()
            )
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addDinosaur(Dinosaur dinosaur) {
    if (this.dinosaurs == null || dinosaur == null) return;
    
    this.dinosaurs.add(dinosaur);
  }

  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeDinosaur(Dinosaur dinosaur) {
    if (dinosaurs != null && dinosaur != null) {
      return dinosaurs.remove(dinosaur);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addEnclosure(Enclosure enclosure) {
    if (this.enclosures == null || enclosure == null) return;
    
    this.enclosures.add(enclosure);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeEnclosure(Enclosure enclosure) {
    if (enclosures != null && enclosure != null) {
      return enclosures.remove(enclosure);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void logActivity(Activity activity) {
    if (activities != null && activity != null) {
      activities.add(activity);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeActivity(Activity activity) {
    if (activities != null && activity != null) {
      return activities.remove(activity);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
