package Binary_Search.Range_Min_Max;

import java.util.Arrays;

public class Range_Min_Max {
  static void main() {}

  public int minimizedMaximum(int k, int[] quantities) {
    int left = 1;
    int right = Arrays.stream(quantities).max().getAsInt();
    int ans = 0;
    int n = quantities.length;
    if (k > right) return -1;
    while (left <= right) {

      int mid = left + (right - left) / 2;
      System.out.println("left ::" + left + " right ::" + right + "mid::" + mid);
      if (isvalidCandy(quantities, mid, k, n)) {
        ans = mid;
        right = mid - 1;
        System.out.println("mid ::" + mid);
      } else {
        left = mid + 1;
      }
    }
    return ans;
  }

  public boolean isvalidCandy(int[] nums, int candy, int k, int n) {
    int sum = 0;
    int i = 0;
    int count = 0;
    while (i < n) {
      count += (int) Math.ceil((double) nums[i] / candy);
      i++;
      //  System.out.println("candy ::"+count);
    }
    //  System.out.println("store ::"+k);
    return count <= k;
  }

  public int minEatingSpeed(int[] piles, int h) {

    int left = 1;
    int right = Arrays.stream(piles).max().getAsInt();
    int n = piles.length;
    int ans = 0;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (isvalidbanana(piles, mid, h, n)) {
        ans = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return ans;
  }

  public boolean isvalidbanana(int[] nums, int banana, int h, int n) {
    long count = 0;
    int i = 0;
    while (i < n) {
      count += (nums[i] + banana - 1) / banana;
      i++;
      if (count > h) return false;
    }
    return count <= h;
  }

  public int shipWithinDays(int[] weights, int days) {
    int left = 0;
    int right = Arrays.stream(weights).sum();
    int n = weights.length;
    int ans = 0;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (isValiadShip(weights, mid, days, n)) {
        ans = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return ans;
  }

  public boolean isValiadShip(int[] nums, int weight, int k, int n) {
    int count = 1;
    int i = 0;
    int sum = 0;
    while (i < n) {

      if (sum + nums[i] > weight) {
        sum = 0;
        count++;
      }
      sum += nums[i];
      if (count > k) return false;
    }
    return count <= k;
  }

  public int findPages(int[] arr, int k) {
    int left = 0;
    int right = Arrays.stream(arr).sum();
    int ans = 0;
    int n = arr.length;
    if (k > n) return -1;
    while (left <= right) {

      int mid = left + (right - left) / 2;
      if (isvalidPage(arr, mid, k, n)) {
        ans = mid;
        right = mid - 1;
        // System.out.println("mid ::"+mid);
      } else {
        left = mid + 1;
      }
      // System.out.println("left ::"+left +" right ::"+right);
    }
    return ans;
  }

  public boolean isvalidPage(int[] nums, int pageCount, int k, int n) {
    int sum = 0;
    int count = 1;
    int i = 0;
    while (i < n) {
      sum += nums[i];

      if (sum > pageCount) {
        sum = 0;
        count++;
      } else {
        i++;
      }
      if (count > k) return false;
    }
    return true;
  }

  public int minDays(int[] bloomDay, int m, int k) {

    int left = Arrays.stream(bloomDay).min().getAsInt();
    int right = Arrays.stream(bloomDay).max().getAsInt();
    int n = bloomDay.length;

    if ((long) m * k > n) return -1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (isValidBooke(bloomDay, mid, k, m)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  public boolean isValidBooke(int[] nums, int flower, int k, int m) {
    int count = 0;

    int bouquet = 0;
    for (int i : nums) {
      if (nums[i] <= flower) {
        count++;
        if (count == k) {
          bouquet++;
          count = 0;
          if (bouquet == m) {
            return true;
          }
        }
      } else {
        count = 0;
      }
    }
    return bouquet >= m;
  }

  public int smallestDivisor(int[] nums, int threshold) {
    int left = 1;
    int right = Arrays.stream(nums).max().getAsInt();

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (isValidthreshold(nums, mid, threshold)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  public boolean isValidthreshold(int[] nums, int mid, int threshold) {
    int count = 0;
    for (int i : nums) {
      count += (int) Math.ceil((double) i / mid);

      if (count > threshold) return false;
    }
    return count <= threshold;
  }

  public int aggressiveCows(int[] stalls, int k) {
    // code here

    Arrays.sort(stalls);
    int size = stalls.length;

    int left = 1;
    int right = stalls[size - 1] - stalls[0];

    while (left <= right) {

      int mid = left + (right - left) / 2;

      if (isValidCows(stalls, k, mid, size)) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return right;
  }

  public boolean isValidCows(int[] nums, int k, int midDiffer, int size) {

    int count = 1;
    int lastCow = nums[0];
    for (int i = 1; i < size; i++) {
      if (nums[i] - lastCow >= midDiffer) {
        count++;
        lastCow = nums[i];
      }
      if (count >= k) return true;
    }
    return false;
  }

  public int maxDistance(int[] position, int m) {

    Arrays.sort(position);
    int size = position.length;

    int left = 1;
    int right = position[size - 1] - position[0];

    while (left <= right) {

      int mid = left + (right - left) / 2;

      if (isValidCows(position, m, mid, size)) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return right;
  }

  public boolean isValidBall(int[] nums, int k, int midDiffer, int size) {

    int count = 1;
    int lastBall = nums[0];
    for (int i = 1; i < size; i++) {
      if (nums[i] - lastBall >= midDiffer) {
        count++;
        lastBall = nums[i];
      }
      if (count >= k) return true;
    }
    return false;
  }

  public int minSpeedOnTime(int[] dist, double hour) {
    if (hour <= dist.length - 1) return -1;
    int left = 1;
    int right = 1000000; // as we have 10^7 so we use the hard code;
    while (left < right) {
      int mid = left + (right - left) / 2;

      if (isValidSpeed(dist, hour, mid)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  public boolean isValidSpeed(int[] dist, double hour, int speed) {
    double time = 0;

    for (int i = 0; i < dist.length; i++) {

      double t = (double) dist[i] / speed;
      // as we need to wait foe the tain to come incase we reach
      if (i != dist.length - 1) time += Math.ceil(t);
      else
        // but for the last tain there ni wait we can go and take the value
        time += t;

      if (time > hour) return false;
    }

    return true;
  }

  public int minimizeMax(int[] nums, int p) {

    return 0;
  }

  public double minMaxDist(int[] stations, int K) {
    // code here
    int n = stations.length;
    int left = 1;
    int right = stations[n - 1] - stations[0];

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (isValidStation(stations, K, mid, n)) {
        right = mid;
      } else {
        left = mid - 1;
      }
    }
    return (double) left;
  }

  // not wpork as we need asn in descimal
  public boolean isValidStation(int[] nums, int k, int minDistance, int size) {

    int count = 0;
    int lastStation = nums[0];
    for (int i = 1; i < size; i++) {
      int distance = nums[i] - lastStation;
      if (distance > minDistance) {
        count += distance / minDistance;
        if (count > k) return false;
      }
    }
    return count <= k;
  }

  public boolean canPlace(int[] stations, int k, double dist) {

    int count = 0;

    for (int i = 1; i < stations.length; i++) {

      double gap = stations[i] - stations[i - 1];

      count += (int) (gap / dist);

      if (gap % dist == 0) count--;

      if (count > k) return false;
    }

    return true;
  }

  public int maximumCandies(int[] candies, long k) {
      long total = 0;
      for (int c : candies) total += c;

      if (total < k) return 0;
    int left = 1;
    int right = Arrays.stream(candies).max().getAsInt();
    // if(candies.length<k) return 0;
    int ans = 0;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (isValidCandy(candies, k, mid)) {
        ans = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return ans;
  }

  public boolean isValidCandy(int[] nums, long k, int candyCount) {
    long count = 0;

    for (int i : nums) {
      count += i / candyCount;
      if (count == k) return true;
    }
    return count >= k;
  }

   public int minTime(int[] arr, int k) {
        int left=Arrays.stream(arr).max().getAsInt();
        int right= Arrays.stream(arr).sum();

        while (left<right){
            int mid=left+(right-left)/2;

            if(isValidPaint(arr,k,mid)){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
   }

   public boolean isValidPaint(int[] nums,int k,int paintTime){
      int currLeng=0;

      int paint=1;
      for(int i:nums){
          if(i>paintTime) return false;

          if(currLeng+i>paintTime){
              paint++;
              currLeng=i;
          }else{
              currLeng+=i;
          }
      }
      return paint<=k;

   }

    public int splitArray(int[] nums, int k) {
        int left=Arrays.stream(nums).max().getAsInt();
        int right=Arrays.stream(nums).sum();

        while(left<right){
            int mid=left+(right-left)/2;

            if(isValidSplit(nums,k,mid)){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }

    public boolean isValidSplit(int[] nums, int k, int split){

      int currentSum=0;
      int partion=1;
      for(int i :nums){
            if(i>split) return false;

            if(currentSum+i>split){
              partion++;
              currentSum=i;
            }else{
                currentSum+=i;
            }
      }
      return partion<=k;
    }
}
