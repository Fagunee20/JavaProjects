package JP_WeatherStation.Classes;


import JP_WeatherStation.Interfaces.I_WeatheStation;
import JP_WeatherStation.Interfaces.I_WeatherForecast;


public class WeatherForecast implements I_WeatherForecast 
{

	//WeatherStation ws= new WeatherStation(null);
    private WeatherStation[] weatherStations;
    private String[] descriptors;



    public WeatherForecast(String[]descriptors, int stations)
    {

        this.descriptors = descriptors;
        this.weatherStations = new WeatherStation[stations];

    }

    @Override
    public boolean addWeatherStation(int[][] dataWeatherStation)
    {

        for (int i = 0; i < weatherStations.length; i++)
        {
            if (weatherStations[i] == null)
            {
                weatherStations[i] = new WeatherStation(dataWeatherStation); // initialised with weather station values.
                return true;
            }
        }
        return false;
    }

    @Override
    public I_WeatheStation getStationAt(int index)
    {

        if (index < 0 || index >= weatherStations.length) 
        {
            return null;
        }
        return weatherStations[index];

    }

    @Override
    public I_WeatheStation[] getWeatherStations()
    {
       return this.weatherStations;

    }

    @Override
    public int totalRainfall()
    {
        int total = 0;
        for (WeatherStation station : weatherStations)
        {
            if (station!= null)
            {
            	station.dataPreparation(descriptors);
                total += station.totalRainfall(descriptors);
            }
        }
        return total;

    }

    @Override
    public int getRainfallAt(int dayIndex, String mode)
    {
    	// Defensive check: if dayIndex is out of bounds
        if (dayIndex < 0 || dayIndex >= weatherStations[0].getData().length) {
            return -1;
        }

        int result = 0;
        boolean DataFound = false; // Flag to check if valid data is found

        // Iterate over all weather stations.
        for (WeatherStation station : weatherStations) {
            if (station == null)
                continue;

            // Call dataPreparation to adjust the data as needed
            station.dataPreparation(descriptors);

            int[][] data = station.getData();
            // Ensures that the dayIndex is valid for the given stations data.
            if (dayIndex >= data.length || data[dayIndex] == null)
                continue;

            switch (mode) {
                case "lowest":
                    int min = data[dayIndex][0];
                    for (int i = 1; i < data[dayIndex].length; i++) {
                        if (data[dayIndex][i] < min) {
                            min = data[dayIndex][i];
                        }
                    }
                    DataFound = true;
                    return min;

                case "biggest":
                    int max = data[dayIndex][0];
                    for (int i = 1; i < data[dayIndex].length; i++) {
                        if (data[dayIndex][i] > max) {
                            max = data[dayIndex][i];
                        }
                    }
                    DataFound = true;
                    return max;

                case "sum":
                    for (int value : data[dayIndex]) {
                        result += value; // Add all values
                    }
                    DataFound = true;
                    break;

                default:
                    return -1; // Unknown mode
            }
        }

            // Return the result, if valid data was found; otherwise return 0
            return DataFound ? result : 0;
    }

    @Override
    public int[][] createAveragesMatrix() // The method calculates daily average measurements for each weather stAation . It returns a 2D array.
    {
    	int numWeatherStations = weatherStations.length;
        int[][] averages = new int[numWeatherStations][];// Creates a 2D array for storing the average values.

        for (int i = 0; i < numWeatherStations; i++) { // Iterates over each weather station.
            if (weatherStations[i] == null || weatherStations[i].getData() == null) {
                continue; // skipping null stations
            }

            int[][] data = weatherStations[i].getData(); // Gets the weather data matrix for this station.
            int numDays = data.length;
            averages[i] = new int[numDays]; // prepares row for this station

            for (int j = 0; j < numDays; j++) { // Iterates over each day.
                if (data[j] == null || data[j].length == 0) {
                    averages[i][j] = 0;
                    continue;
                }

                int sum = 0;
                for (int k = 0; k < data[j].length; k++) {
                    sum += data[j][k]; // Sums up all measurements for that day.
                }
                averages[i][j] = sum / data[j].length;
            }
        }

        return averages;
    }

    @Override
    public int[][] calculateHumidity()
    {
        
    	int[][] averages = createAveragesMatrix();// Called at the start to get the average rainfall per day for each
        // station.
    	int numWeatherStations = averages.length;
    	int numDays = averages[0].length;
    	int[][] humidity = new int[numWeatherStations][numDays];

    	for (int i = 0; i < numWeatherStations; i++) {// Iterates over each weather station.
    		for (int j = 0; j < numDays; j++) {// Iterates over each day for the current station
    			int rainfall = averages[i][j];
    			if (this.descriptors[j].equals("Sunny")) {
    				humidity[i][j] = rainfall * 3;
    			} else if (this.descriptors[j].equals("Rainy")) {
    				humidity[i][j] = rainfall / 2;
    			} else if (this.descriptors[j].equalsIgnoreCase("Thunderstorm")) {
    				if (j == 0) {
    					humidity[i][j] = rainfall / numWeatherStations;// divides the rainfall for the station on day 0
                             // by the total number of stations.
    				} else {
    					humidity[i][j] = rainfall % j;
    				}
    			} else
    				humidity[i][j] = 0; // Unknown descriptor
    		}
    	}
    	return humidity;

    }

    @Override
    public String trend(int n) 
    {
    	
            int[][] humidity = calculateHumidity();
            int numWeatherStations = humidity.length;
            int numDays = humidity[0].length;

            if (n > numDays) {
                return null;
            }

            int sum = 0;
            for (int i = numDays - n; i < numDays; i++) {
                for (int j = 0; j < numWeatherStations; j++) {
                    sum += humidity[j][i];
                }
            }
            double average = (double) sum / (n * numWeatherStations);

            if (average < 50) {
                return "sunny";
            } else if (average > 50 && average < 80) {
                return "rainy";
            } else {
                return "thunderstorm";
            }
        }
}
