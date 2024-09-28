public class LongestIncreasingPathMatrixTest {
  public static void main(String[] args) {
    LongestIncreasingPathMatrix longestIncreasingPathMatrix = new LongestIncreasingPathMatrix();

    int[][] test = {{9, 9, 4}, {6, 6, 8}, {2, 2, 1}};
    System.out.println(longestIncreasingPathMatrix.longestIncreasingPath(test));

  }
}
