import java.util.List;

public class WordLadder2Test {
  public static void main(String[] args) {
    List<String> wordList = List.of("hot","dot","dog","lot","log","cog");
    WordLadder2 t = new WordLadder2();
    List<List<String>> ans = t.findLadders("hit", "cog",wordList );
  }
}
