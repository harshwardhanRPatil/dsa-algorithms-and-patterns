Absolutely! Following the style of your Greedy README, here’s a **complete README for Heaps / Priority Queues** — clean, structured, and interview-ready. You can save this as `README.md` in your repo.

---

# ⚡ HeapPriorityQueue / Priority Queue — Complete Guide

---

## 📌 1. What is a HeapPriorityQueue?

A **heap** is a specialized tree-based data structure that satisfies the **heap property**:

* **Min-HeapPriorityQueue:** Parent ≤ Children → smallest element at root
* **Max-HeapPriorityQueue:** Parent ≥ Children → largest element at root

**In simple terms:**
👉 “Quick access to the smallest or largest element.”

**Key Characteristics:**

* Complete binary tree (all levels full except possibly the last)
* Efficient min/max access
* Used to implement **priority queues**

---

## 📌 2. Why Use Heaps?

Heaps are useful when you need to:

* Maintain **top K elements** efficiently
* Get **minimum or maximum** repeatedly
* Merge **sorted arrays or lists**
* Solve **greedy problems** like scheduling or cost minimization

Common applications:

* Kth largest / smallest element
* Merge K sorted lists
* Dijkstra / Prim / Kruskal algorithms
* Task scheduling and priority-based processing
* Sliding window minimum / maximum

---

## 📌 3. Types of Heaps

| Type     | Property          | Root Element |
| -------- | ----------------- | ------------ |
| Min-HeapPriorityQueue | Parent ≤ Children | Smallest     |
| Max-HeapPriorityQueue | Parent ≥ Children | Largest      |

---

## 📌 4. HeapPriorityQueue Operations

| Operation        | Description                               | Time Complexity |
| ---------------- | ----------------------------------------- | --------------- |
| `insert / offer` | Add a new element                         | O(log n)        |
| `peek`           | Look at the root element without removing | O(1)            |
| `poll / remove`  | Remove and return the root element        | O(log n)        |
| `heapify`        | Convert an array into a heap              | O(n)            |

---

## 📌 5. Using Heaps in Java

### Min-HeapPriorityQueue (default)

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(5);
minHeap.offer(2);
minHeap.offer(8);

System.out.println(minHeap.poll()); // 2
```

### Max-HeapPriorityQueue

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
maxHeap.offer(5);
maxHeap.offer(2);
maxHeap.offer(8);

System.out.println(maxHeap.poll()); // 8
```

---

## 📌 6. Custom Object HeapPriorityQueue

```java
class Node {
    List<Integer> value;   // e.g., indices
    int sum_value;         // key for comparison

    Node(List<Integer> list, int sum_value) {
        this.value = list;
        this.sum_value = sum_value;
    }
}

PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.sum_value - b.sum_value);
```

---

## 📌 7. How to Identify HeapPriorityQueue Problems

HeapPriorityQueue problems often require:

* **Top K smallest/largest elements**
* **Frequent min/max retrieval**
* **Dynamic streaming data**
* **Greedy optimization problems**

**Signs to use a heap:**

✔ Problem mentions “priority” or “top K”
✔ You need repeated access to min/max
✔ Merging sorted arrays or intervals
✔ Scheduling tasks or processing jobs by priority

---

## 📌 8. Common HeapPriorityQueue Patterns

✅ **1. Min/Max HeapPriorityQueue for Top K**

* Maintain size K heap
* Examples: Kth largest element, Top K frequent elements

✅ **2. Merge K Sorted Lists / Arrays**

* Each list is treated as a sorted stream
* Push first element of each list into heap
* Pop smallest, push next element of same list

✅ **3. Sliding Window Min/Max**

* Keep track of min/max in a window
* HeapPriorityQueue allows efficient update when window moves

✅ **4. Greedy + HeapPriorityQueue**

* When greedy requires picking **min/max repeatedly**
* Examples: Connect ropes, task scheduling, Dijkstra’s shortest path

---

## 📌 9. HeapPriorityQueue Problem Categories

| Category              | Examples                               |
| --------------------- | -------------------------------------- |
| Top K Elements        | Kth largest/smallest, Top K frequent   |
| Merge / Stream        | Merge K sorted lists, K closest points |
| Interval / Scheduling | Task scheduler, Meeting rooms          |
| Graphs                | Prim’s MST, Kruskal’s MST, Dijkstra    |

---

## 📌 10. Universal HeapPriorityQueue Approach

1. **Identify the key:** Min or Max element to optimize
2. **Decide heap type:** Min-heap for smallest, Max-heap for largest
3. **Initialize heap:** Often push first element of each list/row
4. **Extract / Process:** Pop root, push next candidate if needed
5. **Repeat until condition met:** e.g., top K, window moves, or end of list

---

## 📌 11. HeapPriorityQueue Time & Space Complexity

| Operation / Pattern | Time Complexity | Space Complexity |
| ------------------- | --------------- | ---------------- |
| Insert / Poll       | O(log n)        | O(n)             |
| Peek                | O(1)            | O(n)             |
| Maintain top K      | O(n log k)      | O(k)             |
| Merge K sorted      | O(k log k)      | O(k)             |

---

## 📌 12. Example: K Smallest Pairs

* Use **Node** with `[i,j]` indices and sum
* Initialize heap with `(i,0)` for each `i`
* Pop smallest sum, push next pair `(i,j+1)`
* Repeat until `k` pairs extracted

```java
heap.add(new Node(Arrays.asList(i,0), nums1[i]+nums2[0]));
```

* This ensures **all combinations are considered efficiently**
* Complexity: O(k log k), Space: O(k)

---

## 📌 13. Interview Tips

✔ Always check if a problem requires **priority access**
✔ Use **heap for top K or merging sorted lists**
✔ Prefer **lazy expansion** instead of brute-force generation
✔ Remember: **heap is a structure, priority queue is its Java implementation**

---

This README is **pattern-based**, just like your Greedy one, so it’s **interview-ready and GitHub-friendly**.

---

If you want, I can also create a **visual version** with:

* Min-HeapPriorityQueue / Max-HeapPriorityQueue tree diagrams
* Step-by-step heap expansion for `K smallest pairs`

This makes it very **attractive for GitHub**.

References

* HeapPriorityQueue on GeeksforGeeks
* Java PriorityQueue Documentation
* LeetCode Problems: 215, 373, 703

