// https://leetcode.com/discuss/interview-experience/5870808/Uber-or-SDE-2-or-Bangalore-or-Sep-2024-Reject

import java.util.Arrays;

public class SquareRootSort {

  int[] squareSort(int[] arr) {
    int n = arr.length;
    if(n <= 1) return  arr;
    int i=0;
    while(i<n && arr[i] < 0) {
      i++;
    }

    int[] ans = new int[n];
    int j = i-1, k = 0;

    while(j>=0 && i<n) {
      if(Math.abs(arr[j]) > arr[i]) {
        ans[k] = arr[i];
        i++;
      } else {
        ans[k] = arr[j];
        j--;
      }
      k++;
    }

    while(j>=0) {
      ans[k] = arr[j];
      j--;
      k++;
    }

    while(i<n) {
      ans[k] = arr[i];
      i++;
      k++;
    }

    return ans;

  }

  int kthSmallestSquare(int[] arr, int k) {
    int n = arr.length;
    if(k > n) return -1;

    int i=0;
    while(i < n && arr[i] < 0) {
      i++;
    }

    if(i == n) {
      return arr[n-k];
    }
    else if(i == 0) {
      return arr[k];
    } else if(Math.abs(arr[i-1]) >= arr[n-1]) {
      int pos = n-i;
      if(k<=pos) return arr[i+k-1];
      else {
        k-=(pos+1);
        return arr[i-k];
      }
    } else if(Math.abs(arr[0]) <= arr[i]) {
      int neg = i;
      if( k <= neg) return arr[i-k];
      else {
        k-=neg;
        return arr[i+k-1];
      }
    }
    else {
      int l1 = 0, r1 = i-1, l2 = i, r2 = n-1;

      while(l2 <= r2) {
        int mid = (l2 + r2)/2;
        int target = k - (mid-i + 1);
        int num1 = arr[mid];
        if(r1-target < 0) {
          l2 = mid+1;
          continue;
        }
        int num2 = arr[r1-target];

        if((r1-target-1 < 0 || Math.abs(arr[r1-target-1]) >= num1)  && (mid+1 >= n ||  arr[mid+1] >= Math.abs(num2))) {
          if(Math.abs(num2) < num1) {
            return num1;
          } else {
            return num2;
          }

        } else if(r1-target-1 >= 0 && Math.abs(arr[r1-target-1]) < num1) {
          r2 = mid-1;
        } else {
          l2 = mid+1;
        }
      }

      if(l2 == n) {
        return arr[n-1] > Math.abs(arr[i+1-(k-n+i+1)]) ? arr[n-1] : arr[i+1-(k-n+i+1)];
      } else {
        return Math.abs(arr[0]) > arr[k-1] ? arr[0] : arr[k-1];
      }

    }
  }

  int kthSmallestSq(int[] arr ,int k) {
    int n = arr.length;
    if(k <= 0 ||  k > n) return Integer.MIN_VALUE;
    int i = 0;
    while(i<n && arr[i] < 0) {
      i++;
    }
    if(i == 0) return arr[k-1];
    else if(i == n) return arr[n-k];
    else {
      int low = 1 + Math.max(0, k-i);
      int high = Math.min(n, i+k);
      while(low <= high) {
        int par1 = (low+high)/2;
        int par2 = i - (k - par1 + i);

        int leftMax1 = par1 == i ? Integer.MIN_VALUE : arr[par1-1];
        int rightMin1 = par1 == n ? Integer.MAX_VALUE : arr[par1];

        int leftMax2 = par2 == 0 ? Integer.MIN_VALUE : Math.abs(arr[par2-1]);
        int rightMin2 = par2 == i ? Integer.MAX_VALUE : Math.abs(arr[par2]);

        if(leftMax1 <= rightMin2 && leftMax2 <= rightMin1) {
          return Math.max(leftMax1, leftMax2);
        } else if(leftMax1 > rightMin2) {
          high = par1-1;
        } else {
          low = par1+1;
        }

      }
    }
    return  Integer.MIN_VALUE;
  }

  public static void main(String[] args) {
    SquareRootSort srs = new SquareRootSort();
    int[] arr = new int[]{-7, -2, -1, -1, 1, 2, 2, 2, 3, 5};
    System.out.println(Arrays.toString(srs.squareSort(arr)));

    System.out.println(srs.kthSmallestSq(arr, 3));
  }
}
