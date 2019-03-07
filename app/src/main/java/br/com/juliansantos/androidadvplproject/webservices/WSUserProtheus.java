package br.com.juliansantos.androidadvplproject.webservices;

import android.util.Base64;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import br.com.juliansantos.androidadvplproject.beans.CompanyProtheus;
import br.com.juliansantos.androidadvplproject.beans.UserProtheus;

public class WSUserProtheus {

    private String urlPath;
    private String ipServer = "187.94.62.92";
    private String portServer = "2085";

    private String authorizationBasic;

    private UserProtheus userProtheus;

    private URL url;
    private HttpURLConnection httpConnection;
    private StringBuilder httpReturn = new StringBuilder();
    private String bufferedLine;
    private BufferedReader bufferedReader;

    public WSUserProtheus(UserProtheus userProtheus) {
        this.userProtheus = userProtheus;
    }

    /**
     * Return a valid userProtheus object.
     * @return
     */
    public UserProtheus requestUserProtheusByCode() {

        // Define url for request.
        urlPath = "http://{ipServer}:{portServer}/REST/GET/JWSRUSERS/cod/{userCode}";
        urlPath = urlPath.replace("{ipServer}", ipServer);
        urlPath = urlPath.replace("{portServer}", portServer);
        urlPath = urlPath.replace("{userCode}", userProtheus.getCode());

        try {

            // Set URL for request.
            url = new URL(urlPath);

            // Set key for authorization basic
            authorizationBasic = "Basic " + Base64.encodeToString((userProtheus.getCode()+ ":" + userProtheus.getPassword()).getBytes()  , Base64.DEFAULT);

            // Open connection HTTP.
            httpConnection = (HttpURLConnection) url.openConnection();

            // set header for request.
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("Authorization", authorizationBasic);
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            httpConnection.setConnectTimeout(5000);
            httpConnection.connect();

            // Get response.
            bufferedLine = "";
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((bufferedLine = bufferedReader.readLine()) != null) {
                httpReturn.append(bufferedLine);
            }

            // Set userProtheus with json reponse.
            userProtheus = new Gson().fromJson(httpReturn.toString(), UserProtheus.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userProtheus;

    }

    /**
     * Return an ArrayList with CompanysProtheus objects.
     * @return
     */
    public ArrayList<CompanyProtheus> requestCompanysProtheus() {

        // Local variables.
        CompanyProtheus[] companysProtheus;
        ArrayList<CompanyProtheus> listCompanysProtheus = new ArrayList<>();

        // Define url for request.
        urlPath = "http://{ipServer}:{portServer}/REST/GET/JWSRUSERS/emp/{userCode}";
        urlPath = urlPath.replace("{ipServer}", ipServer);
        urlPath = urlPath.replace("{portServer}", portServer);
        urlPath = urlPath.replace("{userCode}", userProtheus.getCode());

        try {

            // Set URL for request.
            url = new URL(urlPath);

            // Set key for authorization basic
            authorizationBasic = "Basic " + Base64.encodeToString((userProtheus.getCode()+ ":" + userProtheus.getPassword()).getBytes()  , Base64.DEFAULT);

            // Open connection HTTP.
            httpConnection = (HttpURLConnection) url.openConnection();

            // set header for request.
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("Authorization", authorizationBasic);
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            httpConnection.setConnectTimeout(5000);
            httpConnection.connect();

            // Get response.
            bufferedLine = "";
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((bufferedLine = bufferedReader.readLine()) != null) {
                httpReturn.append(bufferedLine);
            }

            // Set userProtheus with json reponse.
            companysProtheus = (CompanyProtheus[]) new Gson().fromJson(httpReturn.toString(), CompanyProtheus[].class);
            listCompanysProtheus = new ArrayList<CompanyProtheus>(Arrays.asList(companysProtheus));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listCompanysProtheus;

    }

}
