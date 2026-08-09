package Presum;

import java.util.*;

public class presum {

  private int[] bit;
  private int[] nums;
  private int n;
  private int[] prefixsum;

  private int[][] prefix;

  static void main() {}

  public int pivotIndex(int[] nums) {

    int sum = Arrays.stream(nums).sum();
    int left = 0;

    for (int i = 0; i < nums.length; i++) {

      int right = sum - left - nums[i];
      if (left == right) {
        return i;
      }
      left += nums[i];
    }

    return -1;
  }

  public int findMaxLength(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    int result = Integer.MIN_VALUE;

    int zero = 0;
    int one = 0;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) zero++;
      if (nums[i] == 1) one++;

      int diff = zero - one;
      if (map.containsKey(diff)) {
        result = Math.max(result, i - map.get(diff));
      } else {
        map.put(diff, i);
      }
    }

    return result != Integer.MIN_VALUE ? result : 0;
  }

  public int[] productExceptSelf(int[] nums) {
    int size = nums.length;

    int[] result = new int[size];
    int[] prefix = new int[size];
    int[] suffix = new int[size];

    prefix[0] = 1;
    for (int i = 1; i < size; i++) {
      prefix[i] = prefix[i - 1] * nums[i - 1];
    }

    suffix[size - 1] = 1;
    for (int i = size - 2; i >= 0; i--) {
      suffix[i] = suffix[i + 1] * nums[i + 1];
    }

    for (int i = 0; i < size; i++) {
      result[i] = prefix[i] * suffix[i];
    }

    return result;
  }

  public int subarraysDivByK(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int result = 0;

    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      int remainder = Math.abs(sum) % k;

      if (map.containsKey(remainder)) {
        result += map.get(remainder);
      }
      map.put(remainder, map.getOrDefault(remainder, 0) + 1);
    }

    return result;
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
    long[] suffix = new long[n + 1];

    suffix[n] = 0;
    for (int i = n - 1; i >= 0; i--) {
      suffix[i] = shifts[i] + suffix[i + 1];
    }

    char[] arr = s.toCharArray();
    for (int i = 0; i < n; i++) {
      long shift = suffix[i] % 26;

      int newChar = (arr[i] - 'a' + (int) shift) % 26;

      arr[i] = (char) (newChar - 'a');
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

  public boolean carPooling(int[][] trips, int capacity) {
    int maxDistance = 0;

    for (int[] trip : trips) {
      maxDistance = Math.max(maxDistance, trip[2]);
    }
    int[] diff = new int[maxDistance + 2];
    for (int i = 0; i < trips.length; i++) {

      int left = trips[i][1];
      // big miss i have that i thing right is the end but right -1 is the end so we use right only no right +1
      int right = trips[i][2] ;
      int direction = trips[i][0];

      diff[left] += direction;
      if (right < maxDistance+1) {
        diff[right] -= direction;
      }
    }
    System.out.println(diff.toString());
    if (diff[0] > capacity) return false;

    for (int i = 2; i < maxDistance + 1; i++) {
      diff[i] += diff[i - 1];
      if (diff[i] > capacity) return false;
    }
    return true;
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
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    return count;
  }

  // https://leetcode.com/problems/subarray-sum-equals-k/
  /*
     basic formula that need is we need to have the formaula that we use in basi alebra
     a+k=sum
     a=sum-k (so we have a prefix or 1...n we consider it as sum and the check if sum -k =what ever value left is have we encount back the)
     eg -:1 ,2,3
     0-1 (zero encounter 1 time)
     sum=0 , k=3
     and we start now
     sum =1 (sum-k)=-2 did we see this no
     0-1,1-1
     sum =3 (sum-k did we see 0 back then yes so we can add in result )
     0-1,1-1,3-1
     sum =6(sum-k did we see 3 back then yes so we hava k present here and we can add frequence)
     our hashmap have remainder and frequence
  */
  public int subarraySum(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();

    // VERY IMPORTANT
    map.put(0, 1);

    int sum = 0;
    int count = 0;

    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      int key = sum - k;
      if (map.containsKey(key)) {
        count += map.get(key);
      }
      map.putIfAbsent(sum, map.getOrDefault(sum, 0) + 1);
    }
    return count;
  }

  class Fenwick {
    int[] tree;
    int size;

    public Fenwick(int size) {
      this.tree = new int[size + 1];
      this.size = size + 1;
    }

    void update(int index, int value) {

      while (index < size) {
        tree[index] += value;
        index += index & -index;
      }
    }

    public int query(int index) {
      int sum = 0;
      while (index > 0) {
        sum += tree[index];
        index -= index & -index;
      }
      return sum;
    }
  }

  public int countRangeSum(int[] nums, int lower, int upper) {
    long prefix = 0;
    List<Long> allValues = new ArrayList<>();
    allValues.add(0L);

    for (int num : nums) {
      prefix += num;
      allValues.add(prefix);
      allValues.add(prefix - lower);
      allValues.add(prefix - upper);
    }

    Collections.sort(allValues);
    Map<Long, Integer> map = new HashMap<>();

    int id = 1;
    for (Long i : allValues) {
      if (!map.containsKey(i)) {
        map.put(i, id++);
      }
    }
    Fenwick fenwick = new Fenwick(map.size());
    fenwick.update(map.get(0L), 1);

    int count = 0;
    long prefixSum = 0;

    for (int i : nums) {
      prefixSum += i;

      long left = prefixSum - upper;
      long right = prefixSum - lower;

      count += fenwick.query(map.get(right)) - fenwick.query(map.get(left) - 1);

      fenwick.update(map.get(prefixSum), 1);
    }
    return count;
  }

  // nove solution
  public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
    Arrays.sort(tiles, (a, b) -> Integer.compare(a[0], b[0]));
    int n = tiles.length;
    int max = 0;
    for (int i = 0; i < n; i++) {
      int carpetStart = tiles[i][0];
      int carpetEnd = carpetStart + carpetLen - 1;
      int covered = 0;

      for (int j = i; j < n; j++) {

        int tileStart = tiles[j][0];
        int tileEnd = tiles[j][1];
        if (tileStart > carpetEnd) break;
        if (tileEnd <= carpetEnd) {
          covered += tileEnd - tileStart + 1;
        } else {
          covered += carpetEnd - tileStart + 1;
        }
      }
      max = Math.max(max, covered);
    }
    return max;
  }

  // prefix sum  take help from chatgpt
  public int maximumWhiteTilesII(int[][] tiles, int carpetLen) {
    Arrays.sort(tiles, (a, b) -> Integer.compare(a[0], b[0]));
    int max_length = 0;
    int left = 0;
    int carpet_cover = 0;
    int n = tiles.length;

    for (int i = 0; i < n; i++) {
      carpet_cover += tiles[i][1] - tiles[i][0] + 1;

      while (tiles[i][1] - tiles[left][0] + 1 > carpetLen) {
        int exceed = tiles[i][1] - tiles[left][0] + 1 - carpetLen;
        int leftTileLength = tiles[left][1] - tiles[left][0] + 1;

        if (exceed >= leftTileLength) {
          carpet_cover -= leftTileLength;
          left++;
        } else {
          break;
        }
      }
      int totalSpan = tiles[i][1] - tiles[left][0] + 1;
      int partial = Math.max(0, totalSpan - carpetLen);

      max_length = Math.max(max_length, carpet_cover - partial);
    }
    return max_length;
  }

  public void NumArray(int[] nums) {
    this.n = nums.length;
    this.nums = new int[n];
    this.bit = new int[n + 1]; // 1-based indexing

    for (int i = 0; i < n; i++) {
      update(i, nums[i]);
    }
  }

  public void update(int index, int val) {
    // in normal code we do the add so we can do the arr[i]+val but as we are updateing we nned to
    // find the diff first
    int diff = val - nums[index];
    nums[index] = val;

    int i = index + 1;
    while (i <= n) {
      bit[i] += diff;
      i += i & -i;
    }
  }

  private int query(int index) {
    int sum = 0;
    int i = index + 1;

    while (i > 0) {
      sum += bit[i];
      i -= i & -i; // move to parent
    }
    return sum;
  }

  public int sumRange(int left, int right) {
    return query(right) - query(left - 1);
  }

  public void NumArrayII(int[] nums) {
    int n = nums.length;
    prefixsum = new int[n + 1];

    for (int i = 0; i < n; i++) {
      prefixsum[i + 1] = prefixsum[i] + nums[i];
    }
  }

  public int sumRangeII(int left, int right) {
    return prefixsum[right + 1] - prefixsum[left];
  }
}
