package br.com.juliansantos.androidadvplproject.webservices;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class WSAuthenticationProtheus extends AsyncTask {

    private String urlPath = "http://{ipServer}:{portServer}/REST/GET/JWSRAUTH/auth/{user}/{pass}";
    private String ipServer = "187.94.62.92";
    private String portServer = "2085";
    private String userCode;
    private String pass;

    private URL url;
    private HttpURLConnection httpConnection;
    private StringBuilder httpReturn = new StringBuilder();
    private Scanner scanner;

    public WSAuthenticationProtheus(String userCode, String pass) {
        this.userCode = userCode;
        this.pass = pass;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        // Define url for request.
        urlPath = urlPath.replace("{ipServer}", ipServer);
        urlPath = urlPath.replace("{portServer}", portServer);
        urlPath = urlPath.replace("{user}", userCode);
        urlPath = urlPath.replace("{pass}", pass);

        try {

            // Set URL for request.
            url = new URL(urlPath);

            // Open connection HTTP.
            httpConnection = (HttpURLConnection) url.openConnection();

            // set header for request.
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setDoOutput(true);
            httpConnection.setConnectTimeout(5000);
            httpConnection.connect();

            // Get response.
            scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                httpReturn.append(scanner.next());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Boolean.parseBoolean(httpReturn.toString());
    }

}
