public class DynamicIslandTest {
  public static void main(String[] arg) {
    DynamicIsland dynamicIsland = new DynamicIsland();
    dynamicIsland.addLand(0,0);
    dynamicIsland.countIsland();
    dynamicIsland.addLand(0,1);
    dynamicIsland.countIsland();
    dynamicIsland.addLand(5,1);
    dynamicIsland.countIsland();
    dynamicIsland.addLand(4,6);
    dynamicIsland.countIsland();

//    dynamicIsland.getHash(new Pair(4, 1));
//    dynamicIsland.getHash(new Pair(5, 6));
  }
}
