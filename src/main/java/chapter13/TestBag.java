package chapter13;

public class TestBag {
  
  public static void main(String[] args) {
    Bag<Laptop> laptopBag = new Bag<>(new Laptop("Dell", "XPS 15"));
    Bag<Book> bookBag = new Bag<>(new Book("1984", "George Orwell"));
    
    System.out.println(laptopBag);
    System.out.println(bookBag);
  }
  
}
