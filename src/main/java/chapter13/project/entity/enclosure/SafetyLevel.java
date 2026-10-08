package chapter13.project.entity.enclosure;

/**
 * The SafetyLevel enum represents different levels of safety in the park, each associated with a threshold value.
 */
public enum SafetyLevel {
  LOW(50),
  MEDIUM(70),
  HIGH(90),
  EMERGENCY(10);
  
  private int levelThreshold;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Constructor for the SafetyLevel enum.
   * @param levelThreshold the threshold value associated with the safety level.
   */
  SafetyLevel(int levelThreshold) {
    this.levelThreshold = levelThreshold;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  /**
   * Returns the threshold value associated with the safety level.
   * @return the threshold value for the safety level.
   */
  public int getLevelThreshold() {
    return levelThreshold;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
}
