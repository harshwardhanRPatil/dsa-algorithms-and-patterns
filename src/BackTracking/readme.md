---

# 📘 The Complete Backtracking Guide

---

## 🔹 What is Backtracking?

Backtracking is an algorithmic technique used to **build solutions incrementally** by exploring all possibilities and abandoning invalid ones.

It works by recursively:

* Trying all possible choices
* Undoing (backtracking) when a choice fails

👉 **Mental Model:**
**Try → Explore → Undo → Try Next**

---

## 🔹 When to Use Backtracking? (Identification)

Use backtracking when:

* ✅ **All possible answers are required**
  (subsets, permutations, combinations)

* ✅ **Keywords appear**
  *“Find all…”*, *“Return all…”*, *“Generate…”*

* ✅ **Small constraints (n ≤ 20)**
  → Indicates exponential solutions

* ✅ **Decision tree exists**

    * Pick / Not Pick
    * Choose from options
    * Place items

---

## 🔹 General Template (The Golden Rule)

```java
void backtrack(State state, List<Result> results) {
    
    // 1. Base Case
    if (isGoal(state)) {
        results.add(new ArrayList<>(state)); // copy is IMPORTANT
        return;
    }

    // 2. Try all choices
    for (Choice choice : getChoices(state)) {

        // 3. Choose
        makeChoice(state, choice);

        // 4. Explore
        backtrack(state, results);

        // 5. Undo (Backtrack)
        undoChoice(state, choice);
    }
}
```

---

## 🔹 How Backtracking Actually Works (Important)

👉 Think in terms of a **recursion tree**

Example:

```
nums = [1,2]

        []
       /  \
     [1]  []
     / \   / \
 [1,2][1][2][]
```

* Each level = a decision
* Each path = one possible answer

---

## 🔹 Core Patterns & Archetypes

---

### 🔸 1. Subset / Pick-Not-Pick

**Concept:** Each element → 2 choices

```java
void solve(int i, List<Integer> temp) {
    if (i == nums.length) {
        result.add(new ArrayList<>(temp));
        return;
    }

    // Pick
    temp.add(nums[i]);
    solve(i + 1, temp);

    temp.remove(temp.size() - 1); // undo

    // Not Pick
    solve(i + 1, temp);
}
```

📌 Problems:

* Subsets
* Subsets II

---

### 🔸 2. Permutations

**Concept:** Order matters

```java
void solve(List<Integer> temp, boolean[] visited) {
    if (temp.size() == nums.length) {
        result.add(new ArrayList<>(temp));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (visited[i]) continue;

        visited[i] = true;
        temp.add(nums[i]);

        solve(temp, visited);

        temp.remove(temp.size() - 1);
        visited[i] = false;
    }
}
```

📌 Problems:

* Permutations
* Permutations II

---

### 🔸 3. Combination (Target Based)

**Concept:** Build sum / constraint

```java
void solve(int index, int target, List<Integer> temp) {
    if (target == 0) {
        result.add(new ArrayList<>(temp));
        return;
    }

    for (int i = index; i < nums.length; i++) {
        if (nums[i] > target) break;

        temp.add(nums[i]);
        solve(i, target - nums[i], temp); // reuse allowed

        temp.remove(temp.size() - 1);
    }
}
```

📌 Problems:

* Combination Sum
* Combination Sum II

---

### 🔸 4. Partition / Cut Problems

**Concept:** Split into valid parts

```java
void solve(int start, List<String> temp) {
    if (start == s.length()) {
        result.add(new ArrayList<>(temp));
        return;
    }

    for (int end = start; end < s.length(); end++) {
        if (isValid(start, end)) {
            temp.add(s.substring(start, end + 1));
            solve(end + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
```

📌 Problems:

* Palindrome Partitioning
* Word Break II

---

### 🔸 5. Grid / DFS Backtracking

**Concept:** Move in 4 directions

```java
boolean dfs(int r, int c) {
    if (invalid) return false;

    visited[r][c] = true;

    boolean found =
        dfs(r+1,c) || dfs(r-1,c) ||
        dfs(r,c+1) || dfs(r,c-1);

    visited[r][c] = false; // undo
    return found;
}
```

📌 Problems:

* Word Search
* Sudoku Solver

---

### 🔸 6. Constraint Placement

**Concept:** Place safely

```java
void solve(int row) {
    if (row == n) {
        result.add(board);
        return;
    }

    for (int col = 0; col < n; col++) {
        if (isSafe(row, col)) {
            place(row, col);
            solve(row + 1);
            remove(row, col);
        }
    }
}
```

📌 Problems:

* N-Queens

---

## 🔹 Crucial Optimization Techniques

---

### 🔸 1. Pruning (🔥 Most Important)

Stop early if invalid:

```java
if (sum > target) return;
if (outOfBounds) return;
```

👉 Reduces exponential tree drastically

---

### 🔸 2. Handling Duplicates

```java
Arrays.sort(nums);

if (i > index && nums[i] == nums[i - 1]) continue;
```

---

### 🔸 3. Undo Step (Most Common Bug)

```java
temp.add(x);
solve();
temp.remove(temp.size() - 1); // MUST
```

---

## 🔹 Time & Space Complexity

| Type         | Time Complexity | Space |
| ------------ | --------------- | ----- |
| Subsets      | O(2^n)          | O(n)  |
| Permutations | O(n!)           | O(n)  |
| Combination  | O(2^n)          | O(n)  |
| Grid DFS     | O(m*n*4^L)      | O(L)  |
| N-Queens     | O(n!)           | O(n)  |

---

## 🔹 Quick Pattern Mapping

| Problem Type     | Pattern         |
| ---------------- | --------------- |
| All subsets      | Pick / Not Pick |
| All permutations | Visited / Swap  |
| Target sum       | Combination     |
| String split     | Partition       |
| Grid problems    | DFS             |
| Placement        | Constraint      |

---

## 🔹 How to Think in Interviews

Ask yourself:

1. What are my choices?
2. Can I try all possibilities?
3. How do I undo?
4. What is base case?

👉 If this fits → **Backtracking**

---

## 🔹 Common Mistakes

* ❌ Forgetting undo step
* ❌ Not handling duplicates
* ❌ Wrong base case
* ❌ Modifying original list (no copy)
* ❌ Missing pruning

---

## 🔹 Final Summary

Backtracking =
👉 **Brute Force + Pruning**

Steps:

1. Choose
2. Explore
3. Undo

---

## 🚀 Pro Tip (Advanced Insight)

Most problems reduce to:

* **Decision Tree + Constraints**
* Optimize by **cutting branches early**

👉 Master these 5 problems:

* Subsets
* Permutations
* Combination Sum
* Word Search
* N-Queens

---
