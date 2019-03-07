package br.com.juliansantos.androidadvplproject.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import br.com.juliansantos.androidadvplproject.webservices.WSAuthenticationProtheus;

public class TaskAuthorization extends AsyncTask<String, Void, Boolean> {

    Context context;
    ProgressDialog progress;

    public TaskAuthorization(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setMessage("Aguarde...");
        progress.show();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        progress.dismiss();
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

        return true;
    }
}
