package chapter11.project.entity.dinosaur;

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
  
  DinosaurSpecies(String scientificName, double foodScaleFactor) {
    this.scientificName = scientificName;
    this.foodScaleFactor = foodScaleFactor;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public double getFoodScaleFactor() {
    return foodScaleFactor;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getScientificName() {
    return scientificName;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
