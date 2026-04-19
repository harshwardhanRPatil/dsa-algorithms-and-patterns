package Stack;

import java.util.*;

// Identification
/*
   If we have array think of stack
   we focus on change the O(n^2) to O(n) or depented of O(n^2) loop then beter change is use the stack for the 2 loop

*/
public class StackBasic {

  // constant data
  Stack<Integer> inStack;
  Stack<Integer> outStack;

  static void main() {}

  public boolean isValid(String s) {
    Stack<Character> validated = new Stack<>();

    for (int i = 0; i < s.length(); i++) {

      char ch = s.charAt(i);

      if (ch == '(' || ch == '{' || ch == '[') {
        validated.push(ch);
      } else {
        if (validated.isEmpty()) return false;
        char top = validated.pop();
        if ((ch == ')' && top != '(') || (ch == '}' && top != '{') || (ch == ']' && top != '[')) {
          return false;
        }
      }
    }
    return validated.isEmpty();
  }

  public void MyQueue() {
    inStack = new Stack<>();
    outStack = new Stack<>();
  }

  public void push(int x) {
    inStack.push(x);
  }

  public int pop() {
    if (outStack.isEmpty()) {
      move();
    }
    return outStack.pop();
  }

  public int peek() {
    if (outStack.isEmpty()) {
      move();
    }
    return outStack.peek();
  }

  public boolean empty() {
    return inStack.isEmpty() && outStack.isEmpty();
  }

  private void move() {
    while (!inStack.isEmpty()) {
      outStack.push(inStack.pop());
    }
  }

  public ArrayList<Integer> nextLargerElement(int[] arr) {
    // code here

    Stack<Integer> stack = new Stack<>();

    ArrayList<Integer> result = new ArrayList<>();

    int n = arr.length - 1;
    for (int i = n; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() <= arr[i]) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        result.add(-1);
      } else {

        result.add(stack.peek());
      }
      stack.push(arr[i]);
    }
    Collections.reverse(result);
    return result;
  }

  static ArrayList<Integer> preGreaterEle(int[] arr) {
    // code here
    Stack<Integer> stack = new Stack<>();

    ArrayList<Integer> result = new ArrayList<>();

    int n = arr.length;
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && stack.peek() <= arr[i]) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        result.add(-1);
      } else {
        result.add(stack.peek());
      }
      stack.push(arr[i]);
    }
    return result;
  }

  public int[] leftSmaller(int[] arr) {
    // code here

    Stack<Integer> stack = new Stack<>();
    int n = arr.length;
    int[] result = new int[n];
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && stack.peek() >= arr[i]) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        result[i] = -1;
      } else {
        result[i] = stack.peek();
      }
      stack.push(arr[i]);
    }
    return result;
  }

  public int calPoints(String[] operations) {
    Stack<Integer> stack = new Stack<>();

    for (String i : operations) {
      if (i.equals("C")) {
        stack.pop();
      } else if (i.equals("D")) {
        stack.add(stack.peek() * 2);
      } else if (i.equals("+")) {
        int top = stack.pop();
        int newTop = top + stack.peek();
        stack.push(top); // restor
        stack.add(newTop);
      } else {
        stack.add(Integer.parseInt(i));
      }
    }
    int sum = 0;
    while (!stack.isEmpty()) {
      sum += stack.pop();
    }
    return sum;
  }

  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Map<Integer, Integer> map = new HashMap<>();
    Stack<Integer> stack = new Stack<>();

    for (int i = nums2.length - 1; i >= 0; i--) {

      while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
        stack.pop();
      }

      int nextGreater = stack.isEmpty() ? -1 : stack.peek();
      map.put(nums2[i], nextGreater);

      stack.push(nums2[i]);
    }

    int[] result = new int[nums1.length];

    for (int i = 0; i < nums1.length; i++) {
      result[i] = map.get(nums1[i]);
    }

    return result;
  }

  static ArrayList<Integer> nextSmallerEle(int[] arr) {
    // code here
    Stack<Integer> stack = new Stack<>();

    ArrayList<Integer> result = new ArrayList<>();

    int n = arr.length - 1;
    for (int i = n; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() >= arr[i]) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        result.add(-1);
      } else {

        result.add(stack.peek());
      }
      stack.push(arr[i]);
    }
    Collections.reverse(result);
    return result;
  }

  public int[] dailyTemperatures(int[] temperatures) {
    Map<Integer, Integer> map = new HashMap<>();
    Stack<Integer> stack = new Stack<>();
    int[] result = new int[temperatures.length];
    for (int i = temperatures.length - 1; i >= 0; i--) {

      int count = 1;
      while (!stack.isEmpty() && stack.peek() <= temperatures[i]) {
        count += map.get(stack.pop());
      }

      int nextGreater = stack.isEmpty() ? 0 : count;
      result[i] = nextGreater;
      map.put(temperatures[i], nextGreater);

      stack.push(temperatures[i]);
    }
    return result;
  }

  public int[] nextGreaterElements(int[] nums) {
    Stack<Integer> stack = new Stack<>();
    int n = nums.length - 1;
    int[] result = new int[n];
    //        for (int i = n; i >= 0; i--) {
    //            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
    //                stack.pop();
    //            }
    //            if (stack.isEmpty()) {
    //                result[i]=-1;
    //            } else {
    //                result[i]=nums[stack.peek()];
    //            }
    //            stack.push(i);
    //        }
    //
    //        for (int i = n; i >= 0; i--) {
    //            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
    //                stack.pop();
    //            }
    //            if(!stack.isEmpty() && i==stack.peek()) break;
    //            if (stack.isEmpty()) {
    //                result[i]=-1;
    //            } else {
    //                result[i]=nums[stack.peek()];
    //            }
    //            stack.push(i);
    //        }
    for (int i = 2 * n - 1; i >= 0; i--) {
      int index = i % n;

      while (!stack.isEmpty() && nums[stack.peek()] <= nums[index]) {
        stack.pop();
      }
      if (stack.isEmpty()) {
        result[index] = -1;
      } else {
        result[index] = nums[stack.peek()];
      }
      stack.push(index);
    }

    return result;
  }

  public String decodeString(String s) {
    Stack<String> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      char charater = s.charAt(i);
      if (charater != ']') {
        stack.push(String.valueOf(charater));
      } else {
        StringBuffer stringBuild = new StringBuffer();
        while (!stack.peek().equals("[")) {
          stringBuild.insert(0, stack.pop());
        }
        stack.pop();
        StringBuilder num = new StringBuilder();

        while (!stack.isEmpty() && Character.isDigit(stack.peek().charAt(0))) {
          num.insert(0, stack.pop());
        }

        int k = Integer.parseInt(num.toString());
        String repeated = stringBuild.toString().repeat(k);
        stack.push(repeated);
      }
    }
    StringBuilder result = new StringBuilder();
    for (String str : stack) {
      result.append(str);
    }

    return result.toString();
  }

  public int[] asteroidCollision(int[] asteroids) {
    Stack<Integer> stack = new Stack<>();

    for (int i : asteroids) {
      if (i < 0) {
        while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -i) {
          stack.pop();
        }
        if (!stack.isEmpty() && stack.peek() > 0 && stack.peek() == -i) {
          stack.pop(); // both destroyed
        } else if (stack.isEmpty() || stack.peek() < 0) {
          stack.push(i); // survives
        }

      } else {
        stack.add(i);
      }
    }
    if (stack.isEmpty()) return new int[0];

    int[] result = new int[stack.size()];
    int index = stack.size() - 1;
    while (!stack.isEmpty()) {
      result[index--] = stack.pop();
    }
    return result;
  }

  public int evalRPN(String[] tokens) {
    Stack<String> stack = new Stack<>();
    for (int i = 0; i < tokens.length; i++) {
      String charater = tokens[i];
      if (!charater.equals("+")
          && !charater.equals("-")
          && !charater.equals("/")
          && !charater.equals("*")) {
        stack.push(String.valueOf(charater));
      } else {

        int b = Integer.parseInt(stack.pop());
        int a = Integer.parseInt(stack.pop());
        int res = 0;
        System.out.println("data is ::" + a + "::" + b);
        switch (charater) {
          case "+":
            res = a + b;
            break;
          case "-":
            res = a - b;
            break;
          case "*":
            res = a * b;
            break;
          case "/":
            res = a / b;
            break;
        }
        stack.push(String.valueOf(res));
      }
    }
    return Integer.parseInt(stack.pop());
  }

  public String removeKdigits(String num, int k) {
    Stack<Character> stack = new Stack<>();

    for (char c : num.toCharArray()) {
      while (!stack.isEmpty() && k > 0 && stack.peek() > c) {
        stack.pop();
        k--;
      }

      stack.push(c);
    }
    while (k > 0 && !stack.isEmpty()) {
      stack.pop();
      k--;
    }

    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }

    sb.reverse();
    // ghis will helo to remove the 0 at the start
    int i = 0;
    while (i < sb.length() && sb.charAt(i) == '0') {
      i++;
    }

    String result = sb.substring(i);

    return result.isEmpty() ? "0" : result;
  }

  //    public String simplifyPath(String path) {
  //        Stack<String> stack = new Stack<>();
  //
  //        StringBuilder temp = new StringBuilder();
  //
  //        for (char c : path.toCharArray()) {
  //            if (c == '/') {
  //                if (temp.length() > 0) {
  //                    stack.push(temp.toString());
  //                    temp.setLength(0);
  //                }
  //                if(stack.isEmpty() && stack.peek()!="/")
  //                stack.push("/"); // store slash
  //            } else if(c=='.'){
  //                String a=  stack.pop();
  //                String b= stack.peek();
  //                if(a=="." && b=="."){
  //                    stack.add(a);
  //                    stack.add(".");
  //                }
  //            }else {
  //                temp.append(c);
  //            }
  //        }
  //    }

  Map<Integer, Integer> map = new HashMap<>();

  public int next(int price) {
    //        Stack<Integer> stack = new Stack<>();
    //
    //            int temp=0;
    //
    //            while (!stack.isEmpty() && stack.peek() <= price) {
    //                System.out.println(stack.peek()+"::"+map);
    //                temp+=map.get(stack.peek());
    //                map.remove( stack.pop());
    //            }
    //            stack.push(price);
    //            map.put(price,temp+1);
    //        return temp+1;
    // alternative only with the stack
    Stack<int[]> stack = new Stack<>();
    int temp = 1;
    while (!stack.isEmpty() && stack.peek()[0] <= price) {
      temp += stack.pop()[1];
    }
    stack.push(new int[] {price, temp});
    return temp;
  }

  public int largestRectangleArea(int[] heights) {
    Stack<int[]> stackleft = new Stack<>();
    Stack<int[]> stackright = new Stack<>();

    int n = heights.length;

    int[] leftheight = new int[n];
    int[] rightheight = new int[n];

    for (int i = 0; i < n; i++) {
      int temp = 1;
      while (!stackleft.isEmpty() && stackleft.peek()[0] >= heights[i]) {
        temp += stackleft.pop()[1];
      }
      stackleft.push(new int[] {heights[i], temp});
      leftheight[i] = temp;
    }

    for (int i = n - 1; i >= 0; i--) {
      int temp = 1;
      while (!stackright.isEmpty() && stackright.peek()[0] >= heights[i]) {
        temp += stackright.pop()[1];
      }
      stackright.push(new int[] {heights[i], temp});
      rightheight[i] = temp;
    }

    int maxHeight = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      maxHeight = Math.max(maxHeight, (rightheight[i] + leftheight[i] - 1) * heights[i]);
    }
    return maxHeight;
  }

  public int trap(int[] height) {
    // ArrayDeque is faster and preferred over the legacy Stack class
    Deque<Integer> stack = new ArrayDeque<>();
    int water = 0;

    for (int i = 0; i < height.length; i++) {
      while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
        int gorund = height[stack.pop()];
        if (stack.isEmpty()) break;
        int left = stack.peek();

        int minHeight = Math.min(height[left], height[i]) - gorund;
        // we do -1 because we are not consider the i and left we only consider thevalue that are in
        // between them
        int weidth = i - left - 1;
        water += minHeight * weidth;
      }
      stack.push(i);
    }
    return water;
  }

  public int maximalRectangle(char[][] matrix) {
    int n = matrix[0].length;
    int ans = Integer.MIN_VALUE;
    int[] heights = new int[n];
    for (int i = 0; i < n; i++) {
      heights[i] = matrix[0][i] - '0';
    }
    ans = Math.max(ans, MRA(heights, n));

    for (int i = 1; i < matrix.length; i++) {
      for (int j = 0; j < n; j++) {
        int value = matrix[i][j] - '0';
        if (value != 0) {
          heights[j] += value;
        } else {
          heights[j] = 0;
        }
      }
      ans = Math.max(ans, MRA(heights, n));
    }
    return ans;
  }

  public int MRA(int[] heights, int size) {
    Stack<int[]> stackleft = new Stack<>();
    Stack<int[]> stackright = new Stack<>();

    int[] leftheight = new int[size];
    int[] rightheight = new int[size];

    for (int i = 0; i < size; i++) {
      int temp = 1;
      while (!stackleft.isEmpty() && stackleft.peek()[0] >= heights[i]) {
        temp += stackleft.pop()[1];
      }
      stackleft.push(new int[] {heights[i], temp});
      leftheight[i] = temp;
    }

    for (int i = size - 1; i >= 0; i--) {
      int temp = 1;
      while (!stackright.isEmpty() && stackright.peek()[0] >= heights[i]) {
        temp += stackright.pop()[1];
      }
      stackright.push(new int[] {heights[i], temp});
      rightheight[i] = temp;
    }

    int maxHeight = Integer.MIN_VALUE;
    for (int i = 0; i < size; i++) {
      maxHeight = Math.max(maxHeight, (rightheight[i] + leftheight[i] - 1) * heights[i]);
    }
    return maxHeight;
  }

  public List<String> buildArray(int[] target, int n) {

    List<String> result = new ArrayList<>();

    int index = 0;
    int size = target.length;
    int i = 1;
    while (i <= n) {
      if (i == target[index]) {
        result.add("push");
        index++;
      } else {
        result.add("push");
        result.add("pop");
      }
      System.out.println(" index::" + index + " " + n);
      if (index == n) return result;
      i++;
    }
    return result;
  }

  public int minLengthAfterRemovals(String s) {

    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {

      if (!stack.isEmpty()
          && ((stack.peek() == 'a' && s.charAt(i) == 'b')
              || (stack.peek() == 'b' && s.charAt(i) == 'a'))) {
        stack.pop();
      } else {
        stack.add(s.charAt(i));
      }
    }

    return stack.size();
  }

  public int[] finalPrices(int[] prices) {
    int n = prices.length;
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < n; i++) {

      // resolve previous elements
      while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
        int idx = stack.pop();
        prices[idx] -= prices[i];
      }

      stack.push(i);
    }

    return prices;
  }

  public String removeDuplicateLetters(String s) {
    int[] lastIndex = new int[26];

    // store last occurrence
    for (int i = 0; i < s.length(); i++) {
      lastIndex[s.charAt(i) - 'a'] = i;
    }

    boolean[] visited = new boolean[26];
    Stack<Character> stack = new Stack<>();

   for(int i=0;i<s.length();i++){

       char ch= s.charAt(i);
       if(visited[ch-'a']) continue;
       // there is change in future so we remove and check the index make sure it there
       while(!stack.isEmpty() && stack.peek()> ch  && lastIndex[stack.peek()-'a']>i){
           visited[stack.pop()-'a']=false;
       }
       stack.add(ch);
       visited[ch-'a']=true;
   }
    StringBuilder sb = new StringBuilder();
    for (char ch : stack) sb.append(ch);

    return sb.toString();
  }


    public String lexSmallestAfterDeletion(String s) {

        int[] lastIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] += 1;
        }
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<s.length();i++){

            char ch= s.charAt(i);
            while(!stack.isEmpty() && stack.peek()> ch  && lastIndex[stack.peek()-'a']>1){
                lastIndex[stack.peek()-'a']-=1;
                stack.pop();
            }
            stack.add(ch);
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : stack) sb.append(ch);

        return sb.toString();
    }
}
