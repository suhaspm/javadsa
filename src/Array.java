import java.util.*;

public class Array {
    public int[] twoSum(int[] nums, int target){
        int[] indices = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if(map.containsKey(diff) && map.get(diff) != i) {
                indices[0] = map.get(diff);
                indices[1] = i;
                return indices;
            }
        }
        return null;
    }

    public int maxProfit(int[] prices){
        int profit = 0, buy = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i] < buy)
                buy = prices[i];
            else if(prices[i] - buy > profit)
                profit = prices[i] - buy;
        }
        return profit;
    }

    public int majorityElement(int[] nums){
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n/2];
    }

    public boolean containsDuplicate(int[] nums){
        java.util.Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i-1] == nums[i])
                return true;
        }
        return false;
    }

    public boolean canAttendMeetings(List<Interval> intervals){
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });
        for (int i = 1; i < intervals.size(); i++) {
            Interval i1 = intervals.get(i-1);
            Interval i2 = intervals.get(i);
            if(i1.end > i2.start) return false;
        }
        return true;
    }

    public int[][] insert(int[][] intervals, int[] newInterval){
        int n = intervals.length;
        int i = 0;
        List<int[]> res = new ArrayList<>();

        // Case 1: No overlapping case before merging intervals
        // Compare ending point of intervals to starting point of newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        // Case 2: Overlapping case and merging of intervals
        while (i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);

        // Case 3: No overlapping of intervals after newInterval being merged
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        // Convert list to array
        int[][] result = new int[res.size()][2];
        for (int j = 0; j < res.size(); j++) {
            result[j] = res.get(j);
        }

        return result;
    }

    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> newList = new ArrayList<>();
        java.util.Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate elements for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) {
                    // Found a triplet with zero sum
                    newList.add(java.util.Arrays.asList(nums[i], nums[j], nums[k]));

                    // Skip duplicate elements for j
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }

                    // Skip duplicate elements for k
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }

                    // Move the pointers
                    j++;
                    k--;
                } else if (sum < 0) {
                    // Sum is less than zero, increment j to increase the sum
                    j++;
                } else {
                    // Sum is greater than zero, decrement k to decrease the sum
                    k--;
                }
            }
        }
        return newList;
    }

    public int[] productExceptSelf(int[] nums){
        int n = nums.length;
        int[] pre = new int[n];
        int[] suf = new int[n];
        pre[0] = 1;
        suf[n-1] = 1;
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i-1]*nums[i-1];
        }
        for (int i = n-2; i >=0 ; i--) {
            suf[i] = suf[i+1]*nums[i+1];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = pre[i] * suf[i];
        }
        return nums;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target){
        if(target == 0) return null;
        List<List<Integer>> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        sum(result, new ArrayList<>(), candidates, 0, 0, target);

        return result;
    }

    public void sum(int[] candidates, List<List<Integer>> current, List<List<Integer>> result, int i, int sum, int target){
        int idx = current.size()-1;
        if(sum == target) {
            result.get(idx).addAll(current.get(idx));
        }
        else if(sum < target){
            if(current.size()<=i && !current.get(idx).isEmpty()){
                current.add(new ArrayList<>());
                result.add(new ArrayList<>());
            }
            for (int j = i; j < candidates.length; j++) {
                current.get(current.size()-1).add(candidates[j]);
                sum(candidates, current, result, j, candidates[j]+sum, target);
                if(!current.get(current.size()-1).isEmpty())
                    current.get(current.size()-1).removeLast();
            }
        }
        //return result;
    }

    private void sum(List<List<Integer>> list, List<Integer> tempList, int[] candidates, int sum, int i, int target){
        if(sum > target) return;
        else if(sum == target) list.add(new ArrayList<>(tempList));
        else {
            for (int j = i; j < candidates.length; j++) {
                tempList.add(candidates[j]);
                sum(list, tempList, candidates,sum+candidates[j],j,target);
                tempList.removeLast();
            }
        }
    }

    public int[][] merge(int[][] intervals){
        java.util.Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int n = intervals.length;
        int i = 0;
        List<int[]> res = new ArrayList<>();

        for (int[] interval : intervals) {
            if (res.isEmpty() || res.getLast()[1] < interval[0]) {
                res.add(interval);
            } else {
                res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
            }
        }

        return res.toArray(new int[res.size()][]);
        // Case 1: No overlapping case before merging intervals
        // Compare ending point of intervals to starting point of newInterval
//        for (i = 0; i < n-1 && intervals[i][1] < intervals[i+1][0]; i++) {
//            res.add(intervals[i]);
//        }
//
//        // Case 2: Overlapping case and merging of intervals
//        int curr = i+1;
//        for (;i < n-1 && intervals[curr][1] >= intervals[i][0] && intervals[curr][1] > intervals[i][1];i++) {
//                intervals[i+1][0] = Math.min(intervals[i+1][0], intervals[i][0]);
//                intervals[i+1][1] = Math.max(intervals[i+1][1], intervals[i][1]);
//        }
//        for (;i < n-1 && intervals[curr][1] <= intervals[i][1];i++) {
//            intervals[i+1][0] = Math.min(intervals[i+1][0], intervals[i][0]);
//            intervals[i+1][1] = Math.max(intervals[i+1][1], intervals[i][1]);
//        }
//        res.add(intervals[i++]);

        // Case 3: No overlapping of intervals after newInterval being merged
//        for (;i < n;i++) {
//            res.add(intervals[i]);
//        }
//
//        // Convert list to array
//        int[][] result = new int[res.size()][2];
//        for (int j = 0; j < res.size(); j++) {
//            result[j] = res.get(j);
//        }
//
//        return result;
    }

    public void sortColors(int[] nums){
        int left = 0, right = nums.length-1, iter = 0;
        while(iter <= right){
            if(nums[iter] == 0){
                int temp = nums[iter];
                nums[iter] = nums[left];
                nums[left] = temp;
                left++;
                iter++;
            }
            else if(nums[iter] == 2){
                int temp = nums[iter];
                nums[iter] = nums[right];
                nums[right] = temp;
                right--;
            }
            else iter++;
        }
    }

    public int maxArea(int[] height){
        int max = -1, left = 0, right = height.length-1, temp = -1;
        while(left != right){
            if(height[left] < height[right]){
                temp = height[left] * (right-left);
                left++;
            }
            else if(height[left] >= height[right]){
                temp = height[right] * (right-left);
                right--;
            }
            if(temp > max)
                max = temp;
        }
        return max;
    }

    public int canCompleteCircuit(int[] gas, int[] cost){
        int n = gas.length;
        int total_surplus = 0;
        int surplus = 0;
        int start = 0;

        for(int i = 0; i < n; i++){
            total_surplus += gas[i] - cost[i];
            surplus += gas[i] - cost[i];
            if(surplus < 0){
                surplus = 0;
                start = i + 1;
            }
        }
        return (total_surplus < 0) ? -1 : start;
//        int totalCountOfGas = 0;
//        int totalCountOfCost = 0;
//        int index = 0;
//        int current = 0;
//        for(int i=0; i<gas.Length; i++)
//        {
//            totalCountOfGas+=gas[i];
//        }
//        for(int i=0; i<cost.Length; i++)
//        {
//            totalCountOfCost+=cost[i];
//        }
//        if(totalCountOfGas<totalCountOfCost) return -1;
//
//        for (int i=0; i<gas.Length; i++)
//        {
//            current += (gas[i]-cost[i]);
//            if(current < 0)
//            {
//                current = 0;
//                index = i+1;
//            }
//        }
//        return index;
    }

    public int longestConsecutive(int[] nums){
        Set<Integer> map = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            map.add(nums[i]);
        }

        int longest = 0;
        for(int x : map){
            if(!map.contains(x-1)){
                int y = x + 1;
                while(map.contains(y))
                    y++;
                longest = Math.max(longest, y-x);
            }
        }


        return longest;
    }

    public void rotate(int[] nums, int k){
        k = k % nums.length;

        rotate(nums, 0, nums.length-1);
        rotate(nums, 0, k-1);
        rotate(nums, k, nums.length - 1);
    }

    private void rotate(int[] nums, int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public int findMaxLength(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        int sum = 0;
        int subArrayLength = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (sum == 0) {
                subArrayLength = i + 1;
            } else if (mp.containsKey(sum)) {
                subArrayLength = Math.max(subArrayLength, i - mp.get(sum));
            } else {
                mp.put(sum, i);
            }
        }
        return subArrayLength;
    }

    public int subarraySum(int[] arr, int k){
//                int n = arr.length;
//                int[] prefix = new int[n];
//                prefix[0] = arr[0];
//
//                for (int i = 1; i < n; i++) {
//                    prefix[i] = arr[i] + prefix[i - 1];
//                }
//
//                HashMap<Integer, Integer> mp = new HashMap<>();
//                int ans = 0;
//
//                for (int i = 0; i < n; i++) {
//                    if (prefix[i] == k) {
//                        ans++;
//                    }
//
//                    if (mp.containsKey(prefix[i] - k)) {
//                        ans += mp.get(prefix[i] - k);
//                    }
//
//                    mp.put(prefix[i], mp.getOrDefault(prefix[i], 0) + 1);
//                }
//
//                return ans;
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
            if(sum == k) count++;
            if(!map.containsValue(sum-k)) {
                map.put(i, sum);
                count+=map.get(sum-k);
            }
            else if(map.containsValue(sum-k) || arr[i] == k) {
                count++;
                map.put(i, sum);
            }
        }
        return count;
    }

    public int threeSumClosest(int[] nums, int target){
        int sum = 1001, temp, diff = Integer.MAX_VALUE;
        java.util.Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;

            int j = i+1;
            int k = nums.length-1;

            while(j<k){
                temp = nums[i] + nums[j] + nums[k];
                if(temp == target){
                    return temp;
                }
                else if(temp < target)
                    j++;
                else k--;

                //if((target >= 0 && temp - target > diff) || (target < 0 && temp - target < diff)) {
                if(Math.abs(temp-target) < diff){
                    sum = temp;
                    diff = Math.abs(temp - target);
                }
            }
        }
        return sum;
    }

//    public int eraseOverlapIntervals(int[][] intervals){
//        int count = 0;
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return Integer.compare(o1[0], o2[0]);
//            }
//        });
//        HashMap<List<Integer>, Integer> map = new HashMap<>();
//        int n = intervals.length;
//        for (int i = 0; i < n; i++) {
//            List<Integer> interval = Arrays.asList(intervals[i][0],intervals[i][1]);
//            if(!map.containsKey(interval))
//                map.put(interval,0);
//            else {
//                int v = map.get(interval)+1;
//                map.put(interval, v+1);
//            }
//            if(i+1 != n && Arrays.equals(intervals[i], intervals[i+1]) && map.get(interval) > 0) count++;
//        }
//        for (int i = 0; i < n-1; i++) {
//            if(intervals[i][1] > intervals[i+1][0]){
//                List<Integer> interval = Arrays.asList(intervals[i][0],intervals[i][1]);
//                List<Integer> nextInterval = Arrays.asList(intervals[i+1][0],intervals[i+1][1]);
//                map.put(interval,map.get(interval)+1);
//                map.put(nextInterval,map.get(nextInterval)+1);
//            }
//        }
//        if(intervals[n-1][1] > intervals[0][0]) {
//            List<Integer> interval = Arrays.asList(intervals[n-1][0],intervals[n-1][1]);
//            map.put(interval, map.get(interval)+1);
//        }
//        for (Map.Entry<List<Integer>, Integer> entry:map.entrySet()) {
//            if(entry.getValue() > 1) count++;
//        }
//        return count;
//    }

    public int eraseOverlapIntervals(int[][] intervals){
        java.util.Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1],o2[1]);
            }
        });
        int n = intervals.length;
        int count = 1;
        int end = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if(intervals[i][0] >= end){
                end = intervals[i][1];
                count++;
            }
        }
        return n-count;
    }

    public int[] maxSlidingWindow(int[] nums, int k){
        // assume nums is not null
        int n = nums.length;
        if (n == 0 || k == 0) {
            return new int[0];
        }
        int[] result = new int[n - k + 1]; // number of windows
        Deque<Integer> win = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; ++i) {
            // remove indices that are out of bound
            while (win.size() > 0 && win.peekFirst() <= i - k) {
                win.pollFirst();
            }
            // remove indices whose corresponding values are less than nums[i]
            while (win.size() > 0 && nums[win.peekLast()] < nums[i]) {
                win.pollLast();
            }
            // add nums[i]
            win.offerLast(i);
            // add to result
            if (i >= k - 1) {
                result[i - k + 1] = nums[win.peekFirst()];
            }
        }
        return result;
    }

    public int minMeetingRooms(int[][] intervals){
        int maxCount = 0, count = 0;
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        java.util.Arrays.sort(start);
        java.util.Arrays.sort(end);
        int i = 0, j = 0;
        while(i < start.length) {
            if(start[i] <= end[j]) {
                count++;
                i++;
            }
            else {
                count--;
                j++;
            }
            if(count > maxCount)
                maxCount = count;
        }
        return maxCount;
    }

    public List<List<int[]>> employeeFreeTime(int[][] intervals){
        List<List<int[]>> freeTime = new ArrayList<>();
        java.util.Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });
        for (int i = 0; i < intervals.length-1; i++) {
            if(intervals[i][1] >= intervals[i+1][0]) {
                intervals[i+1][0] = Integer.min(intervals[i][0],intervals[i+1][0]);
                intervals[i + 1][1] = Integer.max(intervals[i][1],intervals[i+1][1]);
                intervals[i][0] = -1;
                intervals[i][1] = -1;
            }
        }
        for (int i = 0; i < intervals.length-1; i++) {
            if(intervals[i][0] != -1 && intervals[i][1] != -1){
                List<int[]> list = new ArrayList<>();
                list.add(new int[]{intervals[i][1],intervals[i+1][0]});
                freeTime.add(list);
            }
        }
        return freeTime;
    }
}
