package google.l4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Router {

  public static class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public double getDist(Pair p) {
      return Math.sqrt(((p.x-x)*(p.x-x)) + (p.y-y)*(p.y-y));
    }

    @Override
    public boolean equals(Object obj) {
      if(this == obj) {
        return true;
      }
      if(obj == null || getClass() != obj.getClass()) return false;
      Pair other = (Pair) obj;
      return ((x == other.x) && (y == other.y));
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }


  }

  public static class DSU {
    List<Integer> par;
    public DSU(int n) {
      par = new ArrayList<>();
      for(int i=0; i<n; ++i) {
        par.add(i);
      }
    }

    int find(int x) {
      if(par.get(x) == x) return x;
      par.set(x, find(par.get(x)));
      return par.get(x);
    }

    boolean union(int a, int b) {
      int parA = find(a);
      int parB = find(b);
      if(parB == parA) {
        return false;
      } else {
        par.set(parA, parB);
        return true;
      }
    }
  }


  public static boolean isReachableDSU(List<Pair> location, int range, int startIdx, int endIdx) {
    int n = location.size();
    DSU dsu = new DSU(n);
    if(n<=1) return true;
    for(int i=0; i<n; ++i) {
      for(int j=i+1; j<n; ++j) {
        if(location.get(i).getDist(location.get(j)) <= range) {
          dsu.union(i, j);
        }
      }
    }
    return dsu.find(startIdx) == dsu.find(endIdx);

  }

  public static void main(String[] args) {
    List<Pair> locations = new ArrayList<>();
    locations.add(new Pair(0, 0));
    locations.add(new Pair(0, 8));
    locations.add(new Pair(0, 17));
    locations.add(new Pair(11, 0));

    System.out.println(isReachableDSU(locations, 10, 0, 1));
  }


}
