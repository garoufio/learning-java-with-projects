package chapter12.project.service;

import chapter12.project.entity.activity.Activity;
import chapter12.project.entity.dinosaur.Dinosaur;
import chapter12.project.entity.enclosure.Enclosure;
import chapter12.project.entity.park.DinosaurCareSystem;
import chapter12.project.exception.DinosaurIllException;

import java.util.List;

public class DinosaurCareSystemService {
  
  private DinosaurCareSystem dinosaurCareSystem;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public DinosaurCareSystemService() {
    this.dinosaurCareSystem = new DinosaurCareSystem();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addDinosaurs(Dinosaur... dinosaurs) {
    if (dinosaurs == null || dinosaurs.length == 0) {
      System.out.println("No dinosaurs were added into the Dinosaur Care System");
      return;
    }
    for (Dinosaur d : dinosaurs) {
      if (d != null) dinosaurCareSystem.addDinosaur(d);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addDinosaurs(List<Dinosaur> dinosaurs) {
    if (dinosaurs == null || dinosaurs.isEmpty()) {
      System.out.println("No dinosaurs were added into the Dinosaur Care System");
      return;
    }
    for (Dinosaur d : dinosaurs) {
      if (d != null) dinosaurCareSystem.addDinosaur(d);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void removeDinosaur(Dinosaur dinosaur) {
    if (dinosaur == null) {
      System.out.println("No dinosaur was removed from the Dinosaur Care System");
      return;
    }
    dinosaurCareSystem.removeDinosaur(dinosaur);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void checkDinosaursHealth() {
    try {
      dinosaurCareSystem.checkDinosaursHealth();
      System.out.println("All dinosaurs are healthy");
    } catch (DinosaurIllException e) {
      System.out.println("ATTENTION: " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addEnclosures(Enclosure... enclosures) {
    if (enclosures == null || enclosures.length == 0) {
      System.out.println("No enclosures were added into the Dinosaur Care System");
      return;
    }
    for (Enclosure e : enclosures) {
      if (e != null) dinosaurCareSystem.addEnclosure(e);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void addEnclosures(List<Enclosure> enclosures) {
    if (enclosures == null || enclosures.isEmpty()) {
      System.out.println("No enclosures were added into the Dinosaur Care System");
      return;
    }
    for (Enclosure e : enclosures) {
      if (e != null) dinosaurCareSystem.addEnclosure(e);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void removeEnclosure(Enclosure enclosure) {
    if (enclosure == null) {
      System.out.println("No enclosure was removed from the Dinosaur Care System");
      return;
    }
    dinosaurCareSystem.removeEnclosure(enclosure);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void checkEnclosuresSecurity() {
    try {
      dinosaurCareSystem.checkEnclosuresSecurity();
      System.out.println("All enclosures are safe");
    } catch (Exception e) {
      System.out.println("ATTENTION: " + e.getMessage());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void logActivity(Activity activity) {
    if (activity == null) {
      System.out.println("No activity was logged into the Dinosaur Care System");
      return;
    }
    dinosaurCareSystem.logActivity(activity);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void removeActivity(Activity activity) {
    if (activity == null) {
      System.out.println("No activity was removed from the Dinosaur Care System");
      return;
    }
    dinosaurCareSystem.removeActivity(activity);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
