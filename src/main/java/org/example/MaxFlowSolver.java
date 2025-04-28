// RD Sasmith Devmina W2087750 20230992

package org.example;

import java.util.*;

// Implements maximum flow algorithms for flow networks
public class MaxFlowSolver {

    // Implements Edmonds-Karp algorithm to find maximum flow
    // Returns the maximum flow value from source to sink
    public static int edmondsKarp(FlowNetwork network, int source, int sink) {
        int maxFlow = 0;
        List<Edge>[] graph = network.getGraph();

        while (true) {
            // BFS to find augmenting path
            Edge[] parentMap = new Edge[network.n];
            Queue<Integer> q = new LinkedList<>();
            q.add(source);

            // Find path from source to sink
            while (!q.isEmpty() && parentMap[sink] == null) {
                int current = q.poll();
                for (Edge e : graph[current]) {
                    if (parentMap[e.to] == null && e.to != source && e.remainingCapacity() > 0) {
                        parentMap[e.to] = e;
                        q.add(e.to);
                    }
                }
            }

            // If no augmenting path found, end algorithm
            if (parentMap[sink] == null)
                break;

            // Find bottleneck capacity along the path
            int bottleneck = Integer.MAX_VALUE;
            for (Edge e = parentMap[sink]; e != null; e = parentMap[e.from]) {
                bottleneck = Math.min(bottleneck, e.remainingCapacity());
            }

            // Augment flow along the path
            for (Edge e = parentMap[sink]; e != null; e = parentMap[e.from]) {
                e.augment(bottleneck);
            }

            maxFlow += bottleneck;
            System.out.println("Augmented flow by " + bottleneck + ", current max flow = " + maxFlow);
        }

        System.out.println("\nMaximum Flow: " + maxFlow);
        System.out.println("\nFinal Flows:");
        printFinalFlows(network);

        return maxFlow;
    }

    // Prints the final flow values for each edge in the network
    private static void printFinalFlows(FlowNetwork network) {
        Set<String> printed = new HashSet<>();

        for (int i = 0; i < network.n; i++) {
            for (Edge e : network.getGraph()[i]) {
                String edgeKey = e.from + "->" + e.to;
                if (!printed.contains(edgeKey) && e.capacity > 0) {
                    System.out.printf("Edge (%d -> %d) : Flow = %d/%d%n",
                            e.from, e.to, e.flow, e.capacity);
                    printed.add(edgeKey);
                }
            }
        }
    }
}
