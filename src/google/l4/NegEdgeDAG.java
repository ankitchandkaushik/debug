package google.l4;

import java.util.*;

/*
Given a directed acyclic graph with vertices connected with weights (positive or negative), find the minimum path between a source and a destination.
Find the minimum average weight between the source and the destination. This was somewhat similarly to a problem on AtCoder.

 */
public class NegEdgeDAG {

  static class Edge {
    int to;
    int weight;

    public Edge(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }

  public static int shortestPath(List<Edge>[] graph, int n, int src, int dest) {
    if(graph == null) {
      return 1000000009;
    }
    List<Integer> sorted = new ArrayList<>();
    Queue<Integer> q = new LinkedList<>();
    int[] inDegree = new int[n];
    for(int i=0 ;i<n; ++i) {
      inDegree[i] = 0;
    }
    for(int i=0; i<n; ++i) {
      for(Edge e: graph[i]) {
        inDegree[e.to]++;
      }
    }

    for(int i=0; i<n; ++i) {
      if(inDegree[i] == 0){
        q.add(i);
      }
    }

    while(!q.isEmpty()) {
      int curr = q.poll();
      sorted.add(curr);
      for(Edge e: graph[curr]) {
        inDegree[e.to]--;
        if(inDegree[e.to] == 0) {
          q.add(e.to);
        }
      }
    }
    System.out.println("Printing toposort");
    System.out.println(sorted);

    int[] d = new int[n];

    Arrays.fill(d, Integer.MAX_VALUE);

    d[src] = 0;

    for(int u: sorted) {
      if(d[u] != Integer.MAX_VALUE) {
        for(Edge e : graph[u]) {
          int v = e.to;
          int w = e.weight;
          if(d[v] > d[u] + w) {
            d[v] = d[u] + w;
          }
        }
      }

    }

    for(int i=0 ;i<n; ++i) {
      System.out.println(i + " " + d[i]);
    }
    return d[dest];
  }



  public static double minAvgWeight(List<Edge>[] graph, int n, int src, int dest) {
    if(graph == null) {
      return 1000000009;
    }
    List<Integer> sorted = new ArrayList<>();
    Queue<Integer> q = new LinkedList<>();
    int[] inDegree = new int[n];
    for(int i=0 ;i<n; ++i) {
      inDegree[i] = 0;
    }
    for(int i=0; i<n; ++i) {
      for(Edge e: graph[i]) {
        inDegree[e.to]++;
      }
    }

    for(int i=0; i<n; ++i) {
      if(inDegree[i] == 0){
        q.add(i);
      }
    }

    while(!q.isEmpty()) {
      int curr = q.poll();
      sorted.add(curr);
      for(Edge e: graph[curr]) {
        inDegree[e.to]--;
        if(inDegree[e.to] == 0) {
          q.add(e.to);
        }
      }
    }
    System.out.println("Printing toposort");
    System.out.println(sorted);

    double[] d = new double[n];

    Arrays.fill(d, Integer.MAX_VALUE);

    d[src] = 0.0;
    Edge[] info = new Edge[n];
    for(int i=0; i<n; ++i) {
      info[i] = new Edge(0, 0);
    }

    for(int u: sorted) {
      if(d[u] != Integer.MAX_VALUE) {
        for(Edge e : graph[u]) {
          int v = e.to;
          int w = e.weight;
          Edge p = info[u];
          double avgW = (double)(p.weight + w)/(double) (1 + p.to) ;
          if(d[v] > avgW) {
            d[v] = avgW;
            info[v].to = info[u].to+1;
            info[v].weight= info[u].weight + w;
          }
        }
      }

    }

    for(int i=0 ;i<n; ++i) {
      System.out.println(i + " " + d[i]);
    }
    return d[dest];
  }
}
