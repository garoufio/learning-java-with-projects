package chapter9;

public class InitializationBlocks {
  
  static {
    System.out.println("1. InitializationBLocks static init block");
  }
  
  {
    System.out.println("InitializationBlocks instance init block");
  }
  
  public static void main(String[] args) {
    System.out.println("----> Creating first Child object ");
    new ChildBlock();
    System.out.println("----> Creating second Child object ");
    new ChildBlock();
  }
  
}
