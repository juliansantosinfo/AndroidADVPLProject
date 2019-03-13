package br.com.juliansantos.androidadvplproject.tasks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;

import br.com.juliansantos.androidadvplproject.MainActivity;

public class TaskNetworkState extends AsyncTask<Void, Void, Integer> {

    private Context context;
    private Handler handler;
    private ConnectivityManager connectivityManager;

    public TaskNetworkState(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        handler.sendEmptyMessage(integer);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Integer integer) {
        super.onCancelled(integer);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Integer doInBackground(Void... voids) {

        NetworkInfo networkInfo;
        Network networks;
        Integer networkStateMsgWhatCode;

        connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            networkStateMsgWhatCode = MainActivity.MSG_WHAT_NETWORK_STATE_OFFLINE;
        } else {

            if (networkInfo.isConnected()){
                networkStateMsgWhatCode = MainActivity.MSG_WHAT_NETWORK_STATE_ONLINE;
            } else {
                networkStateMsgWhatCode = MainActivity.MSG_WHAT_NETWORK_STATE_OFFLINE;
            }

        }

        return networkStateMsgWhatCode;
    }
}
