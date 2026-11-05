package chapter14.exercises;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Dinosaur implements Comparable<Dinosaur> {
  
  public static final int DEFAULT_HEALTHSCORE = 75;
  
  private String name;
  private LocalDate birthDate;
  private int healthScore;
  private boolean awake;
  private boolean carnivorous;
  private String diet;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur(String name, LocalDate birthDate, int healthScore, boolean carnivorous, String diet) {
    this.name = name;
    this.birthDate = birthDate;
    setHealthScore(healthScore);
    this.awake = true;
    this.carnivorous = carnivorous;
    this.diet = diet;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur(String name, LocalDate birthDate, int healthScore, boolean carnivorous) {
    this(name, birthDate, healthScore, carnivorous, carnivorous ? "Meat" : "Plants");
  }

  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur(String name, LocalDate birthDate, int healthScore) {
    this(name, birthDate, healthScore, false);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Dinosaur(String name, LocalDate birthDate) {
    this(name, birthDate, DEFAULT_HEALTHSCORE);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public String getName() {
    return name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setName(String name) {
    this.name = name;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public LocalDate getBirthDate() {
    return birthDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public int getHealthScore() {
    return healthScore;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setHealthScore(int healthScore) {
    this.healthScore = healthScore < 0 ? DEFAULT_HEALTHSCORE : healthScore;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public boolean isAwake() {
    return awake;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setAwake(boolean awake) {
    this.awake = awake;
  }
  
  //-------------------------------------------------------------------------------------------------------------------

  public boolean isCarnivore() {
    return carnivorous;
  }

  //-------------------------------------------------------------------------------------------------------------------

  public void setCarnivorous(boolean carnivorous) {
    this.carnivorous = carnivorous;
  }

  //-------------------------------------------------------------------------------------------------------------------
  
  public String getDiet() {
    return diet;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setDiet(String diet) {
    this.diet = diet;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " [" +
        "name=" + name +
        ", birthDate=" + birthDate.format(DateTimeFormatter.ISO_DATE) +
        ", healthScore=" + healthScore +
        ", isAwake=" + awake +
        ", isCarnivore=" + carnivorous +
        ", diet=" + diet +
        "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Dinosaur dinosaur = (Dinosaur) o;
    return name.equals(dinosaur.name) && birthDate.compareTo(dinosaur.birthDate) == 0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(name, birthDate);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int compareTo(Dinosaur o) {
    return name.compareTo(o.name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
