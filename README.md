# Bonus MST Edge Removal
This project demonstrates building and maintaining a Minimum Spanning Tree (MST) using **Kruskal’s algorithm** and efficiently handling **edge removal**.

---

### Project Overview
The goal of this program is to:
1. Build an MST from a given graph.
2. Display the MST edges before removing any edge.
3. Remove one edge from the MST.
4. Show the resulting components after removal.
5. Find a replacement edge to reconnect the components.
6. Display the new MST with the replacement edge and its total cost.

This demonstrates both MST construction and efficient handling of edge removal and reconnection.

---

### How to Run
#### Option 1 — IntelliJ IDEA
1. Clone this repository
2. Open the project in IntelliJ
3. Run the class `graph.MSTEdgeRemovalDemo`

#### Option 2 — Maven CLI
1. **Clone the repository**:
```
git clone https://github.com/Baldauren1/BMSTEdgeRemoval.git
cd BMSTEdgeRemoval
```

2. **Compile and run using Maven:**
````
mvn compile exec:java
````

---

### Output 
````
Original MST:
Edges:
 C - E (1)
 B - C (2)
 A - C (3)
 B - D (5)
Total cost = 11

Removed edge: B - D (5)

Components after removal:
 [D]
 [A, B, C, E]

Replacement edge found: D - E (6)

New MST:
Edges:
 C - E (1)
 B - C (2)
 A - C (3)
 D - E (6)
Total cost = 12
````