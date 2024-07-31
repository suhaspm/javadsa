import com.sun.source.tree.Tree;
import java.util.Stack;

import java.util.*;

public class GraphSolution {
    int res = Integer.MAX_VALUE;
    private int currentVal;
    private int currentCount = 0;
    private int maxCount = 0;
    private List<Integer> modes = new ArrayList<>();
    Integer prev = null;
    List<Integer> list = new ArrayList<>();
    boolean found = false;
    List<Integer> b2a = new ArrayList<>();
    int maxSum = Integer.MIN_VALUE;
    List<Integer>[] adj;
    boolean[] seen;
    int cnt = -1;
    int nodes = 0;
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

    public int minDepth(TreeNode root){
        if(root==null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left+right+1 : Math.min(left,right)+1;
    }

    public List<Integer> postOrderTraversal(TreeNode root){
        if(root == null) return null;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        list.add(root.val);
        return list;
    }

    public List<String> binaryTreePaths(TreeNode root){
        List<String> sList = new ArrayList<>();
        binaryTreePaths(root,"",sList);
        return sList;
    }

    private void binaryTreePaths(TreeNode root, String path, List<String> sList){
        if(root.left == null && root.right == null) sList.add(path + root.val);
        if(root.left != null) binaryTreePaths(root.left, path+root.val+"->",sList);
        if(root.right!=null) binaryTreePaths(root.right, path+root.val+"->",sList);
    }

    public int sumOfLeftLeaves(TreeNode root){
        int[] sum = new int[1];
        sumOfLeftLeaves(root, sum, false);
        return sum[0];
    }

    private void sumOfLeftLeaves(TreeNode root, int[] sum, boolean calSum){
        if(root.left == null && root.right==null && calSum){
            sum[0] += root.val;
            return;
        }
        if(root.left!=null)
            sumOfLeftLeaves(root.left, sum, true);
        if(root.right!=null)
            sumOfLeftLeaves(root.right, sum, false);
    }

    public int[] findMode(TreeNode root){
        inOrderTraversal(root);
        int[] res = new int[modes.size()];
        int i = 0;
        for(int m : modes)
            res[i++] = m;
        return res;
    }

    private void inOrderTraversal(TreeNode node){
        if (node == null) return;

        inOrderTraversal(node.left);

        currentCount = (node.val == currentVal) ? currentCount + 1 : 1;
        if (currentCount == maxCount) {
            modes.add(node.val);
        } else if (currentCount > maxCount) {
            maxCount = currentCount;
            modes.clear();
            modes.add(node.val);
        }
        currentVal = node.val;

        inOrderTraversal(node.right);
    }

    public int islandPerimeter(int[][] grid){
        int islands = 0, neighbours = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1){
                    islands++;
                    if(i < grid.length - 1 && grid[i+1][j] == 1) neighbours++;
                    if(j < grid[i].length - 1 && grid[i][j+1] == 1) neighbours++;
                }
            }
        }
        return islands * 4 - neighbours * 2;
    }

    public int getMinimumDifference(TreeNode root){
        if(root==null) return res;
        getMinimumDifference(root.left);
        if(prev!=null)
            res = Math.min(res, root.val-prev);
        prev = root.val;
        getMinimumDifference(root.right);
        return res;
    }

//    public int maxDepthOfNAry(TreeNode root){
//        if(root==null) return 0;
//        int height = 1;
//        for(TreeNode node : root.children)
//            height = Math.max(height, 1+maxDepthOfNAry(node));
//        return height;
//    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2){
        if(root1==null && root2==null) return null;
        if(root1==null) return root2;
        if(root2==null) return root1;
        TreeNode root = new TreeNode(root1.val+root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }

    public int findTilt(TreeNode root){
//        if(root == null) return 0;
//        if(root.left==null && root.right==null) return root.val;
//        int[] val = new int[]{0,0};
//        int[] res = findTilt(root,val);
//        return res[0]+val[0]+val[1];
        res = 0;
        postOrder(root);
        return res;
    }

    private int[] findTilt(TreeNode root, int[] val){
        int[] res = new int[2];
        if(root == null) return res;
        if(root.left == null && root.right == null){
            res[0] = 0;
            res[1] = root.val;
            return res;
        }
        int[] left = findTilt(root.left, val);
        int[] right = findTilt(root.right,val);
        res[0] = Math.abs(left[1]-right[1]);
        res[1] = left[1]+right[1]+root.val;
        val[0] += left[0];
        val[1] += right[0];
        return res;
    }

    private int postOrder(TreeNode root){
        if(root == null) return 0;
        int left = postOrder(root.left);
        int right = postOrder(root.right);
        res += Math.abs(left-right);
        return root.val+left+right;
    }

    public List<Double> averageOfLevels(TreeNode root){
        Map<Integer, List<Double>> map= new HashMap<>();
        averageOfLevels(root,0, map);
        List<Double> list = new ArrayList<>();
        for(Map.Entry<Integer, List<Double>> entry : map.entrySet())
            list.add((entry.getValue().getLast()/(double)(entry.getValue().size())));

        return list;
    }

    private void averageOfLevels(TreeNode root, int level, Map<Integer, List<Double>> map){
        if(root==null) return;
        if(map.containsKey(level)) {
            double val = map.get(level).getLast();
            map.get(level).add(root.val+val);
        }
        else {
            List<Double> list = new ArrayList<>();
            list.add((double)root.val);
            map.put(level, list);
        }
        averageOfLevels(root.left, level+1, map);
        averageOfLevels(root.right, level+1, map);
    }

    public boolean findTarget(TreeNode root, int k){
        return run(root, root, k);
    }

    public boolean run(TreeNode root, TreeNode currentNode, int k) {
        if (currentNode == null) return false;
        if (find(root, k-currentNode.val, currentNode)) return true;

        return run(root, currentNode.left, k) || run(root, currentNode.right, k);
    }

    public boolean find(TreeNode root, int val, TreeNode current) {
        if (root == null) return false;
        if (val > root.val) return find(root.right, val, current);
        else if (val < root.val) return find(root.left, val, current);
        else if (val == root.val) return root != current;

        return false;
    }

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        if (res < 0) res = root.val;
        return root.val == res && isUnivalTree(root.left)  && isUnivalTree(root.right);
    }

    public boolean isCousins(TreeNode root, int x, int y){
        if(root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            boolean isX = false, isY = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val == x) isX = true;
                if (node.val == y) isY = true;
                if (node.left != null && node.right != null) {
                    if (node.left.val == x && node.right.val == y)
                        return false;
                    else if (node.right.val == x && node.left.val == y)
                        return false;
                }
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            if(isX && isY) return true;
        }
        return false;
    }

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target){
        if(original == null) return original;
        if(original == target)
            return cloned;
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if(left!=null)
            return left;
        else
            return getTargetCopy(original.right, cloned.right, target);
    }

    public boolean validPath(int n, int[][] edges, int source, int destination){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        priorityQueue.offer(new int[] {0, source});

        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int currentDistance = current[0];
            int currentNode = current[1];

            if (currentNode == destination) {
                return true;
            }

            if (currentDistance > distances[currentNode]) {
                continue;
            }

            for (int neighbor : graph.getOrDefault(currentNode, new ArrayList<>())) {
                int distance = currentDistance + 1; // Assuming unweighted graph
                if (distance < distances[neighbor]) {
                    distances[neighbor] = distance;
                    priorityQueue.offer(new int[] {distance, neighbor});
                }
            }
        }

        return false;
    }

    public int findJudge(int n, int[][] trust){
        int[] count = new int[n+1];
        for (int[] t : trust){
            count[t[0]]--;
            count[t[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if(count[i] == n-1) return i;
        }
        return -1;
    }

    public int findCenter(int[][] edges){
        if(edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) {
            return edges[0][0];
        } else {
            return edges[0][1];
        }
    }

    public int[] countOfPairs(int n, int x, int y){
        // Initialize an array to store the result (result):
        int[] result = new int[n];

        // Iterate over all pairs of houses:
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // Calculate the minimum distance using a different approaches
                int val = Math.min(
                        Math.abs(i - j),  // Direct distance between houses i and j, i->j
                        Math.min(
                                Math.abs(i - x) + 1 + Math.abs(y - j),  // Distance through x and y,  i->x->y->j
                                Math.abs(i - y) + 1 + Math.abs(x - j)  // Distance through y and x, i->y->x->j
                        )
                );
                // Update the result array based on the minimum distance
                result[val - 1] += 2; // from i to j and j to i
            }
        }
        return result;
    }

    private boolean helper(int i) {
        seen[i] = true;
        nodes++;
        var edges = 0;
        var ret = true;

        for (var neighbor : adj[i]) {
            edges++;

            if (!seen[neighbor])
                ret &= helper(neighbor);
        }
        if (cnt == -1) cnt = edges;
        return cnt == edges && ret;
    }

    public int countCompleteComponents(int n, int[][] edges) {
        adj = new ArrayList[n];

        for (var i=0; i<n; i++)
            adj[i] = new ArrayList<>();

        for (var e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        seen = new boolean[n];
        var ans = 0;

        for (var i=0; i<n; i++) {
            if (!seen[i]) {
                cnt = -1;
                nodes = 0;

                if (helper(i) && cnt == nodes-1) ans++;
            }
        }
        return ans;
    }

    public int minScore(int n, int[][] roads){
        int l = roads.length, min = Integer.MAX_VALUE;
        List<List<Pair<Integer,Integer>>> pair = new ArrayList<>();
        for (int i = 0; i < n+1; i++) {
            pair.add(new ArrayList<Pair<Integer,Integer>>());
        }
        for(int[] road : roads){
            pair.get(road[0]).add(new Pair<>(road[1],road[2]));
            pair.get(road[1]).add(new Pair<>(road[0],road[2]));
        }
        int[] vis = new int[n+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        vis[1] = 1;
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(Pair<Integer,Integer> p : pair.get(node)){
                int v = p.getKey();
                int dis = p.getValue();
                min = Math.min(min, dis);
                if(vis[v]==0){
                    vis[v]=1;
                    queue.add(v);
                }
            }
        }
        return min;
    }

    public long minimumFuelCost(int[][] roads, int seats) {
        List<List<Integer>> graph = new ArrayList(); cnt = seats; res = 0;
        for (int i = 0; i < roads.length + 1; i++) graph.add(new ArrayList());
        for (int[] r: roads) {
            graph.get(r[0]).add(r[1]);
            graph.get(r[1]).add(r[0]);
        }
        dfs(0, 0, graph);
        return res;
    }
    private int dfs(int i, int prev, List<List<Integer>> graph) {
        int people = 1;
        for (int x: graph.get(i)) {
            if (x == prev) continue;
            people += dfs(x, i, graph);
        }
        if (i != 0) res += (people + cnt - 1) / cnt;
        return people;
    }

    public int mostProfitablePath(int[][] edges, int bob, int[] amount){
        int n = amount.length;

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) graph.put(i, new HashSet<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(v).add(u);
            graph.get(u).add(v);
        }

        // 1. find path
        dfs(bob, 0, graph, new ArrayList<Integer>(){{add(bob); }}, new HashSet<Integer>(){{add(bob); }});

        // 2. modify tree
        for (int i = 0; i < b2a.size() / 2; i++) {
            amount[b2a.get(i)] = 0;
        }
        if (b2a.size() % 2 != 0) {
            int m = b2a.get(b2a.size() / 2);
            amount[m] /= 2;
        }

        // 3. get result
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        maxPathSum(0, graph, amount, visited, amount[0]);
        return maxSum;
    }

    private boolean dfs(int root, int target,  Map<Integer, Set<Integer>> graph, List<Integer> currPath, Set<Integer> visited) {
        if (root == target) {
            b2a = new ArrayList<>(currPath);
            return true;
        }

        for (int neighbor : graph.get(root)) {
            if (visited.contains(neighbor)) continue;
            visited.add(neighbor);
            currPath.add(neighbor);

            if (dfs(neighbor, target, graph, currPath, visited)) return true;

            currPath.remove(currPath.size() - 1);
            visited.remove(neighbor);
        }
        return false;
    }

    private void maxPathSum(int root, Map<Integer, Set<Integer>> graph, int[] amount, Set<Integer> visited, int currSum) {
        int cnt = 0;
        for (int child : graph.get(root)) {
            if (visited.contains(child)) continue;

            visited.add(child);
            maxPathSum(child, graph, amount, visited, currSum + amount[child]);
            visited.remove(child);
            cnt++;

        }
        // leafNode
        if (cnt == 0) maxSum = Math.max(maxSum, currSum);
        return;
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted){
        List<List<Integer>> graph = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        res = 1;
        for (int i = 0; i < restricted.length; i++) {
            set.add(restricted[i]);
        }
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        reachableNodes(graph, 0, set, visited);
        return res;
    }

    private void reachableNodes(List<List<Integer>> graph, int i, Set<Integer> restricted, boolean[] visited){
        for(int val : graph.get(i)){
            if(!restricted.contains(val) && !visited[val]){
                res++;
                visited[val] = true;
                reachableNodes(graph, val, restricted, visited);
            }
        }
    }

    public long countPairs(int n, int[][] edges){
        createGraph(n, edges);
        boolean[] visited = new boolean[n];
        int numVisitedNodes = 0;
        long numUnreachablePairsOfNodes = 0;

        for (int node = 0; node < n; ++node) {
            if (!visited[node]) {
                int numNodesInCurrentGroup = depthFirstSearch_countConnectedNodesInCurrentGroup(node, visited);
                numUnreachablePairsOfNodes += (long) numNodesInCurrentGroup * numVisitedNodes;
                numVisitedNodes += numNodesInCurrentGroup;
            }
        }
        return numUnreachablePairsOfNodes;
    }

    private int depthFirstSearch_countConnectedNodesInCurrentGroup(int node, boolean[] visited) {
        visited[node] = true;
        int numConnectedNodes = 1;

        for (int neighbor : adj[node]) {
            if (!visited[neighbor]) {
                numConnectedNodes += depthFirstSearch_countConnectedNodesInCurrentGroup(neighbor, visited);
            }
        }
        return numConnectedNodes;
    }

    private void createGraph(int n, int[][] edges) {
        adj = new List[n];
        for (int node = 0; node < n; ++node) {
            adj[node] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; ++i) {
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }
    }
}
