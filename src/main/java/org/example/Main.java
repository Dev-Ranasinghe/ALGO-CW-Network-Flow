// RD Sasmith Devmina W2087750 20230992

package org.example;

import java.io.File;
import java.util.Scanner;

// Main class to execute the maximum flow algorithm
public class Main {

    public static void main(String[] args) {

        // Get all text files from resources directory
        File resourceFolder = new File("src/main/resources");
        File[] txtFiles = resourceFolder.listFiles((dir, name) -> name.endsWith(".txt"));

        // Check if input files exist
        if (txtFiles == null || txtFiles.length == 0) {
            System.out.println("No input files found in 'src/main/resources'. Please add .txt files and try again.");
            return;
        }

        // Display available input files
        System.out.println("Available input files in 'src/main/resources':");
        for (int i = 0; i < txtFiles.length; i++) {
            String fileName = txtFiles[i].getName().replace(".txt", "");
            System.out.println((i + 1) + ". " + fileName);
        }

        try (Scanner scanner = new Scanner(System.in)) {
            String fileName;
            File selectedFile;

            // Get valid input file from user
            while (true) {
                System.out.print("\nEnter the name of the input file from the list above (without '.txt'): ");
                fileName = scanner.nextLine().trim();
                selectedFile = new File("src/main/resources/" + fileName + ".txt");

                if (selectedFile.exists() && !selectedFile.isDirectory()) {
                    break;
                } else {
                    System.out.println("File not found. Please enter a valid name from the list.");
                }
            }

            // Parse input file and create flow network
            FlowNetwork network = Parser.parse(selectedFile.getPath());

            // Execute Edmonds-Karp algorithm and measure performance
            long startTime = System.currentTimeMillis();
            int maxFlow = MaxFlowSolver.edmondsKarp(network, 0, network.n - 1);
            long endTime = System.currentTimeMillis();

            // Display results
            System.out.println("\nMaximum Flow: " + maxFlow);
            System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
