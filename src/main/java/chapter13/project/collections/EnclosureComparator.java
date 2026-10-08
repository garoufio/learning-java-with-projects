package chapter13.project.collections;

import chapter13.project.entity.enclosure.Enclosure;

import java.util.Comparator;

/**
 * Comparator for Enclosure objects based on their type and safety level.
 */
public class EnclosureComparator implements Comparator<Enclosure> {
  
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
  
  /**
   * Returns a default comparator for Enclosure objects.
   * @return a Comparator that compares Enclosure objects by their type and safety level.
   */
  public static Comparator<Enclosure> getDefaultComparator() {
    return new EnclosureComparator();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
