package JP_WeatherStation.Test;

import JP_WeatherStation.Classes.WeatherForecast;
import JP_WeatherStation.Interfaces.I_WeatheStation;

public class Test_WeatherForecast {

	public static void main(String[] args) {
	        // Weather descriptors
	        String[] descriptors = { "Sunny", "Rainy", "Thunderstorm" };

	        // Create forecast with 2 weather stations
	        WeatherForecast forecast = new WeatherForecast(descriptors, 2);

	        // Data for 2 stations
	        int[][] station1 = {
	            { 10, 20, 30 },
	            { 40, 50, 60 },
	            { 70, 80, 90 }
	        };

	        int[][] station2 = {
	            { 5, 15, 25 },
	            { 35, 45, 55 },
	            { 65, 75, 85 }
	        };

	        // Add both stations
	        forecast.addWeatherStation(station1);
	        forecast.addWeatherStation(station2);

	        System.out.println("=== Test: Get Station At ===");
	        I_WeatheStation ws = forecast.getStationAt(1);
	        System.out.println(ws != null ? "Station " + (ws != null ? 2 : "") + " exists ✅" : "Station " + (ws != null ? 2 : "") + " missing ❌");

	        System.out.println("\n=== Test: Get All Weather Stations ===");
	        I_WeatheStation[] stations = forecast.getWeatherStations();
	        System.out.println("Total stations: " + stations.length);

	        System.out.println("\n=== Test: Total Rainfall ===");
	        int total = forecast.totalRainfall();
	        System.out.println("Total rainfall across all stations: " + total);

	        System.out.println("\n=== Test: Rainfall at Day Index (mode: sum) ===");
	        int rainfallDay1 = forecast.getRainfallAt(1, "sum");
	        System.out.println("Day 2 rainfall sum: " + rainfallDay1);

	        System.out.println("\n=== Test: Create Averages Matrix ===");
	        int[][] avg = forecast.createAveragesMatrix();
	        printMatrix(avg);

	        System.out.println("\n=== Test: Calculate Humidity ===");
	        int[][] humidity = forecast.calculateHumidity();
	        printMatrix(humidity);

	        System.out.println("\n=== Test: Trend (last 2 days) ===");
	        String trend = forecast.trend(2);
	        System.out.println("Humidity trend: " + trend);
	    }

	    // Helper method
	    private static void printMatrix(int[][] matrix) {
	        for (int i = 0; i < matrix.length; i++) {
	            System.out.print("Station " + (i + 1) + ": ");
	            for (int val : matrix[i]) {
	                System.out.print(val + " ");
	            }
	            System.out.println();
	        }
	    }
	}

