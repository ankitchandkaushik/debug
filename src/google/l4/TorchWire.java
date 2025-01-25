package google.l4;

import java.util.*;

public class TorchWire {

  static class Node {
    int id;
    int type;
    int power;

    Node(int id, int type, int power) {
      this.id = id;
      this.type = type;
      this.power = power;
    }

    public int hashCode() {
      return Objects.hash(this.id);
    }

    public boolean equals(Object o) {
      if(this == o) return true;
      if(o == null || this.getClass() != o.getClass()) return false;
      Node node  = (Node)o;
      return Objects.equals(this.id, node.id);
    }

    public String toString() {
      return this.id + " " + this.type + " " + this.power;
    }


  }

  public void distributePower(Map<Integer, List<Integer>> graph, Map<Integer, Node> nodeMap) {
    if(graph == null) return;
    Queue<Node> q = new LinkedList<>();
    for(int n: graph.keySet()) {
      Node curr = nodeMap.get(n);
      if(curr.type == 1) {
        q.add(curr);
      }
    }

    while(!q.isEmpty()) {
      Node curr = q.poll();
      for(int nextId : graph.get(curr.id)) {
        Node nextNode = nodeMap.get(nextId);
        if(nextNode.power < curr.power - 1) {
          nextNode.power = curr.power-1;
          q.add(nextNode);
        }
      }
    }
   }

}
