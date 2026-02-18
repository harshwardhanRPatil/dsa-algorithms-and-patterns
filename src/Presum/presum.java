package Presum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class presum {

  private int[][] prefix;

  static void main() {}

  public int pivotIndex(int[] nums) {
    int n = nums.length;
    int[] prefix_sum = new int[n + 1];
    prefix_sum[0] = 0;
    for (int i = 1; i < n + 1; i++) {
      prefix_sum[i] = prefix_sum[i - 1] + nums[i - 1];
    }
    //        0.-1,-2,-2,-1,0,0,
    //        0,1,2,3,4,5,6,7
    //                28-11=17-6
    for (int i = 1; i < n; i++) {
      if (prefix_sum[i - 1] == (prefix_sum[n] - prefix_sum[i - 1] - nums[i - 1])) {
        return i - 1;
      }
    }
    return -1;
  }

  public int findMaxLength(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    int sum = 0;
    int max_length = 0;
    for (int i = 0; i < nums.length; i++) {

      if (nums[i] == 0) {
        sum -= 1;
      } else {
        sum += 1;
      }
      if (map.containsKey(sum)) {
        max_length = Math.max(max_length, i - map.get(sum));
      } else {
        map.put(sum, i);
      }
    }

    return max_length;
  }

  public int subarraysDivByK(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int sum = 0;
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      int rem = sum % k;

      if (rem < 0) rem += k;
      if (map.containsKey(rem)) count += map.getOrDefault(rem, 0);

      map.put(rem, map.getOrDefault(rem, 0) + 1);
    }

    return count;
  }

  public int[] corpFlightBookings(int[][] bookings, int n) {
    //    Map<Integer, Integer> map = new HashMap<>();
    //
    //    int[] ans= new int[n];
    //
    //
    //    for(int i=0;i<bookings.length;i++){
    //
    //        int left=bookings[i][0];
    //        int right=bookings[i][1];
    //        int seat=bookings[i][2];
    //
    //
    //        for(int j=left;j<=right;j++){
    //            map.put(j,map.getOrDefault(j,0)+seat);
    //        }
    //      }
    //      for(int i = 1; i <= n; i++){
    //          ans[i - 1] = map.getOrDefault(i, 0);
    //      }
    //
    //      return ans;

    // alternative approch

    int[] diff = new int[n];
    for (int i = 0; i < bookings.length; i++) {

      int left = bookings[i][0] - 1;
      int right = bookings[i][1];
      int seat = bookings[i][2];
      // note -: what i do is that we put  a boundy we put 10 sett at left-1 and tell it goest tille
      // right  so wen we run the below array
      // we do that presum automatilcy update to 0
      // actual --: [10,0,-10,0,0]
      // after prefix --: [10,10,0,0,0]
      diff[left] += seat;

      if (right < n) {
        diff[right] -= seat;
      }
    }
    for (int i = 1; i < n; i++) {
      diff[i] += diff[i - 1];
    }
    return diff;
  }

  public String shiftingLetters(String s, int[] shifts) {
    int n = shifts.length;
    long[] prefixsum = new long[n + 1];

    prefixsum[n - 1] = shifts[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      prefixsum[i] = prefixsum[i + 1] + shifts[i];
    }

    char[] arr = s.toCharArray();
    for (int i = 0; i < n; i++) {

      long shift = prefixsum[i] % 26;

      // arr[i] -a will give the nuber  then we add the value if it go more then 26 we mod so we get
      // again value from a
      int newChar = (arr[i] - 'a' + (int) shift) % 26;

      arr[i] = (char) (newChar + 'a');
    }

    return new String(arr);
  }

  public String shiftingLettersII(String s, int[][] shifts) {
    int n = s.length();
    int[] diff = new int[n];
    for (int i = 0; i < shifts.length; i++) {

      int left = shifts[i][0];
      int right = shifts[i][1] + 1;
      int direction = shifts[i][2];

      if (direction == 0) {
        diff[left] -= 1;
        if (right < n) {
          diff[right] += 1;
        }
      } else {
        diff[left] += 1;
        if (right < n) {
          diff[right] -= 1;
        }
      }
    }
    for (int i = 1; i < n; i++) {
      diff[i] += diff[i - 1];
    }
    char[] arr = s.toCharArray();
    for (int i = 0; i < n; i++) {

      long shift = diff[i] % 26;

      // arr[i] -a will give the nuber  then we add the value if it go more then 26 we mod so we get
      // again value from a
      // need for the positive value
      int newChar = (arr[i] - 'a' + (int) shift + 26) % 26;

      arr[i] = (char) (newChar + 'a');
    }
    return new String(arr);
  }

  public int numSubarraysWithSum(int[] nums, int goal) {
      Map<Integer, Integer> map = new HashMap<>();
      map.put(0, 1);
    int count = 0;
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
// this part can be improve is we check the count insted of the loop
//      if (map.containsKey(sum - goal)) {
//
//        for (int startIndex : map.get(sum - goal)) {
//          count++;
//        }
//      }

        // code to improve
        if (map.containsKey(sum - goal)) {
            count += map.get(sum - goal);
        }
        map.put(sum,map.getOrDefault(sum,0)+1);
    }
    return count;
  }
    public int subarraySum(int[] arr, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        // VERY IMPORTANT
        map.put(0, new ArrayList<>(List.of(-1)));

        int sum = 0;
        int count=0;

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];

            if (map.containsKey(sum - k)) {
                for (int start : map.get(sum - k)) {
                    count++;
                }
            }

            map.computeIfAbsent(sum, x -> new ArrayList<>()).add(i);
        }

        return count;
    }
}
