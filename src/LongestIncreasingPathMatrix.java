import java.util.*;

public class LongestIncreasingPathMatrix {
  int[][] moves = {{0,1}, {1,0}, {-1, 0}, {0,-1}};
  public int longestIncreasingPath(int[][] matrix) {

    int n = matrix.length;
    int m = matrix[0].length;

    Map<Pair, List<Integer>> dp = new HashMap<>();

    for(int i=0; i<n; ++i) {
      for(int j=0; j<m; ++j) {
        dp.put(new Pair(i, j), new ArrayList<>());
      }
    }

    for(int i=0; i<n; ++i) {
      for(int j=0; j<m; ++j) {
        dfs(matrix, dp, i, j);
      }
    }
//    dfs(matrix, dp, 0, 0);
    int ans = 1;
    for(Pair key: dp.keySet()) {
      List<Integer> curr = dp.get(key);
      ans = Math.max(ans, curr.size());
    }
    return ans;

  }

  void dfs(int[][] matrix, Map<Pair, List<Integer>> dp, int x, int y) {
    int n = matrix.length;
    int m = matrix[0].length;

    // if(x<0 || x>=n || y>0 || y>=m) return;

    if(dp.get(new Pair(x, y)).size() != 0) {
      return ;
    }
    else {
      List<Integer> curr = new ArrayList<>();
      curr.add(matrix[x][y]);
      dp.put(new Pair(x, y), curr);
      for(int[] move: moves) {
        int i = x+move[0];
        int j = y+move[1];
        if(i>=0 && i<n && j>=0 && j<m && (matrix[i][j] > matrix[x][y])) {
          dfs(matrix, dp, i, j);
          List<Integer> next = dp.get(new Pair(i,j));
          int nextLen = next.size();
          if(1 + nextLen > curr.size()) {
            next.add(matrix[x][y]);
            dp.put(new Pair(x,y), next);
          }
        }
      }

    }

  }

  class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public boolean equals(Object o) {
      if(this == o) return true;
      if(o == null || getClass() != o.getClass()) return false;
      Pair oth = (Pair) o;
      return (this.x == oth.x && this.y == oth.y);
    }

    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
}
