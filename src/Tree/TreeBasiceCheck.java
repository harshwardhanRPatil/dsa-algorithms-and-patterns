package Tree;

/*
 * This file contains basic tree utilities including:
 * 1. isBalanced()       - Checks if a binary tree is height-balanced.
 * 2. heightFinder()     - Helper method to calculate height and detect imbalance.
 * 3. isSymmetric()      - Checks if a binary tree is symmetric (mirror of itself).
 * 4. isSameTree()       - Checks if two binary trees are structurally identical and have the same values.
 * 5. invertTree         - Invert Binary Tree: swap left and right child of every node using DFS recursion
 * All methods use recursion and are optimized for O(n) time complexity.
 */

import java.util.ArrayList;
import java.util.List;

class TreeBasiceCheckSolution {
  List<Integer> ans = new ArrayList<>();
    int goodNodeCounter=0;
  public boolean isBalanced(TreeNode root) {
    return heightfinder(root) != -1;
  }

  public int heightfinder(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = heightfinder(root.left);
    // this check if the left side is inbalacnce
    if (left == -1) return -1;
    int right = heightfinder(root.right);
    // this check if the right side is inbalacnce

    if (right == -1) return -1;

    if (Math.abs(left - right) > 1) {
      return -1;
    }
    return Math.max(left, right) + 1;
  }

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isSameTree(root.left, root.right);
  }

  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }
    if ((p != null && q == null) || (p == null && q != null)) {
      return false;
    }
    if (p.val == q.val) {
      return isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }
    return false;
  }

  public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return false;
    }
    if (root.left == null && root.right == null) {
      return targetSum == root.val;
    }

    boolean left = hasPathSum(root.left, targetSum - root.val);
    boolean right = hasPathSum(root.right, targetSum - root.val);
    return left || right;
  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;

    TreeNode left = invertTree(root.right);
    TreeNode right = invertTree(root.left);
    root.left = left;
    root.right = right;
    return root;
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    return helper(nums, 0, nums.length - 1);
  }

  private TreeNode helper(int[] nums, int left, int right) {
    if (left > right) return null;
    int mid = (left + right) / 2;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = helper(nums, left, mid - 1);
    root.right = helper(nums, mid + 1, right);
    return root;
  }

  public void flatten(TreeNode root) {
    root = treecreation(null, 0);
  }

  public TreeNode treecreation(TreeNode root, int pointer) {
    if (pointer == ans.size()) return null;
    if (root == null) root = new TreeNode(ans.get(pointer));
    root.right = treecreation(root.left, pointer + 1);
    return root;
  }

  public void preorder(TreeNode root) {
    if (root != null) {
      ans.add(root.val);
      preorder(root.left);
      preorder(root.right);
    }
  }

  // using DFS after the video get the idea
  public void flattenII(TreeNode root) {
    if (root == null) return;

    TreeNode current = root;
    while (current != null) {
      if (current.left != null) {
        TreeNode temp = current.left;
        while (temp.right != null) {
          temp = temp.right;
        }

        temp.right = current.right;
        current.right = current.left;
        current.left = null;
      }
      current = current.right;
    }
  }
    public int goodNodes(TreeNode root) {
        goodNodesFinder(root,root.val);
      return goodNodeCounter;
    }
    public void goodNodesFinder(TreeNode root,int max_val){
      if (root==null) return;

      if(root.val>=max_val) goodNodeCounter++;
        goodNodesFinder(root.left,Math.max(max_val,root.val));
                goodNodesFinder(root.right,Math.max(max_val,root.val));

    }
}

public class TreeBasiceCheck {
  public static void main(String[] args) {}
}
