package chapter9;

public class ParentBlock {
  
  // instance initialization block
  {
    System.out.println("6. Parent instance init block 1");
  }
  
  // static initialization block
  static {
    System.out.println("2. Parent static init block 1");
  }
  
  ParentBlock() {
    System.out.println("8. Parent constructor");
  }
  
  {
    System.out.println("7. Parent instance init block 2");
  }
  
  static {
    System.out.println("3. Parent static init block 2");
  }
  
}
