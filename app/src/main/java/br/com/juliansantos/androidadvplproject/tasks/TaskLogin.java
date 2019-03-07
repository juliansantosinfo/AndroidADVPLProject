package br.com.juliansantos.androidadvplproject.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import br.com.juliansantos.androidadvplproject.activits.CompanysActivity;
import br.com.juliansantos.androidadvplproject.beans.UserProtheus;
import br.com.juliansantos.androidadvplproject.webservices.WSAuthenticationProtheus;
import br.com.juliansantos.androidadvplproject.webservices.WSUserProtheus;

public class TaskLogin extends AsyncTask<Void, String, UserProtheus> {

    Context context;
    ProgressDialog progress;
    UserProtheus userProtheus;

    public TaskLogin(Context context, UserProtheus userProtheus) {
        this.context = context;
        this.userProtheus = userProtheus;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setMessage("Aguarde...");
        progress.show();
    }

    @Override
    protected void onPostExecute(UserProtheus userProtheus) {
        super.onPostExecute(userProtheus);
        progress.dismiss();
    }

    @Override
    protected void onProgressUpdate(String... strings) {
        super.onProgressUpdate(strings);
        progress.setMessage(strings[0]);
    }

    @Override
    protected void onCancelled(UserProtheus userProtheus) {
        super.onCancelled(userProtheus);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected UserProtheus doInBackground(Void... voids) {

        boolean isAuthorized = false;

        // Performs authentication for user and password.
        publishProgress("Autenticando...");
        isAuthorized = new WSAuthenticationProtheus().requestAuthentication(userProtheus.getCode(), userProtheus.getPassword());

        // Is successfully logged, fetches user information.
        if (isAuthorized) {

            publishProgress("Buscando Usuário");
            userProtheus = new WSUserProtheus(userProtheus).requestUserProtheusByCode();

            // If not load user information.
            if (userProtheus.getId().isEmpty()) {
                return userProtheus;
            }

            // Get available company`s for protheus user.
            publishProgress("Buscando Empresas");
            userProtheus.setListCompanys(new WSUserProtheus(userProtheus).requestCompanysProtheus());

            // Open activity company.
            activityCompanyStart();

        } else {
            return new UserProtheus();
        }
        return userProtheus;
    }

    /**
     * Method for start activity companys.
     */
    private void activityCompanyStart() {
        Intent intent = new Intent(context, CompanysActivity.class);
        context.startActivity(intent);
    }

}
