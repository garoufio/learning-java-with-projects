package chapter12.project.api;

import chapter12.project.entity.dinosaur.Dinosaur;
import chapter12.project.entity.enclosure.Enclosure;
import chapter12.project.exception.DinosaurIllException;
import chapter12.project.exception.EnclosureBreachException;

import java.util.List;

public class DinosaurCareSystem {
  
  public static final int DINOSAUR_HEALTH_THRESHOLD = 50;
  public static final int ENCLOSURE_SECURITY_THRESHOLD = 70;
  
  private List<Dinosaur> dinosaurs;
  private List<Activity> activities;

  //-------------------------------------------------------------------------------------------------------------------
  
  public void handleDinosaurHealth(List<Dinosaur> dinosaurs) throws DinosaurIllException {
    if (dinosaurs == null || dinosaurs.isEmpty()) return;
    
    for (Dinosaur dinosaur : dinosaurs) {
      if (dinosaur == null) continue;
      if (dinosaur.getHealthScore() < DINOSAUR_HEALTH_THRESHOLD) {
        throw new DinosaurIllException(
            String.format("Dinosaur '%s' is ill with health score: %d", dinosaur, dinosaur.getHealthScore())
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void handleEnclosureSecurity(List<Enclosure> enclosures) throws EnclosureBreachException {
    if (enclosures == null || enclosures.isEmpty()) return;
    
    for (Enclosure enclosure : enclosures) {
      if (enclosure == null) continue;
      if (enclosure.getSecurityLevel() < ENCLOSURE_SECURITY_THRESHOLD) {
        throw new EnclosureBreachException(
            String.format("Enclosure of type '%s' has breached security level: %d",
                enclosure.getEnclosureType(),
                enclosure.getSecurityLevel()
            )
        );
      }
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addDinosaur(Dinosaur dinosaur) {
    if (dinosaurs != null && dinosaur != null) {
      dinosaurs.add(dinosaur);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean removeDinosaur(Dinosaur dinosaur) {
    if (dinosaurs != null && dinosaur != null) {
      return dinosaurs.remove(dinosaur);
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
