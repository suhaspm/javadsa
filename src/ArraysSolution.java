import com.sun.source.doctree.IndexTree;

import java.util.*;
import java.util.Stack;

public class ArraysSolution {
    int[] prefixSum;
    List<String> ans;
    int cnt = 0;
    boolean found = false;
    public int findMaxConsecutiveOnes(int[] nums){
        int maxCount = 0;
        int count = 0;

        for (int num : nums) {
            if (num == 1) {
                // Increment count for consecutive 1s
                count++;
                // Update maxCount if current count is greater
                maxCount = Math.max(maxCount, count);
            } else {
                // Reset count if current number is 0
                count = 0;
            }
        }
        return maxCount;
    }

    public int findNumbers(int[] nums){
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if(String.valueOf(nums[i]).length()%2==0)
                cnt++;
        }
        return cnt;
    }

    public int[] sortedSquares(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i]*nums[i];
        }
        Sorting sort = new Sorting();
        sort.Quick(nums);
        return nums;
    }

    public void duplicateZeros(int[] arr){
        int temp = 0, idx = 0, zeros = 0;
        int[] tempArr = new int[arr.length];
        tempArr.clone();
        for (int i = 0; i < arr.length-zeros; i++) {
            if(arr[i] == 0){
                tempArr[idx+1] = 0;
                idx++;
                zeros++;
            }
            tempArr[idx] = arr[i];
            idx++;
        }
        arr = tempArr;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Index of last element in nums1
        int j = n - 1; // Index of last element in nums2
        int k = m + n - 1; // Index of last element in the merged array

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // If there are remaining elements in nums2, copy them to nums1
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    public int removeElement(int[] nums, int val){
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val){
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public int removeDuplicates(int[] nums){
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public boolean checkIfExist(int[] arr){
        Hashtable<Integer, Integer> table = new Hashtable<>();
        for (int i = 0; i < arr.length; i++) {
            table.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            if(table.get(arr[i]*2)!=null && i!=table.get(arr[i]*2))
                return true;
        }
        return false;
    }

    public boolean validMountainArray(int[] arr){
        int n = arr.length;
        if (n < 3) {
            return false; // Mountain array must have at least 3 elements
        }

        int i = 0;

        // Walk up the mountain
        while (i < n - 1 && arr[i] < arr[i + 1]) {
            i++;
        }

        // Peak can't be the first or last element
        if (i == 0 || i == n - 1) {
            return false;
        }

        // Walk down the mountain
        while (i < n - 1 && arr[i] > arr[i + 1]) {
            i++;
        }

        // Check if we reached the end of the array
        return i == n - 1;
    }

    public int[] replaceElements(int[] A){
        int mx = -1, n = A.length, a;
        for (int i = n - 1; i >= 0; --i) {
            a = A[i];
            A[i] = mx;
            mx = Math.max(mx, a);
        }
        return A;
    }

    public void moveZeros(int[] nums){
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public int[] sortArrayByParity(int[] nums){
        int oddSize = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]%2 != 0)
                oddSize++;
            else if(oddSize > 0){
                int t = nums[i];
                nums[i] = nums[i - oddSize];
                nums[i-oddSize] = t;
            }
        }
        return nums;
    }

    public int heightChecker(int[] heights){
        int val = 0;
        int[] expected = heights.clone();
        Arrays.sort(expected);
        for (int i = 0; i < heights.length; i++) {
            if(expected[i] != heights[i])
                val++;
        }
        return val;
    }

    public int thirdMax(int[] nums){
        Integer first = null, second = null, third = null;

        for (Integer num : nums) {
            if (num.equals(first) || num.equals(second) || num.equals(third)) {
                continue; // Skip if num is already one of the top three
            }

            if (first == null || num > first) {
                third = second;
                second = first;
                first = num;
            } else if (second == null || num > second) {
                third = second;
                second = num;
            } else if (third == null || num > third) {
                third = num;
            }
        }

        return third != null ? third : first;
    }

    public List<Integer> findDisappearedNumbers(int[] nums){
        List<Integer> list = new ArrayList<>();
        int idx = -1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] < 0){
                idx = nums[i]*-1-1;
            }else{
                idx = nums[i]-1;
            }
            if(nums[idx]>0){
                nums[idx] = -nums[idx];
            }
        }

        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                list.add(i+1);
            }
        }

        return list;
    }

    public int searchInsert(int[] nums, int target){
        int n = nums.length, l = 0, r = n-1;
        while(l<=r){
            int mid = (r+l)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < target)
                l = mid+1;
            else
                r = mid-1;
            if(nums[mid] < target && (mid == n-1 || nums[mid+1] > target))
                return mid+1;
        }
        return -1;
    }

    public int[] plusOne(int[] digits){
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }

    public List<List<Integer>> generate(int numRows){
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        output.add(temp);
        if(numRows>1){
            for (int i = 2; i <= numRows; i++) {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                for (int j = 1; j < i-1; j++) {
                    List<Integer> v = output.get(i-2);
                    list.add(v.get(j-1)+v.get(j));
                }
                list.add(1);
                output.add(list);
            }

        }
        return output;
    }

    public List<Integer> getRow(int numRows){
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        output.add(temp);
        if(numRows>0){
            for (int i = 2; i <= numRows+1; i++) {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                for (int j = 1; j < i-1; j++) {
                    List<Integer> v = output.get(i-2);
                    list.add(v.get(j-1)+v.get(j));
                }
                list.add(1);
                output.add(list);
            }

        }
        return output.get(numRows);
    }

    public boolean containsNearByDuplicate(int[] nums, int k){
        Map<Integer,Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(map.containsKey(nums[i])){
                int idx = map.get(nums[i]);
                if(Math.abs(idx-i)<=k)
                    return true;
                else
                    map.put(nums[i],i);
            }
            else
                map.put(nums[i],i);
        }
        return false;
    }

    public List<String> summaryRanges(int[] nums){
        List<String> list = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int start = nums[i];
            while(i+1<n && nums[i]+1==nums[i+1])
                i++;
            if(start!=nums[i])
                list.add(start+"->"+nums[i]);
            else
                list.add(""+start);
        }
        return list;
    }

    public void numArray(int[] nums){
        if(nums == null)
            return;
        prefixSum = new int[nums.length+1];
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
        }
    }

    public int sumRange(int left, int right){
        if (left < 0 || right >= prefixSum.length - 1) return -1;

        return prefixSum[right+1] - prefixSum[left];
    }

    public int[] intersection(int[] nums1, int[] nums2){
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        int i = 0;
        for (i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (i = 0; i < nums2.length; i++) {
            if(set.contains(nums2[i]))
                intersect.add(nums2[i]);
        }
        int[] res = new int[intersect.size()];
        i = 0;
        for(int num : intersect)
            res[i++] = num;
        return res;
    }

    public int[] intersection2(int[] nums1, int[] nums2){
        Map<Integer,Integer> set = new HashMap();
        List<Integer> intersect = new ArrayList<>();
        int i = 0;
        for (i = 0; i < nums1.length; i++) {
            set.put(nums1[i],set.getOrDefault(nums1[i],0)+1);
        }
        for (i = 0; i < nums2.length; i++) {
            if(set.containsKey(nums2[i]) && set.get(nums2[i]) > 0) {
                intersect.add(nums2[i]);
                set.put(nums2[i],set.get(nums2[i])-1);
            }
        }
        int[] res = new int[intersect.size()];
        i = 0;
        for(int num : intersect)
            res[i++] = num;
        return res;
    }

    public int maximumLength(int[] nums, int k) {
        int dp[][] = new int[k][k], max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < k; j++) {
                max = Math.max(max, dp[nums[i] % k][j] = dp[j][nums[i] % k] + 1);
            }
        }
        return max;
    }

    public int maximumLength(int[] nums){
        int dp[][] = new int[2][2], max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 2; j++) {
                max = Math.max(max, dp[nums[i] % 2][j] = dp[j][nums[i] % 2] + 1);
            }
        }
        return max;
    }

    public long maximumTotalCost(int[] nums) {
        int n = nums.length;
        long addResult = nums[0];
        long subResult = nums[0];
        for (int i = 1; i < n; i++) {
            long tempAdd = Math.max(addResult, subResult) + nums[i];
            long tempSub = addResult - nums[i];

            addResult = tempAdd;
            subResult = tempSub;
        }
        return Math.max(addResult, subResult);
    }

    public int minimumArea(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        int top = m;
        int bottom = 0;
        int left = n;
        int right = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    top = Math.min(top, i);
                    bottom = Math.max(bottom, i);
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                }
            }
        }

        int width = right - left + 1;
        int height = bottom - top + 1;
        int area = width * height;

        return area;
    }

    public int minOperations(int[] nums){
        int n = nums.length;
        int count = 0;
        for(int i=0;i<n;i++){
            if(count%2!=0 && nums[i]==1 || count%2==0 && nums[i]==0){
                count++;
            }
        }
        return count;
    }

    public int minOperations2(int[] nums){
        int n = nums.length;
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0 && i + 2 < n) {
                nums[i] = 1;
                if (nums[i + 1] == 1) {
                    nums[i + 1] = 0;
                } else {
                    nums[i + 1] = 1;
                }
                if (nums[i + 2] == 1) {
                    nums[i + 2] = 0;
                } else {
                    nums[i + 2] = 1;
                }
                cnt++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                return -1;
            }
        }

        return cnt;
    }

//    public long maximumTotalDamage(int[] power){
//        int n = power.length, sum = 0;
//        Map<Integer, Integer> kSum = new HashMap<>(), kCount = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            sum+=power[i];
//            kCount.put(power[i],kCount.getOrDefault(power[i],0)+1);
//        }
//        List<Integer> uniqueDamages = new ArrayList<>(kCount.keySet());
//        Collections.sort(uniqueDamages);
//        int key;
//        long res = 0,temp;
//        for (Map.Entry<Integer, Integer> entry : kCount.entrySet()){
//            key = entry.getKey();
//            temp = 0;
//            temp += kCount.get(key-1)!=null?(key-1)*kCount.get(key-1):0;
//            temp += kCount.get(key+1)!=null?(key+1)*kCount.get(key+1):0;
//            temp += kCount.get(key-2)!=null?(key-2)*kCount.get(key-2):0;
//            temp += kCount.get(key+2)!=null?(key+2)*kCount.get(key+2):0;
//            temp = sum - temp;
//            if(temp > res)
//                res = temp;
//        }
//        return res;
//    }

    public long maximumTotalDamage(int[] power) {
        // Step 1: Count the frequency of each damage value
        Map<Integer, Long> damageFrequency = new HashMap<>();
        for (int damage : power) {
            damageFrequency.put(damage, damageFrequency.getOrDefault(damage, 0L) + 1);
        }

        // Step 2: Extract and sort the unique damage values
        List<Integer> uniqueDamages = new ArrayList<>(damageFrequency.keySet());
        Collections.sort(uniqueDamages);

        int totalUniqueDamages = uniqueDamages.size();
        long[] maxDamageDP = new long[totalUniqueDamages];

        // Step 3: Initialize the DP array with the first unique damage
        maxDamageDP[0] = uniqueDamages.get(0) * damageFrequency.get(uniqueDamages.get(0));

        // Step 4: Fill the DP array with the maximum damage calculations
        for (int i = 1; i < totalUniqueDamages; i++) {
            int currentDamageValue = uniqueDamages.get(i);
            long currentDamageTotal = currentDamageValue * damageFrequency.get(currentDamageValue);

            // Initially, consider not taking the current damage
            maxDamageDP[i] = maxDamageDP[i - 1];

            // Find the previous damage value that doesn't conflict with the current one
            int previousIndex = i - 1;
            while (previousIndex >= 0 &&
                    (uniqueDamages.get(previousIndex) == currentDamageValue - 1 ||
                            uniqueDamages.get(previousIndex) == currentDamageValue - 2 ||
                            uniqueDamages.get(previousIndex) == currentDamageValue + 1 ||
                            uniqueDamages.get(previousIndex) == currentDamageValue + 2)) {
                previousIndex--;
            }

            // Update the DP value considering the current damage
            if (previousIndex >= 0) {
                maxDamageDP[i] = Math.max(maxDamageDP[i], maxDamageDP[previousIndex] + currentDamageTotal);
            } else {
                maxDamageDP[i] = Math.max(maxDamageDP[i], currentDamageTotal);
            }
        }

        // Return the maximum damage possible
        return maxDamageDP[totalUniqueDamages - 1];
    }

    public long countCompleteDays(int[] hours){
        long ans = 0;
        int[] count = new int[24];

        for (int i = 0; i < hours.length; i++) {
            ans += count[(24 - hours[i] % 24) % 24];
            count[hours[i] % 24]++;
        }

        return ans;
    }

    public boolean[] isArraySpecial(int[] nums, int[][] queries){
        boolean[] ans = new boolean[queries.length];
        int[] ps = new int[nums.length];
        for(int i=1;i<nums.length;i++){
            ps[i] = ps[i-1];
            if(nums[i-1]%2 == nums[i]%2){
                ps[i]++;
            }
        }
        for(int i=0;i<queries.length;i++){
            int from = queries[i][0],to = queries[i][1];
            int cnt = ps[to] - ps[from];
            ans[i] = cnt>0 ? false : true;
        }
        return ans;
    }

    public int maxPointsInsideSquare(int[][] points, String s){
        int n = points.length;
        int left = 0, right = 1000000000, result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            HashMap<Character, Integer> charCount = new HashMap<>();
            boolean isValid = true;
            for (int i = 0; i < n; i++) {
                if (Math.abs(points[i][0]) <= mid && Math.abs(points[i][1]) <= mid)
                    charCount.put(s.charAt(i), charCount.getOrDefault(s.charAt(i), 0) + 1);
            }

            for (int count : charCount.values()) {
                if (count > 1) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                result = mid;
                left = mid + 1;
            } else
                right = mid - 1;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(points[i][0]) <= result && Math.abs(points[i][1]) <= result) {
                count++;
            }
        }
        return count;
    }

    public int minimumAddedInteger(int[] nums1, int[] nums2){
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int res = Integer.MAX_VALUE,n=nums1.length;
        int[] diffs = new int[]{nums2[0]-nums1[0],nums2[0]-nums1[1],nums2[0]-nums1[2]};
        for(int diff : diffs){
            int deleted = 0, j = 0;
            for (int i = 0; i < n && j < n-2; i++) {
                if(nums2[j]-nums1[i]!=diff)
                    deleted++;
                else j++;
            }
            if(j==n-2)
                res = Math.min(res,diff);
        }
        return res;
    }

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> sol = new ArrayList<>();
        int aLen = a.length(), bLen = b.length(), sLen = s.length();
        List<Integer> aIdx = new ArrayList<>(), bIdx = new ArrayList<>();
        if(sLen == 1){
            return sol;
        }
        for (int i = 0; i <= sLen-aLen; i++) {
            if(s.substring(i,i+aLen).equals(a))
                aIdx.add(i);
        }
        for (int i = 0; i <= sLen-bLen; i++) {
            if(s.substring(i,i+bLen).equals(b))
                bIdx.add(i);
        }
        for(int i : aIdx){
            for(int j : bIdx){
                if(Math.abs(i-j)<=k) {
                    sol.add(i);
                    break;
                }
            }
        }
        return sol;
    }

    public long minimumSteps(String s){
        int n = s.length();
        long res = 0, zCnt = 0;
        for (int i = n-1;i>=0; i--) {
            char ch = s.charAt(i);
            if(ch == '0')
                zCnt++;
            else
                res += zCnt;
        }
        return res;
    }

    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n=nums.length,min=0,max=0;
        for (int i = indexDifference; i < n; i++) {
            if(nums[i-indexDifference] < nums[min]) min = i - indexDifference;
            if(nums[i-indexDifference] > nums[max]) max = i - indexDifference;
            if(nums[i] - nums[min] >= valueDifference) return new int[]{min, i};
            if(nums[max] - nums[i] >= valueDifference) return new int[]{max, i};
        }
        return new int[]{-1,-1};
    }

    public List<String> validStrings(int n){
        StringBuilder sb = new StringBuilder();
        validStrings(-1, sb, n);
        return ans;
    }

    private void validStrings(int prev, StringBuilder sb, int n){
        if(sb.length() == n){
            ans.add(sb.toString());
            return;
        }
        sb.append('1');
        validStrings(1,sb,n);
        sb.deleteCharAt(sb.length()-1);
        if(prev==1){
            sb.append('0');
            validStrings(0,sb,n);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public int monkeyMove(int n){
        long res = 1, base = 2, mod = (long)1e9 + 7;
        while (n > 0) {
            if (n % 2 == 1)
                res = res * base % mod;
            base = base * base % mod;
            n >>= 1;
        }
        return (int)((res - 2 + mod) % mod);
    }

    public int minNonZeroProduct(int p){
        int mod = 1_000_000_007;
        if (p == 1) return 1;

        long mx = (long)(Math.pow(2, p)) - 1;
        long sm = mx - 1;
        long n = sm/2;
        long sum = rec(sm, n);

        return (int)(sum * (mx % mod) % mod);
    }

    public long rec(long val, long n) {
        int mod = 1_000_000_007;
        if (n == 0) return 1;
        if (n == 1) return (val % mod);

        long newVal = ((val % mod) * (val % mod)) % mod;

        if (n % 2 != 0) {
            return ((rec(newVal, n/2) % mod) * (val % mod)) % mod;
        }

        return rec(newVal, n/2) % mod;
    }

    public int findTheWinner(int n, int k){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int curIdx = 0;
        while(list.size()>1){
            int idxToRemove = (curIdx+k-1)%list.size();
            list.remove(idxToRemove);
            curIdx = idxToRemove;
        }
        return list.getFirst();
    }

    public char findKthBit(int n, int k){
        if(n == 1){
            return '0';
        }

        int numberOfColumns = (int)Math.pow(2,n) - 1;
        int mid = numberOfColumns/2;

        if(k <= mid){
            return findKthBit(n-1,k);
        } else if (k == mid + 1){ //Since we are adding that extra "1" in each new row
            return '1';
        } else {
            k = numberOfColumns - k + 1;
            char c = findKthBit(n-1,k);
            return c == '0' ? '1' : '0';
        }
    }

    public int minimumBeautifulSubstrings(String s) {
        int n = s.length(), dp[] = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') continue;
            for (int j = i, cur = 0; j < n; j++) {
                cur = cur * 2 + s.charAt(j) - '0';
                if (15625 % cur == 0)
                    dp[j + 1] = Math.min(dp[j + 1], dp[i] + 1);
            }
        }
        return dp[n] > n ? -1 : dp[n];
    }

    public long maxStrength(int[] nums){
        int n = nums.length;
        if(n==1) return nums[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int max = Integer.MAX_VALUE;
        long prd = 1;
        for (int i = 0; i < n; i++) {
            if(nums[i]<0)
                pq.offer(nums[i]);
            else if(nums[i] > 0) {
                max = 1;
                prd *= max * nums[i];
            }
        }
        if(pq.size()>=2)
            max=1;
        if(max == Integer.MAX_VALUE) return 0;
        n = pq.size()%2==0?pq.size():pq.size()-1;
        for (int i = 0; i < n; i++) {
            prd*=-pq.poll();
        }
        return prd;
    }

    public int punishmentNumber(int n){
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int squareNum = i * i;
            if (isPartition(squareNum + "", i)) {
                sum+=squareNum;
            }
        }
        return sum;
    }

    private boolean isPartition(String str, int value) {
        if (value == 0 && str == "") return true;
        if (value < 0) return false;
        boolean ans = false;
        for (int i = 0; i < str.length(); i++) {
            String leftStr = str.substring(0, i+1);
            String rightStr = str.substring(i+1, str.length());
            int leftNum = Integer.parseInt(leftStr);
            boolean isPossible = isPartition(rightStr, value -leftNum);
            if (isPossible) {
                ans = true;
                break;
            }
        }
        return ans;
    }

    private int count;
    private Map<Integer, Integer> visited = new HashMap<>();

    public int beautifulSubsets(int[] nums, int k) {
        explore(nums, k, 0);
        return count - 1; // Subtract 1 to exclude the empty subset
    }

    private void explore(int[] nums, int k, int index) {
        if (index == nums.length) {
            count++;
            return;
        }

        int num = nums[index];

        if (!visited.containsKey(num - k) && !visited.containsKey(num + k)) {
            visited.put(num, visited.getOrDefault(num, 0) + 1);
            explore(nums, k, index + 1);
            visited.put(num, visited.get(num) - 1);
            if (visited.get(num) == 0) {
                visited.remove(num);
            }
        }

        explore(nums, k, index + 1);
    }

    public int minimumOperations(int x, int y){
        return minimumOperationsHelper(x,y,new int[Integer.max(x,y)+1]);
    }

    private int minimumOperationsHelper(int x, int y, int[] dp){
        if (x <= y) return y - x;
        if (dp[x] != 0) return dp[x];
        int res = Math.abs(x - y);
        res = Math.min(res, 1 + x % 5 + minimumOperationsHelper(x / 5, y,dp));
        res = Math.min(res, 1 + (5 - x % 5) + minimumOperationsHelper(x / 5 + 1, y,dp));
        res = Math.min(res, 1 + x % 11 + minimumOperationsHelper(x / 11, y,dp));
        res = Math.min(res, 1 + (11 - x % 11) + minimumOperationsHelper(x / 11 + 1, y,dp));
        return dp[x] = res;
    }

    public int minimumMoves(int[][] grid){
        // Base Case
        int t = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                if (grid[i][j] == 0)
                    t++;
        if (t == 0)
            return 0;

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (grid[i][j] == 0) {
                    for (int ni = 0; ni < 3; ++ni) {
                        for (int nj = 0; nj < 3; ++nj) {
                            int d = Math.abs(ni - i) + Math.abs(nj - j);
                            if (grid[ni][nj] > 1) {
                                grid[ni][nj]--;
                                grid[i][j]++;
                                ans = Math.min(ans, d + minimumMoves(grid));
                                grid[ni][nj]++;
                                grid[i][j]--;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    private int[] roww = {0, 0, -1, 1};
    private int[] coll = {-1, 1, 0, 0};
    private void bfs(List<List<Integer>> grid, int[][] score, int n) {
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    score[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0], y = t[1];
            int s = score[x][y];

            for (int i = 0; i < 4; i++) {
                int newX = x + roww[i];
                int newY = y + coll[i];

                if (newX >= 0 && newX < n && newY >= 0 && newY < n && score[newX][newY] > 1 + s) {
                    score[newX][newY] = 1 + s;
                    q.offer(new int[]{newX, newY});
                }
            }
        }
    }

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        int[][] score = new int[n][n];
        for (int[] row : score) Arrays.fill(row, Integer.MAX_VALUE);
        bfs(grid, score, n);

        boolean[][] vis = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.offer(new int[]{score[0][0], 0, 0});

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int safe = temp[0];
            int i = temp[1], j = temp[2];

            if (i == n - 1 && j == n - 1) return safe;
            vis[i][j] = true;

            for (int k = 0; k < 4; k++) {
                int newX = i + roww[k];
                int newY = j + coll[k];

                if (newX >= 0 && newX < n && newY >= 0 && newY < n && !vis[newX][newY]) {
                    int s = Math.min(safe, score[newX][newY]);
                    pq.offer(new int[]{s, newX, newY});
                    vis[newX][newY] = true;
                }
            }
        }

        return -1;
    }
}
