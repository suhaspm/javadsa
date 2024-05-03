import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sorting {

    public void Bubble(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                int temp = arr[j];
                if(temp > arr[j+1]){
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public void Selection(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int min = Integer.MAX_VALUE;
            int idx = 0;
            for (int j = i; j < arr.length; j++) {
                if(arr[j] < min) {
                    min = arr[j];
                    idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    public void Insertion(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i], j = 0;
            for (j = i; j> 0 && arr[j-1]>temp; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    public void Bucket(int[] arr){
        int maxBuckets = (int)Math.ceil(Math.sqrt(arr.length));
        ArrayList<Integer>[] buckets = new ArrayList[maxBuckets];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max)
                max = arr[i];
            int idx = (int)Math.ceil((double) (arr[i] * maxBuckets) / max);
            buckets[idx-1].add(arr[i]);
        }

        for (int i = 0; i < buckets.length; i++) {
            Collections.sort(buckets[i]);
        }

        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                System.out.println(buckets[i].get(j));
            }
        }
    }

    public void Merge(int[] arr){
        Merge(arr, 0, arr.length-1);
    }

    private void Merge(int[] arr, int left, int right){
        if(right > left){
            int m = (right + left) / 2;
            Merge(arr, left, m);
            Merge(arr, m+1, right);
            Merge(arr, left, m, right);
        }
    }

    private void Merge(int[] arr, int left, int middle, int right){
        int[] leftTmpArray = new int[middle-left+2];
        int[] rightTmpArray = new int[right-middle+1];
        for (int i=0; i<=middle-left; i++) {
            leftTmpArray[i] = arr[left+i];
        }
        for (int i=0; i<right-middle; i++) {
            rightTmpArray[i] = arr[middle+1+i];
        }


        leftTmpArray[middle-left+1] = Integer.MAX_VALUE;
        rightTmpArray[right-middle] = Integer.MAX_VALUE;

        int i = 0, j = 0;
        for (int k = left; k<=right; k++) {
            if (leftTmpArray[i] < rightTmpArray[j]) {
                arr[k] = leftTmpArray[i];
                i++;
            } else {
                arr[k] = rightTmpArray[j];
                j++;
            }

        }
    }

    public void Quick(int[] arr){
        Quick(arr, 0, arr.length-1);
    }

    private void Quick(int[] arr, int start, int end){
        if(start < end){
            int pivot = Partition(arr, start, end);
            Quick(arr, start, pivot-1);
            Quick(arr, pivot + 1, end);
        }
    }

    private int Partition(int[] arr, int start, int end){
        int pivot = end;
        int i = start -1;
        for (int j = start; j <=end; j++) {
            if(arr[j]<=arr[pivot]){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return i;
    }

    public void Heap(int[] arr){
        BinaryHeap bh = new BinaryHeap(arr.length);
        for (int i = 0; i < arr.length; i++) {
            bh.insertInHeap(arr[i], "Min");
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = bh.extractFromHeap("Min");
        }
    }

    public void sortColors(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            int min = Integer.MAX_VALUE, idx = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < min){
                    min = nums[j];
                    idx = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[idx];
            nums[idx] = temp;
        }
    }

    public MyNode insertionSortList(MyNode head){
        if(head == null) return null;
        MyNode dummy = new MyNode();
        //dummy.next = head;
        MyNode prev = dummy, current = head, next = null;
        for (int i = 0; current!=null; i++) {
            next = current.next;
            while (prev.next != null && prev.next.value < current.value){
                prev = prev.next;
            }
            current.next = prev.next;
            prev.next = current;
            prev = dummy;
            current = next;
        }
        return dummy.next;
    }
}
