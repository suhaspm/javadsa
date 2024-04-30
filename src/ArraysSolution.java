import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class ArraysSolution {
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
}
