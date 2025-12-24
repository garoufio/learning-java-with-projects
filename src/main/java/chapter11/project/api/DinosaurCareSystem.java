package chapter11.project.api;

import chapter11.project.entity.dinosaur.Dinosaur;
import chapter11.project.entity.enclosure.Enclosure;
import chapter11.project.exception.DinosaurIllException;
import chapter11.project.exception.EnclosureBreachException;

import java.util.List;

public class DinosaurCareSystem {
  
  public static final int DINOSAUR_HEALTH_THRESHOLD = 50;
  public static final int ENCLOSURE_SECURITY_THRESHOLD = 70;

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

}
