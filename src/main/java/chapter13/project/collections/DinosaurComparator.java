package chapter13.project.collections;

import chapter13.project.entity.dinosaur.Dinosaur;

import java.util.Comparator;

/**
 * Comparator for Dinosaur objects based on name, birthdate, type, species, and size. DinosaurComparator implements the
 * Comparator interface for Dinosaur objects.
 */
public class DinosaurComparator implements Comparator<Dinosaur> {
  
  public static final Comparator<Dinosaur> BY_NAME =
      (d1, d2) -> d1.getName().compareToIgnoreCase(d2.getName());
  public static final Comparator<Dinosaur> BY_BIRTHDATE =
      (d1, d2) -> d1.getBirthdate().compareTo(d2.getBirthdate());
  public static final Comparator<Dinosaur> BY_AGE =
      (d1, d2) -> Integer.compare(d1.getAge(), d2.getAge());
  public static final Comparator<Dinosaur> BY_TYPE =
      (d1, d2) -> d1.getType().compareTo(d2.getType());
  public static final Comparator<Dinosaur> BY_SPECIES =
      (d1, d2) -> d1.getSpecies().compareTo(d2.getSpecies());
  public static final Comparator<Dinosaur> BY_SIZE =
      (d1, d2) -> d1.getSize().compareTo(d2.getSize());
  public static final Comparator<Dinosaur> BY_HEALTH_SCORE =
      (d1, d2) -> Integer.compare(d1.getHealthScore(), d2.getHealthScore());
  public static final Comparator<Dinosaur> DEFAULT =
      (d1, d2) -> (new DinosaurComparator()).compare(d1, d2);
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares two Dinosaur objects based on name, birthdate, type, species, and size.
   * @param d1 the first Dinosaur to be compared.
   * @param d2 the second Dinosaur to be compared.
   * @return a negative integer, zero, or a positive integer as the first argument is less than, 
   * equal to, or greater than the second.
   */
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
  
}
