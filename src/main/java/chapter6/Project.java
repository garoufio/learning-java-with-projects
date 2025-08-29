package chapter6;

public class Project {
  
  public static void main(String[] args) {
    String[] dinoNames = new String[10];
    int[] dinoAges = new int[10];
    String[] dinoSpecies = new String[10];
    int[] dinoEnclosures = new int[10];
    double avgAge;
    double avgWeight;
    
    for (int i = 0; i < 10; i++) {
      dinoNames[i] = "Dino" + i;
      dinoAges[i] = (int)(Math.random() * (20) + 1);
      dinoSpecies[i] = "Species " + (i +1 );
      dinoEnclosures[i] = i + 1;
    }
    
    // Displaying the details
    for (int i = 0; i < 5; i++) {
      System.out.printf(
          "Name: %s, Age: %d, Species: %s, Enclosure: %d\n",
          dinoNames[i], dinoAges[i], dinoSpecies[i], dinoEnclosures[i]
      );
    }
    
    // Calculating and displaying average age
    int totalAge = 0;
    for (int age : dinoAges) {
      totalAge += age;
    }
    double averageAge = (double)totalAge / dinoAges.length;
    System.out.println("Average age: " + averageAge);
  }
  
}
