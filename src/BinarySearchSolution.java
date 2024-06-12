import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinarySearchSolution {
    int[] arr;
    List<Integer> time;
    List<String[]> keyValue;

    public int search(int[] nums, int target){
        int l = 0, h = nums.length - 1;
        while(l <= h){
            int mid = (l+h)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target)
                h = mid - 1;
            else
                l = mid + 1;
        }
        return -1;
    }

    public int firstBadVersion(int n){
//        int l = 0, h = n;
//        while(l<=h){
//            int mid = l+(h-l)/2;
//            if(!isBadVersion(mid))
//                l = mid + 1;
//            else
//                h = mid;
//        }
//        return l;
        int start = 1;
        int end = n;
        while(start<end){
            int mid = start+(end-start)/2;
            boolean isBad = isBadVersion(mid);

            if(isBad == true){
                end = mid;
            }
            else{
                start = mid +1;
            }
        }
        return start;
    }

    private boolean isBadVersion(int version){
        if(version > arr.length) return false;
        return arr[version] == 0;
    }

    public int rotatedSearch(int[] nums, int target){
        int k = 0,n=nums.length;
        int l = 0, h = n-1;
        while(l <= h){
            int mid = (h+l)/2;
            if(nums[mid] == target) return mid;
            if(nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    h = mid - 1;
                } else
                    l = mid + 1;
            }
            else {
                if (nums[mid] < target && target <= nums[h]) {
                    l = mid + 1;
                } else {
                    h = mid - 1;
                }
            }
        }
        return -1;
    }

    public void TimeStamp(){
        time = new ArrayList<>();
        keyValue = new ArrayList<>();
    }

    public void set(String key, String value, int timestamp){
        time.add(timestamp);
        keyValue.add(new String[]{key, value});
    }

    public String get(String key, int timestamp){
        int l = 0, h = time.size()-1,i=0;
        boolean notExact = false;
        while(l <= h){
            int mid = (l + h) / 2;
            if(timestamp == time.get(mid)){
                i = mid;
                break;
            }
            if(timestamp < time.get(mid)) {
                h = mid - 1;
                i = l;
                if(notExact)
                    i = h;
            }
            else {
                l = mid + 1;
                i = h;
                notExact = true;
            }
        }
        String[] val = keyValue.get(i);
        String res = "";
        if(val[0] == key)
            res = val[1];
        return res;
    }

    public boolean searchMatrx(int[][] matrix, int target){
        if(matrix.length == 0) return false;
        int rh = matrix.length, left = 0;
        int ch = matrix[0].length, right = rh * ch - 1;
        while(left <= right){
            int mid = (left+right)/2;
            int mr = mid / ch;
            int mc = mid % ch;
            if(matrix[mr][mc] == target) return true;
            if(matrix[mr][mc] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;
    }

    public int findMin(int[] nums){
        int n = nums.length, h = n-1, l = 0;
        while(l < h) {
            int mid = l+(h-l)/2;
            if(nums[mid] < nums[h]) {
                h = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return nums[l];
    }

    public String addBinary(String a, String b){
        int m = a.length()-1, n = b.length()-1;
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        while(m>=0||n>=0||carry==1){
            if(m>=0)
                carry+=a.charAt(m--)-'0';
            if(n>=0)
                carry+=b.charAt(n--)-'0';
            builder.append(carry%2);
            carry/=2;
        }
        return builder.reverse().toString();
    }

    public int[] countBits(int num){
        int[] f = new int[num+1];
        for (int i = 0; i <= num; i++) {
            f[i] = f[i/2] + i % 2;
        }
        return f;
    }

    public int hammingWeight(int n){
        int weight = 0,i;
        while(n!=0){
            i = n%2;
            n = n/2;
            if(i == 1) weight++;
        }
        return weight;
    }

    public int missingNumber(int[] nums){
        int[] output = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            output[nums[i]] = -1;
        }
        for (int i = 0; i < output.length; i++) {
            if(output[i] != -1)
                return i;
        }
        return -1;
    }

    public int reverseBits(int n){
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans <<= 1;
            ans |= (n & 1);
            n >>= 1;
        }
        return ans;
    }
}
