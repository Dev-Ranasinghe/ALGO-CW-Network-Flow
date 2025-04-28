// RD Sasmith Devmina W2087750 20230992

package org.example;

// Represents an edge in a flow network
public class Edge {

    int from, to;
    int capacity;
    int flow;
    Edge residual;

    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    // Returns available capacity in this edge
    public int remainingCapacity() {
        return capacity - flow;
    }

    // Updates flow along this edge and its residual edge
    public void augment(int bottleNeck) {
        this.flow += bottleNeck;
        this.residual.flow -= bottleNeck;
    }
}
