import java.util.*;

public class BinaryTreeLL {
    BinaryNode root;
    int answer;
    BinaryNode lca;

    public BinaryTreeLL(BinaryNode root){
        this.root = root;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(int value){
        BinaryNode newNode = new BinaryNode();
    }

    //Preorder traversal
    public List<Integer> PreOrder(BinaryNode node){
        if(node == null)
            return null;

        List<Integer> list = new ArrayList<>();
        if(!Objects.equals(node.value, null))
            list.add(Integer.valueOf(node.value));
        System.out.println(node.value);
        List<Integer> left = PreOrder(node.left);
        if(left != null)
            list.addAll(left);
        List<Integer> right = PreOrder(node.right);
        if(right != null)
            list.addAll(right);

        return list;
    }

    public void InOrder(BinaryNode node){
        if(node == null) return;
        InOrder(node.left);
        System.out.println(node.value);
        InOrder(node.right);
    }

    private void createMapping(int[] inorder, Map<Integer, Integer> nodeToIndex, int n) {
        for (int i = 0; i < n; i++) {
            nodeToIndex.put(inorder[i], i);
        }
    }

    private BinaryNode solve(int[] inorder, int[] postorder, int[] index, int inorderStart, int inorderEnd, Map<Integer, Integer> nodeToIndex, int n) {
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

    public BinaryNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        int[] postorderIndex = {n - 1};

        Map<Integer, Integer> nodeToIndex = new HashMap<>();
        createMapping(inorder, nodeToIndex, n);
        return solve(inorder, postorder, postorderIndex, 0, n - 1, nodeToIndex, n);
    }

    private void insert(int[] array, int index){
        if(array.length == index) return;
        BinaryNode node = new BinaryNode();
        node.value = String.valueOf(array[index]);
        insert(array, index+1);

    }

    public void PostOrder(BinaryNode node){
        if(node == null) return;
        PostOrder(node.left);
        PostOrder(node.right);
        System.out.println(node.value);
    }

    public void LevelOrder(){
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryNode node = queue.remove();
            System.out.println(node.value);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
    }

    public void Search(String value){
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryNode node = queue.remove();
            if(Objects.equals(node.value, value)) {
                System.out.println("The value " + value + " found");
                return;
            }
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
        System.out.println("The value not found in the tree");
    }

    public void insert(String value){
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        BinaryNode newNode = new BinaryNode();
        newNode.value = value;
        if(root == null){
            root = newNode;
            return;
        }
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryNode node = queue.remove();
            if(node.left == null){
                node.left = newNode;
                break;
            }
            else if(node.right == null){
                node.right = newNode;
                break;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
    }

    public int maxDepth(BinaryNode root) {
        answer = 0;
        maxDepth(root, 1);
        return answer;
    }

    private void maxDepth(BinaryNode root, int depth){
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            answer = Math.max(answer, depth);
        }
        maxDepth(root.left, depth + 1);
        maxDepth(root.right, depth + 1);
    }

    public boolean hasPathSum(BinaryNode root, int targetSum){
        if(root == null) return false;
        hasPathSum(root, Integer.parseInt(root.value), targetSum);
        if(answer == targetSum) return true;
        else return false;
    }

    public void hasPathSum(BinaryNode root, int sum, int targetSum){
        //if(root == null) return;
        if(root.left == null && root.right == null && sum == targetSum) { answer = sum; return;}

        if(root.left != null)
            hasPathSum(root.left, sum + Integer.parseInt(root.left.value), targetSum);
        if(root.right != null)
            hasPathSum(root.right, sum + Integer.parseInt(root.right.value), targetSum);
    }

    public boolean isSymmetric(BinaryNode root){
        if(root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(BinaryNode left, BinaryNode right){
        if(left == null && right == null) return true;
        else if(left == null || right == null) return false;
        return left.value == right.value && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public BinaryNode connect(BinaryNode root){
        if(root == null) return root;
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            BinaryNode node = queue.poll();
            System.out.println(node.value);
            if(node.left != null) {
                queue.add(node.left);
                node.left.next = node.right;
            }
            if(node.right != null) {
                if(node.next != null)
                    node.right.next = node.next.left;
                queue.add(node.right);
            }
        }
        return root;
    }

    public BinaryNode connect2(BinaryNode root){
        BinaryNode head = null;
        BinaryNode prev = null;
        BinaryNode cur = root;
        while(cur != null){
            while(cur!=null){
                if(cur.left != null){
                    if(prev != null)
                        prev.next = cur.left;
                    else
                        head = cur.left;
                    prev = cur.left;
                }
                if(cur.right!=null){
                    if(prev != null)
                        prev.next = cur.right;
                    else
                        head = cur.right;
                    prev = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
            head = null;
            prev = null;
        }
        return root;
    }

    public BinaryNode lca(BinaryNode root, BinaryNode p, BinaryNode q){
        if(root == null || p == root || q == root) return root;
        BinaryNode left = lca(root.left, p, q);
        BinaryNode right = lca(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    private void preOrder(BinaryNode root, BinaryNode p, BinaryNode q){
        if(root == null || p == null || q == null) return;

        if(Objects.equals(root.value, p.value)) lca = p;
        preOrder(root.left, p, q);
        preOrder(root.right, p, q);
    }

    public String serialize(BinaryNode root){
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(BinaryNode root, StringBuilder sb){
        if(root == null) sb.append("NN").append(",");
        else {
            sb.append(root.value).append(",");
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }

    public BinaryNode deserialize(String data){
        Deque<String> str = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(str);
    }

    private BinaryNode buildTree(Deque<String> str){
        String val = str.remove();
        if(Objects.equals(val, "NN")) return null;
        else {
            BinaryNode node = new BinaryNode(val);
            node.left = buildTree(str);
            node.right = buildTree(str);
            return node;
        }
    }
}
