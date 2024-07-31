import java.util.*;
import java.util.Stack;

public class BinarySearchTreeSolution {
    int k;
    PriorityQueue<Integer> queue;
    public TreeNode createBinaryTree(Integer[] levelOrder){
        if (levelOrder == null || levelOrder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode parent = queue.poll();
            if (levelOrder[i] != null) {
                parent.left = new TreeNode(levelOrder[i]);
                queue.offer(parent.left);
            }
            i++;
            if (i < levelOrder.length && levelOrder[i] != null) {
                parent.right = new TreeNode(levelOrder[i]);
                queue.offer(parent.right);
            }
            i++;
        }

        return root;
    }

    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q){
        int small = Math.min(p.val, q.val);
        int large = Math.max(p.val, q.val);
        while(root!=null){
            if(root.val > large)
                root = root.left;
            else if(root.val < small)
                root = root.right;
            else
                return root;
        }
        return null;
    }

    public TreeNode sortedArrayToBST(int[] nums){
        return createBST(nums, 0, nums.length-1);
    }

    private TreeNode createBST(int[] nums, int l, int r){
        if(l>r) return null;
        int mid = l+(r-l)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = createBST(nums,l,mid-1);
        root.right = createBST(nums,mid+1,r);
        return root;
    }

    public boolean isValidBST(TreeNode root){
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

    public int kthSmallest(TreeNode root, int k){
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null||!stack.empty()){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if(k == 0) break;
            root = root.right;
        }
        return root.val;
    }

    public TreeNode inOrderSuccessor(TreeNode root, TreeNode p){
        TreeNode successor = null;
        while(root!=null){
            if(root.val<p.val)
                root = root.right;
            else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }

    public TreeNode searchBST(TreeNode root, int val){
        root = helper(root, val);
        return root;
    }

    private TreeNode helper(TreeNode root, int val){
        if(root == null) return null;
        if(root.val == val) return root;
        if(val < root.val) root = helper(root.left, val);
        else root = helper(root.right, val);
        return root;
    }

    public void KthLargest(int k, int[] nums){
        this.k = k;
        this.queue = new PriorityQueue<>();
        for(int num : nums)
            add(num);
    }

    private int add(int num){
        if(queue.size()<k)
            queue.offer(num);
        else if(queue.peek()<num){
            queue.poll();
            queue.offer(num);
        }
        return queue.peek();
    }

    public int rangeSumBST(TreeNode root, int low, int high){
        if(root == null) return 0;
        int sum = 0;
        if(root.val>low)
            sum += rangeSumBST(root.left, low, high);
        if(root.val < high)
            sum += rangeSumBST(root.right, low, high);
        if(root.val >= low && root.val <= high)
            sum += root.val;
        return sum;
    }
}
