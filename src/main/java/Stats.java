import ApiDataTypes.ApiData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Stats {

    public static String status;
    private static Gson gson;
    public static ApiData data;
    public static String mcUrl = "mc.athaun.me";

    public static void setupGson () {
        gson = new GsonBuilder().create();
    }

    public static void getServerStats() {
        try {
            String timeStamp = new SimpleDateFormat("h:mm:ss a").format(Calendar.getInstance().getTime());
//            System.out.println("[" + timeStamp + "] Fetching server status");
            // Get JSON from minetools.eu API
            URL url = new URL("https://api.minetools.eu/ping/" + mcUrl + "/25565");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                // Scan text from API into JSON Object
                String jsonString = "";
                Scanner scanner = new Scanner(url.openStream());

                // Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    jsonString += scanner.nextLine() + "\n";
                }
                scanner.close();

                data = gson.fromJson(jsonString, ApiData.class);
                Recorder.calculateStats();
            }

        } catch (Exception e) {
            data = new ApiData();
            e.printStackTrace();
        }
    }

    public static String getStatus () {
        return status;
    }
}
