/*
Given an array arr[] of n integers, find the maximum that maximizes the sum of the value of i*arr[i] where i varies from 0 to n-1.

Examples:

Input: arr[] = {8, 3, 1, 2}
Output: 29
Explanation: Lets look at all the rotations,
{8, 3, 1, 2} = 8*0 + 3*1 + 1*2 + 2*3 = 11
{3, 1, 2, 8} = 3*0 + 1*1 + 2*2 + 8*3 = 29
{1, 2, 8, 3} = 1*0 + 2*1 + 8*2 + 3*3 = 27
{2, 8, 3, 1} = 2*0 + 8*1 + 3*2 + 1*3 = 17

Input: arr[] = {3, 2, 1}
Output: 7
Explanation: Lets look at all the rotations,
{3, 2, 1} = 3*0 + 2*1 + 1*2 = 4
{2, 1, 3} = 2*0 + 1*1 + 3*2 = 7
{1, 3, 2} = 1*0 + 3*1 + 2*2 = 7
 */

public class MaximumSumArrUber {

  static int fun(int[] arr ) {
    if(arr == null || arr.length == 0) return 0;

    int n = arr.length;
    int sum = 0;
    int ans = 0;
    for(int i=0; i<n; ++i) {
      sum+=arr[i];
      ans+=(i*arr[i]);
    }

    for(int i=0; i<n-1; ++i) {
      ans = Math.max(ans, ans + sum - n*arr[n-i-1]);
    }

    return ans;
  }

  public static void main(String[] args) {
    int[] arr = {3,2,1};
    System.out.println(fun(arr));
  }
}
