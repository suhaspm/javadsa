public class Node {
    int val, min,idx;//min = freq
    char ch;
    Node next;
    public Node(int val, int min, Node next){
        this.val = val;
        this.min = min;
        this.next = next;
    }

    public Node(int val, int min){
        this.val = val;
        this.min = min;
    }

    public Node(){}
}
