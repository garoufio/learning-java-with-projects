package chapter13;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class TestQueue {
  
  public static void main(String[] args) {
    Queue<String> queue1 = new LinkedList<>();
    queue1.add("Task 1");
    queue1.offer("Task 2");
    System.out.println("Queue1 Head: " + queue1.peek());  // get the head of the queue
    System.out.println("Queue1 Head: " + queue1.element()); // get the head of the queue
    printQueue(queue1, null);
    System.out.println("Removing the head of the Queue1: " + queue1.poll()); // remove the head of the queue
    printQueue(queue1, "Queue1 after removing the head:");
    System.out.println("Removing the new head of the Queue1: " + queue1.remove()); // remove the head of the queue
    printQueue(queue1, "Queue1 after removing the head:");
    
    
    Deque<String> queue2 = new LinkedList<>();
    queue2.add("Task 1"); // or addLast
    queue2.offer("Task 2"); // or offerLast
    queue2.addFirst("Task 3");
    queue2.offerFirst("Task 4");
    printQueue(queue2, null);
    System.out.println("Queue2 Head: " + queue2.peekFirst()); // get the head of the queue
    System.out.println("Queue2 Head: " + queue2.getFirst()); // get the head of the queue
    System.out.println("Queue2 Tail: " + queue2.peekLast()); // get the tail of the queue
    System.out.println("Queue2 Tail: " + queue2.getLast()); // get the tail of the queue
    
    System.out.println("Removing the head of the queue: " + queue2.pollFirst()); // remove the head of the queue
    printQueue(queue2, "Queue2 after removing the head:");
    System.out.println("Removing the new head of the queue: " + queue2.removeFirst()); // remove the head of the queue
    printQueue(queue2, "Queue2 after removing the head:");
    System.out.println("Removing the tail of the queue: " + queue2.removeLast()); // remove the tail of the queue
    printQueue(queue2, "Queue2 after removing the tail:");
    System.out.println("Removing the new tail of the queue: " + queue2.pollLast()); // remove the tail of the queue1
    printQueue(queue2, "Queue2 after removing the tail:");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public static void printQueue(Queue<String> queue, String message) {
    if (queue == null || queue.isEmpty()) {
      System.out.println("Queue is empty.");
      return;
    }
    
    System.out.println(message == null || message.isEmpty() ? "Elements in queue:" : message);
    for (String element : queue) {
      System.out.println(element);
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
