package chapter14.project.collections;

import chapter14.project.entity.enclosure.Enclosure;

import java.util.Comparator;

/**
 * Comparator for Enclosure objects based on their type and safety level. EnclosureComparator implements the
 * Comparator interface for Enclosure objects.
 */
public class EnclosureComparator implements Comparator<Enclosure> {
  
  public static final Comparator<Enclosure> BY_TYPE = Comparator.comparing(Enclosure::getEnclosureType);
  public static final Comparator<Enclosure> BY_SAFETY_LEVEL = Comparator.comparing(Enclosure::getSafetyLevel);
  public static final Comparator<Enclosure> BY_SECURITY_LEVEL = Comparator.comparingInt(Enclosure::getSecurityLevel);
  public static final Comparator<Enclosure> DEFAULT = Comparator.nullsFirst(
      Comparator.comparing(Enclosure::getEnclosureType)
          .thenComparing(Enclosure::getSafetyLevel)
  );
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Compares two Enclosure objects first by their enclosure type and then by their safety level.
   * @param o1 the first Enclosure object to be compared.
   * @param o2 the second Enclosure object to be compared.
   * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater
   * than the second.
   */
  @Override
  public int compare(Enclosure o1, Enclosure o2) {
    return DEFAULT.compare(o1, o2);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
