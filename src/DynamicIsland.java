import java.util.*;

public class DynamicIsland {
  TreeSet<Pair> lands;

  Map<Pair, Pair> parent;
  int islandCount;

  public DynamicIsland() {
    lands = new TreeSet<>();
    parent = new HashMap<>();
    islandCount = 0;
  }

  public void addLand(int x, int y) {
    Pair p = new Pair(x, y);
    parent.put(p, p);
    int mergeCount = 0;
    mergeCount+= union(p, new Pair(x+1, y))?1:0;
    mergeCount+= union(p, new Pair(x-1, y))?1:0;
    mergeCount+= union(p, new Pair(x, y+1))?1:0;
    mergeCount+= union(p, new Pair(x, y-1))?1:0;
    islandCount-=(mergeCount-1);
    lands.add(p);

  }

  public void countIsland() {
    System.out.println(islandCount);
  }



  public boolean union(Pair p1, Pair p2) {
    if(lands.contains(p2)) {
      Pair par1 = find(p1);
      Pair par2 = find(p2);
      if(par1 == par2) {
        return false;
      } else {
        parent.put(par1, par2);
        return true;
      }
    } else {
      return false;
    }
  }

  public Pair find(Pair p) {
    Pair par = parent.get(p);
    while(parent.get(par) != par) {
      par = parent.get(par);
    }
    parent.put(p, par);
    return par;
  }

  void getHash(Pair p) {
    System.out.println(p.hashCode());
  }



}

class Pair implements Comparable<Pair>{
  public int x;
  public int y;

  public Pair(int x, int y) {
    this.x = x;
    this.y = y;
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

  @Override
  public int compareTo(Pair oth) {
    int xCompare = Integer.compare(this.x, oth.x);
    if(xCompare != 0) {
      return xCompare;
    } else {
      return Integer.compare(this.y, oth.y);
    }
  }

}
