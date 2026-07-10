package chapter13;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class TestQueue {
  
  public static void main(String[] args) {
    Queue<String> queue1 = new LinkedList<>();
    queue1.add("Task 1");
    queue1.offer("Task 2");
    System.out.println("Queue 1: " + queue1.peek());  // get the head of the queue
    System.out.println("Queue 1: " + queue1.element()); // get the head of the queue
    System.out.println("Elements in queue:");
    for (String s : queue1) {
      System.out.println(s);
    }
    System.out.println("Removing the head of the queue: " + queue1.poll()); // remove the head of the queue
    System.out.println("Removing the new head of the queue: " + queue1.remove()); // remove the head of the queue
    
    
    Deque<String> queue2 = new LinkedList<>();
    queue2.add("Task 1"); // or addLast
    queue2.offer("Task 2"); // or offerLast
    queue2.addFirst("Task 3");
    queue2.offerFirst("Task 4");
    System.out.println("\nElements in deque:");
    for (String s : queue2) {
      System.out.println(s);
    }
    System.out.println("Removing the head of the queue: " + queue2.pollFirst()); // remove the head of the queue
    System.out.println("Removing the new head of the queue: " + queue2.removeFirst()); // remove the head of the queue
    System.out.println("Removing the tail of the queue: " + queue2.removeLast()); // remove the tail of the queue
    System.out.println("Removing the new tail of the queue: " + queue2.pollLast()); // remove the tail of the queue1
  }
  
}
