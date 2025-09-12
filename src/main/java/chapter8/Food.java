package chapter8;

import java.util.Objects;

public class Food {
  
  private String name;
  private int nutritionalValue;
  private double cost;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Food(String name, int nutritionalValue, double cost) {
    this.name = name;
    setNutritionalValue(nutritionalValue);
    setCost(cost);
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
  
  public int getNutritionalValue() {
    return nutritionalValue;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setNutritionalValue(int nutritionalValue) {
    this.nutritionalValue = nutritionalValue > 0 ? nutritionalValue : -1;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public double getCost() {
    return cost;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void setCost(double cost) {
    this.cost = cost > 0 ? cost : -1.0;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Food food = (Food) o;
    return nutritionalValue == food.nutritionalValue &&
        Double.compare(cost, food.cost) == 0 &&
        Objects.equals(name, food.name);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public int hashCode() {
    return Objects.hash(name, nutritionalValue, cost);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Food [name=" + name + ", nutritionalValue=" + nutritionalValue + ", cost=" + cost + "]";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
