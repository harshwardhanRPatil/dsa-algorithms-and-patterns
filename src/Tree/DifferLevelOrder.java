package Tree;

import java.util.*;

class DifferLevelOrderSolution {

  int diametermaxcoun = Integer.MIN_VALUE;

  public List<List<Integer>> levelOrder(TreeNode root) {
    // declar the linklist for the LIst<List>
    List<List<Integer>> ans = new LinkedList<>();
    // for better peposmence
    Queue<TreeNode> suffer = new ArrayDeque<>();
    if (root == null) return ans;
    suffer.add(root);

    List<Integer> temp_ans = new ArrayList<>();
    Queue<TreeNode> tempStore = new ArrayDeque<>();

    while (!suffer.isEmpty()) {
      TreeNode temp = suffer.poll();
      temp_ans.add(temp.val);
      if (temp.left != null) {
        tempStore.add(temp.left);
      }
      if (temp.right != null) {
        tempStore.add(temp.right);
      }
      if (suffer.isEmpty()) {
        ans.add(new ArrayList<>(temp_ans));
        suffer = tempStore;
        temp_ans.clear();
        tempStore = new ArrayDeque<>();
      }
    }
    return ans;
  }

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    if (root == null) return ans;

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      int right_ans = 0;
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        right_ans = node.val;

        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
      }

      ans.add(right_ans); // no clearing needed
    }
    return ans;
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> ans = new LinkedList<>();
    Stack<TreeNode> queue = new Stack<>();
    if (root == null) return ans;
    queue.add(root);
    boolean right = true;

    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> level = new ArrayList<>();
      Stack<TreeNode> temp = new Stack<>();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.pop();
        level.add(node.val);
        if (right) {
          if (node.left != null) temp.add(node.left);
          if (node.right != null) temp.add(node.right);
        } else {
          if (node.right != null) temp.add(node.right);
          if (node.left != null) temp.add(node.left);
        }
      }
      right = !right;
      ans.add(level);
      queue = temp;
    }

    return ans;
  }

  class Storage {
    TreeNode root;
    int row;
    int col;

    public Storage(TreeNode root, int row, int col) {
      this.root = root;
      this.row = row;
      this.col = col;
    }
  }

  public List<List<Integer>> verticalTraversal(TreeNode root) {

    List<List<Integer>> ans = new LinkedList<>();
    if (root == null) return ans;

    Queue<Storage> queue = new LinkedList<>();
    Map<Integer, Map<Integer, PriorityQueue<Integer>>> holder = new TreeMap<>();

    queue.offer(new Storage(root, 0, 0));

    while (!queue.isEmpty()) {
      int size = queue.size();
      Storage storage = queue.poll();
      holder
          .computeIfAbsent(storage.col, k -> new TreeMap<>())
          .computeIfAbsent(storage.row, k -> new PriorityQueue<>())
          .add(storage.root.val);

      if (storage.root.left != null) {
        queue.offer(new Storage(storage.root.left, (storage.row + 1), (storage.col - 1)));
      }

      if (storage.root.right != null) {
        queue.offer(new Storage(storage.root.right, (storage.row + 1), (storage.col + 1)));
      }
    }
    for (Map<Integer, PriorityQueue<Integer>> rows : holder.values()) {
      List<Integer> column = new ArrayList<>();
      for (PriorityQueue<Integer> pq : rows.values()) {
        while (!pq.isEmpty()) {
          column.add(pq.poll());
        }
      }
      ans.add(column);
    }
    return ans;
  }

  public List<Double> averageOfLevels(TreeNode root) {
    List<Double> ans = new ArrayList<>();
    if (root == null) return ans;

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      double right_ans = 0;
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        right_ans += node.val;

        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
      }

      ans.add(right_ans / size); // no clearing needed
    }
    return ans;
  }

  // for this we use  in order travelser as we need the k elemtn in as it in BST we have the inorder
  // pees ractive
  public int kthSmallest(TreeNode root, int k) {
    List<Integer> ans = new ArrayList<>();
    inorder(root, ans);
    return ans.get(k - 1);
  }

  public void inorder(TreeNode root, List<Integer> ans) {
    if (root != null) {
      inorder(root.left, ans);
      ans.add(root.val);
      inorder(root.right, ans);
    }
  }

  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> ans = new LinkedList<>();
    Queue<TreeNode> queue = new ArrayDeque<>();

    if (root == null) return ans;
    queue.add(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> level = new ArrayList<>();

      while (size-- > 0) {
        TreeNode node = queue.poll();
        level.add(node.val);

        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
      }

      ans.add(level);
    }
    return ans.reversed();
  }

  public TreeNode levelOrderSuccessor(TreeNode root, int key) {
    if (root == null) return null;

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();

        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
        if (node.val == key) {
          return queue.peek(); // next element in level order
        }
      }
    }
    return null;
  }

  public Node connect(Node root) {
    if (root == null) return root;

    //    Queue<Node> queue = new LinkedList<>();
    //    queue.add(root);
    //    while (!queue.isEmpty()) {
    //      int size = queue.size();
    //      Queue<Node> temp = new LinkedList<>();
    //      for (int i = 0; i < size; i++) {
    //        Node node = queue.poll();
    //
    //        if (node.left != null) temp.offer(node.left);
    //        if (node.right != null) temp.offer(node.right);
    ////        node.next = queue.peek();
    //      }
    //      queue = temp;
    //    }
    //    return root;

    /*
    optimal way by yusing the loggic as i forgot to read that it a perfect tree so we will have left child then only we have right to we can connect them
     */
    Node leftmost = root;
    // next is already mark as null so we can use it as a part of link list so when we travel to
    // right now we got to next
    while (leftmost.left != null) {
      Node currect = leftmost;
      while (currect != null) {
        //              currect.left.next=currect.right;
        //              if(currect.next!=null){
        //                  currect.right.next=currect.next.left;
        //              }
        //              // this we can use as we have already connect the right node to the lest
        // when we travel from the left
        //              currect=currect.next;
      }
      leftmost = currect.left;
    }
    return root;
  }

  public int diameterOfBinaryTree(TreeNode root) {
    if (root == null) return diametermaxcoun;
    diameterOfBinaryTreeFinder(root);
    return diametermaxcoun - 1;
  }

  public int diameterOfBinaryTreeFinder(TreeNode root) {
    if (root == null) return 0;

    int left = diameterOfBinaryTreeFinder(root.left);
    int right = diameterOfBinaryTreeFinder(root.right);

    diametermaxcoun = Math.max(diametermaxcoun, (left + right + 1));
    return Math.max(left, right) + 1;
  }

  public int maxPathSum(TreeNode root) {
    diameterOfBinaryTreeFinderII(root);
    return diametermaxcoun;
  }

  public int diameterOfBinaryTreeFinderII(TreeNode root) {
    if (root == null) return 0;

    int left = diameterOfBinaryTreeFinderII(root.left);
    int right = diameterOfBinaryTreeFinderII(root.right);

    left = Math.max(0, left);
    right = Math.max(0, right);
    int pathSum = left + right + root.val;
    diametermaxcoun = Math.max(diametermaxcoun, pathSum);
    return Math.max(left, right) + root.val;
  }
}

public class DifferLevelOrder {
  public static void main(String[] args) {
    BST tree = new BST();
    int[] values = {23, 15, 14, 13, 20, 26, 24, 23, 25, 27};
    for (int val : values) {
      tree.root = tree.insert(tree.root, val);
    }
  }
}
