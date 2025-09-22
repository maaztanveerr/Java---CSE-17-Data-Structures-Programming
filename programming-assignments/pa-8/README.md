# PA8 — Generic Collections, Linked List, Array List, and Priority Queue (Java)

## Overview
This project implements a small **collections framework** in Java, including a generic `Collection` and `List` interface, two list implementations (`ArrayList`, `LinkedList`), and a `PriorityQueue` (min-heap). A `Test` driver exercises the APIs to validate functionality such as insertion, deletion, iteration, cloning, bulk operations, searching, sorting, and conversion to arrays.

---

## Key Components

### Interfaces
- **`Collection<E>`**
  - Core operations: `add`, `addAll`, `retainAll`, `removeAll`, `clear`, `contains`, `equals`, `isEmpty`, `iterator`, `remove`, `size`, `toArray`.
- **`List<E>` extends `Collection<E>`**
  - Indexed and deque-style operations: `add(int, E)`, `get`, `set`, `indexOf`, `lastIndexOf`, `sort`, `addFirst`, `addLast`, `getFirst`, `getLast`, `listIterator()`, `listIterator(int)`, `remove(int)`, `removeFirst`, `removeLast`.

### Implementations
- **`ArrayList<E>`**
  - Array-backed list with dynamic growth (`ensureCapacity()` uses 1.5× strategy).
  - Supports all `List` and `Collection` methods, iterators (`Iterator`, `ListIterator`), `clone()`, and `toArray()`.
  - `sort(Comparator<E>)` implemented by **draining to a min-heap** (`PriorityQueue<E>`) and rebuilding in ascending order.

- **`LinkedList<E>`**
  - **Doubly-linked list** with `head`/`tail` pointers.
  - O(1) deque operations: `addFirst`, `addLast`, `getFirst`, `getLast`, `removeFirst`, `removeLast`.
  - Full `List` and `Collection` support, `indexOf` / `lastIndexOf`, `remove(int)`, `set`, `add(int,E)`.
  - `ListIterator<E>` supports forward/backward traversal (`hasNext/next`, `hasPrevious/previous`).

- **`PriorityQueue<E>`**
  - ArrayList-based **min-heap** with optional `Comparator<E>`.
  - Key methods: `offer` (heapify-up), `poll` (heapify-down), `peek`, `isEmpty`, `clear`, `size`.

- **`Test`**
  - Runs the same test suite on both `ArrayList<Integer>` and `LinkedList<Integer>`:
    - Adds via multiple APIs (`add`, `add(index,…)`, `addFirst`, `addLast`).
    - `clone()` and **bulk operations** (`addAll`, `retainAll`, `removeAll`).
    - Accessors and mutators (`get`, `getFirst`, `getLast`, `set`).
    - Removal (`remove(Object)`, `remove(int)`, `removeFirst`, `removeLast`).
    - **Search** (`contains`, `indexOf`, `lastIndexOf`).
    - **Iteration** (`Iterator`, `ListIterator`, `ListIterator(int)`).
    - **Sorting** with natural order and a custom comparator (by number-of-digits).
    - `toArray()` conversion.

---

## Project Structure
PA8/
├── Collection.java // Base collection interface
├── List.java // List interface (extends Collection)
├── ArrayList.java // Array-backed List implementation + iterators
├── LinkedList.java // Doubly-linked List implementation + iterators
├── PriorityQueue.java // Min-heap with optional Comparator
└── Test.java // Driver that exercises all features on both lists


---

## Build and Run
```bash
# Compile
javac Collection.java List.java ArrayList.java LinkedList.java PriorityQueue.java Test.java

# Run
java Test

Selected API Details
ArrayList<E>

Growth: automatic (1.5×).

Iteration: Iterator<E> and ListIterator<E> (forward/backward).

Sorting: sort(Comparator<E>) via PriorityQueue (stable-by-heap order not guaranteed).

Time complexity (typical):

add(e) amortized O(1); add(i,e) O(n)

get/set O(1)

remove(i) O(n)

contains/indexOf/lastIndexOf O(n)

iterator/listIterator creation O(1)

sort O(n log n) using heap

LinkedList<E>

Structure: nodes with value, next, previous.

Deque ops: addFirst/addLast/getFirst/getLast/removeFirst/removeLast all O(1).

Indexed ops: get(i), set(i,…), remove(i), add(i,…) traverse → O(n).

Bulk ops: addAll, retainAll, removeAll.

Iteration: Iterator<E> and ListIterator<E> with forward/backward traversal.

Sorting: sort(Comparator<E>) via PriorityQueue (O(n log n)).

PriorityQueue<E>

Comparator-aware: uses provided Comparator<E>; otherwise E must be Comparable.

Operations: offer O(log n), poll O(log n), peek O(1).

Complexity Summary

Insertion (end): ArrayList.add(e) amortized O(1); LinkedList.addLast(e) O(1).

Random access: ArrayList.get(i) O(1); LinkedList.get(i) O(n).

Removals by index: O(n) for both (shift for array; traverse/relink for list).

Search: O(n) for both (contains, indexOf, lastIndexOf).

Sorting: O(n log n) using heap-based approach in both implementations.

Deque operations (LL only): O(1) for first/last add/get/remove.

What This Demonstrates

Designing a type-safe collections API and implementing multiple concrete data structures.

Implementing iterators and list iterators for forward/backward traversal.

Using a heap to implement comparator-driven sorting across different list backings.

Understanding and documenting algorithmic complexity and trade-offs between array-based and linked lists.
