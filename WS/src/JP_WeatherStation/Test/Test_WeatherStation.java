package JP_WeatherStation.Test;

import JP_WeatherStation.Classes.WeatherForecast;
import JP_WeatherStation.Classes.WeatherStation;
import JP_WeatherStation.Interfaces.I_WeatheStation;


public class Test_WeatherStation 
{
	public static void main(String[] args) {
        // Descriptors for weather conditions
        String[] descriptors = { "Sunny", "Rainy", "Thunderstorm" };

        // Data for a weather station
        int[][] stationData = {
                { -10, 22, 33, 19, 45, 75, 20 }, // Day 1
                { 35, -6, 57, 8, 10, -100, 10 }, // Day 2
                { 15, 20, -29, 39, 30, 70, 30 } // Day 3
        };

        // Create WeatherStation object
        WeatherStation station = new WeatherStation(stationData);

        // Test: Get Data
        System.out.println("=== Test: Get Data ===");
        printMatrix(station.getData());

        // Test: Add Data
        // System.out.println("\n=== Test: Add Data ===");
        // int[][] newData = { { 30, 10, 20, 45, 67, 23, 98 } };
        // boolean addResult = station.addData(newData);
        // System.out.println("Add data successful: " + addResult);
        // printMatrix(station.getData());

        // Test: Get Data at Specific Day Index
        System.out.println("\n=== Test: Get Data At Day Index ===");
        try {
            int[] dayData = station.getDataAt(1);
            printArray(dayData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Test: Data Preparation (Sunny, Rainy, Thunderstorm)
        System.out.println("\n=== Test: Data Preparation (Sunny, Rainy, Thunderstorm) ===");
        station.dataPreparation(descriptors);
        printMatrix(station.getData());

        // Test: Average Rainfall at Day Index
        System.out.println("\n=== Test: Average Rainfall at Day Index ===");
        int averageRainfall = station.averageRainfallAtDay(0, descriptors);
        System.out.println("Average rainfall at Day 1: " + averageRainfall);
        int averageRainfall1 = station.averageRainfallAtDay(1, descriptors);
        System.out.println("Average rainfall at Day 2: " + averageRainfall1);

        // Test: Total Rainfall
        System.out.println("\n=== Test: Total Rainfall ===");
        int totalRainfall = station.totalRainfall(descriptors);
        System.out.println("Total rainfall: " + totalRainfall);

        // Test: Rainfall at Specific Day Index (Mode: Lowest, Biggest, Sum)
        System.out.println("\n=== Test: Rainfall at Specific Day Index ===");
        int lowestRainfall = station.getRainfallAt(0, "Lowest", descriptors);
        System.out.println("Lowest rainfall at Day 1: " + lowestRainfall);

        int biggestRainfall = station.getRainfallAt(1, "biggest", descriptors);
        System.out.println("Biggest rainfall at Day 2: " + biggestRainfall);

        int rainfallSum = station.getRainfallAt(2, "sum", descriptors);
        System.out.println("Sum of rainfall at Day 3: " + rainfallSum);
    }

    // Helper method to print 2D array (data for each day)
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("Day " + (i + 1) + ": ");
            for (int val : matrix[i]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // Helper method to print 1D array (data for a specific day)
    private static void printArray(int[] array) {
        for (int val : array) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
