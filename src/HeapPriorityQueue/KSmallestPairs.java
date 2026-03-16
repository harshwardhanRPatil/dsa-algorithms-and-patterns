package Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
//Total space: O(k)
//Time Complexity: O(k⋅logk)
class Node {
  List<Integer> value;
  int sum_value;

  Node(List<Integer> list, int value) {
    this.value = list;
    this.sum_value = value;
  }
}

class KSmallestPairsSolution {
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.sum_value - b.sum_value);
    List<List<Integer>> ans = new ArrayList<>();
    if (nums1.length == 0 || nums2.length == 0) return ans;

    for (int i : nums1) {
      for (int j : nums2) {
        heap.add(new Node(List.of(i, j), i + j));
      }
    }
    while (k > 0 && !heap.isEmpty()) {
      Node temp = heap.poll();
      ans.add(Arrays.asList(temp.value.get(0), temp.value.get(1)));
      k--;
    }

    // alter native approc in which we take the 1 colume only then then add the other grid value in
    // the heap if we not reach the target
//    for (int i = 0; i < Math.min(nums1.length, k); i++) {
//      heap.add(new Node(Arrays.asList(i, 0), nums1[i] + nums2[0]));
//    }
//    while (k-- > 0 && !heap.isEmpty()) {
//      Node temp = heap.poll();
//      ans.add(Arrays.asList(nums1[temp.value.get(0)], nums2[temp.value.get(1)]));
//      if (temp.value.get(1) + 1 < nums2.length) {
//        heap.add(
//            new Node(
//                Arrays.asList(temp.value.get(0), temp.value.get(1) + 1),
//                nums1[temp.value.get(0)] + nums2[temp.value.get(1) + 1]));
//      }
//    }
    return ans;
  }
}

public class KSmallestPairs {
  public static void main(String args[]) {
    KSmallestPairsSolution kSmallestPairsSolution = new KSmallestPairsSolution();
    System.out.println(
        kSmallestPairsSolution.kSmallestPairs(new int[] {1, 7, 11}, new int[] {2, 4, 6}, 3));
  }
}
