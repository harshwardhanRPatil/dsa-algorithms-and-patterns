# ⛓️ Linked List Data Structure — Complete Revision Guide & Problem Patterns

This README is designed as a **one-stop revision guide** for Linked Lists, covering **concepts, patterns, and interview question mapping**. Use it to master the nuances of linear pointer-based structures.

---

## 📌 1. What is a Linked List?

A **Linked List** is a linear data structure where elements are not stored in contiguous memory locations. Instead, elements are linked using pointers.

### Key Properties

* Composed of **Nodes** (Data + Next Pointer)
* **Dynamic size** (allocated at runtime)
* **Non-contiguous** memory allocation
* Access is sequential ()
* Insertions/Deletions are efficient () if the pointer is known

---

## 📌 2. Why Linked Lists?

Linked Lists are preferred over arrays when:

* The number of elements is unknown beforehand.
* Constant time insertions/deletions are required at the beginning or middle.
* You want to avoid the "costly" resizing/shifting of arrays.

In interviews, they test your ability to:

* Handle **Pointers/References** safely.
* Manage **Edge Cases** (Empty list, single node, tail node).
* Implement **In-place algorithms**.

---

## 📌 3. Types of Linked Lists

### 🔹 Singly Linked List

Each node points to the next node. The last node points to `null`.

### 🔹 Doubly Linked List

Each node has two pointers: `next` and `prev`. Allows bidirectional traversal.

### 🔹 Circular Linked List

The tail node points back to the head node, forming a loop.

---

## 📌 4. Linked List Terminology

| Term | Meaning |
| --- | --- |
| **Head** | The first node of the list. |
| **Tail** | The last node (points to `null`). |
| **Node** | The basic unit containing data and a pointer. |
| **Pointer/Next** | The reference to the following node. |
| **Sentinel/Dummy** | A "fake" node used to simplify edge-case logic. |

---

## 📌 5. Basic Operations & Complexity

| Operation | Time Complexity | Note |
| --- | --- | --- |
| **Access/Search** |  | Must traverse from Head. |
| **Insert at Head** |  | Change head pointer. |
| **Insert at Tail** |  |  if tail pointer is maintained. |
| **Delete at Head** |  | Move head to `head.next`. |
| **Delete at Node** |  | Requires reference to the node. |

---

## 📌 6. How to Approach ANY Linked List Problem (Framework)

### Step 1 — Identify the Pattern

1️⃣ **Two Pointers** (Fast & Slow)
2️⃣ **Dummy Node** (Simplifies head changes)
3️⃣ **Reverse** (In-place pointer manipulation)
4️⃣ **Merge/Combine** (Sorted list patterns)
5️⃣ **Cycle Detection** (Floyd’s Cycle-Finding)

---

### Step 2 — Handle Edge Cases (The "Big 3")

* Is the list `null`?
* Does the list have only **one** node?
* Are you at the **tail**? (Avoid `null.next` errors)

---

### Step 3 — Universal Templates

#### 🔥 Two Pointers (Slow & Fast)

Used for finding the middle or detecting cycles.

```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
// slow is now at the middle

```

#### 🔥 Dummy Node Template

Used when the head might change (e.g., deleting nodes, merging).

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode curr = dummy;

while (curr.next != null) {
    if (condition) curr.next = curr.next.next;
    else curr = curr.next;
}
return dummy.next;

```

---

## 📌 7. Essential Techniques

### 🔄 Reversing a List (In-Place)

Crucial for many problems like "Palindrome Linked List".

```java
ListNode prev = null, curr = head;
while (curr != null) {
    ListNode nextTemp = curr.next;
    curr.next = prev;
    prev = curr;
    curr = nextTemp;
}
return prev;

```

---

## 📌 8. Problem Patterns + Examples

### 🔗 A. Fast & Slow Pointers

**Pattern:** Find middle, find K-th node from end, detect cycle.

* **Examples:** Linked List Cycle, Middle of the Linked List, Palindrome Linked List.

### 🔗 B. Reversal Patterns

**Pattern:** Reverse portions or the whole list.

* **Examples:** Reverse Linked List, Reverse Nodes in k-Group, Reorder List.

### 🔗 C. Merging/Sorting

**Pattern:** Combine two lists or sort one.

* **Examples:** Merge Two Sorted Lists, Merge k Sorted Lists, Sort List (Merge Sort).

### 🔗 D. Dummy Node & Deletion

**Pattern:** Safely removing nodes without losing the head.

* **Examples:** Remove N-th Node From End, Delete Node in a Linked List, Remove Duplicates.

---

## 📌 9. Common Interview Questions (Pattern-wise)

### ✅ Easy

* Reverse Linked List
* Merge Two Sorted Lists
* Linked List Cycle
* Middle of the Linked List

### ✅ Medium

* Add Two Numbers
* Copy List with Random Pointer
* LRU Cache (Doubly Linked List + Hashmap)
* Odd Even Linked List

### ✅ Hard

* Merge k Sorted Lists
* Reverse Nodes in k-Group
* Linked List Cycle II (Find Start of Loop)

---

## 📌 10. Linked List vs Array — When to Use What?

| Requirement | Use |
| --- | --- |
| Frequent Insert/Delete | **Linked List** |
| Random Access (Indexing) | **Array** |
| Memory Efficiency | **Array** (LL has pointer overhead) |
| Unknown Growth | **Linked List** |
| Cache Locality | **Array** |

---

## 📌 11. Final Interview Tips

✔ **Use a Dummy Node:** It saves you from `if (head == null)` checks 90% of the time.
✔ **Draw it out:** Pointers are confusing; trace the `next` links on paper.
✔ **Order matters:** When swapping nodes, be careful not to break the chain and lose access to the rest of the list.
✔ **Fast/Slow Pointer:** If you need to find a specific position in one pass, this is usually the answer.

---

🎯 **Use this README as a daily revision checklist before interviews.**

Would you like me to generate a **visual guide for Floyd’s Cycle Detection** or a **step-by-step breakdown of Merge Sort on Linked Lists**?