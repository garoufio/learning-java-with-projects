package chapter14.project.entity.park;

import chapter14.project.entity.activity.Activity;
import chapter14.project.entity.dinosaur.Dinosaur;
import chapter14.project.entity.enclosure.Enclosure;
import chapter14.project.exception.DinosaurIllException;
import chapter14.project.exception.EnclosureBreachException;

import java.util.HashSet;
import java.util.Set;

/**
 * Manages the health and security of dinosaurs and enclosures within a park. Provides methods to check dinosaur health, 
 * enclosure security, and log activities.
 * @see Dinosaur
 * @see Enclosure
 * @see Activity
 */
public class DinosaurCareSystem {
  
  public static final int DINOSAUR_HEALTH_THRESHOLD = 50;
  public static final int ENCLOSURE_SECURITY_THRESHOLD = 70;
  
  private Set<Dinosaur> dinosaurs;
  private Set<Enclosure> enclosures;
  private Set<Activity> activities;

  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new DinosaurCareSystem with empty sets of dinosaurs, enclosures, and activities.
   * @see Dinosaur
   * @see Enclosure
   * @see Activity
   */
  public DinosaurCareSystem() {
    this.dinosaurs = new HashSet<>();
    this.enclosures = new HashSet<>();
    this.activities = new HashSet<>();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Checks the health of all dinosaurs in the system and throws an exception if any dinosaur is ill.
   * @throws DinosaurIllException
   */
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
  
  /**
   * Checks the security of all enclosures in the system and throws an exception if any enclosure is breached.
   * @throws EnclosureBreachException
   */
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
  
  /**
   * Adds a new dinosaur to the system.
   * @param dinosaur the dinosaur, see {@link Dinosaur}, to be added.
   */
  public void addDinosaur(Dinosaur dinosaur) {
    if (this.dinosaurs == null || dinosaur == null) return;
    
    this.dinosaurs.add(dinosaur);
  }

  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes a dinosaur from the system if it exists.
   * @param dinosaur the dinosaur, see {@link Dinosaur}, to be removed.
   * @return true if the dinosaur was successfully removed, false otherwise.
   */
  public boolean removeDinosaur(Dinosaur dinosaur) {
    if (dinosaurs != null && dinosaur != null) {
      return dinosaurs.remove(dinosaur);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns a list of all dinosaurs currently in the system.
   * @return an unmodifiable Set of dinosaurs.
   */
  public Set<Dinosaur> getDinosaurs() {
    if (dinosaurs == null || dinosaurs.isEmpty()) return Set.of();
    
    return Set.copyOf(dinosaurs);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Adds a new enclosure to the system.
   * @param enclosure the enclosure, see {@link Enclosure}, to be added.
   */
  public void addEnclosure(Enclosure enclosure) {
    if (this.enclosures == null || enclosure == null) return;
    
    this.enclosures.add(enclosure);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes an enclosure from the system if it exists.
   * @param enclosure the enclosure, see {@link Enclosure}, to be removed.
   * @return true if the enclosure was successfully removed, false otherwise.
   */ 
  public boolean removeEnclosure(Enclosure enclosure) {
    if (enclosures != null && enclosure != null) {
      return enclosures.remove(enclosure);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Logs a new activity in the system.
   * @param activity the activity, see {@link Activity}, to be logged.
   */
  public void logActivity(Activity activity) {
    if (activities != null && activity != null) {
      activities.add(activity);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Removes an activity from the system if it exists.
   * @param activity the activity, see {@link Activity}, to be removed.
   * @return true if the activity was successfully removed, false otherwise.
   */
  public boolean removeActivity(Activity activity) {
    if (activities != null && activity != null) {
      return activities.remove(activity);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Retrieves all activities currently logged in the system.
   * @return a set of activities, see {@link Activity}, currently logged in the system.
   */
  public Set<Activity> getActivities() {
    if (activities == null || activities.isEmpty()) return Set.of();
    
    return Set.copyOf(activities);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
