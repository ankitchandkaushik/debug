import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    String ans = Main.minWindow("ADOBECODEBANC", "ABC");
    System.out.println(ans);
  }
  public static String minWindow(String s, String t) {
    int n = s.length();
    int m = t.length();
    Map<Character, Integer> sMap = new HashMap<>();
    Map<Character, Integer> tMap = new HashMap<>();

    for(int i=0; i<m; ++i) {
      char c = t.charAt(i);
      tMap.put(c, tMap.getOrDefault(c, 0)+1);
    }
    int minSize = n+1, l = 0, r = 0, lIdx = 0, rIdx=0;

    while(r < n) {
      // char c = s.charAt(r);
      // sMap.put(c, sMap.getOrDefault(c, 0)+1);
      while(r < n && !isValid(sMap, tMap)) {

        char c = s.charAt(r);
        sMap.put(c, sMap.getOrDefault(c, 0)+1);
        r++;
      }
      if(!isValid(sMap, tMap) && r == n) break;
      while(l<r && isValid(sMap, tMap)) {
        char c = s.charAt(l);
        System.out.println(c + " ");
        sMap.put(c, sMap.get(c)-1);
        l++;
      }

      if(r-l+1 < minSize) {
        minSize = r-l+1;
        lIdx = l-1;
        rIdx = r-1;
      }
      // r++;

    }
    if(minSize == n+1) return "";
    return s.substring(lIdx, rIdx+1);
  }

  static boolean isValid(Map<Character, Integer> sMap, Map<Character, Integer> tMap){
    for(Character key: tMap.keySet()){
      int value = tMap.get(key);
      int sValue = sMap.getOrDefault(key, 0);
      if(value > sValue) return false;
    }
    return true;
  }



}
