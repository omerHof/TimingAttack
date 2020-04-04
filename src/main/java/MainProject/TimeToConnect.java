package MainProject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * this class is measure the time to connect to the site
 */

public class TimeToConnect {

    String url;
    ProcessBuilder processBuilder;
    Process p;


    public TimeToConnect(String url) throws IOException {

        this.url = url;
        String command ="curl -skw %{time_total} -o - "+this.url;
        this.processBuilder = new ProcessBuilder(command.split(" ")).redirectErrorStream(true);

    }

    public double measureMultipleTime() {
        ArrayList<Double> timeList= new ArrayList<>();
        try{
            for(int i=0;i<200;i++){
                double time = (Double)timeToConnect();
                timeList.add(time);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error measure time for url: "+ url);
        }
        Collections.sort(timeList);
        double totalMedianTime = (timeList.get(timeList.size()/2)+ timeList.get(timeList.size()/2-1))/2;
        return totalMedianTime;
    }


    public Serializable timeToConnect() throws IOException {
        this.p = this.processBuilder.start();
        InputStream is = p.getInputStream();
        this.processBuilder.redirectErrorStream(true);
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? Double.parseDouble(s.next())*1000 : "";
    }
}
