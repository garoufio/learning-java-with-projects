package chapter13.project.collections;

import chapter13.project.entity.enclosure.Enclosure;

import java.util.Comparator;

/**
 * Comparator for Enclosure objects based on their type and safety level. EnclosureComparator implements the
 * Comparator interface for Enclosure objects.
 */
public class EnclosureComparator implements Comparator<Enclosure> {
  
  public static final Comparator<Enclosure> BY_TYPE =
      (e1, e2) -> e1.getEnclosureType().name().compareTo(e2.getEnclosureType().name());
  public static final Comparator<Enclosure> BY_SAFETY_LEVEL =
      (e1, e2) -> e1.getSafetyLevel().compareTo(e2.getSafetyLevel());
  public static final Comparator<Enclosure> BY_SECURITY_LEVEL =
      (e1, e2) -> Integer.compare(e1.getSecurityLevel(), e2.getSecurityLevel());
  public static final Comparator<Enclosure> DEFAULT =
      (e1, e2) -> (new EnclosureComparator()).compare(e1, e2);
  
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
    if (o1 == null && o2 == null) return 0;
    if (o1 == null) return -1;
    if (o2 == null) return 1;

    // compare by enclosure type
    int cmp = o1.getEnclosureType().name().compareTo(o2.getEnclosureType().name());
    if (cmp != 0) return cmp;

    // compare by safety level
    return o1.getSafetyLevel().compareTo(o2.getSafetyLevel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
