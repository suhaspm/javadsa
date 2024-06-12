import java.util.*;

public class HashTableSolution {
    Map<Integer, Integer> map;
    List<Integer> list;
    int[] prefixSum;
    int sum;
    Random random;
    public boolean canConstruct(String ransomNote, String magazine){
        if(ransomNote.length() > magazine.length()) return false;
        HashMap<Character, Integer> table = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char ch = magazine.charAt(i);
            table.put(ch,table.getOrDefault(ch,0)+1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char ch = ransomNote.charAt(i);
            if(!table.containsKey(ch) || table.get(ch) == 0) return false;
            table.put(ch,table.get(ch)-1);
        }
        return true;
    }

    public void RandomizedSet(){
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val){
        if(map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size()-1);
        return true;
    }

    public boolean remove(int val){
        if(!map.containsKey(val)) return false;
        int idx = map.get(val);
        list.set(idx, list.get(list.size()-1));
        map.put(list.get(idx),idx);
        list.remove(list.size()-1);
        map.remove(val);
        return true;
    }

    public int getRandom(){
        Random r = new Random();
        return list.get(r.nextInt(list.size()));
    }

    public int firstMissingPositive(int[] nums){
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while(nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i]-1]){
                int a = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[a-1] = a;
            }
        }

        for (int i = 0; i < n; i++) {
            if(nums[i] != i +1) return i+1;
        }
        return n+1;
    }

    public int romanToInt(String s){
        int num = 0;
        int n = s.length();
        for (int i = n-1; i >=0; i--) {
            char ch = s.charAt(i);
            switch (ch){
                case 'M':
                    num+=1000;
                    break;
                case 'D':
                    num+=500;
                    break;
                case 'C':
                    if(i+1<=n-1 && (s.charAt(i+1)=='M' || s.charAt(i+1)=='D'))
                        num-=100;
                    else num+=100;
                    break;
                case 'L':
                    num+=50;
                    break;
                case 'X':
                    if(i+1<=n-1 && (s.charAt(i+1)=='L' || s.charAt(i+1)=='C'))
                        num-=10;
                    else num+=10;
                    break;
                case 'V':
                    num+=5;
                    break;
                case 'I':
                    if(i+1<=n-1 && (s.charAt(i+1)=='X' || s.charAt(i+1)=='V'))
                        num-=1;
                    else num+=1;
                    break;
            }
        }
        return num;
    }

    public void Solution(int[] w){
        prefixSum = new int[w.length];
        prefixSum[0] = w[0];
        for(int i=1; i<w.length; i++) {
            prefixSum[i] = prefixSum[i-1] + w[i];
        }
        sum = prefixSum[w.length - 1];
        random = new Random();
    }

    public int pickIndex(){
        int r = random.nextInt(sum) + 1;
        int low = 0, high = prefixSum.length - 1, ans = -1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if(prefixSum[mid] >= r) {
                ans = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public double myPow(double x, int n){
        double ans = 1.0;
        long nn = n;
        if(n<0) nn=nn*-1;

        while (nn>0){
            if(nn%2==1){
                ans =ans*x;
                nn=nn-1;
            }
            else{
                x=x*x;
                nn/=2;
            }
        }
        if(n<0) ans = (double)(1.0)/(double)(ans);
        return ans;
    }

    public int reverse(int x){
        long output= 0;
        while(x!=0){
            int t = x%10;
            output = output*10+t;
            if(output > Integer.MAX_VALUE || output < Integer.MIN_VALUE)
                return 0;
            x/=10;
        }
        return (int)output;
    }

    public int findDuplicate(int[] nums){
        int slow = nums[0];
        int fast = nums[0];
        while(true){
            slow = nums[slow];
            fast = nums[nums[fast]];
            if(slow == fast)
                break;
        }
        int slow2 = nums[0];
        while(slow!=slow2){
            slow = nums[slow];
            slow2 = nums[slow2];
        }
        return slow;
    }
}
