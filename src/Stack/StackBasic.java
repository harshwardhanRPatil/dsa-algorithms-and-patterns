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

  //    public int ping(int t) {
  //
  //    }
}
