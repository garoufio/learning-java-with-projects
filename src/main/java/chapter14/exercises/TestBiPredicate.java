package chapter14.exercises;

import java.util.function.BiPredicate;

public class TestBiPredicate {
  
  static void main() {
    BiPredicate<String, String> isFromDomain = (email, domain) -> email.contains("@" + domain);
    System.out.printf(
        "Is this mail 'test@gmail.com' from Google? %b\n", isFromDomain.test("test@gmail.com", "gmail")
    );
    System.out.printf(
        "Is this mail 'test@hotmail.com' from Google? %b\n", isFromDomain.test("test@hotmail.com", "gmail")
    );
  }
  
}
