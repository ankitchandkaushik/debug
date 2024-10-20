
/*
Given two inputs,


First input is the location map, a 2D array


| O | E | E | E | X |


| E | O | X | X | X |


| E | E | E | E | E |


| X | E | O | E | E |


| X | E | X | E | X |


O = Robot, E = Empty, X = blocker


Second input is the *query. It’s a 1D array consisting of distance to the closest blocker in the order from **left, **top, *bottom and right
[2, 2, 4, 1]


This means distance of 2 to the left blocker, 2 to the top blocker, 4 to the bottom blocker and 1 to the right blocker


Note: The location map boundary is also considered blocker, meaning if the robot hits the boundary it also means it’s hitting the blocker.
Return the coordinates of all robots who can satisfy move given by second input.


Is there any leetcode equivalent of this problem?
I proposed solution which goes linearly through the location map, for each robot detected, we would do boundary check and return the result. Time complexity of such solution would be O(mnmax(secondInputValues)).
Is there any better solution than this?
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RobotUber {

  List<List<Integer>> ans;
  private static final int[][] DIRECTIONS = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };  // left, top, bottom, right

  List<List<Integer>> fun(char[][] graph, int[] dist) {
    ans = new ArrayList<>();
    if(graph == null || graph.length == 0) return ans;
    int n = graph.length;
    int m = graph[0].length;

    int[][][] dp = new int[n][m][4];

    for(int i=0; i<n; ++i) {
      for(int j=0; j<m; ++j) {
        for(int k=0; k<4; ++k) {
          dp[i][j][k] = Integer.MAX_VALUE;
        }
      }
    }

    Queue<int[]> q = new LinkedList<>();
    for(int i=0; i<n; ++i) {
      for(int j=0; j<m; ++j) {
        if(graph[i][j] == 'X') {


          for(int d=0; d<4; ++d) {
            dp[i][j][d] = 0;
            q.add(new int[] {i, j, d});
          }
        }

      }
    }

    while(!q.isEmpty()) {
      int[] curr = q.poll();
      int x = curr[0];
      int y = curr[1];
      int d = curr[2];

//      for(int d=0; d<4; ++d) {
        int newX = x + DIRECTIONS[d][0];
        int newY = y + DIRECTIONS[d][1];

        if(newX >= 0 && newX < n && newY >= 0 && newY < m && graph[newX][newY] != 'X') {
          if(dp[newX][newY][d] > 1 + dp[x][y][d]) {
            if(newX == 0 && newY == 0) {
              System.out.println(x + " x " + y + " " + d + " " + dp[x][y][d]);
            }
            dp[newX][newY][d] = 1 + dp[x][y][d];
            q.offer(new int[]{newX, newY, d});
          }
        }

//      }


    }

    for(int i=0; i<n; ++i) {
      for(int j=0; j<m; ++j) {
        if(graph[i][j] == 'O') {
          int l = Math.min(j+1, dp[i][j][0]);
          int r = Math.min(m-j, dp[i][j][1]);
          int u = Math.min(i+1, dp[i][j][2]);
          int d = Math.min(n-i, dp[i][j][3]);
          System.out.println(i + " " + j + " " + l + " " + r + " " + u + " " + d);
          if(l==dist[0] && r == dist[1] && u == dist[2] && d == dist[3]) {
            ans.add(new ArrayList<>(List.of(i, j)));
          }
        }
      }
    }
    return ans;

  }
}
