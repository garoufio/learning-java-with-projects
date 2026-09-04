package chapter13.project.collections;

import chapter13.project.entity.dinosaur.Dinosaur;

import java.util.Comparator;

public class DinosaurComparator implements Comparator<Dinosaur> {
  
  @Override
  public int compare(Dinosaur d1, Dinosaur d2) {
    if (d1 == null && d2 == null) return 0;
    if (d1 == null) return -1;
    if (d2 == null) return 1;

    // compare by name
    int cmp = d1.getName().compareTo(d2.getName());
    if (cmp != 0) {
      return cmp;
    }

    // compare by birthdate
    cmp = d1.getBirthdate().compareTo(d2.getBirthdate());
    if (cmp != 0) {
      return cmp;
    }
    
    // compare by type
    cmp = d1.getType().compareTo(d2.getType());
    if (cmp != 0) {
      return cmp;
    }
    
    // compare by species
    cmp = d1.getSpecies().compareTo(d2.getSpecies());
    if (cmp != 0) {
      return cmp;
    }
    
    // compare by size
    return d1.getSize().compareTo(d2.getSize());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static Comparator<Dinosaur> getDefaultComparator() {
    return new DinosaurComparator();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
