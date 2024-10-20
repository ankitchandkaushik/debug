import java.util.List;

public class RobotUberTest {

  public static void main(String[] args) {
    char[][] graph = {
        {'O','E','E','E','X'},
        {'E','O','E','X','X'},
        {'E','E','E','E','E'},
        {'X','E','O','E','X'},
        {'X','E','X','E','X'}
    };

    int[] dist = {2,2,4,1};

    RobotUber r = new RobotUber();

    List<List<Integer>> ans = r.fun(graph, dist);

    for(List<Integer> a: ans) {
      System.out.println(a.get(0) + " " + a.get(1));
    }
  }
}
