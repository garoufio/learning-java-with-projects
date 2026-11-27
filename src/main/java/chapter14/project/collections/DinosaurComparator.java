package chapter14.project.collections;

import chapter14.project.entity.dinosaur.Dinosaur;

import java.util.Comparator;

/**
 * Comparator for Dinosaur objects based on name, birthdate, type, species, and size. DinosaurComparator implements the
 * Comparator interface for Dinosaur objects.
 */
public class DinosaurComparator implements Comparator<Dinosaur> {
  
  public static final Comparator<Dinosaur> BY_NAME =
      Comparator.comparing(Dinosaur::getName, String::compareToIgnoreCase);
  public static final Comparator<Dinosaur> BY_BIRTHDATE = Comparator.comparing(Dinosaur::getBirthdate);
  public static final Comparator<Dinosaur> BY_AGE = Comparator.comparingInt(Dinosaur::getAge);
  public static final Comparator<Dinosaur> BY_TYPE = Comparator.comparing(Dinosaur::getType);
  public static final Comparator<Dinosaur> BY_SPECIES = Comparator.comparing(Dinosaur::getSpecies);
  public static final Comparator<Dinosaur> BY_SIZE = Comparator.comparing(Dinosaur::getSize);
  public static final Comparator<Dinosaur> BY_HEALTH_SCORE = Comparator.comparingInt(Dinosaur::getHealthScore);
  public static final Comparator<Dinosaur> DEFAULT = Comparator.nullsFirst(
      Comparator.comparing(Dinosaur::getName)
          .thenComparing(Dinosaur::getBirthdate)
          .thenComparing(Dinosaur::getType)
          .thenComparing(Dinosaur::getSpecies)
          .thenComparing(Dinosaur::getSize)
  );
  
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
    return DEFAULT.compare(d1, d2);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
