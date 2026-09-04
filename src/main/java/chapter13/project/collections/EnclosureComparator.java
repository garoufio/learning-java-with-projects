package chapter13.project.collections;

import chapter13.project.entity.enclosure.Enclosure;

import java.util.Comparator;

public class EnclosureComparator implements Comparator<Enclosure> {
  
  @Override
  public int compare(Enclosure o1, Enclosure o2) {
    if (o1 == null && o2 == null) return 0;
    if (o1 == null) return -1;
    if (o2 == null) return 1;

    // compare by enclosure type
    int cmp = o1.getEnclosureType().name().compareTo(o2.getEnclosureType().name());
    if (cmp != 0) {
      return cmp;
    }

    // compare by safety level
    return o1.getSafetyLevel().compareTo(o2.getSafetyLevel());
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static Comparator<Enclosure> getDefaultComparator() {
    return new EnclosureComparator();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
