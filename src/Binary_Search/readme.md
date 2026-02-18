
---

# 🔍 Binary Search Guide

Binary Search is not just about finding an element in a sorted array.
In interviews, it appears in **multiple patterns**. This guide covers **all core variants** with **when to use**, **how to think**, and **safe templates**.

---

## 1️⃣ Standard Binary Search (Index-Based)

### ✅ When to use

* Array is **sorted** (or monotonic)
* You are searching for an **index**
* Variants: exact match, lower bound, upper bound

### 🎯 Goal

* Find an element
* Find first / last occurrence
* Find insert position

### 🔹 Example: Find an element in a sorted array

```java
int low = 0, high = n - 1;
while (low <= high) {
    int mid = low + (high - low) / 2;
    if (arr[mid] == target) {
        return mid;
    } else if (arr[mid] < target) {
        low = mid + 1;
    } else {
        high = mid - 1;
    }
}
return -1;
```

📌 **Important Note**
For **first / last occurrence**, **do NOT return immediately**.
Store `mid` and continue searching left or right.

---

## 2️⃣ Binary Search on Answer (Solution Space / Min–Max)

### ✅ When to use

* Problem asks:

    * **Minimize the maximum**
    * **Maximize the minimum**
* Answer lies in a **range**, not directly in the array
* You can write a **feasibility check**
* Feasibility is **monotonic**

### 🎯 Goal

* Find **smallest or largest valid answer**

### 🔥 Common Problems

* 🐄 Aggressive Cows → maximize minimum distance
* 📦 Split Array Largest Sum → minimize maximum subarray sum
* 🌸 Min Days to Make Bouquets → minimize days
* 🍌 Koko Eating Bananas → minimize eating speed

---

### 🔹 Template (Integer Answer)

```java
int low = minPossible, high = maxPossible;
int ans = -1;

while (low <= high) {
    int mid = low + (high - low) / 2;
    if (feasible(mid)) {
        ans = mid;
        high = mid - 1;   // MIN problem → move left
    } else {
        low = mid + 1;
    }
}
return ans;
```

### 🧠 Direction Rule (Very Important)

* **Minimize** → move **left** (`high = mid - 1`)
* **Maximize** → move **right** (`low = mid + 1`)

---

## 3️⃣ Binary Search with Precision (Real Numbers)

### ✅ When to use

* Answer is **decimal**
* Need approximation within tolerance
* Examples: square root, speed, ratio, probability

### 🎯 Goal

* Approximate answer up to required precision

### 🔹 Example: Square Root with Precision

```java
double low = 0, high = x;
for (int i = 0; i < 100; i++) { // controls precision
    double mid = (low + high) / 2;
    if (mid * mid <= x) {
        low = mid;
    } else {
        high = mid;
    }
}
return low;
```

---

## ⚠️ Common Binary Search Mistakes

* Returning too early when duplicates exist
* Wrong `low` / `high` initialization
* Feasibility function not monotonic
* Using index-based BS instead of answer-based BS
* Infinite loop (`low < high` vs `low <= high`)
* Integer overflow when computing `mid`

---

## ✅ Quick Checklist (Does this problem need Binary Search?)

* Is the **search space sorted or monotonic**?
* Are we asked **min of max** or **max of min**?
* Is the answer a **number in a range**, not an index?
* Can I write a **`feasible(mid)`** function?

👉 If **YES** → **Binary Search on Answer**

---

## 🧠 One-Line Interview Cheat

> “If the answer is monotonic and lies in a range, I use Binary Search on the answer space.”

---

