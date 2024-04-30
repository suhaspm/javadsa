import org.w3c.dom.Node;

class MyLinkedList {
    int size;
    MyNode head;
    MyNode tail;

    public MyLinkedList(){
        head = null;
        tail = null;
    }

    public MyNode get(int index) {
        if(head == null || index >= size) return null;

        MyNode node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void addAtHead(int val) {
        MyNode node = new MyNode();
        node.value = val;
        node.next = head;
        node.prev = null;
        head = node;
        if(tail == null) tail = node;
        size++;
    }

    public void addAtTail(int val) {
        MyNode node = new MyNode();
        node.value = val;
        if(tail != null) {
            tail.next = node;
            node.prev = tail;
        } else {
            head = node;
        }
        tail = node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if(index == 0) { addAtHead(val); return; }
        if(index == size) { addAtTail(val); return; }

        if(index > size) return; // Invalid index

        MyNode newNode = new MyNode();
        newNode.value = val;

        MyNode prev = null;
        MyNode curr = head;
        for (int i = 0; i < index; i++) {
            prev = curr;
            curr = curr.next;
            curr.prev = prev;
        }

        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = curr;
        curr.prev = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        if(head == null || index >= size) return;
        if(index == 0) {
            head = head.next;
            if(size == 1) tail = null; // Update tail if deleting the last element
        } else {
            MyNode prev = null;
            MyNode curr = head;
            for (int i = 0; i < index; i++) {
                prev = curr;
                curr = curr.next;
            }
            prev.next = curr.next;
            curr.prev = prev.prev;
            if(curr == tail) tail = prev; // Update tail if deleting the last element
        }
        size--;
    }

    public boolean hasCycle(MyNode head){
        MyNode fast = head, slow = head;
        while(fast  != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast)
                return true;
        }
        return false;
    }

    public MyNode detectCycle(MyNode head){
        MyNode fast = head, slow = head;
        while(fast  != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public MyNode getIntersectionNode(MyNode nodeA, MyNode nodeB){
        if(nodeA == null || nodeB == null) return null;

        MyNode a = nodeA, b = nodeB;
        while(a!=b){
        a = a == null?nodeB:a.next;
        b = b == null?nodeA:b.next;
        }
        return a;
    }

    public MyNode removeNthFromEnd(MyNode node, int n){
        MyNode dummy = new MyNode();
        dummy.value = 0;
        dummy.next = head;
        MyNode first = dummy, second = dummy;
        for (int i = 0; i < n+1; i++) {
            first = first.next;
        }
        while(first!=null){
            first=first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public MyNode reverseList(MyNode node){
        if(node==null) return null;
        MyNode temp = node, newHead = node.next, temp2;
        while(newHead!=null){
            temp2 = node;
            node = newHead;
            newHead = newHead.next;
            node.next = temp2;
            temp.next = newHead;
        }
        return node;
    }

    public MyNode removeElements(MyNode node, int val){
        if(node == null) return null;
        MyNode dummy = new MyNode();
        dummy.value = 0;
        dummy.next = node;
        node = dummy;
        MyNode temp = dummy.next, h = dummy;
        while(temp != null){
            if(temp.value == val) {
                temp = temp.next;
                node.next = temp;
                continue;
            }
            node = node.next;
            temp = temp.next;
        }
        return h.next;
    }

    public MyNode oodEvenList(MyNode node){
        if(node == null) return null;

        MyNode odd = node, even = node.next, evenStart = node.next;
        while(odd.next!=null && even.next != null){
            odd.next = even.next;
            even.next = odd.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenStart;
        return node;
    }

    public boolean isPalindrome(MyNode node){
        MyNode fast = node, slow = node;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        MyNode temp = reverseList(slow);
        MyNode temp1 = node;
        while(temp != null){
            if(temp1.value != temp.value)
                return false;

            temp = temp.next;
            temp1 = temp1.next;
        }
        return true;
    }

    public MyNode mergeTwoLists(MyNode node1, MyNode node2){
        MyNode node = new MyNode();
        node.value = 0;
        node.next = null;
        head = node;
        while(node1 != null && node2 != null){
            if(node1.value <= node2.value) {
                node.next = node1;
                node = node.next;
                node1 = node1.next;
            }
            else {
                node.next = node2;
                node = node.next;
                node2 = node2.next;
            }
        }
        if(node1 == null)
            node.next = node2;
        else if(node2 == null)
            node.next = node1;
        return head.next;
    }

    public MyNode addTwoNumbers(MyNode l1, MyNode l2){
        MyNode head = new MyNode();
        MyNode node = head;
        int carry = 0;
        while(l1!= null || l2!=null){
            int l1Val = 0, l2Val = 0;
            MyNode newNode = new MyNode();
            node.next = newNode;
            node = node.next;
            if(l1 != null) { l1Val = l1.value;l1 = l1.next; }
            if(l2 != null) {l2Val = l2.value;l2 = l2.next; }
            int sum = l1Val+l2Val+carry;
            if(carry > 0) carry = 0;
            if(sum >= 10)
            {
                carry = 1;
                sum = sum - 10;
            }
            newNode.value = sum;
        }
        if(carry > 0) {
            node.next = new MyNode();
            node.next.value = carry;
            node.next.next = null;
        }
        return head.next;
    }

    public MyNode flatten(MyNode node){
        MyNode temp = node;
        MyNode next = null;
        while(temp.next!=null){
            if(temp.child != null) {
                next = temp.next;
                MyNode flatten = flatten(temp.child);
                temp.next = temp.child;
                temp.child = null;
                flatten.next = next;
                temp = next;
            }
            temp = temp.next;
        }
        return temp;
    }

    public MyNode rotateRight(MyNode head, int k){
        if(head == null || k == 0) return head;

        MyNode tail = head;
        int size = 1;
        while(tail.next!=null) {
            tail = tail.next;
            size++;
        }
        tail.next = head;
        int n = k % size;
        for (int i = 0; i < size-n; i++) {
            tail = tail.next;
        }
        head = tail.next;
        tail.next = null;
        return head;
    }
}
