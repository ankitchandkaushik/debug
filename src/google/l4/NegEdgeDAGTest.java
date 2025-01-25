package google.l4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
5 5
Enter u v and w
0 1 1
1 2 2
1 3 5
3 4 8
4 2 -15
Enter source and dest
0 2

Ans = -1
*/


public class NegEdgeDAGTest {
  public static void main(String[] arg) {
    System.out.println("Enter number of nodes and edges");
    int n, e;
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    e = sc.nextInt();
    System.out.println("Enter u v and w");
    List<NegEdgeDAG.Edge>[] graph = new ArrayList[n];
    for(int i=0; i<n; ++i) {
      graph[i] = new ArrayList<NegEdgeDAG.Edge>();
    }
    for(int i=0; i<e; ++i) {
      int u,v,w;
      u = sc.nextInt();
      v = sc.nextInt();
      w = sc.nextInt();
      graph[u].add(new NegEdgeDAG.Edge(v, w));

    }
    System.out.println("Printing graph");
    for(int i=0 ;i<n; ++i) {
      System.out.println(i);
      for(NegEdgeDAG.Edge edge: graph[i]) {
        System.out.println(edge.to + " " + edge.weight);
      }

    }
    System.out.println("Enter source and dest");
    int src, dest;
    src = sc.nextInt();
    dest = sc.nextInt();


    System.out.println("Calculating shortest path");

    System.out.println(NegEdgeDAG.shortestPath(graph, n, src, dest));

    System.out.println("Calculating shortest avg");

    System.out.println(NegEdgeDAG.minAvgWeight(graph, n, src, dest));
  }
}
