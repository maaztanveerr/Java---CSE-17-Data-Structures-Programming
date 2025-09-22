# PA10 — Hash Maps in Java (Linear Probing vs. Separate Chaining)

## Overview
This project implements and benchmarks two classic **hash table** strategies in Java:

- **`HashMapLP<K,V>`** — Open addressing with **linear probing**  
- **`HashMapSC<K,V>`** — **Separate chaining** using linked lists

A common abstract API (**`HashMap<K,V>`**) is provided, and both implementations are exercised on a realistic workload of **62K+ movies** and **ratings**. The driver measures **hash table characteristics**, **lookup/remove iteration counts**, and performs a **merge sort** over aggregated movie statistics.

---

## What’s Included
- **`HashMap<K,V>`** — abstract base with shared contract (`put`, `get`, `remove`, `values`, `toList`, etc.) and utilities (power-of-two capacity, load factor).
- **`HashMapLP<K,V>`** — linear probing table with:
  - Power-of-two capacity, bit-masking index (`hashCode & (capacity-1)`).
  - Rehashing when `size >= capacity * loadFactor`.
  - **Cluster** diagnostics (count, min/max cluster size) and **collision** counts.
  - Instrumented iteration counters for `get`/`remove`.
- **`HashMapSC<K,V>`** — separate chaining table with:
  - Array of `LinkedList<MapEntry<K,V>>`.
  - Rehashing and **bucket** diagnostics (non-empty buckets, min/max bucket size) and collisions (non-empty bucket insert).
  - Instrumented iteration counters for `get`/`remove`.
- **`MapEntry<K,V>`** — minimal key–value pair used by both maps.
- **Domain model (`Movie`)** — id/title/genre, incremental **average rating** and **ratings count**.
- **`Test` driver** — loads data, benchmarks both maps, and sorts movies (merge sort; natural order by ratings count, then by average rating).

---

## Data Files (expected in working directory)
- **`movies.csv`** — `movieId,title,genre` (≈ 62K rows)  
- **`ratings.csv`** — `userId,movieId,rating,timestamp` (multiple millions typical; this code uses `movieId` + `rating`)

> The driver reads both files, aggregates ratings into each `Movie`, and stores movies into BOTH hash maps for A/B comparison.

---

## Build & Run
```bash
javac *.java
java Test
API Highlights

Common operations (both maps)

V put(K key, V value) — insert or update (rehash on load)

V get(K key) — retrieve value or null

void remove(K key) — delete (no-op if absent)

int size(), boolean isEmpty(), void clear()

ArrayList<MapEntry<K,V>> toList() — linearized snapshot

ArrayList<V> values() — values snapshot

void printCharacteristics() — capacity/size + collision/bucket or cluster stats

Instrumentation

HashMapSC.getIterations / removeIterations

HashMapLP.getIterations / removeIterations

HashMapLP.collisions and HashMapSC.collisions

Complexity (Amortized; Unbalanced, non-cuckoo, non-Robin-Hood)

Average case:

put / get / remove: O(1) expected with a well-chosen load factor.

Worst case:

May degrade to O(n) (pathological hash distribution or very high load).

This code path:

SC removal is O(bucket length) (observed 1–2).

LP removal may trigger cluster rebuilding (potentially many probes) — as reflected in iteration counts.

Design Choices & Notes

Power-of-two capacity with bit-masking yields fast index computation and smooth resizing.

Load factors: LP at 0.5 (more conservative to limit clustering), SC at 0.9 (higher cache of buckets; short chains observed).

LP deletion strategy: remove target slot and reinsert subsequent cluster entries to maintain probe correctness; this is simple and correct, but can be costly on large clusters.

Stable comparison: both maps are filled from the same source to allow apples-to-apples iteration and performance comparisons.

Takeaways

Separate chaining favored predictable deletes and low per-bucket depth on this dataset.

Linear probing achieved comparable get cost at this load, but removes were sensitive to cluster sizes.

The project demonstrates practical trade-offs in hash table design and showcases the full pipeline from abstract API → implementations → instrumented benchmarking → value-level analytics (sorted movie rankings).
