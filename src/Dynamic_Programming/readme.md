# 101 Dynamic Programming (DP) – Complete Beginner to Advanced Guide

This repository is a one-stop resource for mastering Dynamic Programming. It moves from the fundamental "Why" to advanced optimization techniques used in high-level technical interviews.

---

## 1. What is Dynamic Programming?

Dynamic Programming is an optimization technique used to solve complex problems by breaking them down into simpler subproblems.

**The Golden Rule:**

> "DP = Recursion + Memory (Memoization)"

It is used when a problem has:

1. **Overlapping Subproblems:** You find yourself solving the same calculation multiple times.
2. **Optimal Substructure:** The optimal solution to the big problem can be built from optimal solutions of its subproblems.

---

## 2. Top-Down vs. Bottom-Up Approach

There are two main ways to implement a DP solution. Understanding the difference is crucial for interviews.

### 2.1 Top-Down (Memoization)

* **Concept:** Start with the final goal and recursive "drill down" to the base cases.
* **Mechanism:** Uses **Recursion**. Before calculating `f(n)`, check if it exists in your `memo` (array or hashmap).
* **Best for:** When you don't need to solve all possible subproblems to find the answer.

### 2.2 Bottom-Up (Tabulation)

* **Concept:** Start from the smallest base cases and "build up" the table iteratively.
* **Mechanism:** Uses **Loops**. You fill a `dp[]` table from `0` to `n`.
* **Best for:** Avoiding stack overflow errors and performing **Space Optimization**.

### Comparison Table

| Feature        | Top-Down (Memoization)               | Bottom-Up (Tabulation) |
| -------------- | ------------------------------------ | ---------------------- |
| **Method**     | Recursive                            | Iterative              |
| **State**      | From goal down to base               | From base up to goal   |
| **Efficiency** | Slightly slower (recursion overhead) | Faster (simple loops)  |
| **Memory**     | Stack space + Table space            | Table space only       |

---

## 3. The 6-Step DP Framework

Whenever you encounter a new DP problem, follow these steps in order:

1. **Define the State:** What does `dp[i]` or `dp[i][j]` represent in plain English? (e.g., "Max profit up to day i").
2. **Identify the Choices:** At the current state, what can you do? (e.g., Pick the coin or skip it).
3. **Write the State Transition:** Create the formula. `dp[n] = f(dp[n-1], dp[n-2])`.
4. **Identify Base Cases:** What is the simplest version of the problem you know the answer to? (e.g., `dp[0] = 0`).
5. **Decide Implementation:** Choose Top-Down or Bottom-Up.
6. **Space Optimization:** Can you replace an array with just 2 variables?

---

## 4. Core DP Patterns

Most DP problems fall into these common categories:

| Pattern           | Common Examples                    | Transition Logic            |
| ----------------- | ---------------------------------- | --------------------------- |
| **1D / Linear**   | Climbing Stairs, House Robber      | `dp[i] = dp[i-1] + ...`     |
| **Knapsack**      | 0/1 Knapsack, Subset Sum           | `include` or `exclude` item |
| **Strings**       | LCS, Edit Distance                 | Compare `s1[i]` and `s2[j]` |
| **Grid / Matrix** | Unique Paths, Min Path Sum         | `dp[i][j] = min(top, left)` |
| **Interval**      | Matrix Chain Multiplication        | Solve for range `[i, j]`    |
| **Trees**         | Diameter of Tree, House Robber III | Solve for children first    |

---

## 5. Complexity Analysis

To estimate if your DP solution will pass time limits:

* **Time Complexity:** Usually `O(N)`, `O(N*M)`, or `O(N^2)` depending on state dimensions.
* **Space Complexity:** Size of DP table + recursion stack (if top-down).

Rule of Thumb:

> If dp has dimensions [n][m] → Time ≈ O(n*m), Space ≈ O(n*m)

---

## 6. Practice Roadmap

### Phase 1: The Basics (Warmup)

* [ ] Fibonacci Numbers
* [ ] Climbing Stairs
* [ ] Min Cost Climbing Stairs

### Phase 2: Fundamental Patterns

* [ ] **Coin Change** (Unbounded Knapsack)
* [ ] **Longest Common Subsequence** (String DP)
* [ ] **Longest Increasing Subsequence** (Array DP)
* [ ] **0/1 Knapsack** (Classic)

### Phase 3: Advanced Optimization

* [ ] Edit Distance
* [ ] Best Time to Buy and Sell Stock (with Cooldown/Fee)
* [ ] Palindromic Partitioning

---

## 7. Common Mistakes to Avoid

* **Wrong Loop Order:** In Bottom-Up, ensure your dependencies are calculated *before* you use them.
* **Off-by-One Errors:** Be careful with array sizes (usually `n + 1`).
* **Initializing with 0:** If the answer can be 0, initialize your DP table with `-1` or `Infinity`.
* **Not Space Optimizing:** If you only need the previous row, don't keep the whole matrix.

---

## 8. Interview Mindset & Heuristics

Ask these questions immediately:

1. What are the **states**?
2. What are the **choices**?
3. What is the **transition**?
4. What are the **base cases**?
5. Can I **optimize space**?

---

## 9. Golden Rules

* If recursion is slow → add memo → it becomes DP.
* If you see "max/min/ways" → suspect DP.
* If brute force is exponential → try DP.

---

## 10. Next Steps

Add code templates for each pattern:

* 1D DP template
* 0/1 Knapsack template
* LCS template
* Grid DP template
* Tree DP template

---

Happy Coding. Master the state, and DP becomes easy.
