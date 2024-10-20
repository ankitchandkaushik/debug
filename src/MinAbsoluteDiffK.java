//import java.util.Arrays;
//import java.util.TreeMap;
//import java.util.TreeSet;
//
//// https://algo.monster/liteproblems/2817
//// https://leetcode.com/discuss/interview-experience/5655907/Uber-or-SDE-2-or-Bangalore-or-July-2024-Offer
//// https://leetcode.com/problems/minimum-absolute-difference-between-elements-with-constraint/description/
//
//// assuming n>=k
//public class MinAbsoluteDiffK {
//
//  public int[] minAbsoluteDiffK(int[] nums, int k) {
//    if(nums == null) return new int[1];
//    int n = nums.length;
//    if(k == 1) return new int[n-k+1];
//
//    TreeMap<Integer, Integer> map = new TreeMap<>();
//    int i=0;
//    int []ans = new int[n-k+1];
//    int min = Integer.MAX_VALUE;
//    while(i<n) {
//
//      if(i<k-1) {
//        map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
//      } else {
//        if(i!=k-1) {
//          if(map.get(nums[i-k+1]) == 1) {
//            map.remove(nums[i-k+1]);
//          } else {
//            map.put(nums[i-k+1], map.get(nums[i-k+1])-1);
//          }
//        }
//        if(map.ceilingKey(nums[i]) != null) {
//          min = Math.min(min, Math.abs(map.ceilingKey(nums[i]) - nums[i]));
//        }
//        if(map.floorKey(nums[i]) != null) {
//          min = Math.min(min, Math.abs(map.floorKey(nums[i]) - nums[i]));
//        }
//
//
//
//      }
//      i++;
//    }
//  }
//}
