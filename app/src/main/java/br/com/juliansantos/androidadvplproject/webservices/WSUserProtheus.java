package br.com.juliansantos.androidadvplproject.webservices;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import br.com.juliansantos.androidadvplproject.beans.CompanyProtheus;
import br.com.juliansantos.androidadvplproject.beans.UserProtheus;

public class WSUserProtheus extends AsyncTask {

    private String urlPath = "http://{ipServer}:{portServer}/REST/GET/JWSRUSERS/{action}/{param}";
    private String ipServer = "187.94.62.92";
    private String portServer = "2085";
    private String action;
    private String param;

    private URL url;
    private HttpURLConnection httpConnection;
    private StringBuilder httpReturn = new StringBuilder();
    private Scanner scanner;

    public WSUserProtheus(String action, String param) {
        this.action = action;
        this.param = param;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        // Define url for request.
        urlPath = urlPath.replace("{ipServer}", ipServer);
        urlPath = urlPath.replace("{portServer}", portServer);
        urlPath = urlPath.replace("{action}", action);
        urlPath = urlPath.replace("{param}", param);

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
            httpConnection.setDoInput(true);
            httpConnection.setConnectTimeout(5000);
            httpConnection.connect();

            // Get response.
            scanner = new Scanner(url.openStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                httpReturn.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (action.equals("id") || action.equals("cod")) {
            return new Gson().fromJson(httpReturn.toString(), UserProtheus.class);
        } else if (action.equals("emp")) {
            return new Gson().fromJson(httpReturn.toString(), CompanyProtheus[].class);
        } else {
            return null;
        }


    }

}
