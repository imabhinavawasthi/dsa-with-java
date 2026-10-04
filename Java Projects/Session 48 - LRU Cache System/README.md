# Session 48: Java Mini Project 2 — LRU Cache & Fast Data Management

A complete, production-grade implementation of a **Least Recently Used (LRU) Cache** in Java 17+, paired with a real-world **Online Quiz Platform** showing cache-interceptor mechanics (Cache Hit vs. Cache Miss).

---

## 1. Architectural Highlights

### The Dual Structure Strategy
```
             ┌────────────────────────────────────────────────────────┐
             │                      HashMap                           │
             │           Key  ───>  Node Reference                   │
             └───────────────────────┬────────────────────────────────┘
                                     │ O(1) direct memory pointer
                                     ▼
        ┌────────┐       ┌────────┐       ┌────────┐       ┌────────┐
        │  HEAD  │ <===> │ Node 1 │ <===> │ Node 2 │ <===> │  TAIL  │
        │(Dummy) │       │ (MRU)  │       │ (LRU)  │       │(Dummy) │
        └────────┘       └────────┘       └────────┘       └────────┘
```

1. **Strict $O(1)$ Time Guarantee:**
   - `get(K key)`: $O(1)$ lookup via HashMap, $O(1)$ pointer rearrangement to move node to MRU head.
   - `put(K key, V val)`: $O(1)$ insertion or eviction using dummy sentinel nodes (`tail.prev`).
2. **Sentinel Dummy Nodes:**
   Permanent `head` and `tail` sentinels eliminate all null-pointer checks for empty lists or single-element boundary conditions.
3. **Double Key-Value Storage:**
   The `Node` stores both `key` and `value`. When evicting `tail.prev`, the key is required so that `map.remove(lru.key)` can be called in $O(1)$ without an $O(N)$ reverse scan.
4. **Online Quiz App Interceptor:**
   Demonstrates practical backend caching:
   - **Cache Hit (<1ms):** Served immediately from volatile RAM.
   - **Cache Miss (1500ms):** Fallback to `SlowDatabase`, populates cache, serves future requests instantly.
5. **Multi-Thread Safety:**
   Synchronized core mutations prevent race conditions during concurrent cache access.
6. **Interactive Web Dashboard:**
   The Java HTTP server serves a quiz browser with live hit/miss telemetry and visualizations of the HashMap and MRU-to-LRU doubly linked list. Quiz requests use the same `QuizService` and `LRUCache` as the terminal demo.

---

## 2. Project Directory Layout

```
Session 48 - LRU Cache System/
├── src/
│   ├── engine/
│   │   ├── LRUCache.java
│   │   └── Node.java
│   ├── model/
│   │   └── Quiz.java
│   ├── database/
│   │   └── SlowDatabase.java
│   ├── service/
│   │   └── QuizService.java
│   ├── web/
│   │   ├── QuizWebServer.java
│   │   └── index.html
│   └── Main.java
└── README.md
```

---

## 3. How to Compile and Run

From the project root:
```bash
cd src
javac Main.java engine/*.java model/*.java database/*.java service/*.java web/*.java
java Main
```

Then open **http://localhost:8080**. The dashboard starts with a cache capacity of 3 and a simulated database delay of 1500 ms. Request a lesson from the quiz library to see the first miss populate the cache; request it again to see a hit promote its node to MRU. Request more than three different quizzes to observe LRU eviction.

To use another port, run `java Main --port=8081`. To run the original interactive terminal demo, run `java Main --cli`.
