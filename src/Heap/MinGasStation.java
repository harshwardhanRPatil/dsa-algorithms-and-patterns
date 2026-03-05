package Heap;

import java.util.*;

class Pair {
  double val;
  int idx;

  Pair(double v, int i) {
    val = v;
    idx = i;
  }

  public double getVal() {
    return val;
  }

  public int getIdx() {
    return idx;
  }
}

class MinGasStationSolution {
  public double minMaxDist(int[] stations, int K) {
    // code here
    int n = stations.length;
    PriorityQueue<Pair> pq =
        new PriorityQueue<>(
            (a, b) -> Double.compare(b.val, a.val) // max-heap
            );

    // initial gaps
    for (int i = 0; i < n - 1; i++) {
      pq.add(new Pair(stations[i + 1] - stations[i], i));
    }

    List<Integer> higmax = new ArrayList<>(Collections.nCopies(n - 1, 0));

    // place K stations
    for (int i = 0; i < K; i++) {
      Pair p = pq.poll();
      if (p == null) {
        // queue is empty, handle safely
        break; // or return 0.0, depending on your logic
      }
      int idx = p.getIdx();

      higmax.set(idx, higmax.get(idx) + 1);

      double indDiff = stations[idx + 1] - stations[idx];
      double newGap = indDiff / (higmax.get(idx) + 1);

      pq.add(new Pair(newGap, idx));
    }

    return pq.isEmpty() ? 0.0 : pq.peek().val; // max gap after placing stations
  }

  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    PriorityQueue<Integer> maxHeap =
        new PriorityQueue<>(
            (a, b) -> {
              int diff = Math.abs(b - x) - Math.abs(a - x);
              if (diff == 0) {
                return b - a; // remove larger number first
              }
              return diff;
            });
    for (int num : arr) {
      maxHeap.offer(num);

      if (maxHeap.size() > k) {
        maxHeap.poll(); // remove farthest
      }
    }

    List<Integer> result = new ArrayList<>(maxHeap);
    Collections.sort(result); // final answer must be sorted

    return result;
  }

  public List<Integer> findClosestElementsII(int[] arr, int k, int x) {

    List<Integer> list = new ArrayList<>();

    for (int num : arr) {
      list.add(num);
    }


    Collections.sort(
        list,
        (a, b) -> {
          int diff = Math.abs(a - x) - Math.abs(b - x);
          return diff == 0 ? a - b : diff;
        });
    // Final result must be sorted ascending
      List<Integer> result = list.subList(0, k);

      Collections.sort(result);

    return result;
  }
}

public class MinGasStation {
  public static void main(String args[]) {
    MinGasStationSolution minGasStationSolution = new MinGasStationSolution();
    System.out.println(minGasStationSolution.minMaxDist(new int[] {13}, 5));
  }
}
