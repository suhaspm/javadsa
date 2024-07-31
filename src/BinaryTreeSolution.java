import com.sun.source.tree.Tree;

import java.util.*;

public class BinaryTreeSolution {
    private int res = 0;
    private int p = 0, i = 0;
    List<List<Integer>> pathSum = new ArrayList<>();
    Map<Long, Integer> hMap;

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

    public TreeNode invertTree(TreeNode root){
        if(root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null)  return true;
        if (Height(root) == -1)  return false;
        return true;
    }

    public int Height(TreeNode root) {
        if (root == null)  return 0;
        int leftHeight = Height(root.left);
        int rightHight = Height(root.right);
        if (leftHeight == -1 || rightHight == -1)  return -1;
        if (Math.abs(leftHeight - rightHight) > 1)  return -1;
        return Math.max(leftHeight, rightHight) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root){
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root){
        if(root == null) return 0;
        int leftDepth = dfs(root.left);
        int rightDepth = dfs(root.right);
        res = Math.max(res, leftDepth + rightDepth);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    public int maxDepth(TreeNode node){
        maxDepth(node, 1);
        return res;
    }

    private void maxDepth(TreeNode node, int depth){
        if(node == null) return;
        if(node.left == null && node.right == null)
            res = Math.max(res, depth);
        maxDepth(node.left,depth+1);
        maxDepth(node.right,depth+1);
    }

    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        else if(p == null || q == null) return false;
        if(p.val == q.val){
            if(isSameTree(p.left, q.left) && isSameTree(p.right,q.right))
                return true;
        }
        return false;
    }

    public boolean isSymmetric(TreeNode root){
        if(root == null)return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right){
        if(left == null && right == null) return true;
        else if(left == null || right == null) return false;
        return left.val == right.val && isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
    }

    public boolean isSubtree(TreeNode root, TreeNode subroot){
        if(root == null) return false;
        if(isSameTree(root,subroot)) return true;
        return isSubtree(root.left,subroot) || isSubtree(root.right,subroot);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        preOrder(root, 0, list);
        return list;
    }

    private void preOrder(TreeNode root, int level, List<List<Integer>> list){
        if(root == null) return;
        if(list.size()==level){
            List<Integer> l = new ArrayList<>();
            l.add(root.val);
            list.add(l);
        }
        else
            list.get(level).add(root.val);
        preOrder(root.left, level+1, list);
        preOrder(root.right, level+1, list);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == root || q == root) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    public List<Integer> rightSideView(TreeNode root){
        List<Integer> list = new ArrayList<>();
        rightSideView(root, list,1);
        return list;
    }

    private void rightSideView(TreeNode root, List<Integer> list,int level){
        if(root == null) return;
        if(res < level){
            list.add(root.val);
            res = level;
        }
        rightSideView(root.right, list, level+1);
        rightSideView(root.left, list, level+1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder){
        return buildTree(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int stop){
        if(p >= preorder.length) return null;
        if(inorder[i] == stop){
            ++i;
            return null;
        }

        TreeNode node = new TreeNode(preorder[p++]);
        node.left = buildTree(preorder,inorder,node.val);
        node.right = buildTree(preorder,inorder,stop);
        return node;
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum){
        List<Integer> list = new ArrayList<>();
        pathSum(root, targetSum, 0, list);
        return pathSum;
    }

    private void pathSum(TreeNode root, int targetSum, int sum, List<Integer> list){
        if(root == null) return;
        sum+=root.val;
        list.add(root.val);
        if(root.left == null && root.right == null && sum == targetSum)
            pathSum.add(new ArrayList<>(list));
        pathSum(root.left, targetSum, sum, list);
        pathSum(root.right, targetSum, sum, list);
        list.removeLast();
    }

    public int pathSum3(TreeNode root, int targetSum){
        hMap = new HashMap<>();
        res = 0;
        pathSum3(root, targetSum, 0);
        return pathSum.size();
    }

    private void pathSum3(TreeNode root, int targetSum, long sum){
        if(root == null) return;
        sum+=root.val;
        //(sum > targetSum || (targetSum < 0 && sum < targetSum)) &&
        if(hMap.containsKey(sum - targetSum))
            res+=hMap.get(sum-targetSum);

        if(sum == targetSum)
            res++;

        hMap.put(sum, hMap.getOrDefault(sum,0)+1);
        pathSum3(root.left, targetSum, sum);
        pathSum3(root.right, targetSum, sum);
        hMap.put(sum,hMap.get(sum)-1);
    }

    public int widthOfBinaryTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        int[] out = new int[1];
        dfs(root,1,0,list,out);
        return out[0];
    }

    private void dfs(TreeNode root, int num, int depth, List<Integer> list, int[] res){
        if(root == null) return;
        if(depth >= list.size()) list.add(num);
        res[0] = Integer.max(res[0], num+1-list.get(depth));
        dfs(root.left, num*2, depth+1,list, res);
        dfs(root.right, num*2+1,depth+1,list, res);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> list = new ArrayList<>();
        if(root == null) return list;
        zigzagLevelOrder(root,1,list);
        return list;
    }

    private void zigzagLevelOrder(TreeNode root, int level,List<List<Integer>> list){
        if(root == null) return;
        if(level > list.size())
            list.add(new ArrayList<>());
        if(level % 2 == 0)
            list.get(level-1).addFirst(root.val);
        else
            list.get(level-1).addLast(root.val);
        zigzagLevelOrder(root.left,level+1,list);
        zigzagLevelOrder(root.right,level+1,list);
    }

    public void getParentsByBFS(Map<TreeNode, TreeNode> parent, TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            while (n-- > 0) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    parent.put(curr.left, curr);
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    parent.put(curr.right, curr);
                    queue.offer(curr.right);
                }
            }
        }
    }

    public void getParentsByDFS(Map<TreeNode, TreeNode> parent, TreeNode root) {
        if (root == null)
            return;
        if (root.left != null)
            parent.put(root.left, root);
        if (root.right != null)
            parent.put(root.right, root);
        getParentsByDFS(parent, root.left);
        getParentsByDFS(parent, root.right);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        getParentsByDFS(parent, root);
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Boolean> visited = new HashMap<>();
        visited.put(target, true);
        queue.offer(target);
        int level = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            if (level == k)
                break;
            level++;
            while (n-- > 0) {
                TreeNode curr = queue.poll();
                if (curr.left != null && !visited.containsKey(curr.left)) {
                    visited.put(curr.left, true);
                    queue.offer(curr.left);
                }
                if (curr.right != null && !visited.containsKey(curr.right)) {
                    visited.put(curr.right, true);
                    queue.offer(curr.right);
                }
                if (parent.containsKey(curr) && !visited.containsKey(parent.get(curr))) {
                    visited.put(parent.get(curr), true);
                    queue.offer(parent.get(curr));
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            ans.add(queue.poll().val);
        }
        return ans;
    }

    public boolean isPalindrom(int x){
        if(x < 0) return false;
        else if(x == 0) return true;
        else{
            if(x%10==0) return false;
            int y = 0 ,z=x;
            while(z!=0){
                y=y*10+z%10;
                z/=10;
            }
            if(x==y)return true;
        }
        return false;
    }

    public int singleNumber(int[] nums){
        int ans=0;
        for(int x:nums)
            ans^=x;
        return ans;
    }
}
