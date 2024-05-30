import java.util.*;
import java.util.Stack;

public class StackSolution {
    private Stack stack;
    private Stack tempStack = null;
    private int size;
    private Node head;
    private Map<Integer, Integer> list;
    private Map<Integer, Stack<Integer>> map;
    int maxfreq;

    public StackSolution(){
        stack = new Stack<>();
        size = 0;
    }

    public int pop(){
        size--;
        tempStack = new Stack();
        for (int i = 0; i < stack.size(); i++) {
            tempStack.push(stack.pop());
        }
        int pop = (int) tempStack.pop();
        for (int i = 0; i < tempStack.size(); i++) {
            stack.push(tempStack.pop());
        }
        return pop;
    }

    public void push(int x){
        stack.push(x);
        size++;
    }

    public int peek(){
        tempStack = new Stack();
        for (int i = 0; i < stack.size(); i++) {
            tempStack.push(stack.pop());
        }
        int pop = (int) tempStack.peek();
        for (int i = 0; i < tempStack.size(); i++) {
            stack.push(tempStack.pop());
        }
        return pop;
    }

    public boolean empty(){
        if(size == 0) return true;
        else return false;
    }

    public boolean backspaceCompare(String s, String t){
        Stack stack1 = new Stack();
        Stack stack2 = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '#' && !stack1.isEmpty()) stack1.pop();
            else if(ch != '#') stack1.push(ch);
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if(ch == '#' && !stack2.isEmpty()) stack2.pop();
            else if(ch != '#') stack2.push(ch);
        }
        if(stack1.size() != stack2.size()) return false;
        while(!stack1.isEmpty()) {
            if(stack1.pop() != stack2.pop()) return false;
        }
        return true;
    }

    public void MinStack(){

    }

    public void pushS(int val){
        if(head == null)
            head = new Node(val, val, null);
        else
            head = new Node(val, Math.min(val, head.min), head);
    }

    public void popS(){
        head = head.next;
    }

    public int top(){
        return head.val;
    }

    public int getMin(){
        return head.min;
    }

    public String decodeString(String s){
        Stack<StringBuilder> stStr = new Stack<>();
        Stack<Integer> stInt = new Stack<>();
        StringBuilder pattern = new StringBuilder();

        int num = 0;

        for ( Character ch : s.toCharArray()) {

            if (Character.isLetter(ch)) {
                pattern.append(ch);
            } else if ( Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if ( ch == '[') {
                if ( num != 0)
                    stInt.push(num);
                stStr.push(pattern);
                pattern = new StringBuilder();
                num = 0;

            } else if ( ch == ']') {
                int count = stInt.pop();
                StringBuilder temp = stStr.isEmpty() ? new StringBuilder() : stStr.pop();
                for (int k=0; k<count; ++k) {
                    temp.append(pattern);
                }
                num = 0;
                pattern = temp;
            }

        }

        return pattern.toString();
    }

    public int[] asteroidCollision(int[] asteroids){
        Stack stack = new Stack();
        for (int i = 0; i < asteroids.length; i++) {
            if(asteroids[i] > 0)
                stack.push(asteroids[i]);
            else{
                while (!stack.isEmpty() && (int)stack.peek() > 0 && (int)stack.peek() < -asteroids[i])
                    stack.pop();
            }
            if(stack.isEmpty() || (int)stack.peek() < 0)
                stack.push(asteroids[i]);
            if((int)stack.peek() == -asteroids[i])
                stack.pop();
//            boolean isAdded = false;
//            if(!stack.isEmpty()){
//                while(!stack.isEmpty() && (int)stack.peek() > 0 && asteroids[i] < 0){
//                    int val = (int)stack.pop();
//                    if(asteroids[i] + val < 0 && (stack.isEmpty() || (int)stack.peek() < 0)) {
//                        stack.push(Math.min(val, asteroids[i]));
//                        isAdded = true;
//                            break;
//                    }
//                    else if(asteroids[i] + val > 0 && (stack.isEmpty () || (int)stack.peek() > 0)) {
//                        stack.push(Math.max(val, asteroids[i]));
//                        isAdded = true;
//                            break;
//                    }
//                    else if(asteroids[i] + val == 0)
//                        isAdded = true;
//                }
//            }
//            if(!isAdded)
//                stack.push(asteroids[i]);
        }
        int i = stack.size();
        int[] res = new int[i];
        while(!stack.isEmpty()) {
            res[--i] = (int)stack.pop();
        }
        return res;
    }

    public int calculate(String s){
        int len;
        if(s==null || (len = s.length())==0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for(int i=0;i<len;i++){
            if(Character.isDigit(s.charAt(i))){
                num = num*10+s.charAt(i)-'0';
            }
            if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
                if(sign=='-'){
                    stack.push(-num);
                }
                if(sign=='+'){
                    stack.push(num);
                }
                if(sign=='*'){
                    stack.push(stack.pop()*num);
                }
                if(sign=='/'){
                    stack.push(stack.pop()/num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }

        int re = 0;
        for(int i:stack){
            re += i;
        }
        return re;
    }

    public int trap(int[] height){
        int n = height.length;
        int[] left = new int[n];
        left[0] = height[0];
        int[] right = new int[n];
        right[n-1] = height[n-1];
        int trapped = 0;
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i-1],height[i]);
        }
        for (int i = n-2; i >= 0 ; i--) {
            right[i] = Math.max(right[i+1], height[i]);
        }
        for (int i = 0; i < n; i++) {
            int minHeight = Math.min(left[i], right[i]);
            trapped+=minHeight-height[i];
        }
        return trapped;
    }

    public int calculateSum(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                number = 10 * number + (int)(c - '0');
            }else if(c == '+'){
                result += sign * number;
                number = 0;
                sign = 1;
            }else if(c == '-'){
                result += sign * number;
                number = 0;
                sign = -1;
            }else if(c == '('){
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            }else if(c == ')'){
                result += sign * number;
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis

            }
        }
        if(number != 0) result += sign * number;
        return result;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if(n == 0) return 0; // Base Condition
        int maxArea = 0;
        int left[] = new int[n]; //fill left boundary
        int right[] = new int[n]; // fill right boundary

        left[0] = -1;
        right[n - 1] = n;

        for(int i = 1; i < n; i++){
            int prev = i - 1; // previous for comparing the heights
            while(prev >= 0 && heights[prev] >= heights[i]){
                prev = left[prev]; // we have done this to minimise the jumps we make to the left
            }
            left[i] = prev;
        }
        // Similarly we do for right
        for(int i = n - 2; i >= 0; i--){
            int prev = i + 1;
            while(prev < n && heights[prev] >= heights[i]){
                prev = right[prev];
            }
            right[i] = prev;
        }
        // once we have these two arrays fill we need width & area
        for(int i = 0; i < n; i++){
            int width = right[i] - left[i] - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }
        return maxArea;
    }

    public void FreqStack(){
        list = new HashMap<>();
        map = new HashMap<>();
        maxfreq = 0;
    }

    public void pushF(int x){
        int f = list.getOrDefault(x, 0) + 1;
        list.put(x, f);
        maxfreq = Math.max(maxfreq, f);
        if (!map.containsKey(f)) map.put(f, new Stack<Integer>());
        map.get(f).add(x);
    }

    public int popF(){
        int x = map.get(maxfreq).pop();
        list.put(x, maxfreq - 1);
        if (map.get(maxfreq).size() == 0) maxfreq--;
        return x;
    }

    public int longestValidParentheses(String s){
        int count = 0;
        int n = s.length();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') st.push(i);
            else {
                if (!st.empty()) {
                    if (s.charAt(st.peek()) == '(') st.pop();
                    else st.push(i);
                }
                else st.push(i);
            }
        }
        if(st.isEmpty()) count = n;
        else{
            int a = n, b = 0;
            while(!st.isEmpty()){
                b = st.pop();
                count = Math.max(count, a-b-1);
                a = b;
            }
            count = Math.max(count, a);
        }

        return count;
    }
}
