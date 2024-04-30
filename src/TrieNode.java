import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character, TrieNode> map;
    boolean endOfString;

    public TrieNode(){
        map = new HashMap<>();
        endOfString = false;
    }
}
