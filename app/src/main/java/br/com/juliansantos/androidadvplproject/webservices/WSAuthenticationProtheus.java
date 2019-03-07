package br.com.juliansantos.androidadvplproject.webservices;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import br.com.juliansantos.androidadvplproject.MainActivity;

public class WSAuthenticationProtheus extends AsyncTask<Void, Void, Boolean> {

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

    /**
     * Runs on the UI thread before {@link #doInBackground}.
     *
     * @see #onPostExecute
     * @see #doInBackground
     */
    @Override
    protected void onPreExecute() {
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     *
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @return A result, defined by the subclass of this task.
     *
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Boolean doInBackground(Void... voids) {

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

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     *
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param isAuthorized The result of the operation computed by {@link #doInBackground}.
     */
    @Override
    protected void onPostExecute(Boolean isAuthorized) {

    }
}
