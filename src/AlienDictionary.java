import java.util.*;


// https://www.geeksforgeeks.org/problems/alien-dictionary/1
public class AlienDictionary {
  public String findOrder(String[] dict, int n, int k) {
    // Write your code here

    int len = dict.length;
    Map<Integer, List<Integer>> graph = new HashMap<>();
    // for(int i=0; i<k; ++i) {
    //     graph[i] = new ArrayList<>();
    // }
    for(int i=0; i<len-1; ++i) {
      String s1 = dict[i];
      String s2 = dict[i+1];
      int l1 = s1.length();
      int l2 = s2.length();
      int idx1 = 0;
      int idx2 = 0;

      while(idx1<l1 && idx2<l2 && (s1.charAt(idx1) == s2.charAt(idx2))) {
        idx1++;
        idx2++;
      }

      if(idx1 != l1 && idx2 != l2) {
        int c1 = s1.charAt(idx1);
        int c2 = s2.charAt(idx2);

        //        graph.getOrDefault(c1-'a', new ArrayList<>()).add(c2-'a');
        graph.putIfAbsent(c1 - 'a', new ArrayList<>());
        graph.get(c1 - 'a').add(c2 - 'a');
        // graph[c1-'a'].add(c2-'a');
      }
    }

    //  for(int key: graph.keySet()) {
    //      List<Integer> next = graph.get(key);
    //      System.out.println(key + " ");
    //      for(int v: next) {
    //          System.out.println(v + " ");
    //          // inDegree[v]++;
    //      }
    //      System.out.println("_--------------------");
    //  }

    List<Integer> sorted = topoSort(graph, k);
    if(sorted == null) {
      return "";
    }


    StringBuilder sortedString = new StringBuilder();

    for(int i: sorted) {
      sortedString.append((char)(i+'a'));
    }
    return sortedString.toString();
  }

  List<Integer> topoSort(Map<Integer, List<Integer>> graph, int n) {

    int[] inDegree = new int[n];
    List<Integer> ans = new ArrayList<>();

    for(int key: graph.keySet()) {
      List<Integer> next = graph.get(key);
      for(int v: next) {
        inDegree[v]++;
      }
    }

    Queue<Integer> q = new LinkedList<>();


    for(int i=0; i<n; ++i) {
      if(inDegree[i] == 0) {
        q.add(i);
        ans.add(i);
      }
    }

    while(!q.isEmpty()) {
      int curr = q.poll();
      List<Integer> next = graph.get(curr);
      if(next == null) {
        continue;
      }
      for(int v: next) {
        inDegree[v]--;
        if(inDegree[v] == 0) {
          q.add(v);
          ans.add(v);
        }
      }
    }

    for(int i=0; i<n; ++i) {
      if(inDegree[i] != 0) {
        return null;
      }
    }

    return ans;

  }


  public static void main(String[] args) {
    AlienDictionary alienDictionary = new AlienDictionary();

    String[] dict = {"baa","abcd","abca","cab","cad"};

    String s = alienDictionary.findOrder(dict, 5, 4);
    System.out.println(s);
  }




}
