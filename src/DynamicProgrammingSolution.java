import java.util.*;

public class DynamicProgrammingSolution {
    public int climbStairs(int n){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (int i = 2; i < n; i++) {
            list.add(list.get(i-1)+list.get(i-2));
        }
        return list.getLast();
    }

    public boolean isSubsequence(String s, String t){
        int i = 0, j = 0;
        if(s.length() > t.length()) return false;
        else if(s.length()==0) return true;
        StringBuilder sb = new StringBuilder();
        while(i < s.length() && j<t.length()){
            if(s.charAt(i) == t.charAt(j)){
                sb.append(s.charAt(i));
                i++;
            }
            j++;
        }
        return s.equals(sb.toString());
    }

    public int minCostClimbingStairs(int[] cost){
        int sum = 0, n = cost.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if(i<2) dp[i] = cost[i];
            else dp[i] = cost[i] + Math.min(dp[i-1],dp[i-2]);
        }
        return Math.min(dp[n-1],dp[n-2]);
    }

    public int tribonacci(int n){
        if(n == 0) return 0;
        else if(n==1||n==2) return 1;
        int[] dp = new int[n+1];
        dp[0] = 0;dp[1] = 1;dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
        }
        return dp[n];
    }

    public int maxRepeating(String sequence, String word){
        int repeating = 0;
        StringBuilder sb = new StringBuilder(word);
        while (sequence.contains(sb)) {
            repeating++;
            sb.append(word);
        }
        return repeating;
    }

    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups){
        int n = words.length;
        List<String> list = new ArrayList<>();
        int t = groups[0];
        list.add(words[0]);
        for (int i = 1; i < n; i++) {
            if(groups[i]!=t){
                t = groups[i];
                list.add(words[i]);
            }
        }
        return list;
    }

    public int findContentChildren(int[] g, int[] s){
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        for(int j=0;i<g.length && j<s.length;j++) {
            if(g[i]<=s[j]) i++;
        }
        return i;
    }

    public int arrayPairSum(int[] nums){
        Arrays.sort(nums);
        int n = nums.length,sum=0;
        for (int i = 0; i < n;i+=2) {
            sum+=nums[i];
        }
        return sum;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n){
        if(n==0) return true;
        int s = flowerbed.length;
        for (int i = 0; i < s; i++) {
            if(flowerbed[i] == 0 && (i==0 || flowerbed[i-1] == 0) && (i == flowerbed.length-1 || flowerbed[i+1]==0)) {
                flowerbed[i] = 1;
                n--;
                if(n==0) return true;
            }
        }
        return false;
    }

    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
            i++;
            j--;
        }

        return true;
    }

    /* Check is s[i...j] is palindrome. */
    private boolean isPalindrome(String s, int i, int j) {

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public boolean lemonadeChange(int[] bills){
        int five = 0, ten = 0;
        for(int i : bills){
            if(i == 5) five++;
            else if(i == 10){ five--; ten++;}
            else if(ten > 0){ ten--; five--;}
            else five-=3;
            if(five < 0) return false;
        }
        return true;
    }

    public int[] diStringMatch(String s){
        int n = s.length();
        int[] res = new int[n+1];
        int low = 0, high = n;
        for (int i = 0; i < n; i++) {
            if(s.charAt(i) == 'I')
                res[i] = low++;
            else
                res[i] = high--;
        }
        res[n] = high;
        return res;
    }

    public int largestPerimeter(int[] nums){
        Arrays.sort(nums);
        for (int i = nums.length-1;i > 1 ; i--) {
            if(nums[i] < nums[i-1]+nums[i-2])
                return nums[i] + nums[i-1]+ nums[i-2];
        }
        return 0;
    }

    public int largestSumAfterKNegations(int[] nums, int K) {
        Arrays.sort(nums);
        Arrays.sort(nums);
        int sum = 0;
        if (nums[0] >= 0 && K % 2 != 0) {
            nums[0] = - nums[0];
        } else if (nums[0] < 0 ) { //&& K % 2 == 0) {
            int change = 0;
            while (K > 0 && change < nums.length-1 && nums[change] < 0 && change < nums.length) {
                nums[change] = - nums[change++];
                K--;
            }
            if (K % 2 != 0) {
                int index = nums[change] < nums[change - 1] ? change : change - 1;
                nums[index] = - nums[index];
            }
        }
        for (int val : nums) sum += val;
        return sum;
    }

    public boolean canThreePartsEqualSum(int[] A){
        int sum=0;
        for(int i: A)
            sum=sum+i;
        if(sum%3!=0)
            return false;
        int each=sum/3,temp=0,found=0;
        for (int i=0; i<A.length; i++) {
            temp=temp+A[i];
            if(temp==each){
                temp = 0;
                found++;
            }
        }
        if(found>=3)
            return true;
        return false;
    }

    public int minCostToMoveChips(int[] position) {
        int even = 0;
        for(int i : position)
            if(i%2==0)
                even++;

        return Math.min(even, position.length-even);
    }

    public int[] numberGame(int[] nums){
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i+=2) {
            int temp = nums[i];
            nums[i] = nums[i+1];
            nums[i+1] = temp;
        }
        return nums;
    }

    public long pickGifts(int[] nums, int k){
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->(b-a));    // MAX-HEAP

        for(int i = 0; i<n; i++){
            pq.add(nums[i]);
        }

        long sum = 0;
        while(k-->0){
            pq.add((int)Math.sqrt(pq.remove()));
        }

        while(pq.size()>0){
            sum+=pq.remove();
        }

        return sum;
    }

    public int deleteGreatestValue(int[][] grid) {
        for(int i=0; i<grid.length; i++)    Arrays.sort(grid[i]);
        int res = 0;
        for(int i=0; i<grid[0].length; i++){
            int max = 0;
            for(int j=0; j<grid.length; j++)
                max = Math.max(max, grid[j][i]);
            res += max;
        }
        return res;
    }

    public String[] findRelativeRanks(int[] score) {

        int n = score.length;

        int maxScore = 0;
        for (int i = 0 ; i < n ; i++) {
            maxScore = Math.max(maxScore, score[i]);
        }

        int[]  score2Index = new int[maxScore+1];

        for (int i = 0 ; i < n ; i++) {
            score2Index[score[i]] = i+1;
        }

        String[] ans = new String[n];

        int place = 1;

        for (int i = maxScore ; i >= 0 ; i--) {

            if (score2Index[i] == 0) continue;

            int actualIndex = score2Index[i] - 1;
            if (place == 1) {
                ans[actualIndex] = "Gold Medal";
            } else if (place == 2) {
                ans[actualIndex] = "Silver Medal";
            } else if (place == 3) {
                ans[actualIndex] = "Bronze Medal";
            } else {
                ans[actualIndex] = String.valueOf(place);
            }
            place++;
        }

        return ans;
    }

    public int[] maxSubsequence(int[] nums, int k){
        int n = nums.length;
        int[][] indexAndVal = new int[n][2];
        for (int i = 0; i < n; ++i) {
            indexAndVal[i] = new int[]{i, nums[i]};
        }
        // Reversely sort by value.
        Arrays.sort(indexAndVal, Comparator.comparingInt(a -> -a[1]));
        int[][] maxK = Arrays.copyOf(indexAndVal, k);
        // Sort by index.
        Arrays.sort(maxK, Comparator.comparingInt(a -> a[0]));
        int[] seq = new int[k];
        for (int i = 0; i < k; ++i) {
            seq[i] = maxK[i][1];
        }
        return seq;
    }

    public int maxProduct(int[] nums){
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num >= max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }

        return (max1 - 1) * (max2 - 1);
    }

    public int[] weakestRows(int[][] mat, int k){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(a[0]!=b[0]?b[0]-a[0]:b[1]-a[1]));
        int[] res = new int[k];
        for (int i = 0; i < mat.length; i++) {
            pq.offer(new int[]{numOnes(mat[i]),i});
            if(pq.size()>k)
                pq.poll();
        }
        while(k>0)
            res[--k] = pq.poll()[1];
        return  res;
    }

    private int numOnes(int[] row){
        int l = 0, h = row.length-1;
        while(l<h){
            int mid = l+(h-l)/2;
            if(row[mid] == 1)
                l = mid+1;
            else
                h = mid-1;
        }
        return l;
    }

    public int lastStoneWeight(int[] stones){
        int n = stones.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for (int i = 0; i < n; i++) {
            pq.offer(stones[i]);
        }while (pq.size() > 1) {
            int s1 = pq.poll(), s2 = pq.poll();
            if (s1 > s2)
                pq.offer(s1 - s2);
        }
        return pq.isEmpty() ? 0 : pq.peek();
    }
}
