package Heap;

import java.util.Collections;
import java.util.PriorityQueue;

// https://leetcode.com/problems/median-of-two-sorted-arrays/description/

class MedianTwoSortedArraysSolution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
        PriorityQueue<Integer> right = new PriorityQueue<>(); // min-heap

        int[] merged = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, merged, 0, nums1.length);
        System.arraycopy(nums2, 0, merged, nums1.length, nums2.length);

        int leftCounter = 0, rightCounter = 0;

        for (int i = 0; i < merged.length; i++) {
            if (leftCounter == 0 && rightCounter == 0) {
                // first element always goes to left
                left.add(merged[i]);
                leftCounter++;
            }
            else if (leftCounter == rightCounter) {
                // heaps are balanced → push to left, maybe rebalance with right
                if (!right.isEmpty() && merged[i] > right.peek()) {
                    left.add(right.poll());
                    right.add(merged[i]);
                } else {
                    left.add(merged[i]);
                }
                leftCounter++;
            }
            else {
                // left is bigger → next should go to right
                if (merged[i] > left.peek()) {
                    right.add(merged[i]);
                } else {
                    right.add(left.poll());
                    left.add(merged[i]);
                }
                rightCounter++;
            }
        }
int total=merged.length;
        if (total % 2 == 0) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return left.peek(); // left always has the extra element
        }
    }

    private void addNum(int num, PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        // Step 1: add to max-heap
        left.add(num);

        // Step 2: balance (max from left -> right)
        right.add(left.poll());

        // Step 3: ensure left has equal or +1 size
        if (left.size() < right.size()) {
            left.add(right.poll());
        }
    }
}

public class MedianTwoSortedArrays {
  public static void main() {
    MedianTwoSortedArraysSolution medianTwoSortedArraysSolution =
        new MedianTwoSortedArraysSolution();
    System.out.println(
        medianTwoSortedArraysSolution.findMedianSortedArrays(
            new int[] {1, 2, 3, 6, 10}, new int[] {3, 4, 7, 12, 15}));
  }
}
