package Tree;

import java.util.*;

class DepthProblemSolution {

  List<List<Integer>> ans = new ArrayList<>();
  int count = 0;
  long maxVal = 0;
  long totalSum = 0;

  public int maxDepth(Node root) {
    if (root == null) {
      return 0;
    }
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }

  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null) return minDepth(root.right) + 1;

    if (root.right == null) return minDepth(root.left) + 1;
    return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
  }

  public int widthOfBinaryTree(TreeNode root) {
    if (root == null) return 0;

    Queue<TreeNode> queue = new LinkedList<>();
    Map<Integer, List<Integer>> holder = new TreeMap<>();

    queue.add(root);
    int level = 0;
    int maxWidth = 0;
    int approvalLevel = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        TreeNode p = queue.poll();
        if (approvalLevel == level) {
          if (p != null) {
            if (p.right != null || p.left != null) approvalLevel++;
            holder.computeIfAbsent(level, k -> new ArrayList<>()).add(p.val);
            queue.add(p.left);
            queue.add(p.right);
          } else {
            queue.add(null);
            queue.add(null);
          }
          System.out.println(queue);
        }
      }
      for (List<Integer> list : holder.values()) {
        holder.computeIfAbsent(level, k -> new ArrayList<>()).add(-1);
        maxWidth = Math.max(findLength((list)), maxWidth);
      }
    }

    return maxWidth;
  }

  public int findLength(List<Integer> list) {
    int first = -1;
    int last = -1;

    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) != null) {
        if (first == -1) first = i;
        last = i;
      }
    }

    if (first == -1) return 0; // all nulls
    return last - first;
  }

  int cameras = 0;

  public int minCameraCover(TreeNode root) {
    if (dfs(root) == 0) cameras++;
    return cameras;
  }

  private int dfs(TreeNode node) {
    if (node == null) return 2; // null is covered

    int left = dfs(node.left);
    int right = dfs(node.right);

    if (left == 0 || right == 0) {
      cameras++;
      return 1;
    }

    if (left == 1 || right == 1) {
      return 2;
    }

    return 0;
  }

  public int pseudoPalindromicPaths(TreeNode root) {
    dfsfinder(root, new ArrayList<>());
    return ans.size();
  }

  // backtrack + dfs
  public void dfsfinder(TreeNode root, List<Integer> temp) {
    if (root == null) {
      return;
    }
    temp.add(root.val);
    if (root.left == null && root.right == null) {
      if (canFormPalindrome(temp)) {
        ans.add(temp);
      }
    }
    if (root.left != null) {
      dfsfinder(root.left, temp);
    }
    if (root.right != null) {
      dfsfinder(root.right, temp);
    }
    temp.remove(temp.size() - 1);
  }

  // using the dfs+ bitmanuplation
  public void dfsfinderII(TreeNode root, int mask) {
    if (root == null) return;

    // toggle bit for current value
    mask ^= (1 << root.val);

    // leaf node
    if (root.left == null && root.right == null) {
      if (isPseudoPalindrome(mask)) {
        count++;
      }
      return;
    }

    dfsfinderII(root.left, mask);
    dfsfinderII(root.right, mask);
  }

  public boolean canFormPalindrome(List<Integer> nums) {
    Map<Integer, Integer> freq = new HashMap<>();

    // Count frequency of each number
    for (int n : nums) {
      freq.put(n, freq.getOrDefault(n, 0) + 1);
    }

    // Count how many numbers appear an odd number of times
    int oddCount = 0;
    for (int count : freq.values()) {
      if (count % 2 != 0) {
        oddCount++;
        if (oddCount > 1) return false; // More than one odd → impossible
      }
    }

    return true; // Can form a palindrome
  }

  // at most one bit set
  public boolean isPseudoPalindrome(int mask) {
    return (mask & (mask - 1)) == 0;
  }

  public int maxLevelSum(TreeNode root) {
    if (root == null) return -1;

    // Step 2: get depth and create array
    int depth = maxDepth(root);
    int[] levelSum = new int[depth]; // auto filled with 0

    // Step 3: DFS with level
    dfs(root, 0, levelSum);

    // Step 4: find level with max sum
    int maxVal = Integer.MIN_VALUE;
    int levelIndex = 0;

    for (int i = 0; i < levelSum.length; i++) {
      if (levelSum[i] > maxVal) {
        maxVal = levelSum[i];
        levelIndex = i;
      }
    }

    return levelIndex + 1; // 0-based level
  }

  public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }

  private void dfs(TreeNode root, int level, int[] levelSum) {
    if (root == null) return;

    levelSum[level] += root.val;

    dfs(root.left, level + 1, levelSum);
    dfs(root.right, level + 1, levelSum);
  }

  public int minSubArrayLen(int target, int[] nums) {
    int size = nums.length;
    int max_length = Integer.MAX_VALUE;

    int sum_value = 0;
    int i = 0;
    for (int j = 0; j < size; j++) {
      sum_value += nums[j];

      while (sum_value >= target) {
        max_length = Math.min(max_length, j - i + 1);
        sum_value -= nums[i];
        i++;
      }
    }
    return max_length == Integer.MAX_VALUE ? 0 : max_length;
  }

  public int maxProduct(TreeNode root) {
    totalSum = findTotalSum(root);
    if (root == null) return (int) (maxVal % 1000000007);
    dfsmaxProduct(root.left);
    dfsmaxProduct(root.right);
    return (int) (maxVal % 1000000007);
  }

  private long findTotalSum(TreeNode root) {
    if (root == null) return 0;
    return root.val + findTotalSum(root.left) + findTotalSum(root.right);
  }

  private long dfsmaxProduct(TreeNode root) {
    if (root == null) return 0;

    long left = dfsmaxProduct(root.left);
    long right = dfsmaxProduct(root.right);

    long subtreeSum = left + right + root.val;

    long product = subtreeSum * (totalSum - subtreeSum);
    maxVal = Math.max(maxVal, product);

    return subtreeSum; // return for parent use
  }
}

public class DepthProblem {
  public static void main(String[] args) {
    BST tree = new BST();
    int[] values = {4, 2, 7, 1, 3};
    for (int val : values) {
      tree.root = tree.insert(tree.root, val);
    }
    DepthProblemSolution depthProblemSolution = new DepthProblemSolution();
    depthProblemSolution.maxDepth(tree.root);
  }
}
