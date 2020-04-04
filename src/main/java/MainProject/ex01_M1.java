package MainProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class ex01_M1
{
    final static String URL = "http://aoi.ise.bgu.ac.il\\";
    public static void main( String[] args ) throws IOException {

        testTimeToConnect();
        //testCheckPasswordLength();
    }


    private static void testTimeToConnect() throws IOException {
        TimeToConnect tmc = new TimeToConnect(URL);
        for (int i=0;i<100;i++){
            Double time = (Double) tmc.timeToConnect();
            System.out.println(time.intValue());
        }
    }

    private static void testCheckPasswordLength() {
        CheckPasswordLength cpl = new CheckPasswordLength(URL);
        cpl.measureConnectionWithDifferentLength();
        System.out.println("the length of the password is: "+ cpl.getLength());
    }



    /**
     * check if the password is correct
     * @param password - url of the site with user name and password
     * @return if the password that was given is correct
     * @throws IOException
     */
    private static boolean isPasswordCorrect(String password) throws IOException  {
        String url = "http://aoi.ise.bgu.ac.il/?user=307832972&password=" + password + "&difficulty=1";
        HttpURLConnection connection;
        connection = (HttpURLConnection) new URL(password).openConnection();
        //add headers to the connection, or check the status if desired..

        // handle error response code it occurs
        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (200 <= responseCode && responseCode <= 299) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        inputStream));

        StringBuilder response = new StringBuilder();
        String currentLine;

        while ((currentLine = in.readLine()) != null)
            response.append(currentLine);

        in.close();

        return response.toString().equals("1");
    }

}

