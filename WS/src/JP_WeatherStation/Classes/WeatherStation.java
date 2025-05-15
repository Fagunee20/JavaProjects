package JP_WeatherStation.Classes;

import JP_WeatherStation.Interfaces.I_WeatheStation;

public class WeatherStation implements I_WeatheStation{
	
	private int[][] Data;

	public WeatherStation (int [][]data)
	{
		this.Data = new int[data.length][];
        for (int i = 0; i < data.length; i++) 
        {
            this.Data[i] = data[i].clone();  // clone inner arrays
        }
    }

    @Override
    public String toString()
    {
    	 StringBuilder sb = new StringBuilder();
    	    for (int i = 0; i < Data.length; i++) 
    	    {
    	        sb.append("Day ").append(i + 1).append(": ");
    	        for (int j = 0; j < Data[i].length; j++) 
    	        {
    	            sb.append(Data[i][j]).append(" ");
    	        }
    	        sb.append("\n");
    	    }
    	    return sb.toString();
    }

    @Override
    public boolean addData(int[][] toAdd)
    {
    	if (Data.length == 0 || toAdd.length != 1 || toAdd[0].length != Data[0].length) 
    	{
    		return false;
    	}
        int[][] newData = new int[Data.length + 1][];
        for (int i = 0; i < Data.length; i++) 
        {
        	newData[i] = Data[i];
        }
        newData[Data.length] = toAdd[0].clone();
        this.Data = newData;
        return true;
    }

    @Override
    public int[][] getData()
    {
    	int[][] copy = new int[Data.length][];
        for (int i = 0; i < Data.length; i++) 
        {
            copy[i] = Data[i];
        }
        return copy;
    }

    @Override
    public int[] getDataAt (int dayIndex)
    {
    	if (dayIndex < 0 || dayIndex >= this.getData().length) {
            throw new IllegalArgumentException("Day index out of bounds");
        }
        return this.getData()[dayIndex];

    }

    @Override
    public void dataPreparation(String[] descriptors)
    {
    	for (int k = 0; k < descriptors.length && k < getData().length; k++) {
            String descriptor = descriptors[k];
            int[] dayData = getData()[k];

            switch (descriptor) {
                case "Sunny":
                    for (int j = 0; j < dayData.length; j++) {
                        if (dayData[j] < 0) {
                            dayData[j] = 0;
                        }
                    }
                    break;

                case "Rainy":
                    int sum = 0;
                    int count = 0;
                    for (int value : dayData) {
                        if (value > 0) {
                            sum += value;
                            count++;
                        }
                    }

                    int avg = (count > 0) ? (sum / count) : 0;
                    for (int j = 0; j < dayData.length; j++) {
                        if (dayData[j] < 0) {
                            dayData[j] = avg;
                        }
                    }
                    break;

                case "Thunderstorm":
                    for (int j = 0; j < dayData.length; j++) {
                        if (dayData[j] < 0) {
                            dayData[j] = -dayData[j]; // Make value positive
                        }
                    }
                    break;

                default:
                    // Ignore unknown descriptors
                    break;
            }
        }


    }

    @Override
    public int averageRainfallAtDay(int dayIndex, String []descriptors)
    {
    	
    	this.dataPreparation(descriptors);
        if (dayIndex >= this.getData().length || dayIndex < 0) {
            return -1;
        }
        if (descriptors[dayIndex].equals("Sunny")) {
            return 0;
        } else {
            int avg = 0;
            int sum = 0;
            int count = 0;
            int[] dayData = this.getData()[dayIndex];
            for (int value : dayData) {
                sum += value;
                count++;
            }
            avg = avg + (sum / count);
            return avg;
        }
    }

    @Override
    public int totalRainfall(String[] descriptors)
    {
    	int sum = 0;
        for (int i = 0; i < this.getData().length; i++) {
            String descriptor = descriptors[i];
            if (descriptor.equals("Rainy") || descriptor.equals("Thunderstorm")) {
                for (int value : getDataAt(i)) {// iterates over all measurement values for that day
                    sum += value;
                }
            }
        }
        return sum;
    }

    @Override
    public int getRainfallAt(int dayIndex, String mode, String[] descriptors)
    {
    	// Check for valid dayIndex
        if (dayIndex < 0 || dayIndex >= this.getData().length) {
            return -1; // Return -1 for invalid dayIndex
        }

        // Handle the mode
        if (mode.equals("lowest")) {
            int min = this.getData()[dayIndex][0]; // Initialise with the first value for the day
            for (int i = 1; i < this.getData()[dayIndex].length; i++) {
                if (this.getData()[dayIndex][i] < min) {
                    min = this.getData()[dayIndex][i]; // Update min if a smaller value is found
                }
            }
            return min;

        } else if (mode.equals("biggest")) {
            int max = this.getData()[dayIndex][0]; // Initialise with the first value for the day
            for (int i = 1; i < this.getData()[dayIndex].length; i++) {
                if (this.getData()[dayIndex][i] > max) {
                    max = this.getData()[dayIndex][i]; // Update max if a larger value is found
                }
            }
            return max;

        } else if (mode.equals("sum")) {
            int sum = 0;
            for (int i = 0; i < this.getData()[dayIndex].length; i++) {
                sum += this.getData()[dayIndex][i]; // Add all values for the day
            }
            return sum;

        } else {
            return 0; // Invalid mode
        }
    
     
    }
    
}
