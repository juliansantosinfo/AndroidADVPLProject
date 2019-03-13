package br.com.juliansantos.androidadvplproject.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import br.com.juliansantos.androidadvplproject.webservices.WSAuthenticationProtheus;

public class TaskAuthorization extends AsyncTask<String, Void, Boolean> {

    Handler handler;
    Context context;

    public TaskAuthorization(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Boolean aBoolean) {
        super.onCancelled(aBoolean);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Boolean doInBackground(String... strings) {

        String userCode = strings[0];
        String userPass = strings[1];
        boolean isAuthenticated = false;

        isAuthenticated = new WSAuthenticationProtheus().requestAuthentication(userCode, userPass);

        return isAuthenticated;
    }
}
