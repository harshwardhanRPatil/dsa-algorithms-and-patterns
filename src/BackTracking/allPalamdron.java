package BackTracking;

import java.util.*;
import java.util.stream.Collectors;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

class SolutionPalmdron {

  int[] dp;
    int count=0;
  public ArrayList<String> solve(String s) {
    ArrayList<String> ans = new ArrayList<>();
    ArrayList<String> temp = new ArrayList<>();
    dividerlist(s, ans, temp, 0);
    return ans;
  }

  public void dividerlist(String s, ArrayList ans, ArrayList<String> temp, int start) {
    //        System.out.println(start);
    if (start == s.length()) {
      ans.add(new ArrayList<>(temp));
      System.out.println(ans.toString());
      return;
    }

    for (int i = start; i < s.length(); i++) {
      String palemdron = s.substring(start, i + 1);
      if (isPalindrome(palemdron)) {
        temp.add(palemdron);
        dividerlist(s, ans, temp, i + 1);
        System.out.println(palemdron + " " + temp.size() + " " + temp.get(0));
        temp.remove(temp.size() - 1);
      }
    }
  }

  public boolean isPalindrome(String str) {
    int left = 0, right = str.length() - 1;
    while (left < right) {
      if (Character.toLowerCase(str.charAt(left)) != Character.toLowerCase(str.charAt(right))) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

  // https://leetcode.com/problems/permutations/
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    combinationPermute(nums, 0, ans);
    return ans;
  }

  public void combinationPermute(int[] nums, int index, List<List<Integer>> ans) {

    // after all the combination we have add this valud so we add at end of the list witjh this we
    // can add the inital data also
    if (index == nums.length) ans.add(toList(nums));
    for (int i = index; i < nums.length; i++) {
      swap(nums, index, i);
      combinationPermute(nums, index + 1, ans);
      swap(nums, i, index);
    }
  }

  //  https://leetcode.com/problems/permutations-ii/
  public List<List<Integer>> permuteUnique(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    combinationPermuteUnique(nums, 0, ans);
    return ans;
  }

  public void combinationPermuteUnique(int[] nums, int index, List<List<Integer>> ans) {

    // after all the combination we have add this valud so we add at end of the list witjh this we
    // can add the inital data also
    if (index == nums.length) ans.add(toList(nums));

    for (int i = index; i < nums.length; i++) {
      // the basic logic not work here we need to have a code to make sure this work
      /*
      this logic tell if i have see the value or not
      1122
      for the 1,1,2,2--> i havew swap and become 1,2,1,2 -> but then after thee solution we becom back to orgin 1,1,2,2
      now i want to check for index 1 and 3 so i loop from 1-3 to see if i have any whee i can see this value so i can mark it as vising so i can skip thwe
      value
       */
      if (shouldSkip(nums, index, i)) continue;

      swap(nums, index, i);
      combinationPermuteUnique(nums, index + 1, ans);
      swap(nums, i, index);
    }
  }

  private boolean shouldSkip(int[] nums, int start, int curr) {
    for (int k = start; k < curr; k++) {
      if (nums[k] == nums[curr]) return true;
    }
    return false;
  }

  public void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public List<Integer> toList(int[] arr) {
    List<Integer> list = new ArrayList<>(arr.length);
    for (int num : arr) {
      list.add(num);
    }
    return list;
  }

  // https://leetcode.com/problems/subsets/
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    combinationSubset(nums, 0, ans, new ArrayList<Integer>());
    return ans;
  }

  public void combinationSubset(
      int[] nums, int index, List<List<Integer>> ans, List<Integer> temp) {

    // as it help as to have the [] also in the as as expected
    ans.add(new ArrayList<>(temp));

    for (int i = index; i < nums.length; i++) {
      temp.add(nums[i]);
      combinationSubset(nums, i + 1, ans, temp);
      temp.remove(temp.size() - 1);
    }
  }

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    combinationSubsetWithDup(nums, 0, result, new ArrayList<Integer>());

    return result;
  }

  public void combinationSubsetWithDup(
      int[] nums, int index, Set<List<Integer>> ans, List<Integer> temp) {

    // as it help as to have the [] also in the as as expected
    ans.add(new ArrayList<>(temp));

    for (int i = index; i < nums.length; i++) {
      temp.add(nums[i]);
      // make sure we don't have the value againn
      if (i > 0 && nums[i] != nums[i - 1]) {
        combinationSubsetWithDup(nums, i + 1, ans, temp);
      }
      temp.remove(temp.size() - 1);
    }
  }

  // alternative
  public void combinationSubsetWithDup(
      int[] nums, int index, List<List<Integer>> ans, List<Integer> temp) {

    // as it help as to have the [] also in the as as expected
    ans.add(new ArrayList<>(temp));

    for (int i = index; i < nums.length; i++) {
      // so what here we do is check if the later we have is not the index one as we have the index
      // valu beadd for the first time so we need
      // if it not that we can skip
      // eg 14444
      // when we have 14 alredaay in temp and we go for the 2 index as ininted so that add for the 1
      // timme in the unique combine [1,4] we accept
      // then we check i =3 and compare it with the i-1 , as this also we have in the ans [1,4,4] so
      // we no need to check as we have all it espective and
      // present for us
      if (i > index && nums[i] == nums[i - 1]) continue;
      temp.add(nums[i]);
      // make sure we don't have the value againn

      combinationSubsetWithDup(nums, i + 1, ans, temp);

      temp.remove(temp.size() - 1);
    }
  }

  // https://leetcode.com/problems/letter-case-permutation/
  public List<String> letterCasePermutation(String s) {
    List<String> result = new ArrayList<>();
    combinationletterCasePermutation(new StringBuilder(s), 0, result);
    return result;
  }

  public void combinationletterCasePermutation(StringBuilder s, int index, List<String> ans) {
    if (index == s.length()) {
      ans.add(s.toString());
      return;
    }

    for (int i = index; i < s.length(); i++) {
      char ch = s.charAt(i);
      if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
        s.setCharAt(i, toggleCase(ch));
        System.out.println(s.toString());
        combinationletterCasePermutation(s, i + 1, ans);
        s.setCharAt(i, toggleCase(s.charAt(i)));
      }
    }
  }

  public char toggleCase(char ch) {
    if (Character.isLowerCase(ch)) {
      return Character.toUpperCase(ch);
    }
    return Character.toLowerCase(ch);
  }

  // https://leetcode.com/problems/generate-parentheses/

  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    combinegenerateParenthesis(n, result, 0, 0, new StringBuilder());
    return result;
  }

  public void combinegenerateParenthesis(
      int n, List<String> ans, int open, int close, StringBuilder temp) {

    if (open == n && close == n) ans.add(temp.toString());

    /*
    ok i have the if and not the for loop here is because we want to check the posiblity of the ( and )
    eg close and open both are 2 so string is "(())" after that we remove the close as it complet it recussion
    it become "((" open 2 and close 0 after that it come to open frpom last recussion and it remove the "(" and open become 1
    that is greated then the close se in second if we get the close < open true and then we get
    "() "and this continume

     */

    if (open < n) {
      temp.append("(");
      open++;
      combinegenerateParenthesis(n, ans, open, close, temp);
      open--;
      temp.deleteCharAt(temp.length() - 1);
    }
    if (close < open) {
      temp.append(")");
      close++;
      combinegenerateParenthesis(n, ans, open, close, temp);
      close--;
      temp.deleteCharAt(temp.length() - 1);
    }
  }

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> ans = new ArrayList<>();
    combinationpathSum(root, targetSum, 0, ans, new ArrayList<>());
    return ans;
  }

  public void combinationpathSum(
      TreeNode root, int targetSum, int current, List<List<Integer>> ans, List<Integer> temp) {
    if (root == null) return;

    if (root.left == null && root.right == null && current == targetSum) {
      ans.add(new ArrayList<>(temp));
    }
    System.out.println(temp.toString());
    current += root.val;
    temp.add(root.val);
    combinationpathSum(root.left, targetSum, current, ans, temp);
    combinationpathSum(root.right, targetSum, current, ans, temp);
    temp.remove(temp.size() - 1);
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    combinecombinationSum(candidates, target, ans, new ArrayList<>(), 0);
    return ans;
  }

  // https://leetcode.com/problems/combination-sum/
  public void combinecombinationSum(
      int[] candidates, int target, List<List<Integer>> ans, List<Integer> temp, int index) {

    if (target == 0) {
      ans.add(new ArrayList<>(temp));
    }

    for (int i = index; i < candidates.length; i++) {
      if (candidates[i] <= target) {
        temp.add(candidates[i]);
        combinecombinationSum(candidates, target - candidates[i], ans, temp, i);
        temp.remove(temp.size() - 1);
      }
    }
  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(candidates);
    combinecombinationSum2(candidates, target, ans, new ArrayList<>(), 0);
    return ans;
  }

  // https://leetcode.com/problems/combination-sum-ii/
  public void combinecombinationSum2(
      int[] candidates, int target, List<List<Integer>> ans, List<Integer> temp, int index) {

    if (target == 0) {
      ans.add(new ArrayList<>(temp));
    }

    for (int i = index; i < candidates.length; i++) {
      if (candidates[i] <= target && !shouldSkip(candidates, index, i)) {
        temp.add(candidates[i]);
        combinecombinationSum(candidates, target - candidates[i], ans, temp, i + 1);
        temp.remove(temp.size() - 1);
      }
    }
  }

  // https://leetcode.com/problems/combination-sum-iii/
  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> ans = new ArrayList<>();
    combinationSum3List(k, n, ans, new ArrayList<>(), 1);
    return ans;
  }

  public void combinationSum3List(
      int k, int target, List<List<Integer>> ans, List<Integer> temp, int index) {

    if (target == 0 && temp.size() == k) {
      ans.add(new ArrayList<>(temp));
    }
    if (temp.size() == k) return;

    for (int i = index; i < 10; i++) {
      temp.add(i);
      combinationSum3List(k, target - i, ans, temp, i + 1);
      temp.remove(temp.size() - 1);
    }
  }

  //    https://leetcode.com/problems/palindrome-partitioning/

  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    partitionCombination(s, result, new ArrayList<>(), 0);
    return result;
  }

  public void partitionCombination(
      String s, List<List<String>> result, List<String> temp, int index) {

    if (index == s.length()) {
      result.add(new ArrayList<String>(temp));
    }

    for (int i = index; i < s.length(); i++) {
      if (isPalindrome(s, index, i)) {
        temp.add(s.substring(index, i + 1));
        partitionCombination(s, result, temp, i + 1);
        temp.remove(temp.size() - 1);
      }
    }
  }

  private boolean isPalindrome(String s, int left, int right) {
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) return false;
      left++;
      right--;
    }
    return true;
  }

  // https://leetcode.com/problems/palindrome-partitioning-ii/submissions/1981498578/

  // we need to have the result as global or we have to pass
  // normal que work but dp is need as we face the TEL
  public int minCut(String s) {
    int n = s.length();
    dp = new int[n];
    Arrays.fill(dp, -1);
    return partitionCombination(s, 0) - 1;
  }

  public int partitionCombination(String s, int index) {

    if (index == s.length()) {
      return 0;
    }

    if (dp[index] != -1) {
      return dp[index];
    }

    int min = Integer.MAX_VALUE;
    for (int i = index; i < s.length(); i++) {
      if (isPalindrome(s, index, i)) {
        int cuts = 1 + partitionCombination(s, i + 1);
        min = Math.min(min, cuts);
      }
    }
    return dp[index] = min;
  }

  public List<String> restoreIpAddresses(String s) {
    List<String> result = new ArrayList<>();
    restoreIpAddressesCombine(s, result, new ArrayList<>(), 0);
    return result;
  }

  public void restoreIpAddressesCombine(
      String s, List<String> result, List<String> temp, int index) {

    if (index == s.length() && temp.size() == 4) {
      result.add(buildIP(temp));
    }
    if (temp.size() == 4) {
      return;
    }

    for (int i = index; i < s.length(); i++) {

      if (isValid(s.substring(index, i + 1))) {

        temp.add(s.substring(index, i + 1));
        // System.out.println(temp.toString());
        restoreIpAddressesCombine(s, result, temp, i + 1);
        temp.remove(temp.size() - 1);
      }
    }
  }

  private String buildIP(List<String> parts) {
    return String.join(".", parts);
  }

  private boolean isValid(String s) {
    // length check
    if (s.length() == 0 || s.length() > 3) return false;

    // leading zero check
    if (s.length() > 1 && s.charAt(0) == '0') return false;

    // convert to number
    int num = Integer.parseInt(s);
    return num >= 0 && num <= 255;
  }

  // https://leetcode.com/problems/word-search
  public boolean exist(char[][] board, String word) {
    int n = board.length;
    int m = board[0].length;

    boolean[][] visited = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      Arrays.fill(visited[i], false);
    }

    int index = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == word.charAt(index)) {
          visited[i][j] = true;
          if (existPath(board, word, index + 1, visited, i + 1, j, n, m)
              || existPath(board, word, index + 1, visited, i - 1, j, n, m)
              || existPath(board, word, index + 1, visited, i, j + 1, n, m)
              || existPath(board, word, index + 1, visited, i, j - 1, n, m)) return true;
          visited[i][j] = false;
        }
      }
    }
    return false;
  }

  public boolean existPath(
      char[][] board,
      String word,
      int index,
      boolean[][] visited,
      int x,
      int y,
      int boardLength,
      int boardWeidth) {

    if (word.length() == index) return true;

    if (x < 0 || x >= boardLength || y < 0 || y >= boardWeidth) {
      return false;
    }
    if (visited[x][y]) return false;

    if (board[x][y] == word.charAt(index)) {
      visited[x][y] = true;
      if (existPath(board, word, index + 1, visited, x + 1, y, boardLength, boardWeidth)
          || existPath(board, word, index + 1, visited, x - 1, y, boardLength, boardWeidth)
          || existPath(board, word, index + 1, visited, x, y + 1, boardLength, boardWeidth)
          || existPath(board, word, index + 1, visited, x, y - 1, boardLength, boardWeidth))
        return true;
      visited[x][y] = false;
    }
    return false;
  }

  // https://leetcode.com/problems/letter-combinations-of-a-phone-number/
  public List<String> letterCombinations(String digits) {
    List<String> combination = new ArrayList<>();
    if (digits.length() == 0) {
      return combination;
    }
    Map<Character, String> digitToLetters = new HashMap<>();

    digitToLetters.put('2', "abc");
    digitToLetters.put('3', "def");
    digitToLetters.put('4', "ghi");
    digitToLetters.put('5', "jkl");
    digitToLetters.put('6', "mno");
    digitToLetters.put('7', "pqrs");
    digitToLetters.put('8', "tuv");
    digitToLetters.put('9', "wxyz");
    List<String> temp = new ArrayList<>();
    letterCombinationsList(digits, 0, combination, new StringBuilder(), digitToLetters);
    return combination;
  }

  public void letterCombinationsList(
      String digits,
      int index,
      List<String> result,
      StringBuilder stringBuilder,
      Map<Character, String> digitToLetters) {

    if (digits.length() == index) {
      result.add(stringBuilder.toString());
        return;
    }

    char ch = digits.charAt(index);
    int num = ch - '0';
    String letter = digitToLetters.get(ch);
    for (int j = 0; j < letter.length(); j++) {

      char temp = letter.charAt(j);
      stringBuilder.append(temp);
      letterCombinationsList(digits, index + 1, result, stringBuilder, digitToLetters);
      stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }
  }

  // https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
  public boolean canPartitionKSubsets(int[] nums, int k) {

      int n = nums.length;
      int sum = Arrays.stream(nums).sum();
      if (sum % k != 0)
          return false;
      Arrays.sort(nums); // ascending

      // reverse
      for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
          int temp = nums[i];
          nums[i] = nums[j];
          nums[j] = temp;
      }
      boolean[] visted = new boolean[n];
      Arrays.fill(visted, false);

      return  canPartitionKSubsetsList(nums, k, sum / k, visted, 0,sum / k);
  }

    public boolean canPartitionKSubsetsList(int[] nums, int k, int target, boolean[] visited, int index,int bucket) {

        if(k==1) return true;

        if (target == 0) {
            return canPartitionKSubsetsList(nums, k-1, bucket, visited, 0,bucket);
        }
        if (index == nums.length)
            return false;

        for (int i = index; i < nums.length; i++) {
            if (nums[i] <= target && !visited[i]) {
                // avoid duplicates
                // if we can't use the last we can't use this one also as it a duplicates CALL  all
                // again and it imprve the performance
                if (i > index && nums[i] == nums[i - 1] && !visited[i - 1]) continue;

                visited[i] = true;
                if (canPartitionKSubsetsList(nums, k, target - nums[i], visited, i + 1,bucket)) return true;
                visited[i] = false;
            }
        }
        return false;
    }
}

public class allPalamdron {
  public static void main(String args[]) {
    SolutionPalmdron solutionPalmdron = new SolutionPalmdron();
    System.out.println(solutionPalmdron.solve("abca"));
  }
}
