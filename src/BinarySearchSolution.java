public class BinarySearchSolution {
    int[] arr;
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
}
