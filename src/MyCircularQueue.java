import java.util.*;
import java.util.Stack;

public class MyCircularQueue {
    int[] queue;
    int front, rear, size;
    int count = 0;
    public MyCircularQueue(int k){
        queue = new int[k];
        front = 0;
        rear = 0;
        size = 0;
    }

    public int front(){
        return queue[front];
    }

    public int rear(){
        return queue[rear-1];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == queue.length;
    }

    public boolean enQueue(int value){
        boolean enqueue = false;
        if(isFull()) return enqueue;
        rear = rear % queue.length;
        queue[rear] = value;
        if(isEmpty()) front = rear;
        rear++;
        size++;
        return true;
    }

    public boolean deQueue(){
        boolean dequeue = false;
        if(isEmpty()) return dequeue;
        front = front % queue.length;
        queue[front] = 0;
        size--;
        if(size > 0)
            front++;
        else
            rear = 0;
        return true;
    }

    public int numIslands(char[][] grid){
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int numOfIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1'){
                    numOfIslands++;
                    dfs(grid, i, j);
                }
            }
        }
        return  numOfIslands;
    }

    private void dfs(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1')
            return;

        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i, j+1);
        dfs(grid, i+1,j);
        dfs(grid, i, j-1);
    }

    public int openLock(String[] deadends, String target) {
        Set<String> deadendSet = new HashSet<>(Arrays.asList(deadends));
        if (deadendSet.contains("0000")) {
            return -1;
        }

        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>("0000", 0));
        Set<String> visited = new HashSet<>();
        visited.add("0000");

        while (!queue.isEmpty()) {
            Pair<String, Integer> current = queue.poll();
            String currentCombination = current.getKey();
            int moves = current.getValue();

            if (currentCombination.equals(target)) {
                return moves;
            }

            for (int i = 0; i < 4; i++) {
                for (int delta : new int[]{-1, 1}) {
                    int newDigit = (currentCombination.charAt(i) - '0' + delta + 10) % 10;
                    String newCombination = currentCombination.substring(0, i) +
                            newDigit +
                            currentCombination.substring(i + 1);

                    if (!visited.contains(newCombination) && !deadendSet.contains(newCombination)) {
                        visited.add(newCombination);
                        queue.offer(new Pair<>(newCombination, moves + 1));
                    }
                }
            }
        }

        return -1; // Target is not reachable
    }

    public int perfectSquares(int n){
        Queue<Integer> queue = new ArrayDeque<>();
        HashSet<Integer> set = new HashSet<>();
        queue.add(n);
        int level = 0;
        while(!queue.isEmpty()){
            level++;
            for (int i = queue.size(); i > 0 ; i--) {
                int head = queue.poll();
                for (int j = 1; j * j <= head; j++) {
                    int remainder = head - j * j;
                    if(remainder == 0)
                        return level;
                    if(set.add(remainder))
                        queue.add(remainder);
                }
            }
        }
        return n;
    }

    public boolean isValid(String s){
        if(s == "") return true;
        boolean isValid = false;
        Stack stack = new Stack();
        stack.push(s.charAt(0));
        int i = 1;
        int l = s.length();
        while(i < s.length()){
            if(s.charAt(i) == ')') {
                if(stack.size() == 0) return false;
                char ch = (char) stack.pop();
                if (ch == '(') isValid = true;
                else return false;
            }
            else if(s.charAt(i) == ']') {
                if(stack.size() == 0) return false;
                char ch = (char) stack.pop();
                if (ch == '[') isValid = true;
                else return false;
            }
            else if(s.charAt(i) == '}') {
                if(stack.size() == 0) return false;
                char ch = (char) stack.pop();
                if (ch == '{') isValid = true;
                else return false;
            }
            else
                stack.push(s.charAt(i));

            i++;
        }
        return  isValid && stack.size() == 0;
    }

    public int[] dailyTemperatures(int[] temp){
        int[] res = new int[temp.length];
        Stack stack = new Stack();
        for (int i = 0; i < temp.length; i++) {
            while(!stack.isEmpty() && temp[(int) stack.peek()] < temp[i]){
                int idx = (int) stack.pop();
                res[idx] = i - idx;
            }
            stack.push(i);
        }
        return  res;
    }

    public int evalRPN(String[] tokens){
        int result = -201;
        Stack stack = new Stack();
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")){
                if(stack.isEmpty()) return -201;
                int sec = (int)stack.pop();
                if(stack.isEmpty()) return  -201;
                int first = (int)stack.pop();
                if(tokens[i].equals("+")) result = first + sec;
                else if(tokens[i].equals("-")) result = first - sec;
                else if(tokens[i].equals("*")) result = first * sec;
                else if(tokens[i].equals("/")) result = first / sec;
                stack.push(result);
            }
            else
                stack.push(Integer.parseInt(tokens[i]));
        }
        return (int) stack.pop();
    }

    public int findTargetSumWays(int[] nums, int target){
        calculate(nums, 0,0,target);
        return count;
    }

    private void calculate(int[] nums, int i, int sum, int target){
        if(i==nums.length){
            if(sum == target){
                count++;
            }
        }
        else{
            calculate(nums, i+1,sum+nums[i],target);
            calculate(nums, i+1,sum-nums[i],target);
        }
    }

    public List<Integer> inOrderTraversal(BinaryNode root){
        Stack stack = new Stack();
        List<Integer> list = new ArrayList<>();
        BinaryNode temp = root;
        while(temp != null || !stack.isEmpty()){
            while(temp != null){
                stack.push(temp);
                temp = temp.left;
            }
            temp = (BinaryNode) stack.pop();
            list.add(Integer.parseInt(temp.value));
            temp = temp.right;
        }
        return list;
    }

    public String decodeString(String s){
        Stack stack = new Stack(); int curNum = 0; String curString = "";
        for (char c : s.toCharArray()) {
            if (c == '['){
                stack.push(curString);
                stack.push(curNum);
                curString = "";
                curNum = 0;
            }
            else if (c ==']') {
                int num = Integer.parseInt(String.valueOf(stack.pop()));
                String prevString = (String)stack.pop();
                for (int i = 0; i < num; i++) {
                    prevString += curString;
                }
                curString = prevString;
            }
            else if (Character.isDigit(c))
                    curNum = curNum * 10+Integer.parseInt(String.valueOf(c));
            else
                curString += c;
        }
        return curString;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color){
        if(image[sr][sc] == color) return image;

        dfs(image, sr, sc, image[sr][sc], color);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int srcColor, int color){
        if(sr < 0 || sc < 0 || sr >= image.length || sc >= image[sr].length || image[sr][sc]!=srcColor) return;
        if(image[sr][sc] == srcColor)
            image[sr][sc] = color;

        dfs(image, sr-1,sc,srcColor,color);
        dfs(image, sr,sc-1,srcColor,color);
        dfs(image, sr+1,sc,srcColor,color);
        dfs(image, sr,sc+1,srcColor,color);
    }

    public int[][] updateMatrix(int[][] mat) {
        int[] DIR = new int[]{0, 1, 0, -1, 0};
        int m = mat.length, n = mat[0].length; // The distance of cells is up to (M+N)
        Queue<int[]> q = new ArrayDeque<>();
        for (int r = 0; r < m; ++r)
            for (int c = 0; c < n; ++c)
                if (mat[r][c] == 0) q.offer(new int[]{r, c});
                else mat[r][c] = -1; // Marked as not processed yet!

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            for (int i = 0; i < 4; ++i) {
                int nr = r + DIR[i], nc = c + DIR[i + 1];
                if (nr < 0 || nr == m || nc < 0 || nc == n || mat[nr][nc] != -1) continue;
                mat[nr][nc] = mat[r][c] + 1;
                q.offer(new int[]{nr, nc});
            }
        }
        return mat;
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms){
        List<Integer> keys = new ArrayList<>();
        boolean[] visited = new boolean[rooms.size()];
        dfs(rooms, visited, 0);
        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]) return false;
        }
        return true;
    }

    private void dfs(List<List<Integer>> rooms, boolean[] visited, int index){
        visited[index] = true;
        for(int key : rooms.get(index)){
            if(!visited[key])
                dfs(rooms, visited, key);
        }
    }
}
