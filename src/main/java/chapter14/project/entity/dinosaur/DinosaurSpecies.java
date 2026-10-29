package chapter14.project.entity.dinosaur;

/** 
 * Represents the species of a dinosaur. Each species has a scientific name and a food scale factor.
 * This enum defines various dinosaur species along with their scientific names and food scale factors.
 */
public enum DinosaurSpecies {
  TYRANNOSAURUS("Tyrannosaurus", 1.75),
  TRICERATOPS("Triceratops", 0.75),
  VELOCIRAPTOR("Velociraptor", 1.10),
  STEGOSAURUS("Stegosaurus", 1.5),
  BRACHIOSAURUS("Brachiosaurus", 2.1),
  SPINOSAURUS("Stegosaurus", 1.1),
  PARASAUROLOPHUS("Parasaurolophus", 1.4),
  ANKYLOSAURUS("Ankylosaurus", 1.2),
  PTEROSAUR("Pterosaur",  0.6),
  PLIOSAURS("Pliosaur", 1.8);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private String scientificName;
  private double foodScaleFactor;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructs a new DinosaurSpecies with the specified scientific name and food scale factor.
   * @param scientificName the scientific name of the dinosaur species.
   * @param foodScaleFactor the food scale factor of the dinosaur species.
   */
  DinosaurSpecies(String scientificName, double foodScaleFactor) {
    this.scientificName = scientificName;
    this.foodScaleFactor = foodScaleFactor;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the food scale factor of the dinosaur species.
   * @return the food scale factor of the dinosaur species.
   */
  public double getFoodScaleFactor() {
    return foodScaleFactor;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the scientific name of the dinosaur species.
   * @return the scientific name of the dinosaur species.
   */
  public String getScientificName() {
    return scientificName;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
