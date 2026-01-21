package TwoPointers_SlidingWindow_MasterList;

import java.util.*;

class SlidingWindowBasicSolution {
  public int maxSubarraySum(int[] arr, int k) {
    int max_sum = Integer.MIN_VALUE;
    int i = 0;
    int temp = 0;
    for (int j = 0; j < arr.length; j++) {
      temp += arr[j];

      if (j - i + 1 > k) {
        temp -= arr[i];
        i++;
      }

      if (j - i + 1 == k) {
        max_sum = Math.max(temp, max_sum);
      }
    }
    return max_sum;
  }

  public double findMaxAverage(int[] arr, int k) {
    Double max_sum = Double.NEGATIVE_INFINITY;
    if (arr.length == 1) return arr[0];
    int i = 0;
    Double temp = 0.0;
    for (int j = 0; j < arr.length; j++) {
      temp += arr[j];

      if (j - i + 1 > k) {
        temp -= arr[i];
        i++;
      }

      if (j - i + 1 == k) {
        max_sum = Math.max((double) temp / k, max_sum);
      }
    }
    return max_sum;
  }

  public int[] decrypt(int[] code, int k) {
    int size = code.length;
    int[] ans = new int[size];

    if (k == 0) {
      Arrays.fill(ans, 0);
      return ans;
    }

    if (k > 0) {
      int j = 0;
      int temp = 0;
      int i = 0;
      while (i < code.length) {
        temp += code[j % size];

        if (j - i + 1 > k) {
          temp -= code[i];
          i++;
        }
        if (j - i + 1 == k) {
          ans[(i - 1 + size) % size] = temp;
        }
        j++;
      }
    }

    if (k < 0) {
      int j = size - 1;
      int temp = 0;
      int i = size - 1;
      int storage = 0;
      k = Math.abs(k);
      while (j > -k) {
        temp += code[(j - 1 + size) % size];

        if (i - j + 1 > k) {
          temp -= code[(i - 1 + size) % size];
          i--;
        }
        if (i - j + 1 == k) {
          ans[(storage - 1 + size) % size] = temp;
          storage--;
        }
        j--;
      }
    }
    return ans;
  }

  static List<Integer> firstNegInt(int arr[], int k) {
    // write code here
    // write code here
    List<Integer> ans = new ArrayList<>();
    int i = 0;
    Queue<Integer> queue = new LinkedList<>();
    for (int j = 0; j < arr.length; j++) {
      if (arr[j] < 0) {
        queue.add(j);
      }
      if (j - i + 1 > k) {
        if (queue.size() > 0 && i == queue.peek()) {
          queue.poll();
        }
        i++;
      }

      if (j - i + 1 == k) {
        if (queue.size() > 0) {
          int temp = queue.peek();
          ans.add(arr[temp]);
        } else {
          ans.add(0);
        }
      }
    }
    return ans;
  }

  public int lengthOfLongestSubstring(String s) {
    if (s.length() == 0) return 0;
    int ans = Integer.MIN_VALUE;
    Map<Character, Integer> placeholder = new HashMap<Character, Integer>();

    int i = 0;
    int j = 0;
    while (j < s.length()) {
      char ch = s.charAt(j);
      if (placeholder.containsKey(ch)) {
        while (placeholder.get(ch) > 0) {
          char temp = s.charAt(i);
          placeholder.put(temp, placeholder.get(temp) - 1);
          i++;
        }
      }
      // System.out.println(placeholder);
      placeholder.put(ch, 1);
      ans = Math.max(ans, j - i + 1);
      j++;
    }
    return ans;
  }

  public List<Integer> findAnagrams(String s, String p) {

    int k = p.length();
    int size = s.length();
    List<Integer> ans = new ArrayList<>();
    if (size == 0 || size < k) return ans;

    Map<Character, Integer> placeholder = new HashMap<Character, Integer>();

    for (int i = 0; i < k; i++) {
      char ch = s.charAt(i);

      if (placeholder.containsKey(ch)) {
        placeholder.put(ch, placeholder.get(ch) + 1);
      } else {
        placeholder.put(ch, 1);
      }
    }

    int i = 0;
    int j = 0;
    while (j < size) {
      char ch = s.charAt(j);
      if (placeholder.containsKey(ch)) {
        placeholder.put(ch, placeholder.get(ch) - 1);

        if (placeholder.get(ch) < 0) {
          while (placeholder.get(ch) < 0) {
            char temp = s.charAt(i);
            placeholder.put(temp, placeholder.get(temp) + 1);
            i++;
          }
        }
        if (j - i + 1 == k) {
          ans.add(i);
        }
      } else {
        while (i <= j) {
          char temp = s.charAt(i);
          placeholder.put(temp, placeholder.get(temp) + 1);
          i++;
        }
        i++;
      }
      // System.out.println(placeholder);

      j++;
    }
    return ans;
  }

  // To count subarrays with exactly k odd numbers:
  // Counting "exactly k" directly is hard, but counting "at most k" is easy using sliding window.
  // Subarrays with exactly k odds = atMost(k) - atMost(k - 1).
  // This works because the condition (oddCount ≤ k) is monotonic.

  public int numberOfSubarrays(int[] nums, int k) {
    return atMost(nums, k) - atMost(nums, k - 1);
  }

  public int atMost(int[] nums, int k) {
    int i = 0;
    int count = 0;
    int oddCount = 0;

    for (int j = 0; j < nums.length; j++) {
      if ((nums[j] & 1) == 1) {
        oddCount++;
      }

      while (oddCount > k) {
        if ((nums[i] & 1) == 1) {
          oddCount--;
        }
        i++;
      }

      count += (j - i + 1);
    }
    return count;
  }

  public boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length()) return false;

    Map<Character, Integer> freq = new HashMap<>();
    for (char c : s1.toCharArray()) {
      freq.put(c, freq.getOrDefault(c, 0) + 1);
    }

    int i = 0, count = freq.size();

    for (int j = 0; j < s2.length(); j++) {
      char ch = s2.charAt(j);

      if (freq.containsKey(ch)) {
        freq.put(ch, freq.get(ch) - 1);
        if (freq.get(ch) == 0) count--;
      }

      if (j - i + 1 > s1.length()) {
        char left = s2.charAt(i++);
        if (freq.containsKey(left)) {
          if (freq.get(left) == 0) count++;
          freq.put(left, freq.get(left) + 1);
        }
      }

      if (count == 0) return true;
    }
    return false;
  }

  // a better approch is to use the array and not a hashmap
  // don;t change the max_val bas onthe decress we will just min the windos size

  public int characterReplacement(String s, int k) {

    int[] freq = new int[26];
    int size = s.length();
    if (size == 0) return 0;

    int maxlen = 0;
    int max_val = 0;

    int i = 0;
    for (int j = 0; j < size; j++) {
      int temp = s.charAt(j) - 'A';

      freq[temp]++;

      max_val = Math.max(max_val, freq[temp]);

      while ((j - i + 1 - max_val) > k) {
        char sr = s.charAt(i);
        freq[s.charAt(i) - 'A']--;
        i++;
      }
      maxlen = Math.max(maxlen, j - i + 1);
    }
    return maxlen;
  }

  public int[] maxSlidingWindow(int[] arr, int k) {
    int size = arr.length;

    Deque<Integer> queue = new ArrayDeque<>();

    int[] ans = new int[size - k + 1];
    int i = 0;

    for (int j = 0; j < size; j++) {
      if (!queue.isEmpty() && arr[queue.peekLast()] > arr[j]) {
        queue.add(j);
      } else {
        while (!queue.isEmpty() && arr[queue.peekLast()] < arr[j]) {
          queue.pollLast();
        }
        queue.add(j);
      }
      while (j - i + 1 > k) {
        if (queue.size() > 0 && i == queue.peekFirst()) {
          queue.pollFirst();
        }
        i++;
      }

      if (j - i + 1 == k) {
        int temp = queue.peekFirst();
        ans[i] = (arr[temp]);
      }
      // System.out.println(queue);
    }
    return ans;
  }

  public int numSubarrayProductLessThanK(int[] nums, int k) {
    if (k <= 1) return 0;
    int count = 0;
    int prod = 1;
    int i = 0;

    for (int j = 0; j < nums.length; j++) {

      prod *= nums[j];
      while (prod >= k) {
        prod /= nums[i];
        i++;
      }
      count += j - i + 1;
    }
    return count;
  }

  public int totalFruit(int[] fruits) {
    if (fruits.length <= 2) return fruits.length;

    int count = 0;
    Map<Integer, Integer> holder = new HashMap<>();
    int i = 0;
    for (int j = 0; j < fruits.length; j++) {
      int fruit = fruits[j];
      holder.put(fruit, holder.getOrDefault(fruit, 0) + 1);

      while (holder.size() > 2) {
        int temp = fruits[i];
        holder.put(temp, holder.get(temp) - 1);

        if (holder.get(temp) == 0) {
          holder.remove(temp);
        }
        i++;
      }
      count = Math.max(count, j - i + 1);
    }
    return count;
  }

  // Count subarrays with sum exactly = goal using:
  // exactly(goal) = atMost(goal) - atMost(goal - 1)
  // Works because array contains only 0 and 1

  public int numSubarraysWithSum(int[] nums, int k) {
    return atMostGoal(nums, k) - atMostGoal(nums, k - 1);
  }

  private int atMostGoal(int[] nums, int k) {
    if (k < 0) return 0;

    int i = 0, sum = 0, count = 0;

    for (int j = 0; j < nums.length; j++) {
      sum += nums[j];

      while (sum > k) {
        sum -= nums[i];
        i++;
      }

      count += (j - i + 1);
    }
    return count;
  }

  public int numSubarrayBoundedMax(int[] nums, int left, int right) {
    return countAtMost(nums, right) - countAtMost(nums, left - 1);
  }

  private int countAtMost(int[] nums, int bound) {
    int count = 0;
    int current = 0;

    for (int num : nums) {
      if (num <= bound) {
        current++;
      } else {
        current = 0;
      }
      count += current;
    }
    return count;
  }
}

public class SlidingWindowBasic {
  public static void main(String args[]) {}
}
