// RD Sasmith Devmina W2087750 20230992

package org.example;

import java.util.*;

// Represents a flow network with vertices and edges
public class FlowNetwork {

    int n; // number of nodes
    List<Edge>[] graph;

    @SuppressWarnings("unchecked")
    public FlowNetwork(int n) {
        this.n = n;
        graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
    }

    // Adds an edge and its corresponding residual edge to the network
    public void addEdge(int from, int to, int capacity) {
        Edge e1 = new Edge(from, to, capacity);
        Edge e2 = new Edge(to, from, 0); // residual edge
        e1.residual = e2;
        e2.residual = e1;
        graph[from].add(e1);
        graph[to].add(e2);
    }

    public List<Edge>[] getGraph() {
        return graph;
    }
}
