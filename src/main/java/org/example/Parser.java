// RD Sasmith Devmina W2087750 20230992

package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Reads and parses input files to create flow networks
public class Parser {

    // Parses a file containing network data and creates a FlowNetwork
    // File format: first line contains number of nodes (n)
    // Following lines contain: fromNode toNode capacity
    public static FlowNetwork parse(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        int n = scanner.nextInt();
        FlowNetwork network = new FlowNetwork(n);
        while (scanner.hasNextInt()) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int capacity = scanner.nextInt();
            network.addEdge(from, to, capacity);
        }
        scanner.close();
        return network;
    }
}
