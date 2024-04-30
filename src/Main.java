import java.util.List;

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
//Queue and stack
//Decision tree

public class Main {
    public static void main(String[] args) {
        //SetOfStacks();
        //AnimalQueue();
        //BinaryTree();
        //Trie();
        //Sorting();
        //ArraysSolution();
        LinkedList();
    }

    public static void LinkedList(){
//        int[] inputs = new int[]{84,2,39,3,1,42,1,80,14,1,53,98,19,12,2,16,33,4,17,6,8,37,43,11,80,31,13,23,17,4,10,0,21,73,22,24,37,14,97,8,6,17,50,28,76,79,18,30,5,9,83,3,40,26,20,90,30,40,56,15,23,51,21,26,83,30,12,8,4,20,45,10,56,18,33,2,70,57,31,24,16,92,40,23,26,1,92,3,78,42,18,39,9,13,33,17,51,18,95,18,33,80,21,7,17,46,33,60,26,4,9,45,38,95,78,54,42,86};
//        String[] input = new String[]{"addAtHead","addAtTail","addAtTail","get","get","addAtTail","addAtIndex","addAtHead","addAtHead","addAtTail","addAtTail","addAtTail","addAtTail","get","addAtHead","addAtHead","addAtIndex","addAtIndex","addAtHead","addAtTail","deleteAtIndex","addAtHead","addAtHead","addAtIndex","addAtTail","get","addAtIndex","addAtTail","addAtHead","addAtHead","addAtIndex","addAtTail","addAtHead","addAtHead","get","deleteAtIndex","addAtTail","addAtTail","addAtHead","addAtTail","get","deleteAtIndex","addAtTail","addAtHead","addAtTail","deleteAtIndex","addAtTail","deleteAtIndex","addAtIndex","deleteAtIndex","addAtTail","addAtHead","addAtIndex","addAtHead","addAtHead","get","addAtHead","get","addAtHead","deleteAtIndex","get","addAtHead","addAtTail","get","addAtHead","get","addAtTail","get","addAtTail","addAtHead","addAtIndex","addAtIndex","addAtHead","addAtHead","deleteAtIndex","get","addAtHead","addAtIndex","addAtTail","get","addAtIndex","get","addAtIndex","get","addAtIndex","addAtIndex","addAtHead","addAtHead","addAtTail","addAtIndex","get","addAtHead","addAtTail","addAtTail","addAtHead","get","addAtTail","addAtHead","addAtTail","get","addAtIndex"};
       MyLinkedList ll = new MyLinkedList();
        ll.addAtHead(6);
        ll.addAtHead(5);
        ll.addAtHead(4);
        ll.addAtHead(3);
        ll.addAtHead(2);
        ll.addAtHead(1);
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
        int[] arr = new int[]{9, 4, 7, 1, 6, 2, 8, 3, 5};
        Sorting sort = new Sorting();
        //sort.Bubble(arr);
        //sort.Selection(arr);
        //sort.Insertion(arr);
        //sort.Bucket(arr);
        //sort.Merge(arr);
        //sort.Quick(arr);
        sort.Heap(arr);
    }

    public static void Trie(){
        Trie trie = new Trie();
        trie.insert("API");
        trie.insert("APIS");
        trie.insert("APPLE");
        trie.delete("APIS");
    }

    public static void BinaryTree(){

        BinaryTree tree = new BinaryTree(5);
        tree.insert("A");
        tree.insert("B");
        tree.insert("C");
        tree.insert("D");
        tree.insert("E");
        tree.preOrder(1);
        tree.inOrder(1);
        tree.postOrder(1);
        tree.search("B");
        tree.delete("B");
        tree.preOrder(1);
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
}