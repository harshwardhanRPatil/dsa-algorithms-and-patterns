package Contest;

import java.util.*;

public class Augus_09_08 {
  public double minPrice(int[] prices, int[] discounts) {
    Arrays.sort(prices);
    Arrays.sort(discounts);

    int n = prices.length;
    int m = discounts.length;
    double ans = 0;

    int run = Math.min(n, m);
    for (int i = 0; i < run; i++) {
      int price = prices[n - 1 - i];
      int discount = discounts[m - 1 - i];

      ans += price * (100.0 - discount) / 100.0;
    }

    for (int i = 0; i < n - run; i++) {
      ans += prices[i];
    }
    return ans;
  }

  public long weightedSum(int[] parent, int[] nums) {

    int n = parent.length;

    List<Integer>[] tree = new ArrayList[n];
    for (int i = 0; i < n; i++) {
      tree[i] = new ArrayList<>();
    }
    int root = -1;
    for (int i = 0; i < n; i++) {
      if (parent[i] == -1) {
        root = i;
      } else {
        tree[parent[i]].add(i);
      }
    }
    int[] depth = new int[n];

    Queue<Integer> queue = new ArrayDeque<>();
    queue.add(root);

    int h = 0;

    while (!queue.isEmpty()) {
      int node = queue.poll();
      h = Math.max(h, depth[node]);

      for (int child : tree[node]) {
        depth[child] = depth[node] + 1;
        queue.add(child);
      }
    }

    long ans = 0;
    for (int i = 0; i < n; i++) {
      ans += (long) nums[i] * (h - depth[i] + 1);
    }
    return ans;
  }

  static void main() {}
}
