import java.util.*;
import java.util.Stack;

public class LinkedListSolution {
    int capacity, count;
    Node head, tail;
    Node[] map;
    boolean[] mp;

    public ListNode createLinkedList(int[] list){
        if(list.length == 0) return null;
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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }

    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start + 1 == end) {
            return merge2(lists[start], lists[end]);
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        return merge2(left, right);
    }

    private ListNode merge2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = (l1 != null) ? l1 : l2;

        return dummy.next;
    }

    public int getSum(int a, int b) {
        if(a==0) return b;
        if(b==0) return a;

        while(b!=0){
            int carry = a&b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

    public int[] topKFrequent(int[] nums, int k){
        if(k==nums.length)return nums;
        HashMap<Integer, Integer> output = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int i = 0,n=nums.length;
        while(i < n){
            output.put(nums[i],output.getOrDefault(nums[i++],0)+1);
        }
        List<Integer>[] bucket = new List[n+1];
        for(int key : output.keySet()){
            int freq = output.get(key);
            if(bucket[freq] == null)
                bucket[freq] = new ArrayList<>();
            bucket[freq].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for(int p = bucket.length-1; p >= 0 && res.size()<k; p--){
            if(bucket[p]!=null)
                res.addAll(bucket[p]);
        }
        int[] r = new int[res.size()];
        i=0;
        for(int v : res)
            r[i++] = v;
        return r;
    }

    public HashMap<Integer,Integer> sortByValue(HashMap<Integer,Integer> hm){
        List<Map.Entry<Integer,Integer>> list = new LinkedList<>(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }

        });
        HashMap<Integer,Integer> temp = new HashMap<>();
        for(Map.Entry<Integer,Integer> entry:list)
            temp.put(entry.getKey(),entry.getValue());
        return temp;
    }

    public void setZeros(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean isRow0 = false, isCol0 = false;
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 0)
                isRow0 = true;
        }
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0)
                isCol0 = true;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }
        if (isRow0){
            for (int i = 0; i < rows; i++) {
                matrix[0][i] = 0;
            }
        }
        if(isCol0){
            for (int i = 0; i < cols; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int rows = matrix.length, cols = matrix[0].length, i = 0, j = 0, tol = -1, incr = 1;
        while (true) {
            boolean isBreak = true;
            while (j < cols && j >= 0) {
                list.add(matrix[i][j]);
                j += incr;
                isBreak = false;
            }
            tol++;
            i+=incr;
            j-=incr;
            cols--;
            while (i < rows && i >= 0) {
                list.add(matrix[i][j]);
                i += incr;
                isBreak = false;
            }
            incr *=-1;
            i+=incr;
            j+=incr;
            rows--;
            if(isBreak) break;
        }
        return list;
    }

    public boolean exist(char[][] board, String word){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(exist(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, String word, int i, int j, int x){
        if(x == word.length()) return true;
        if(i < 0  || j < 0 || i == board.length || j == board[i].length) return false;
        if(board[i][j] != word.charAt(x)) return false;
        board[i][j] ^= 256;
        boolean exist = exist(board, word, i-1,j,x+1) ||
                exist(board, word, i+1,j,x+1) ||
                exist(board, word, i, j-1,x+1) ||
                exist(board, word, i, j+1,x+1);
        board[i][j] ^= 256;
        return exist;
    }

    public ListNode deleteDuplicates(ListNode head){
        if(head == null) return head;
        ListNode node = head;
        while(node!=null){
            if(node.next != null && node.val == node.next.val){
                node.next = node.next.next;
            }
            else
                node = node.next;
        }
        return head;
    }

    public void MyHashSet(){
        mp = new boolean[1000001];
        Arrays.fill(mp, false);
    }

    public void add(int key){
        mp[key] = true;
    }

    public boolean contains(int key){
        return mp[key];
    }

    public void remove(int key){
        mp[key] = false;
    }

    public ListNode doubleIt(ListNode head){
        ListNode dummy = new ListNode(0), prev = dummy, node = head;
        prev.next = head;
        if(node.val >= 5) head = dummy;
        while(node!=null){
            node.val = 2 * node.val;
            if(node.val >= 10){
                prev.val = prev.val+node.val / 10;
                node.val = node.val % 10;
            }
            prev = node;
            node = node.next;
        }
        return head;
    }

    public ListNode insertGreatestCommonDivisors(ListNode head){
        ListNode node = head;
        while(node.next!=null){
            int gcd = gcd(node.val, node.next.val);
            ListNode gcdNode = new ListNode(gcd);
            gcdNode.next = node.next;
            node.next = gcdNode;
            node = gcdNode.next;
        }
        return head;
    }

    private int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a%b);
    }

    public ListNode removeNodes(ListNode head){
        head = reverseList(head);
        ListNode node = head;
        while(node.next!=null){
            if(node.next.val<node.val){
                node.next = node.next.next;
            }
            else node = node.next;
        }
        head = reverseList(head);
        return head;
    }

    private ListNode reverseList(ListNode node){
        ListNode temp = node, newHead = node.next, temp2;
        while(newHead!=null){
            temp2 = node;
            node = newHead;
            newHead = newHead.next;
            node.next = temp2;
            temp.next = newHead;
        }
        return node;
    }

    public int[][] spiralMatrix(int m, int n, ListNode head){
        int[][] ans=new int[m][n];
        for(int[] arr:ans){
            Arrays.fill(arr,-1);
        }

        int rowBegin=0;
        int rowEnd=m-1;
        int columnBegin=0;
        int columnEnd=n-1;
        ListNode cur=head;

        while(rowBegin<=rowEnd && columnBegin<=columnEnd && cur!=null){
            for(int i=columnBegin;i<=columnEnd && cur!=null;i++){
                if(cur!=null){
                    ans[rowBegin][i]=cur.val;
                }
                cur=cur.next;
            }
            rowBegin++;
            for(int i=rowBegin;i<=rowEnd && cur!=null;i++){
                if(cur!=null){
                    ans[i][columnEnd]=cur.val;
                }
                cur=cur.next;
            }
            columnEnd--;
            if(rowBegin<=rowEnd){
                for(int i=columnEnd;i>=columnBegin && cur!=null;i--){
                    if(cur!=null){
                        ans[rowEnd][i]=cur.val;
                    }
                    cur=cur.next;
                }
            }
            rowEnd--;
            if(columnBegin<=columnEnd){
                for(int i=rowEnd;i>=rowBegin && cur!=null;i--){
                    if(cur!=null){
                        ans[i][columnBegin]=cur.val;
                    }
                    cur=cur.next;
                }
            }
            columnBegin++;
        }
        return ans;
    }

    public int totalSteps(int[] A){
        int n = A.length, res = 0, j = -1;
        int dp[] = new int[n], stack[] = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            while (j >= 0 && A[i] > A[stack[j]]) {
                dp[i] = Math.max(++dp[i], dp[stack[j--]]);
                res = Math.max(res, dp[i]);
            }
            stack[++j] = i;
        }
        return res;
    }

    public ListNode mergeNodes(ListNode head){
        ListNode modify = head.next;
        ListNode nextSum = modify;

        while (nextSum != null) {
            int sum = 0;
            // Find the sum of all nodes until you encounter a 0.
            while (nextSum.val != 0) {
                sum += nextSum.val;
                nextSum = nextSum.next;
            }

            // Assign the sum to the current node's value.
            modify.val = sum;
            // Move nextSum to the first non-zero value of the next block.
            nextSum = nextSum.next;
            // Move modify also to this node.
            modify.next = nextSum;
            modify = modify.next;
        }
        return head.next;
    }
}
