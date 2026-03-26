package HeapPriorityQueue.Basic;

import Linked_List.ListNode;

import java.util.*;

class Pair {
  List<Integer> pair = new ArrayList<>();
  int sum;

  public Pair(int a, int b, int sum) {
    this.pair.add(a);
    this.pair.add(b);
    this.sum = sum;
  }
}

class PairDiff {
  String key;
  int value;

  PairDiff(String key, int value) {
    this.key = key;
    this.value = value;
  }
}

public class HeapBasic {

  int K;
  PriorityQueue<Integer> p;

  PriorityQueue<int[]> maxFreq;
  Map<Integer, Integer> freq;
  int time;

  PriorityQueue<Integer> small; // maxHeap
  PriorityQueue<Integer> large; // minHeap
  Map<Integer, Integer> delayed;

  int smallSize, largeSize;
  int k;

  PriorityQueue<Integer> maxHeap;
  PriorityQueue<Integer> minHeap;

  static void main() {}

  public void KthLargest(int k, int[] nums) {
    this.K = k;
    p = new PriorityQueue<>();
    for (int i : nums) {
      p.offer(i);
      if (p.size() > k) p.poll();
    }
  }

  public int add(int val) {
    p.offer(val);
    if (p.size() > K) p.poll();
    return p.peek();
  }

  public int lastStoneWeight(int[] stones) {
    PriorityQueue<Integer> stone = new PriorityQueue<>(Collections.reverseOrder());

    for (int i : stones) {
      stone.add(i);
    }
    while (stone.size() > 1) {
      int a = stone.poll();
      int b = stone.poll();
      if (a != b) {
        stone.add(a - b);
      }
    }
    return stone.size() != 0 ? stone.poll() : 0;
  }

  public int[] topKFrequent(int[] nums, int k) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

    Map<Integer, Integer> map = new HashMap<>();

    for (int i : nums) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      pq.offer(new int[] {entry.getKey(), entry.getValue()});
      if (pq.size() > k) {
        pq.poll();
      }
    }

    int[] result = new int[k];

    int index = 0;

    while (!pq.isEmpty()) {
      result[index++] = pq.poll()[0];
    }
    return result;
  }

  public int[][] kClosest(int[][] points, int k) {
    //    PriorityQueue<int[]> pq =
    //        new PriorityQueue<>((a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] *
    // a[1]));
    //
    //    for (int[] i : points) {
    //      pq.offer(i);
    //      if (pq.size() > k) pq.poll();
    //    }
    //
    //    int[][] result = new int[k][2];
    //
    //    int index = 0;
    //
    //    while (!pq.isEmpty()) {
    //      result[index++] = pq.poll();
    //    }
    //    return result;

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

    for (int[] i : points) {
      pq.offer(new int[] {i[0] * i[0] + i[1] * i[1], i[0], i[1]});
      if (pq.size() > k) pq.poll();
    }
    int[][] result = new int[k][2];
    int index = 0;

    while (!pq.isEmpty()) {
      int[] ans = pq.poll();
      result[index++] = new int[] {ans[1], ans[2]};
    }
    return result;
  }

  public String[] findRelativeRanks(int[] score) {
    String[] result = new String[score.length];

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

    for (int i = 0; i < score.length; i++) {
      pq.add(new int[] {score[i], i});
    }

    int rank = 1;
    while (!pq.isEmpty()) {

      int[] current = pq.poll();
      int index = current[1];
      System.out.println(" index:: " + index + " value ::" + current[0]);
      if (rank == 1) result[index] = "Gold Medal";
      else if (rank == 2) result[index] = "Silver Medal";
      else if (rank == 3) result[index] = "Bronze Medal";
      else {
        result[index] = String.valueOf(current[0]);
      }
      rank++;
    }

    return result;
  }

  public static int minCost(int[] arr) {
    // code here
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    if (arr.length <= 1) return arr[0];
    int count = 0;
    for (int i : arr) {
      pq.add(i);
    }
    while (!pq.isEmpty()) {
      int a = pq.poll();
      int b = pq.poll();

      count += a + b;
      pq.add(a + b);
    }
    return count;
  }

  public String frequencySort(String s) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

    Map<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      char charater = s.charAt(i);

      map.put(charater, map.getOrDefault(charater, 0) + 1);
    }

    for (Map.Entry<Character, Integer> i : map.entrySet()) {
      pq.offer(new int[] {i.getKey(), i.getValue()});
    }
    StringBuilder sb = new StringBuilder();

    while (!pq.isEmpty()) {
      int[] dataMapper = pq.poll();
      char ch = (char) dataMapper[0];
      int freq = dataMapper[1];

      for (int i = 0; i < freq; i++) {
        sb.append(ch);
      }
    }
    return sb.toString();
  }

  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i : nums) {
      pq.offer(i);
      if (pq.size() > k) pq.poll();
    }
    return pq.peek();
  }

  // Note we know the elemet  is only shift k distance so we can reach k position and then take the
  // elemt and put at tindex
  public void nearlySorted(int[] arr, int k) {
    // code here
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int index = 0;
    for (int i : arr) {
      pq.offer(i);
      if (pq.size() > k) {
        arr[index] = pq.poll();
        index++;
      }
    }
    while (!pq.isEmpty()) {
      arr[index] = pq.poll();
      index++;
    }
  }

  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

    PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> Integer.compare(y.sum, x.sum));

    for (int i : nums1) {
      for (int j : nums2) {
        int sum = i + j;

        // FIX 3: Early exit to prevent Time Limit Exceeded.
        // If our queue is full and the current sum is greater than the max in our queue,
        // we break because all following elements in nums2 will also be greater.
        if (pq.size() >= k && sum > pq.peek().sum) {
          break;
        }
        pq.offer(new Pair(i, j, i + j));
        if (pq.size() > k) pq.poll();
      }
    }
    List<List<Integer>> result = new ArrayList<>();

    while (!pq.isEmpty()) {
      Pair pair = pq.poll();
      result.add(pair.pair);
    }
    return result;
  }

  public int kthSmallest(int[][] matrix, int k) {

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        pq.offer(matrix[i][j]);
        if (pq.size() > k) pq.poll();
      }
    }
    return pq.poll();
  }

  public int leastInterval(char[] tasks, int n) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    Map<Character, Integer> map = new HashMap<>();
    for (char i : tasks) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    for (Map.Entry<Character, Integer> element : map.entrySet()) {
      pq.add(new int[] {element.getKey(), element.getValue()});
    }

    // ccopy form chat gpt
    Queue<int[]> waitingQue = new LinkedList<>();

    int index = 0;

    while (!pq.isEmpty() || !waitingQue.isEmpty()) {

      if (!waitingQue.isEmpty() && waitingQue.peek()[2] <= index) {
        int[] readyTask = waitingQue.poll();
        pq.offer(new int[] {readyTask[0], readyTask[1]});
      }

      if (!pq.isEmpty()) {
        int[] task = pq.poll();
        int myChar = task[0];
        int freq = task[1];

        freq--;
        if (freq > 0) {
          // It can run again at the current time + n + 1
          waitingQue.offer(new int[] {myChar, freq, index + n + 1});
        }
      }
      index++;
    }
    return index;
  }

  public long taskSchedulerII(int[] tasks, int n) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    Map<Integer, Integer> map = new HashMap<>();
    for (int i : tasks) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    for (Map.Entry<Integer, Integer> element : map.entrySet()) {
      pq.add(new int[] {element.getKey(), element.getValue()});
    }

    // ccopy form chat gpt
    Queue<int[]> waitingQue = new LinkedList<>();

    int index = 0;

    while (!pq.isEmpty() || !waitingQue.isEmpty()) {

      if (!waitingQue.isEmpty() && waitingQue.peek()[2] <= index) {
        int[] readyTask = waitingQue.poll();
        pq.offer(new int[] {readyTask[0], readyTask[1]});
      }

      if (!pq.isEmpty()) {
        int[] task = pq.poll();
        int myChar = task[0];
        int freq = task[1];

        freq--;
        if (freq > 0) {
          // It can run again at the current time + n + 1
          waitingQue.offer(new int[] {myChar, freq, index + n + 1});
        }
      }
      index++;
    }
    return index;
  }

  public String reorganizeString(String s) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

    Map<Character, Integer> map = new HashMap<>();

    Queue<int[]> waitingQue = new LinkedList<>();

    int boundry = s.length();

    for (int i = 0; i < boundry; i++) {
      char word = s.charAt(i);
      map.put(word, map.getOrDefault(word, 0) + 1);
    }
    System.out.println(map.toString());

    for (Map.Entry<Character, Integer> letter : map.entrySet()) {
      pq.offer(new int[] {letter.getKey(), letter.getValue()});
    }

    StringBuilder stringBuilder = new StringBuilder();
    int index = 0;
    while (!pq.isEmpty() || !waitingQue.isEmpty()) {

      if (!waitingQue.isEmpty() && waitingQue.peek()[2] <= index) {
        int[] readyTask = waitingQue.poll();
        pq.offer(new int[] {readyTask[0], readyTask[1]});
      }

      if (!pq.isEmpty()) {
        int[] task = pq.poll();
        char myChar = (char) task[0];
        int freq = task[1];
        stringBuilder.append(myChar);
        freq--;
        if (freq > 0) {
          if (index + 1 + 1 < boundry) {
            waitingQue.offer(new int[] {myChar, freq, index + 1 + 1});
          } else {
            return "";
          }
        }
      }
      index++;
    }
    return stringBuilder.toString();
  }

  public void MedianFinder() {
    maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    if (maxHeap.size() == minHeap.size()) {
      maxHeap.add(num);
    } else if (minHeap.size() > maxHeap.size()) {
      if (minHeap.peek() > num) {
        maxHeap.add(num);
      } else {
        int temp = minHeap.poll();
        minHeap.add(num);
        maxHeap.add(temp);
      }
    } else {
      if (maxHeap.peek() < num) {
        minHeap.add(num);
      } else {
        int temp = maxHeap.poll();
        minHeap.add(num);
        maxHeap.add(temp);
      }
    }
  }

  public double findMedian() {
    if (maxHeap.size() > minHeap.size()) {
      return maxHeap.peek();
    }

    return (maxHeap.peek() + minHeap.peek()) / 2.0;
  }

  public int furthestBuilding(int[] heights, int bricks, int ladders) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int n = heights.length;
    for (int i = 0; i < n - 1; i++) {
      int a = heights[i];
      int b = heights[i + 1];
      if (b > a) {
        pq.add(b - a);

        /// we alwas consider that we can use the ladder for the cline and no needd break but if we
        // gi over the ladder ther we check if we have the break to pu for the less differ
        // if yese rwe can pass the value
        if (pq.size() > ladders) {
          bricks -= pq.poll();
        }

        if (bricks < 0) {
          return i;
        }
      }
    }
    return n - 1;
  }

  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    PriorityQueue<Integer> pq =
        new PriorityQueue<>(
            (a, b) -> {
              int diffA = Math.abs(a - x);
              int diffB = Math.abs(b - x);

              if (diffB == diffA) {
                return b - a;
              }
              return diffB - diffA;
            });

    for (int i = 0; i < arr.length; i++) {
      pq.offer(arr[i]);
      if (pq.size() > k) pq.poll();
    }
    List<Integer> result = new ArrayList<>(pq);
    Collections.sort(result);
    return result;
  }

  public int minRefuelStops(int target, int startFuel, int[][] stations) {

    PriorityQueue<int[]> pq =
        new PriorityQueue<>(
            (a, b) -> {
              if (b[1] == a[1]) {
                return b[0] - a[0];
              }
              return b[1] - a[1];
            });

    int count = 0;
    int distanceCover = 0;
    int currentFlue = startFuel;
    for (int[] i : stations) {
      pq.offer(new int[] {i[0], i[1]});

      if (currentFlue <= i[0]) {
        if (pq.size() > 0) {
          distanceCover += currentFlue;
          int[] station = pq.poll();
          currentFlue -= station[0] + station[1];
          count++;
        } else {
          return -1;
        }
      }
      if (currentFlue < i[0]) return -1;
    }
    return count;
  }

  public int[] kthSmallestPrimeFraction(int[] arr, int k) {
    //        PriorityQueue<double[]> pq = new PriorityQueue<>(
    //                (a, b) -> Double.compare(b[2], a[2])
    //        );
    //        int n=arr.length;
    //        for(int i=0;i<n-1;i++){
    //            for(int j=i+1;j<n;j++){
    //                pq.offer(new double[]{arr[i],arr[j],(double) arr[i]/arr[j]});
    //                if(pq.size()>k) pq.poll();
    //            }
    //        }
    //        double[] result=pq.poll();
    //        return new int[]{result[0],result[1]};

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] * a[1] - a[0] * b[1]);
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        pq.offer(new int[] {arr[i], arr[j]});
        if (pq.size() > k) pq.poll();
      }
    }
    return pq.poll();
  }

  public long sumBetweenTwoKth(long A[], long N, long K1, long K2) {
    // Your code goes here
    long small = KthSmallest(K1, A);
    long large = KthSmallest(K2, A);

    long ans = 0l;

    for (long i : A) {
      if (i > small && i < large) {
        ans += i;
      }
    }
    return ans;
  }

  public long KthSmallest(long k, long[] nums) {

    PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
    for (long i : nums) {
      pq.offer(i);
      if (p.size() > k) p.poll();
    }

    return pq.poll();
  }

  public int[] frequencySort(int[] nums) {
    PriorityQueue<int[]> pq =
        new PriorityQueue<>(
            (a, b) -> {
              if (b[1] == a[1]) return b[0] - a[0];
              return a[1] - b[1];
            });

    Map<Integer, Integer> map = new HashMap<>();

    int n = nums.length;
    for (int i : nums) {

      map.put(i, map.getOrDefault(i, 0) + 1);
    }

    for (Map.Entry<Integer, Integer> i : map.entrySet()) {
      pq.offer(new int[] {i.getKey(), i.getValue()});
    }
    int[] result = new int[n];
    int index = 0;
    while (!pq.isEmpty()) {
      int[] dataMapper = pq.poll();
      int ch = dataMapper[0];
      int freq = dataMapper[1];

      for (int i = 0; i < freq; i++) {
        result[index++] = ch;
      }
    }
    return result;
  }

  public List<String> topKFrequent(String[] words, int k) {
    PriorityQueue<PairDiff> pq =
        new PriorityQueue<>(
            (a, b) -> {
              if (Integer.compare(a.value, b.value) == 0) {
                return b.key.compareTo(a.key);
              }
              return Integer.compare(a.value, b.value);
            });

    Map<String, Integer> map = new HashMap<>();

    for (String i : words) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      pq.offer(new PairDiff(entry.getKey(), entry.getValue()));
      if (pq.size() > k) {
        pq.poll();
      }
    }

    List<String> result = new ArrayList<>();

    while (!pq.isEmpty()) {
      PairDiff ans = pq.poll();
      result.add(ans.key);
    }
    Collections.sort(result);
    return result;
  }

  public void SeatManager(int n) {
    p = new PriorityQueue<>();

    // initially all seats are available
    for (int i = 1; i <= n; i++) {
      p.offer(i);
    }
  }

  public int reserve() {
    return p.poll();
  }

  public void unreserve(int seatNumber) {
    p.offer(seatNumber);
  }

  public int[] getOrder(int[][] tasks) {
    PriorityQueue<int[]> pq =
        new PriorityQueue<>(
            (a, b) -> {
              if (a[0] == b[0]) {
                return a[1] - b[1];
              }
              return a[1] - b[1];
            });

    int n = tasks.length;

    for (int i = 0; i < n; i++) {
      pq.offer(new int[] {tasks[i][0], tasks[i][1], i});
    }

    int index = 0;
    int freetime = 0;

    int[] result = new int[n];
    int ans = 0;

    while (!pq.isEmpty()) {
      if (index > freetime & pq.peek()[0] <= index) {
        int[] temp = pq.poll();
        int freq = temp[1];

        freetime += freq;
        result[ans++] = temp[2];
      }
      index++;
    }
    return result;
  }

  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    int n = profits.length;

    int[][] arr = new int[n][2];
    for (int i = 0; i < n; i++) {
      arr[i][0] = profits[i];
      arr[i][1] = capital[i];
    }
    Arrays.sort(arr, (a, b) -> a[1] - b[1]);

    PriorityQueue<int[]> pq =
        new PriorityQueue<>(
            (a, b) -> {
              if (a[1] != b[1]) return b[0] - a[0];
              return b[0] - a[0];
            });

    int i = 0; // pointer for tasks
    int capitalCurrent = w; // current CPU time
    int j = 0;
    while (j < k) {

      while (i < n && arr[i][1] <= capitalCurrent) {
        pq.offer(new int[] {arr[i][0], arr[i][1]});
        i++;
      }
      if (pq.isEmpty()) break;
      //   System.out.println("current data ::"+ pq.peek()[0]);

      capitalCurrent += pq.poll()[0];
      j++;
    }
    return capitalCurrent;
  }

  public void FreqStack() {
    freq = new HashMap<>();
    time = 0;

    maxFreq =
        new PriorityQueue<>(
            (a, b) -> {
              if (a[1] != b[1]) return b[1] - a[1];
              return b[2] - a[2];
            });
  }

  public void push(int val) {
    int f = freq.getOrDefault(val, 0) + 1;
    freq.put(val, f);

    maxFreq.offer(new int[] {val, f, time++});
  }

  public int pop() {
    int[] top = maxFreq.poll();
    int val = top[0];

    freq.put(val, freq.get(val) - 1);

    return val;
  }

  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<int[]> pq =
        new PriorityQueue<>(
            (a, b) -> {
              return a[0] - b[0];
            });

    Map<Integer, Integer> map = new HashMap<>();

    int n = lists.length;
    for (ListNode node : lists) {
      while (node != null) {
        int val = node.val;
        map.put(val, map.getOrDefault(val, 0) + 1);
        node = node.next;
      }
    }

    for (Map.Entry<Integer, Integer> i : map.entrySet()) {
      pq.offer(new int[] {i.getKey(), i.getValue()});
    }
    ListNode result = new ListNode(0);
    ListNode temp = result;
    int index = 0;
    while (!pq.isEmpty()) {
      int[] dataMapper = pq.poll();
      int ch = dataMapper[0];
      int freq = dataMapper[1];

      for (int i = 0; i < freq; i++) {
        temp.next = new ListNode(ch);
        temp = temp.next;
      }
    }
    return result.next;
  }

  public double[] medianSlidingWindow(int[] nums, int k) {

    this.k = k;
    small = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    large = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
    int n = nums.length;

    double[] result = new double[n - k + 1];
    for (int i = 0; i < k; i++) {
      add(nums[i]);
    }
    result[0] = getMedian();

    // sliding window
    for (int i = k; i < n; i++) {
      add(nums[i]); // add new
      remove(nums[i - k]); // remove old

      result[i - k + 1] = getMedian();
    }

    return result;
  }

  public void addvalue(int value) {
    if (smallSize < largeSize || value <= small.peek()) {
      small.add(value);
      smallSize++;
    } else {
      largeSize++;
      large.add(value);
    }
    balance();
  }

  public void remove(int num) {
    delayed.put(num, delayed.getOrDefault(num, 0) + 1);
    if (num <= small.peek()) {
      smallSize--;
      if (num == small.peek()) {
        prune(small);
      }
    } else {
      largeSize++;
      if (num == large.peek()) {
        prune(large);
      }
    }
    balance();
  }

  public void balance() {
    if (small.size() > large.size() + 1) {
      large.add(small.poll());
      largeSize++;
      smallSize--;
      prune(small);
    } else {
      small.add(large.poll());
      largeSize--;
      smallSize++;
      prune(small);
    }
  }

  // not able to get the idea behind this how we are usn it
  public void prune(PriorityQueue<Integer> heap) {
    while (!heap.isEmpty()) {
      int num = heap.peek();
      if (delayed.containsKey(num)) {
        delayed.put(num, delayed.get(num) - 1);
        if (delayed.get(num) == 0) {
          delayed.remove(num);
        }
        heap.poll();
      } else {
        break;
      }
    }
  }

  private double getMedian() {
    if (k % 2 == 1) {
      return small.peek();
    }
    return ((double) small.peek() + large.peek()) / 2.0;
  }

  public long totalCost(int[] costs, int k, int candidates) {
    PriorityQueue<Integer> minLeft = new PriorityQueue<>();
    PriorityQueue<Integer> minRight = new PriorityQueue<>();

    int index = 0;
    int sumvalue = 0;

    int left = 0;
    int right = costs.length - 1;

    // can't use it if we have  3 elemt then the 2 elemtn add 2 tiome which can cause the issue
    // for (int i = 0; i < candidates; i++) {
    //   minLeft.offer(costs[left++]);
    //   minRight.offer(costs[right--]);
    //   if (left>right) break;
    // }

    for (int i = 0; i < candidates && left <= right; i++) {
      minLeft.offer(costs[left++]);
    }

    // fill right
    for (int i = 0; i < candidates && left <= right; i++) {
      minRight.offer(costs[right--]);
    }

    while (index < k) {

      if (minRight.isEmpty() || (!minLeft.isEmpty() && minLeft.peek() <= minRight.peek())) {
        sumvalue += minLeft.poll();
        if (left < right) {
          minLeft.offer(costs[left++]);
        }
      } else {
        sumvalue += minRight.poll();
        if (left < right) {
          minRight.offer(costs[right--]);
        }
      }
      index++;
    }
    return sumvalue;
  }

  public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
    //        PriorityQueue<int[]> pq= new PriorityQueue<>((a,b)->{
    //            if(a[2]!=b[2]) return b[2]-a[2];
    //            return a[1]-b[1];
    //        });
    //
    //        int n=quality.length;
    //        for (int i=0;i<n;i++){
    //            pq.offer(new int[]{quality[i],wage[i],wage[i]/quality[i]});
    //        }
    //
    //        int minration=pq.isEmpty()?0:pq.peek()[2];
    //        System.out.println("ans:: "+minration);
    //        int ans=0;
    //
    //        for(int i=0;i<k;i++){
    //            ans=pq.poll()[0];
    //        }
    //
    //        return ans*minration;

    // working code that i get from  youtube

    int n = quality.length;

    // (ratio, quality)
    double[][] workers = new double[n][2];

    for (int i = 0; i < n; i++) {
      workers[i][0] = (double) wage[i] / quality[i]; // ratio
      workers[i][1] = quality[i];
    }

    // sort by ratio ASC
    Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));

    // max heap for qualities
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    int sumQuality = 0;
    double ans = Double.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      int q = (int) workers[i][1];
      double ratio = workers[i][0];

      maxHeap.offer(q);
      sumQuality += q;

      // keep only k workers (smallest qualities)
      if (maxHeap.size() > k) {
        sumQuality -= maxHeap.poll();
      }

      // when we have k workers
      if (maxHeap.size() == k) {
        ans = Math.min(ans, sumQuality * ratio);
      }
    }

    return ans;
  }

  public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
    int MOD = 1_000_000_007;

    int[][] engineers = new int[n][2];
    for (int i = 0; i < n; i++) {
      engineers[i][0] = efficiency[i];
      engineers[i][1] = speed[i];
    }
    Arrays.sort(engineers, (a, b) -> b[0] - a[0]);

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    long sumSpeed = 0;
    long maxPerf = 0;

    for (int[] eng : engineers) {
      int eff = eng[0];
      int spd = eng[1];

      minHeap.offer(spd);
      sumSpeed += spd;
      if (minHeap.size() > k) {
        sumSpeed -= spd;
      }

      if (minHeap.size() == k) {
        maxPerf = Math.max(maxPerf, sumSpeed * eff);
      }
    }

    return (int) (maxPerf % MOD);
  }
}
