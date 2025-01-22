package sf;

import java.util.Scanner;

public class ListManipulationTest {
  public static void main(String[] args) {
    ListManipulation lm = new ListManipulation();
    Scanner sc = new Scanner(System.in);
    while(true) {
      int key = 0, value = 0;
      key = sc.nextInt();
      if(key == 1 || key == 2){
        value = sc.nextInt();
      }
      lm.function(key, value);
    }

  }
}
