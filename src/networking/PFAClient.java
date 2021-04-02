package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class PFAClient {

    public static void main(String[] args) {
        try {
            Socket timeClientSocket = new Socket("time-a.nist.gov", 13);

            InputStream inputStream = timeClientSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bw = new BufferedReader(inputStreamReader);
            bw.lines().forEach(s -> System.out.println("the time is "+ s));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
