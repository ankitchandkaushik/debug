package google.l4;

import java.util.Arrays;

public class DoorTest {
  public static void main(String[] args) {
    int[] arrival = {0,1,1,2,4};
    int[] state = {0,1,0,0,1};

//    int[] arrival = {0,0,0};
//    int[] state = {1,0,1};

    System.out.println(Arrays.toString(Door.crossTheDoor(arrival, state)));
  }
}
