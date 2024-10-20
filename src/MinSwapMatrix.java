
/*
Given a n x n matrix with 0s and 1s, given the min number of swaps (not necessary to be adjacent) required to created a y x y (y<=n) matrix of 1s inside.
 */
public class MinSwapMatrix {

  int fun(int[][] matrix, int y) {
    int n = matrix.length;
    if(n < y) return -1;

    int oneCount = 0;
    for(int i=0; i<n; ++i) {
      for(int j=0; j<n; ++j) {
        if(matrix[i][j] == 1) oneCount++;
      }
    }

    if(oneCount < y*y) return -1;
    if(y == 1) return 0;
    int[][] prefix = findPrefix(matrix);
    int ans = -1;
    for(int i = y-1; i<n; ++i) {
      for(int j=y-1; j<n; ++j) {
        int avlOne = prefix[i][j];
        if(i!=y-1) {
          avlOne-=prefix[i-y][j];
        }
        if(j!=y-1) {
          avlOne-=prefix[i][j-y];
        }
        if(i!=y-1 && j!=y-1) {
          avlOne+=prefix[i-y][j-y];
        }
        ans = Math.max(ans, avlOne);
      }
    }
    return y*y-ans;
  }

  int[][] findPrefix(int[][] matrix) {
    int n = matrix.length;;
    int[][] ans = new int[n][n];
    for(int i=0; i<n; ++i) {
      for(int j=0; j<n; ++j) {
        ans[i][j] = matrix[i][j];
        if(i>0) {
          ans[i][j]+=ans[i-1][j];
        }
        if(j > 0) {
          ans[i][j]+=ans[i][j-1];
        }
        if(i > 0 && j >  0) {
          ans[i][j]-=ans[i-1][j-1];
        }
      }
    }
    return ans;
  }
}
