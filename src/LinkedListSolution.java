public class LinkedListSolution {
    public ListNode middleNode(ListNode head){
        if(head == null) return head;
        int length = 0;
        ListNode node = head;
        while(node != null){
            node = node.next;
            length++;
        }
        length = length/2;
        node = head;
        for (int i = 0; i < length; i++) {
            node = node.next;
        }
        return node;
    }
}
