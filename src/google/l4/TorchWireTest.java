package google.l4;

import java.util.*;

public class TorchWireTest {

  public static void main(String[] args) {
    int n, m;
    Scanner sc = new Scanner(System.in);
    System.out.println("Input number of node ans edges");
    n = sc.nextInt();
    m = sc.nextInt();
    Map<Integer, List<Integer>> graph= new HashMap<>();
    Map<Integer, TorchWire.Node> nodeMap = new HashMap<>();
    for(int i=0; i<n; ++i) {
      int id, power;
      int type;
      id = sc.nextInt();
      type =sc.nextInt();
      if(type == 1) {
        power = 16;
      } else {
        power = 0;
      }
      nodeMap.put(id, new TorchWire.Node(id, type, power));
      graph.put(id, new ArrayList<>());
    }

    for(int i=0; i<m; ++i) {
      int u,  v;
      u = sc.nextInt();
      v = sc.nextInt();
      graph.get(u).add(v);
      graph.get(v).add(u);
    }


    TorchWire tw = new TorchWire();
    tw.distributePower(graph, nodeMap);

    for(int key: nodeMap.keySet()) {
      System.out.println(nodeMap.get(key).toString());
    }

  }



}
