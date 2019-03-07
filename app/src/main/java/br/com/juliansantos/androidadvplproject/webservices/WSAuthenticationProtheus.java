package br.com.juliansantos.androidadvplproject.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class WSAuthenticationProtheus {

    private String urlPath;
    private String ipServer = "187.94.62.92";
    private String portServer = "2085";

    private String authorizationBasic;

    private URL url;
    private HttpURLConnection httpConnection;
    private StringBuilder httpReturn = new StringBuilder();
    private String bufferedLine;
    private BufferedReader bufferedReader;

    public Boolean requestAuthentication(String userCode, String userpass) {

        // Define url for request.
        urlPath = "http://{ipServer}:{portServer}/REST/GET/JWSRAUTH/auth/{user}/{pass}";
        urlPath = urlPath.replace("{ipServer}", ipServer);
        urlPath = urlPath.replace("{portServer}", portServer);
        urlPath = urlPath.replace("{user}", userCode);
        urlPath = urlPath.replace("{pass}", Base64.encodeToString(userpass.getBytes(), Base64.DEFAULT));

        try {

            // Set URL for request.
            url = new URL(urlPath);

            // Set key for authorization basic
            authorizationBasic = "Basic " + Base64.encodeToString((userCode + ":" + userpass).getBytes()  , Base64.DEFAULT);

            // Open connection HTTP.
            httpConnection = (HttpURLConnection) url.openConnection();

            // set header for request.
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("Authorization", authorizationBasic);
            httpConnection.setDoOutput(true);
            httpConnection.setConnectTimeout(5000);
            httpConnection.connect();

            // Get response.
            bufferedLine = "";
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((bufferedLine = bufferedReader.readLine()) != null) {
                httpReturn.append(bufferedLine);
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
