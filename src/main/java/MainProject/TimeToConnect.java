package MainProject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
        this.processBuilder = new ProcessBuilder(command.split(" "));
        this.p = processBuilder.start();

    }

    public Serializable timeToConnect(){
        InputStream is = p.getInputStream();
        this.processBuilder.redirectErrorStream(true);
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? Double.parseDouble(s.next())*1000 : "";
    }
}
