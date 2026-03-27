package edu.northeastern.wea26sp_team3;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {

    public static String get(String urlString) throws IOException {
        HttpURLConnection conn = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            Log.d("ApiClient", "Requesting: " + urlString);
            int code = conn.getResponseCode();
            InputStream inputStream = (code >= 200 && code < 300)
                    ? conn.getInputStream()
                    : conn.getErrorStream();

            if (inputStream == null) {
                throw new IOException("No response from server.");
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            if (code >= 200 && code < 300) {
                return result.toString();
            } else {
                throw new IOException("HTTP " + code + ": " + result);
            }

        } finally {
            if (reader != null) reader.close();
            if (conn != null) conn.disconnect();
        }
    }
}
