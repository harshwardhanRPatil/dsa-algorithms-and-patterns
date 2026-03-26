
---

# ⚡ **Greedy Algorithm — Complete Guide**

## 📌 **1. What is a Greedy Algorithm?**

A **Greedy Algorithm** makes decisions **step-by-step**, choosing the **locally optimal choice** at each step **hoping**
that it will lead to a global optimum.

### In simple terms:

👉 “Pick the best available option RIGHT NOW.”

### Key Characteristics:

* Builds the solution piece-by-piece
* No backtracking
* No reconsideration of earlier decisions
* Very fast (usually **O(n log n)** or **O(n)**)

---

## 📌 **2. Why Use Greedy Algorithms?**

Greedy works when:

* Local optimum → leads to global optimum
* Problem exhibits **optimal substructure**
* You can sort something (intervals, tasks, values)
* You can make independent decisions

Greedy is widely used in:

* Scheduling systems
* Resource allocation
* Network routing
* Priority-based decisions
* Optimization problems

---

# 📌 **3. When Does Greedy Work? (The Greedy Check)**

Before applying greedy, ask:

### ✔ Can I make decisions independently?

### ✔ Does sorting help?

### ✔ Are earlier choices irreversible?

### ✔ Do subproblems reduce naturally after a choice?

If YES → greedy likely works.

If NOT → use **DP**.

---

# 📌 **4. Greedy vs Dynamic Programming**

| Greedy                          | DP                             |
|---------------------------------|--------------------------------|
| Fast (O(n) or O(n log n))       | Slower                         |
| Makes immediate choice          | Considers all possibilities    |
| No backtracking                 | Backtracking allowed           |
| Works only for special problems | Works for all optimal problems |

Use **DP** if choices depend on future outcomes.
Use **Greedy** if the optimal local choice guarantees the global optimum.

---

# 📌 **5. Common Greedy Techniques**

### ✅ **1. Sort + Pick**

Most greedy problems start by sorting:

* Sort by **end time** → interval problems
* Sort by **value/weight** → knapsack
* Sort by **ratio** → maximize/minimize

---

### ✅ **2. Use a Min-HeapPriorityQueue / Max-HeapPriorityQueue**

Useful when always picking:

* Largest
* Smallest
* Most frequent

Examples:

* Reorganize string
* Connect ropes with minimum cost
* CPU scheduling

---

### ✅ **3. Two Pointers**

Used when sorting + scanning helps.

Examples:

* Assign cookies
* Boats to save people
* Two-sum variants

---

### ✅ **4. Priority-based decisions**

Pick tasks/jobs based on:

* Highest profit
* Earliest deadline
* Smallest weight

---

# 📌 **6. Greedy Problem Categories (Important!)**

Greedy problems fall into these patterns:

---

## 🌟 **A. Interval Scheduling / Activity Selection**

Choose non-overlapping intervals.

Problems:

* Meeting Rooms
* Non-overlapping intervals
* Minimum rooms required
* Merge intervals

Idea:

```
Sort by end time → pick earliest finishing jobs
```

---

## 🌟 **B. Minimization / Maximization Problems**

Find minimum number of operations, or maximum value.

Examples:

* Min arrows to burst balloons
* Max units on a truck
* Min cost to connect sticks

Techniques:

* Sort
* HeapPriorityQueue

---

## 🌟 **C. Resource Allocation**

Assign limited resources optimally.

Examples:

* Assign cookies
* Gas station
* Job sequencing

Think:
👉 “Give smaller resource to smaller requirement first.”

---

## 🌟 **D. Constructive Greedy**

Build the solution step-by-step:

Examples:

* Reorganize string
* Remove K digits
* Make string valid parentheses

Techniques:

* Stack
* Frequency arrays
* Greedy removal

---

## 🌟 **E. Greedy with Counting**

Problems involving frequency:

Examples:

* Majority element
* Can reorder to form power of 2
* Partition labels

---

## 🌟 **F. Greedy on Graphs**

Advanced but important.

Examples:

* Prim’s MST
* Kruskal’s MST
* Dijkstra’s shortest path (uses greedy + heap)

---

# 📌 **7. Universal Greedy Problem Approach (Must Read!)**

When solving a greedy problem:

---

### ✔ Step 1: Identify what to optimize

* Maximize value
* Minimize cost
* Reduce overlaps
* Minimize operations

---

### ✔ Step 2: Ask yourself

**“If I had to make the choice RIGHT NOW, what would it be?”**

Example:

* Pick interval with smallest end time
* Pick job with largest profit
* Remove largest digit if it blocks a smaller digit

---

### ✔ Step 3: Sort or use a heap

Almost EVERY greedy solution sorts first.

---

### ✔ Step 4: Simulate the decision-making

Walk through example inputs and confirm greedy works.

---

### ✔ Step 5: Implement using simple loops

Greedy code is ALWAYS small & simple!

---

# 📌 **8. Classic Greedy Problems (Google, Amazon, Meta)**

### 💡 Easy

* Assign Cookies
* Maximum Units on a Truck
* Lemonade Change
* Largest Perimeter Triangle
* Minimum Sum of Four Digit Number

### 💡 Medium

* Jump Game
* Gas Station
* Partition Labels
* Remove K Digits
* Reorganize String
* Min Arrows to Burst Balloons
* Boats to Save People
* Task Scheduler
* Hand of Straights

### 💡 Hard

* Candy Problem
* Trapping Rain Water (two pointers)
* Job Sequencing Problem
* Merge Stones
* Minimum Deviation
* Dijkstra / Prim / Kruskal (graph greedy)

---

# 📌 **9. Greedy Code Templates**

## ✔ Sorting-based greedy

```java
Arrays.sort(intervals, (a, b) ->a[1]-b[1]);
int count = 0;
int end = -1;

for(
int[] iv :intervals){
        if(iv[0]>=end){
count++;
end =iv[1];
        }
        }
```

---

## ✔ HeapPriorityQueue-based greedy

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
for(
int x :nums)pq.

add(x);

while(pq.

size() >1){
int a = pq.poll();
int b = pq.poll();
    pq.

add(a +b);
}
```

---

## ✔ Greedy eliminating characters

```java
Stack<Character> st = new Stack<>();
for(
char c :s.

toCharArray()){
        while(!st.

isEmpty() &&k >0&&st.

peek() >c){
        st.

pop();

k--;
        }
        st.

push(c);
}
```

---

# 📌 **10. Tips for Cracking Greedy Problems**

### ✔ Try greedy before DP

If greedy works → faster.

### ✔ Always test greedy on tricky examples

Focus on corner cases:

* Equal values
* Overlapping intervals
* Negative numbers
* Large ranges

### ✔ Use sorting as your superpower

Sorting is 90% of greedy.

### ✔ Memorize the patterns

Greedy is pattern-based more than logic-based.
* Revise  -: https://leetcode.com/discuss/post/7370510/20-greedy-problems-to-revise-before-inte-jl4c/

---

# 🎯 **This README is perfect for GitHub — clean, structured, interview-ready.**

If you want, I can also generate:

📌 A **Greedy Cheatsheet PDF**
📌 A folder of solved greedy problems
📌 Visual flowcharts for greedy decisions

Just tell me **“give greedy cheat sheet”** or **“give flowchart”**.
