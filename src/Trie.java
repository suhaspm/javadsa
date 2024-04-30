public class Trie {
    TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void insert(String value){
        TrieNode current = root;
        for (int i = 0; i < value.length(); i++) {
            Character ch = value.charAt(i);
            TrieNode node = current.map.get(ch);
            if(node == null){
                node = new TrieNode();
                current.map.put(ch, node);
            }
            current = node;
        }
        current.endOfString = true;
    }

    public boolean search(String value){
        TrieNode current = root;
        for (int i = 0; i < value.length(); i++) {
            Character ch = value.charAt(i);
            TrieNode node = current.map.get(ch);
            if(node == null){
                System.out.println("The value does not exist in the trie");
                return false;
            }
            current = node;
        }
        if(current.endOfString == true) {
            System.out.println("The value is found in the trie");
            return true;
        }
        else
            System.out.println("The value does not exist in the trie, but it is a prefix of another string");

        return current.endOfString;
    }

    public void delete(String value){
        delete(value, root, 0);
    }

    private boolean delete(String value, TrieNode node, int index){
        Character ch = value.charAt(index);
        TrieNode newNode = node.map.get(ch);
        if(newNode.map.size() > 1){
            delete(value, newNode, index+1);
            return false;
        }
        if(index == value.length() - 1){
            if(!newNode.map.isEmpty()){
            newNode.endOfString = false;
            return false;
            }
            else{
                node.map.remove(ch);
                return true;
            }
        }
        if(newNode.endOfString){
            delete(value, newNode, index+1);
            return false;
        }
        boolean canBeDeleted = delete(value, newNode, index+1);
        if(canBeDeleted) {
            node.map.remove(ch);
            return true;
        }
        else return false;
    }
}
