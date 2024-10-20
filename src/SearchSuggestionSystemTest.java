public class SearchSuggestionSystemTest {
  public static void main(String[] args) {
    SearchSuggestionSystem s= new SearchSuggestionSystem();
    String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
    String searchWord = "mouse";

    s.suggestedProducts(products, searchWord);
  }
}
