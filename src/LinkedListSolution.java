import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LinkedListSolution {
    int capacity, count;
    Node head, tail;
    Node[] map;

    public ListNode createLinkedList(int[] list){
        ListNode head = new ListNode(list[0]);
        ListNode temp = head;
        for (int i = 1; i < list.length; i++) {
            head.next = new ListNode(list[i]);
            head = head.next;
        }
        head.next = null;
        return temp;
    }

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

    public ListNode swapPairs(ListNode head){
        if(head == null) return head;
        ListNode res = head,prev = null,temp;
        if(head.next!=null)res = head.next;
        while(head!=null && head.next != null){
            temp = head.next;
            head.next = temp.next;
            temp.next = head;
            if(prev!=null)prev.next = temp;
            prev = head;
            head = prev.next;
        }
        return res;
    }

    public void reorderList(ListNode head){
        if(head == null) return;
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head, temp = head.next;
        stack.push(node);
        while(node.next!=null){
            stack.push(node.next);
            node = node.next;
        }
        int n = stack.size();
        node = head;
        for (int i = 0; i < n / 2; i++) {
            node.next = stack.pop();
            node = node.next;
            node.next = temp;
            temp = temp.next;
            node = node.next;
        }
        node.next = null;
    }

//    public ListNode sortList(ListNode head){
//        if(head == null) return head;
//        ListNode node = head.next, temp = head, low = head, prev;
//        while(temp.next != null){
//            if(node.val <= temp.val){
//                temp.next = node.next;
//                if(node.val <= low.val){
//                    node.next = low;
//                    low = node;
//                }
//                else {
//                    prev = low;
//                    while (prev != temp) {
//                        if (prev.val < node.val && prev.next.val > node.val) {
//                            node.next = prev.next;
//                            prev.next = node;
//                            break;
//                        }
//                        prev = prev.next;
//                    }
//                }
//                node = temp.next;
//            }
//            else {
//                node = node.next;
//                temp = temp.next;
//            }
//            head = low;
//        }
//        return head;
//    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }

    ListNode merge(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(0), p = l;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if (l1 != null)
            p.next = l1;

        if (l2 != null)
            p.next = l2;

        return l.next;
    }

    public ListNode reverseKGroup(ListNode head, int k){
        int size = 0;
        ListNode node = head, tail = node, temp2, newHead, prevTail = tail;
        while(node != null){
            size++;
            node = node.next;
        }
        node = head;

        for (int i = k; i <= size; i+=k) {
            int idx = 1;
            newHead = node.next;
            tail = node;
            while(idx < k){
                temp2 = node;
                node = newHead;
                newHead = newHead.next;
                node.next = temp2;
                tail.next = newHead;
                idx++;
            }
            if(i == k) {
                head = node;
            }
            else
                prevTail.next = node;
            prevTail = tail;
            node = tail.next;
        }
        return head;
    }

    public ListNode reverseList(ListNode node, int k) {
        if(node==null) return null;
        int i = 1;
        ListNode temp = node, newHead = node.next, temp2;
        while(i < k){
            temp2 = node;
            node = newHead;
            newHead = newHead.next;
            node.next = temp2;
            temp.next = newHead;
            i++;
        }
        return temp;
    }

    public void LRUCache(int capacity){
        this.capacity = capacity;
        count = 0;
        map = new Node[10_000+1];
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
    }

    public int get(int key){
        if(map[key] != null){
            Node node = map[key];
            int val = node.val;
            deleteNode(node);
            addNodeToHead(node);
            return val;
        }
        else
            return -1;
    }

    public void put(int key, int value){
        if(map[key] != null){
            Node node = map[key];
            node.val = value;
            deleteNode(node);
            addNodeToHead(node);
        }
        else{
            Node node = new Node();
            node.val = value;
            node.min = key;
            map[key] = node;
            if(count < capacity){
                count++;
                addNodeToHead(node);
            }
            else{
                map[tail.prev.min] = null;
                deleteNode(tail.prev);
                addNodeToHead(node);
            }
        }
    }

    private  void deleteNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeToHead(Node node){
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }
}
