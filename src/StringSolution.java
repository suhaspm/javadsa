import java.io.CharConversionException;
import java.util.*;

public class StringSolution {
    public boolean isPalindrome(String s){
        int n = s.length();
        int j = 0;
        char[] ch = new char[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                ch[j] = Character.toLowerCase(s.charAt(i));
                j++;
                cnt++;
            }
        }
        j = cnt-1;
        for (int i = 0; i < cnt && i <= j; i++,j--) {
            if(ch[i] != ch[j])
                return false;
        }
        return true;
    }

    public boolean isAnagram(String s, String t){
        if(s.length() != t.length()) return false;
        Map<Character, Integer> set = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            set.put(s.charAt(i),set.getOrDefault(s.charAt(i),0)+1);
        }
        for (int i = 0; i < s.length(); i++) {
            set.put(t.charAt(i),set.getOrDefault(t.charAt(i),0)-1);
        }
        for (int val : set.values()) {
            if(val != 0)
                return false;
        }
        return true;
    }

    public int longestPalindrome(String s){
        int l = 0, n = s.length();
        int oddIdx = 0;
        boolean odd = true;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch,0)+1);
            if(map.get(ch) % 2 == 1)
                oddIdx++;
            else
                oddIdx--;
        }
        if(oddIdx > 1) {
            l = n -oddIdx + 1;
            return l;
        };
        l = n;
        return l;
    }

    public String longestCommonPrefix(String[] strs){
        StringBuilder builder = new StringBuilder();
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length-1];
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if(first.charAt(i) != last.charAt(i))
                return builder.toString();
            else
                builder.append(first.charAt(i));
        }
        return builder.toString();
    }

    public int longestSubString(String s){
        int n = s.length();
        int maxLength = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (!charMap.containsKey(s.charAt(right)) || charMap.get(s.charAt(right)) < left) {
                charMap.put(s.charAt(right), right);
                maxLength = Math.max(maxLength, right - left + 1);
            } else {
                left = charMap.get(s.charAt(right)) + 1;
                charMap.put(s.charAt(right), right);
            }
        }

        return maxLength;
    }

    public int myAtoi(String s){
        long num = 0, n = s.length();
        boolean isNegative = false, pre = false;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if(pre && ch == ' ') break;
            if(ch == ' ') continue;
                //number is negative
            else if(!pre && ch == '-'){
                pre = true;
                isNegative = true;
            }
            else if(!pre && ch == '+') {
                pre = true;
                continue;
            }
            //character in the string
            else if(!Character.isDigit(ch)) break;
            //2147483647, -2147483648
            else if(num * 10 + ch - '0' > Integer.MAX_VALUE){
                if(isNegative) {
                    num = Integer.MIN_VALUE;
                    isNegative = false;
                }
                else
                    num = Integer.MAX_VALUE;
                break;
            }
            //number
            else if(ch >= '0' && ch <= '9') {
                num = num * 10 + ch - '0';
                pre = true;
            }
        }
        if(isNegative) num *= -1;
        return (int)num;
    }

    public String longestPalindromes(String s){
        int start = 0, end = 0,n = s.length(), maxLen;
        for (int i = 0; i < n; i++) {
            int odd = expandAroundCenter(s, i, i);
            int even = expandAroundCenter(s, i, i +1);
            maxLen = Math.max(odd, even);

            if(maxLen > end - start){
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }
        return s.substring(start, end+1);
    }

    private int expandAroundCenter(String s, int left, int right){
        while(left >= 0 && right <= s.length()-1 && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }

    public List<Integer> findAnagrams(String s, String p){
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || s.length() < p.length()) {
            return result;
        }

        // Arrays to keep track of character counts
        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // Populate the pCount array with the frequency of characters in p
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
        }

        // Initial window in s
        for (int i = 0; i < p.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
        }

        // Compare the first window
        if (Arrays.equals(pCount, sCount)) {
            result.add(0);
        }

        // Slide the window across s
        for (int i = p.length(); i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++; // Add new character to the current window
            sCount[s.charAt(i - p.length()) - 'a']--; // Remove the character left out of the current window

            // Compare current window with pCount
            if (Arrays.equals(pCount, sCount)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
//        Map<Character,Integer> set = new HashMap<>();
//        for (int i = 0; i < p.length(); i++) {
//            set.put(p.charAt(i), set.getOrDefault(p.charAt(i),0)+1);
//        }
//        for (int i = 0; i < s.length() - p.length() + 1; i++) {
//            if(set.containsKey(s.charAt(i))){
//                if(set.containsKey(s.charAt(i+p.length()-1))) {
//                    int val = findPair(set, s, p, i);
//                    if (val != -1) {
//                        list.add(val);
//                    }
//                }
//                else
//                    i += p.length()-1;
//            }
//        }
    }

    private int findPair(Map set, String s, String p, int i){
        Map<Character,Integer> temp = new HashMap<>(set);
        for (int j = i ; j < i + p.length() && j < s.length(); j++) {
            char ch = s.charAt(j);
            if(temp.get(ch) == null || temp.get(ch) == 0)
                return -1;
            temp.put(ch,temp.get(ch)-1);
        }
        return i;
    }

    public String largestNumber(int[] nums){
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        Comparator<String> comp = new Comparator<String>(){
            @Override
            public int compare(String str1, String str2){
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1); // reverse order here, so we can do append() later
            }
        };
        Arrays.sort(arr, comp);
        if(arr[0].charAt(0) == '0')
            return "0";
        StringBuilder sb = new StringBuilder();
        for(String s: arr)
            sb.append(s);
        return sb.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs){
        Map<String, List<String>> map = new HashMap<>();
        for(String word : strs) {
            char[] ch = word.toCharArray();
            Arrays.sort(ch);
            String newWord = new String(ch);
            if (!map.containsKey(newWord)) {
                map.put(newWord, new ArrayList<>());
            }
            map.get(newWord).add(word);
        }
        return new ArrayList<>(map.values());
    }

    public int characterReplacement(String s, int k){
        int maxLen = 0, n = s.length();
        int[] arr = new int[26];
        int j = 0, res = 0;
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'A']++;
            maxLen = Math.max(maxLen, arr[s.charAt(i) - 'A']);
            if(i - j + 1 - maxLen > k){
                arr[s.charAt(j) - 'A']--;
                j++;
            }
            res = Math.max(maxLen, i - j + 1);
        }
        return res;
    }

//    public String minWindow(String s, String t){
//        int n = t.length(), maxLen = 1;
//        String res = "";
//        if(n > s.length()) return res;
//
//        int[] sArr = new int[26];
//        int[] tArr = new int[26];
//        char ch = 'A';
//        for (int i = 0; i < n; i++) {
//            if(Character.isLowerCase(t.charAt(i)))
//                ch = 'a';
//            else ch = 'A';
//            tArr[t.charAt(i) - ch]++;
//        }
//        n = s.length();
//        boolean same = false, same2 = false;
//        for (int i = 0; i < n; i++) {
//            if(Character.isLowerCase(s.charAt(i)))
//                ch = 'a';
//            else ch = 'A';
//            sArr[s.charAt(i) - ch]++;
//            if(i>=t.length()-1 && !same && !same2){
//                same = areOnSameIndices(sArr, tArr);
//                if(same) {
//                    res = s.substring(i-maxLen+1,i+1);
//                    maxLen = i + 1;
//                }
//            }
//            else if(same){
//                //sArr[s.charAt(i) - 'A']++;
//                sArr[s.charAt(i - maxLen) - ch]--;
//                same2 = areOnSameIndices(sArr, tArr);
//                if(same2) {
//                    res = s.substring(i-maxLen+1, i+1);
//                    sArr[s.charAt(i) - ch]--;
//                    i--;
//                    maxLen--;
//                    same = false;
//                }
//            }
//            else if(same2){
//                sArr[s.charAt(i - maxLen) - ch]--;
//                same2 = areOnSameIndices(sArr, tArr);
//                if(same2){
//                    sArr[s.charAt(i) - ch]--;
//                    i--;
//                    maxLen--;
//                }
//                else {
//                    sArr[s.charAt(i - maxLen) - ch]++;
//                    maxLen++;
//                    same = true;
//                    res = s.substring(i-maxLen+1, i+1);
//                }
//            }
//        }
//        return res;
//    }
//
//    private boolean areOnSameIndices(int[] arr1, int[] arr2){
//        for (int i = 0; i < arr2.length; i++) {
//            if(arr2[i] > 0 && arr1[i] != arr2[i])
//                return false;
//        }
//        return true;
//    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int counter = t.length(), begin = 0, end = 0, minLength = Integer.MAX_VALUE, head = 0;

        while (end < s.length()) {
            char endChar = s.charAt(end);
            if (map.containsKey(endChar)) {
                if (map.get(endChar) > 0) {
                    counter--;
                }
                map.put(endChar, map.get(endChar) - 1);
            }
            end++;

            while (counter == 0) {
                if (end - begin < minLength) {
                    minLength = end - begin;
                    head = begin;
                }
                char beginChar = s.charAt(begin);
                if (map.containsKey(beginChar)) {
                    map.put(beginChar, map.get(beginChar) + 1);
                    if (map.get(beginChar) > 0) {
                        counter++;
                    }
                }
                begin++;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(head, head + minLength);
    }

    int wordMaxLength = 301;
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = words.length;
        Map<String, Integer> map = new HashMap<>(n << 2);
        boolean[] seen = new boolean[wordMaxLength];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            map.put(word, i);
            seen[word.length()] = true;
        }

        for (int i = 0; i < n; i++) {
            String word = words[i];
            int m = word.length();
            if (m == 0) continue;
            char[] cs = word.toCharArray();
            String re = new StringBuilder(word).reverse().toString();

            for (int j = 0; j < m - 1; j++) {
                if (seen[j + 1] && isPalindrome(cs, j + 1, m - 1)) {
                    String s = re.substring(m - j - 1, m);
                    Integer k = map.get(s);
                    if (k != null){
                        ans.add(List.of(i, k));
                    }
                }
                if (seen[m - j - 1] && isPalindrome(cs, 0, j)) {
                    String s = re.substring(0, m - j - 1);
                    Integer k = map.get(s);
                    if (k != null){
                        ans.add(List.of(k, i));
                    }
                }
            }

            if (isPalindrome(cs, 0, m - 1)) {
                Integer k = map.get("");
                if (k != null) {
                    ans.add(List.of(k, i));
                    ans.add(List.of(i, k));
                }
            } else {
                Integer k = map.get(re);
                if (k != null) {
                    ans.add(List.of(i, k));
                }
            }
        }
        return ans;
    }

    private boolean isPalindrome(char[] cs, int left, int right) {
        while (left < right) {
            if (cs[left] != cs[right])
                return false;
            left++;
            right--;
        }
        return true;
    }
}
