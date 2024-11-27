//
//
//
//// Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
//
//// 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
//// 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
//// 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
//// 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
//// Notice that there could be some signs on the cells of the grid that point outside the grid.
//
//// You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.
//
//// You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
//
//// Return the minimum cost to make the grid have at least one valid path.
//
//
//
//// Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
//// Output: 3
//
//// int[][] dp = new int[m][n];
//
//// fun(x,y,)
//// if(dp[x][y] != -1) return dp[x][y];
//// ans = Integer.MAX_VALUE;
//// for(int i=1; i<=4; ++i) {
////     int curr = 0;
////     if(i != matrix[x][y]) {
////         curr+=1;
////     }
////     curr+=fun(x, y+1);
////     ans = Math.min(curr, ans);
//// }
//
//// time complexity: O(m*n*4)
//// space complexity: O(m*n)
//
//
//// Main class should be named 'Solution' and should not be public.
//class Uber {
//  static int[][] dp;
//  static boolean[][] visited;
//  static int[][] dirs = {
//      {0,1},
//      {0,-1},
//      {1,0},
//      {-1,0}
//  };
//
//  static int maxValue = 100000;
//
//
//  static int minCost(int x, int y, int[][] matrix , int parX, int parY) {
//
//
//    int n = matrix.length;
//    int m = matrix[0].length;
//
//    if(x == n-1 && y == m-1) {
//      return 0;
//    }
//
//    // if()
//
//    if(x>=0 && x<n && y>=0 && y<m) {
//
//      if(dp[x][y] != -1) {
//        return dp[x][y];
//      }
//
//
//      int ans = maxValue;
//      for(int i=1; i<=4; ++i) {
//        int curr = 0;
//        if(matrix[x][y] != i) {
//          curr+=1;
//        }
//        int newX = x + dirs[i-1][0];
//        int newY = y + dirs[i-1][1];
//        if(newX == parX && newY == parY) continue;
//         System.out.println(newX + " " + newY);
//        long remainingCost = minCost(newX, newY, matrix, x, y);
//        // System.out.println(remainingCost);
//        if(maxValue != remainingCost) {
//          curr+=remainingCost;
//          ans = Math.min(ans, curr);
//        }
//      }
//
//      // System.out.println("ans " + ans);
//
//      dp[x][y] = ans;
//      return dp[x][y];
//
//
//    } else {
//      return maxValue;
//    }
//
//  }
//
//  static int minCostUtil(int[][] matrix) {
//    if(matrix == null || matrix.length == 0) {
//      return 0;
//    }
//    int n = matrix.length;
//    int m = matrix[0].length;
//
//    dp = new int[n][m];
//
//    for(int i=0; i<n; ++i) {
//      for(int j=0; j<m; ++j) {
//        dp[i][j] = -1;
//      }
//    }
//
//    return minCost(0, 0, matrix, -1, -1);
//  }
//
//
//  public static void main(String[] args) {
//    // System.out.println"Hello, World");
//
//
//    int[][] matrix = {
//        {1,1,1,1},
//        {2,2,2,2},
//        {1,1,1,1},
//        {2,2,2,2}
//    };
//
//    int result = minCostUtil(matrix);
//    System.out.println(result);
//
//  }
//
//
//}
//


import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {

  // Directions: {Right, Left, Down, Up}
  static int[][] dirs = {
      {0, 1},   // 1 -> right
      {0, -1},  // 2 -> left
      {1, 0},   // 3 -> down
      {-1, 0}   // 4 -> up
  };

  static int maxValue = Integer.MAX_VALUE;

  public static int minCost(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    // PriorityQueue for BFS with the smallest cost first
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // {x, y, cost}
    pq.offer(new int[] {0, 0, 0});

    // Distance matrix to store the minimum cost to reach each cell
    int[][] dist = new int[n][m];
    for (int[] row : dist) {
      Arrays.fill(row, maxValue);
    }
    dist[0][0] = 0; // Starting point has 0 cost

    // BFS with priority queue (Dijkstra's algorithm)
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int x = cur[0], y = cur[1], cost = cur[2];

      // If we reached the bottom-right corner, return the cost
      if (x == n - 1 && y == m - 1) {
        return cost;
      }

      // Skip if we've already found a cheaper way to reach this cell
      if (cost > dist[x][y]) continue;

      // Explore all four possible directions
      for (int i = 0; i < 4; i++) {
        int newX = x + dirs[i][0];
        int newY = y + dirs[i][1];
        int newCost = cost;

        // Check if we need to modify the sign (i.e., incur a cost of 1)
        if (grid[x][y] != i + 1) {
          newCost++;
        }

        // Only move if it's within bounds
        if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
          // If the new cost is cheaper, update and add it to the priority queue
          if (newCost < dist[newX][newY]) {
            dist[newX][newY] = newCost;
            pq.offer(new int[] {newX, newY, newCost});
          }
        }
      }
    }

    // If the bottom-right corner is unreachable, return -1 (though it should be reachable)
    return -1;
  }

  public static void main(String[] args) {
    int[][] grid = {
        {1, 1, 1, 1},
        {2, 2, 2, 2},
        {1, 1, 1, 1},
        {2, 2, 2, 2}
    };

    int result = minCost(grid);
    System.out.println(result);  // Output: 3
  }
}

