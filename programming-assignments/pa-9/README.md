# PA9 — TreeMap & TreeSet (Binary Search Trees) in Java

## Overview
This project implements **binary-search-tree–backed collections** in Java:
- A generic **`TreeMap<K,V>`** that stores key–value pairs (`MapEntry<K,V>`) with either **natural key ordering** or a **custom `Comparator`**.
- A generic **`TreeSet<E>`** (value-only BST) supporting add/remove/search and traversals.
- A **`Test`** driver that loads a dataset of flower → colors from `flowers.txt`, exercises the API (search, min/max, floor/ceiling, keys/values), and repeats the tests with a **custom comparator**.

---

## Key Features

### `TreeMap<K,V>`
- **Ordering:** natural (`K extends Comparable<K>`) or **custom** via `Comparator<K>`.
- **Core operations:**  
  - `add(K,V)` — inserts a new key–value pair (rejects duplicate keys)  
  - `remove(K)` — deletes a key and re-links the BST correctly (all cases: 0/1/2 children)  
  - `contains(K)` — iterative search  
  - `clear()`, `size()`, `isEmpty()`  
- **Queries:**  
  - `first()` / `last()` — smallest / largest key  
  - `ceiling(K)` — smallest key ≥ `K` (or `null`)  
  - `floor(K)` — largest key ≤ `K` (or `null`)  
  - `keys()` — in-order traversal of **keys** as a `Collection<K>`  
  - `values()` — in-order traversal of **values** as a `Collection<V>`  
  - `preorder()`, `inorder()`, `postorder()` — formatted traversals (console output)
- **Node structure:** `TreeNode` holds a `MapEntry<K,V>` (`key`, `value`) plus `left`/`right`.

### `TreeSet<E>`
- Value-only BST with **natural ordering** (`E extends Comparable<E>`).
- `add(E)`, `remove(E)`, `contains(E)`, `clear()`, `size()`, `isEmpty()`.
- `preorder()`, `inorder()`, `postorder()` for traversal output.

### `MapEntry<K,V>`
- Minimal key–value container with getters/setters and `toString()` → `"(key, value)"`.

---

## Project Structure
PA9/
├── MapEntry.java // (K,V) pair with getters/setters/toString
├── TreeMap.java // BST-backed map with ceiling/floor/keys/values/traversals
├── TreeSet.java // BST-backed set with add/remove/contains/traversals
├── Test.java // Loads flowers.txt, runs 26 test cases (natural + comparator)
└── flowers.txt // CSV dataset: flower name, followed by one or more colors


---

## Input Format (`flowers.txt`)
Each line is a **CSV**: the **first token** is the flower name (key), remaining tokens are colors (values list).
Lavender,purple
Geranium,white,red,pink,purple,orange
Hyacinth,purple,blue,pink,red,orange,yellow,white
...

---

## Build & Run
Compile and execute from the project directory:
```bash
javac MapEntry.java TreeMap.java TreeSet.java Test.java
java Test

Algorithmic Complexity (BST, worst-case if unbalanced)

Search / Insert / Remove: O(h) where h is tree height → worst-case O(n), average O(log n)

first() / last() / floor() / ceiling(): O(h)

Traversals (inorder, keys, values): O(n)

Space: O(n) nodes

What This Demonstrates

Implementing ordered maps/sets over a BST with both natural and custom ordering.

Correct handling of BST deletion cases (leaf, one child, two children with predecessor swap).

Implementing useful ordered-map operations: first/last, floor/ceiling, and in-order collections of keys/values.

Building a small, testable data-structure library with clear traversal and inspection utilities.
