import java.util.*;

public class WordLadder2 {

  List<List<String >> ans;
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    ans = new ArrayList<>();
    if(beginWord.equals(endWord)) {
      ans.add(new ArrayList<>(List.of(beginWord)));
      return ans;
    }

    Map<String, Integer> visited = new HashMap<>();
    Queue<String> q = new LinkedList<>();
    int level = 1;
    visited.put(beginWord , level);
    q.add(beginWord);
    boolean flag = false;

    while(!q.isEmpty()) {
      // int n = q.size();
      // Set<String> lVisited = new HashSet<>();

      // for(int i=0; i<n; ++i) {
      String word = q.poll();
      // q.pop();
      // String word = curr.get(curr.size()-1);

      for(String next: wordList) {
        if(!visited.containsKey(next) && isNext(word, next)) {
          visited.put(next, visited.get(word) + 1);
          q.add(next);
          System.out.println(word + " " + next );
          if(next.equals(endWord)) {
            break;
          }
        }
      }
    }

    for(String k: visited.keySet()) {
      System.out.println(k + " " + visited.get(k));
    }



    // while(!q.isEmpty()) {
    //     ans.add(q.poll());
    // }
    if(!visited.containsKey(endWord)) {
      System.out.println(endWord + " " + visited.get(endWord));
      return ans;
    }
    List<String> currPath = new ArrayList<>(List.of(endWord));

    dfs(endWord, visited, currPath, wordList);
    return ans;
  }

  void dfs(String word, Map<String, Integer> visited, List<String> currPath, List<String> wordList) {
    System.out.println(word);

    int currLevel = visited.get(word);
    if(currLevel == 1) {
      // currPath.add(word);
      List<String> temp = new ArrayList<>();
      temp.addAll(currPath);
      // temp.add(word);
      Collections.reverse(temp);
      ans.add(temp);
      return ;
    }

    for(String w: wordList) {
      if(isNext(word, w) && visited.containsKey(w) && visited.get(w) == currLevel-1) {
        currPath.add(w);
        dfs(w, visited, currPath, wordList);
        currPath.remove(currPath.size()-1);
      }
    }

  }

  boolean isNext(String word, String next) {
    int n = word.length();
    int diff = 0;
    for(int i=0; i<n; ++i) {
      if(word.charAt(i) != next.charAt(i)) {
        diff++;
      }
    }
    return diff == 1;
  }


}
