package chapter9.project.entity.employee;

import chapter9.project.entity.dinosaur.DinosaurSpecies;

import java.util.Arrays;
import java.util.Objects;

public non-sealed class Curator extends Employee {

  private DinosaurSpecies[] species;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Curator(String name, int yearsOfExperience, DinosaurSpecies... species) {
    super(name, JobTitle.CURATOR, yearsOfExperience);
    copySpecies(species);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurSpecies[] getSpecies() {
    return species;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setSpecies(DinosaurSpecies... species) {
    copySpecies(species);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  private void copySpecies(DinosaurSpecies... species) {
    if (species == null) return;
    
    this.species = new DinosaurSpecies[species.length];
    System.arraycopy(species, 0, this.species, 0, species.length);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    
    Employee e = (Employee) o;
    if (e instanceof Curator c) {
      return Arrays.equals(species, c.species);
    }
    return false;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getYearsOfExperience(), Arrays.hashCode(species));
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Curator: [" +
        "name=" + getName() + ", " +
        "jobTitle=" + getJobTitle().name() +
        ", yearsOfExperience=" + getYearsOfExperience() +
        ", species=" + Arrays.toString(species) +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------

}
