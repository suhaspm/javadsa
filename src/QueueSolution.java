import java.util.*;
import java.util.Stack;

public class QueueSolution {
    Queue<Integer> queue;
    int value, k, cnt;

    public int firstUniqChar(String s) {
        int ans = Integer.MAX_VALUE;
        for (char c = 'a';  c <= 'z'; c++) {
            int i = s.indexOf(c);
            if(i!=-1&&i==s.lastIndexOf(c))
                ans = Math.min(ans,i);
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    public int ping(int t){
        queue = new LinkedList<>();
        queue.offer(t);
        while(queue.peek()<t-3000) queue.poll();
        return queue.size();
    }

    public int countStudents(int[] students, int[] sandwiches){
        int ones = 0, zeros = 0;
        for(int student : students){
            if(student == 0)zeros++;
            else  ones++;
        }
        for(int sandwich : sandwiches){
            if(sandwich == 0){
                if(zeros == 0)
                    return ones;
                zeros--;
            }
            else{
                if(ones == 0)
                    return zeros;
                ones--;
            }
        }
        return 0;
    }

    public int timeToBuy(int[] tickets, int k){
        int time = 0, n = tickets.length;
        for (int i = 0; i < n; i++) {
            if(i<=k)
                time+=Math.min(tickets[i],tickets[k]);
            else
                time+=Math.min(tickets[i],tickets[k]-1);
        }
        return time;
    }

    public String longestNiceSubstring(String s){
        if (s.length() < 2) return "";
        char[] arr = s.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c: arr) set.add(c);
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (set.contains(Character.toUpperCase(c)) && set.contains(Character.toLowerCase(c))) continue;
            String sub1 = longestNiceSubstring(s.substring(0, i));
            String sub2 = longestNiceSubstring(s.substring(i+1));
            return sub1.length() >= sub2.length() ? sub1 : sub2;
        }
        return s;
    }

    public List<String> readBinaryWatch(int turnedOn){
        List<String> list = new ArrayList<>();
        int[] arr = new int[]{1,2,4,8,1,2,4,8,16,32};
        backtrack(arr,0,0,0,turnedOn,list);
        return list;
    }

    private void backtrack(int[] arr, int position, int hours, int minutes, int num, List<String> list) {
        if(num==0){
            if(hours<=11 && minutes <=59){
                StringBuilder sb = new StringBuilder();
                sb.append(hours).append(":").append(minutes<=9?"0"+minutes:minutes);
                list.add(sb.toString());
            }
            return;
        }
        for (int i = position; i < arr.length; i++) {
            if(isHour(i)) hours +=arr[i];
            else minutes+=arr[i];
            backtrack(arr, i+1, hours, minutes, num-1, list);
            if(isHour(i)) hours-=arr[i];
            else minutes -= arr[i];
        }
    }

    private boolean isHour(int position){
        return position >=0 && position<=3;
    }

    public int subsetXORSum(int[] nums){
        return subsetXORSum(nums, 0, 0);
    }

    private int subsetXORSum(int[] nums, int index, int currentXor){
        if(index == nums.length) return currentXor;
        int withElem = subsetXORSum(nums, index+1, currentXor ^ nums[index]);
        int withoutElem = subsetXORSum(nums, index+1, currentXor);
        return withoutElem+withElem;
    }


    public int minimumCoins(int[] A) {
        int n = A.length, dp[] = new int[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!q.isEmpty() && (q.getFirst() + 1) * 2 < i + 1)
                q.removeFirst();
            while (!q.isEmpty() && dp[q.getLast()] + A[q.getLast()] >= dp[i] + A[i])
                q.removeLast();
            q.addLast(i);
            dp[i + 1] = dp[q.getFirst()] + A[q.getFirst()];
        }
        return dp[n];
    }

    public void Datastream(int value, int k){
        this.value = value;
        this.k = k;
    }

    public boolean consec(int num){
        if(num != value){
            cnt = 0;
            return false;
        }
        else{
            cnt++;
            if(cnt >= k)
                return true;
        }
        return false;
    }

    public int[] occurencesOfElement(int[] nums, int[] queries, int x){
        int n = nums.length;
        List<Integer> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(nums[i] == x) {
                map.add(i);
            }
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int val = queries[i];
            res[i] = val > map.size() ? -1 : map.get(val-1);
        }
        return res;
    }

    public long sumDigitDifferences(int[] A){
        int n = A.length, m = String.valueOf(A[0]).length();
        int[][] count = new int[m][10];
        long res = 1L * n * (n - 1) / 2 * m;
        for (int a : A) {
            for (int i = 0; i < m && a > 0; i++) {
                res -= count[i][a % 10]++;
                a /= 10;
            }
        }
        return res;
    }
}
