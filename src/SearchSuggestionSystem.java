//import java.util.ArrayList;
import java.util.*;

public class SearchSuggestionSystem {
  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    List<List<String>> ans = new ArrayList<>();
    if(products == null || products.length == 0) return ans;

    Arrays.sort(products);
    System.out.println(Arrays.toString(products));
    int n = products.length;
    StringBuilder curr = new StringBuilder("");
    for(char c: searchWord.toCharArray()) {
      curr.append(c);
      int idx = lowerBound(products, curr);

      List<String> temp = new ArrayList<>();
      System.out.println(curr + " " + idx);

      if(idx == -1) {
        ans.add(temp);
      } else {
        for(int i = idx; i<Math.min(idx+3, n) && compare(products[i], curr) == 0; ++i) {
          temp.add(products[i]);
        }
        ans.add(temp);
      }

    }
    return ans;
  }

  int lowerBound(String[] products, StringBuilder s) {
    int n = products.length;
    int stringLen = s.length();
    int l=0, r = n-1;
    int ans = -1;
    while(l<=r) {
      int mid = (l+r)/2;
      String curr = products[mid];
      int flag = compare(curr, s);
      if(flag == 0) {
        ans = mid;
        r = mid-1;
      } else if(flag == 1) {
        l = mid+1;
      } else {
        r = mid-1;
      }
    }
    System.out.println("Inside lowerBound " + s + " " + ans);
    return ans;
  }

  int compare(String s, StringBuilder t) {
    int n = s.length();
    int m = t.length();

    int i=0;
    while(i < Math.min(n,m)) {
      if(s.charAt(i) > t.charAt(i)) {
        return 1;
      } else if (s.charAt(i) < t.charAt(i)) {
        return -1;
      }
      i++;
    }
    if(i == m) {
      return 0;
    }
    return -1;
  }
}
