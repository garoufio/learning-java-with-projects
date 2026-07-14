package chapter13;

public record Book(String title, String author) {
  
  private static final String UNKN = "Unknown";
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public Book {
    title = title == null ? UNKN : title;
    author = author == null ? UNKN : author;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Book { Title: " + title + ", Author: " + author + " }";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}