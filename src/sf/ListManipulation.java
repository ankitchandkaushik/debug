package sf;


import java.util.PriorityQueue;

/*
First question: There are 3 kinds of queries on a stream of numbers:
(1, X) - Add X in your list of numbers
(2, X) - Add X to all the existing numbers
(3)    - Print the min number so far and remove it from the list

For e.g.
(1, 3) - current list = [3]
(1, 5) - current list = [3, 5]
(2, 10) - add 10 to all the existing numbers, list becomes [13, 15]
(1, 12) - current list = [12, 13, 15]
(3)  - print the minimum number and remove it from the list: 12

Second question: A variation of coin change problem
 */
public class ListManipulation {
  PriorityQueue<Integer> pq = new PriorityQueue<>();

  int currSum = 0;

  public void function(int key, int value) {
    if(key == 1) {
      value-=currSum;
      System.out.println("Adding to list "+ value);
      pq.add(value);
    } else if(key == 2) {
      currSum+=value;
      System.out.println("Curr sum = " + currSum);
    } else if (key == 3){
      if(!pq.isEmpty()) {
        int top = pq.poll();
        top+=currSum;
        System.out.println("Current minimum value is "+ top);
      } else {
        System.out.println("Nothing to print");
      }
    } else {
      System.out.println("Invalid input");
    }
  }

}


