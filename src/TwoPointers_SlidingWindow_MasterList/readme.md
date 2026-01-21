
---

# 🧩 Two Pointers & Sliding Window — DSA Tracker (Java Edition)

This repository is a comprehensive tracker for Two Pointers and Sliding Window problems, aimed at SDE-2 / MAANG-level preparation. It is structured to help you identify, approach, and master each problem using optimized Java implementations.

## 📊 Quick Links
* **Main Tracker**: [DSA Progress Excel Sheet](https://docs.google.com/spreadsheets/d/1WVqDLo54GPWAzlp9yNbh0w8fXNOEUZnSY_H722bvQ7Y/edit?gid=844808582#gid=844808582)
## 📌 Table of Contents

1. [What is the Goal?](https://www.google.com/search?q=%231-what-is-the-goal)
2. [Identification & Keywords](https://www.google.com/search?q=%232-how-to-identify)
3. [Java Templates & Implementation](https://www.google.com/search?q=%233-java-templates)
4. [Types & Patterns](https://www.google.com/search?q=%234-types--patterns)
5. [The "At-Most-K" Trick](https://www.google.com/search?q=%235-the-at-most-k-trick)
6. [Best Practices & Common Pitfalls](https://www.google.com/search?q=%236-best-practices)

---

## 1️⃣ What is the Goal?

The primary goal is to optimize brute-force  or  solutions into linear time complexity.

* **Time Complexity:** Target  (or  if sorting is needed).
* **Space Complexity:** Target  for Two Pointers;  or  for Sliding Window (using `HashMap` or `int[]` frequency arrays).

---

## 2️⃣ How to Identify These Problems

Look for these specific keywords in the problem description:

| Pattern | Keywords / Cues |
| --- | --- |
| **Two Pointers** | "Sorted Array", "Pairs", "Triplets", "In-place", "Reverse/Swap" |
| **Sliding Window** | "Contiguous", "Subarray", "Substring", "Longest/Shortest with condition" |

---

## 3️⃣ Java Templates

Use these standard structures to write bug-free code during interviews.

### A. Variable Sliding Window (Java)

```java
public int slidingWindowTemplate(int[] nums) {
    int left = 0, right = 0, result = 0;
    Map<Integer, Integer> map = new HashMap<>(); // or int[256] for chars

    for (right = 0; right < nums.size(); right++) {
        // 1. Expand: Add nums[right] to state
        map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

        // 2. Shrink: While condition is violated
        while (conditionBroken()) {
            map.put(nums[left], map.get(nums[left]) - 1);
            if (map.get(nums[left]) == 0) map.remove(nums[left]);
            left++;
        }

        // 3. Update Result
        result = Math.max(result, right - left + 1);
    }
    return result;
}

```

### B. Two Pointers (Opposite Direction)

```java
public void twoPointerTemplate(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    
    while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum == target) return new int[]{left, right};
        else if (sum < target) left++;
        else right--;
    }
}

```

---

## 4️⃣ Types & Patterns

### 🔹 Two Pointers

* **Opposite Ends:** (Two Sum II, Container with Most Water).
* **Slow-Fast:** (Remove duplicates, Linked List cycle).
* **Three-Pointer:** (Sort Colors / Dutch National Flag).

### 🔹 Sliding Window

* **Fixed Size:** Window length `k` is constant (Max Average Subarray).
* **Variable Size:** Window grows/shrinks (Longest Substring Without Repeating Characters).
* **Monotonic Deque:** Using `ArrayDeque` to find Max/Min in a window.

---

## 5️⃣ The "At-Most-K" Trick

For "Hard" problems like *Subarrays with K Different Integers*, calculating "exactly K" is difficult because the window movement isn't monotonic. Use this mathematical identity:

This allows you to reuse the same "At Most" sliding window function twice.

---

## 6️⃣ Best Practices & Common Pitfalls

* **Frequency Arrays:** In Java, if you are dealing only with lowercase English letters, use `int[26]` instead of `HashMap<Character, Integer>` for a significant performance boost.
* **Off-by-One:** Remember that the length of a window is `(right - left + 1)`.
* **The Zero-Count Map Pitfall:** When shrinking the window, if a character's frequency reaches `0`, you **must** remove the key from the Map, or `map.size()` will be incorrect.
* **Dry Run:** Always trace the window movement for an array of size 3 before finalizing your `while` loop logic.

---

