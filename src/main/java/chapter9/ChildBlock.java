package chapter9;

public class ChildBlock extends ParentBlock {
  
  {
    System.out.println("9. Child instance init block 1");
  }
  
  static  {
    System.out.println("4. Child static init block 1");
  }
  
  ChildBlock(){
    System.out.println("11. Child constructor");
  }
  
  {
    System.out.println("10. Child instance init block 2");
  }
  
  static  {
    System.out.println("5. Child static init block 2");
  }
  
}