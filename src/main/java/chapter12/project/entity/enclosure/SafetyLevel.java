package chapter12.project.entity.enclosure;

public enum SafetyLevel {
  LOW(50),
  MEDIUM(70),
  HIGH(90),
  EMERGENCY(10);
  
  private int levelThreshold;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  SafetyLevel(int levelThreshold) {
    this.levelThreshold = levelThreshold;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getLevelThreshold() {
    return levelThreshold;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
}
