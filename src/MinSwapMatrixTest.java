public class MinSwapMatrixTest {
  public static void main(String[] args) {
    MinSwapMatrix m = new MinSwapMatrix();

    int[][] matrix = {
        {1,0,1,0},
        {1,1,1,1},
        {0,0,0,0},
        {1,1,1,0}
    };


    int ans = m.fun(matrix, 5);
    System.out.println(ans);
  }
}
