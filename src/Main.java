import java.util.*;

//Sorting
//Arrays(Done)
//LinkedList(Done)
//BinaryTree
//Recursion-I
//Recursion-II
//BinarySearch
//N-Ary tree
//BinarySearchTree
//Trie
//HashTable
//Array and string
//Queue and stack(Done)
//Decision tree

public class Main {
    public static void main(String[] args) {
        Solution();
    }

    public static void Queue(){
        MyCircularQueue queue = new MyCircularQueue(3);
//        String[] value = new String[]{"enQueue","Rear","Rear","deQueue","enQueue","Rear","deQueue","Front","deQueue","deQueue","deQueue"};
//        int[] val = new int[]{6,0,0,0,5,0};
//        for (int i = 0; i < value.length; i++) {
//            switch (value[i]){
//                case "enQueue": boolean enq = queue.enQueue(val[i]);
//                break;
//                case "deQueue": boolean deq = queue.deQueue();
//                break;
//                case "Rear": int v = queue.rear();
//                break;
//                case "Front": int f = queue.front();
//                break;
//                case "isFull": boolean isF = queue.isFull();
//                break;
//                case "isEmpty": boolean isE = queue.isEmpty();
//                break;
//            }
//        }
        int[][] grid = new int[][]{
                {0,0,0},
                {0,1,0},
                {1,1,1}
        };
//        queue.numIslands(grid);
//        BinaryNode node = new BinaryNode("1");
//        node.right = new BinaryNode("2");
//        node.right.left = new BinaryNode("3");
        String s = "3[a]2[bc]de";
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1,3));
        list.add(Arrays.asList(1,4));
        list.add(Arrays.asList(2,3,4,1));
        list.add(Arrays.asList());
        list.add(Arrays.asList(4,3,2));
        boolean isValid = queue.canVisitAllRooms(list);
    }

    public static void LinkedList(){
//        int[] inputs = new int[]{84,2,39,3,1,42,1,80,14,1,53,98,19,12,2,16,33,4,17,6,8,37,43,11,80,31,13,23,17,4,10,0,21,73,22,24,37,14,97,8,6,17,50,28,76,79,18,30,5,9,83,3,40,26,20,90,30,40,56,15,23,51,21,26,83,30,12,8,4,20,45,10,56,18,33,2,70,57,31,24,16,92,40,23,26,1,92,3,78,42,18,39,9,13,33,17,51,18,95,18,33,80,21,7,17,46,33,60,26,4,9,45,38,95,78,54,42,86};
//        String[] input = new String[]{"addAtHead","addAtTail","addAtTail","get","get","addAtTail","addAtIndex","addAtHead","addAtHead","addAtTail","addAtTail","addAtTail","addAtTail","get","addAtHead","addAtHead","addAtIndex","addAtIndex","addAtHead","addAtTail","deleteAtIndex","addAtHead","addAtHead","addAtIndex","addAtTail","get","addAtIndex","addAtTail","addAtHead","addAtHead","addAtIndex","addAtTail","addAtHead","addAtHead","get","deleteAtIndex","addAtTail","addAtTail","addAtHead","addAtTail","get","deleteAtIndex","addAtTail","addAtHead","addAtTail","deleteAtIndex","addAtTail","deleteAtIndex","addAtIndex","deleteAtIndex","addAtTail","addAtHead","addAtIndex","addAtHead","addAtHead","get","addAtHead","get","addAtHead","deleteAtIndex","get","addAtHead","addAtTail","get","addAtHead","get","addAtTail","get","addAtTail","addAtHead","addAtIndex","addAtIndex","addAtHead","addAtHead","deleteAtIndex","get","addAtHead","addAtIndex","addAtTail","get","addAtIndex","get","addAtIndex","get","addAtIndex","addAtIndex","addAtHead","addAtHead","addAtTail","addAtIndex","get","addAtHead","addAtTail","addAtTail","addAtHead","get","addAtTail","addAtHead","addAtTail","get","addAtIndex"};
       MyLinkedList ll = new MyLinkedList();
        ll.addAtHead(-2);
        ll.addAtHead(0);
        ll.addAtHead(4);
        ll.addAtHead(3);
        ll.addAtHead(5);
        ll.addAtHead(-1);
        MyNode val = ll.rotateRight(ll.head, 4);
//        MyLinkedList ll2 = new MyLinkedList();
//        ll2.addAtHead(10);
//        ll2.addAtHead(9);
//        ll2.addAtHead(8);
//        ll2.addAtHead(7);
//        ll.get(2).child = ll2.get(0);
//        MyLinkedList ll3 = new MyLinkedList();
//        ll3.addAtHead(12);
//        ll3.addAtHead(11);
//        ll2.get(1).child = ll3.get(0);
//       ll.flatten(ll.head);
//        int j = 0;
//        int val = 0;
//        for (int i = 0; i < input.length; i++,j++) {
//            if(input[i] == "addAtHead")
//                ll.addAtHead(inputs[j]);
//            else if(input[i] == "addAtTail")
//                ll.addAtTail(inputs[j]);
//            else if(input[i] == "get")
//                val = ll.get(inputs[j]);
//            else if(input[i] == "deleteAtIndex")
//                ll.deleteAtIndex(inputs[j]);
//            else if(input[i] == "addAtIndex")
//            {ll.addAtIndex(inputs[i], inputs[j+1]);j++;}
//        }
    }

    public static void ArraysSolution(){
        ArraysSolution sol = new ArraysSolution();
//        int arr = new int{1,2,3,0,0,0};
//        int arr2 = new int{2,5,6};
//        sol.merge(arr, arr.length, arr2, arr2.length);
//        int nums = new int{0,0,1,1,1,2,2,3,3,4}; // Input array
//        int val = 2; // Value to remove
//        int expectedNums = new int{0,1,2,3,4}; // The expected answer with correct length.
//        int actualLength = expectedNums.length;
//        // It is sorted with no values equaling val.
//
//        int k = sol.removeDuplicates(nums); // Calls your implementation
//
//        assert k == expectedNums.length;
//        //sort(nums, 0, k); // Sort the first k elements of nums
//        for (int i = 0; i < actualLength; i++) {
//            assert numsi == expectedNumsi;
//        }
        int[] arr = new int[]{4,3,2,7,8,2,3,1};
        List<Integer> val = sol.findDisappearedNumbers(arr);
        //System.out.println(val);
    }

    public static void Sorting(){
        int[] arr = new int[]{7,3,2,5,6,10,9,8,1};
        Sorting sort = new Sorting();
        //sort.Bubble(arr);
        //sort.Selection(arr);
        //sort.Insertion(arr);
        //sort.Bucket(arr);
        //sort.Merge(arr);
        //sort.Quick(arr);
        sort.Heap(arr);
        //sort.sortColors(arr);
        MyLinkedList ll = new MyLinkedList();
        ll.addAtHead(-2);
        ll.addAtHead(0);
        ll.addAtHead(4);
        ll.addAtHead(3);
        ll.addAtHead(5);
        ll.addAtHead(-1);
        sort.insertionSortList(ll.head);
    }

    public static void Trie(){
        Trie trie = new Trie();
        trie.insert("API");
        trie.insert("APIS");
        trie.insert("APPLE");
        trie.delete("APIS");
    }

    public static void BinaryTree(){
        BinaryNode node = new BinaryNode();
        node.value = "1";
        BinaryTreeLL bt = new BinaryTreeLL(node);
        bt.root.left = new BinaryNode("2");
        bt.root.right = new BinaryNode("3");
//        bt.root.left.left = new BinaryNode("1");
//        bt.root.left.right = new BinaryNode("7");
//        bt.root.left.left.left = new BinaryNode("5");
//        bt.root.left.left.right = new BinaryNode("1");
        bt.root.right.left = new BinaryNode("4");
        bt.root.right.right = new BinaryNode("5");
//        bt.root.right.left.right = new BinaryNode("6");
//        bt.root.right.right.right = new BinaryNode("8");
        String val = bt.serialize(node);
        BinaryNode v = bt.deserialize(val);
    }

    private static void createMapping(int[] inorder, Map<Integer, Integer> nodeToIndex, int n) {
        for (int i = 0; i < n; i++) {
            nodeToIndex.put(inorder[i], i);
        }
    }

    private static BinaryNode solve(int[] inorder, int[] postorder, int[] index, int inorderStart, int inorderEnd, Map<Integer, Integer> nodeToIndex, int n) {
        if (index[0] < 0 || inorderStart > inorderEnd)
            return null;

        int element = postorder[index[0]];
        BinaryNode root = new BinaryNode(String.valueOf(element));
        int position = nodeToIndex.get(element);
        index[0] -= 1;

        root.right = solve(inorder, postorder, index, position + 1, inorderEnd, nodeToIndex, n);
        root.left = solve(inorder, postorder, index, inorderStart, position - 1, nodeToIndex, n);

        return root;
    }

    public static BinaryNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        int[] postorderIndex = {n - 1};

        Map<Integer, Integer> nodeToIndex = new HashMap<>();
        createMapping(inorder, nodeToIndex, n);
        return solve(inorder, postorder, postorderIndex, 0, n - 1, nodeToIndex, n);
    }

    public static void SetOfStacks(){
        SetOfStacks sos = new SetOfStacks(3);
        sos.push(1);
        sos.push(2);
        sos.push(3);
        sos.push(4);
        sos.push(5);
        int val = sos.popAt(0);
        int val1 = sos.popAt(1);
    }

    public static void AnimalQueue(){
        AnimalQueue animals = new AnimalQueue();
        animals.enqueue(new Cat("Kiki"));
        animals.enqueue(new Cat("Kari"));
        animals.enqueue(new Dog("Beji"));
        animals.enqueue(new Cat("Reki"));
        animals.enqueue(new Dog("Dexter"));

        String name = animals.dequeueAny().name();
        name = animals.dequeueDogs().name();
        name = animals.dequeueCats().name();
    }

    public static void Solution(){
        HashTableSolution sol = new HashTableSolution();
        //TreeNode root = sol.createBinaryTree(new Integer[]{3,5,1,6,2,0,8,null,null,7,4});
        //sol.arr = new int[]{1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0};
        //ListNode root = sol.createLinkedList(new int[]{1,2,3,4,5});
        sol.findDuplicate(new int[]{1,3,4,2,2});
//        sol.set("foo","bar3",7);
//        sol.set("foo","bar4",9);
//        sol.set("foo","bar5",10);
//        sol.set("foo","bar6",12);
//        sol.set("foo","bar7",15);
//        sol.set("foo","bar8",16);
//        sol.set("foo","bar9",18);
//        sol.set("foo","bar10",21);
//        val = sol.get("foo",6);
//        val = sol.get("foo",11);
//        val = sol.get("foo",14);
//        val = sol.get("foo",19);
        //TreeNode root2 = sol.createBinaryTree(new Integer[]{4,1,2});
    }
}