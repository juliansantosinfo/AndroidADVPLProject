package br.com.juliansantos.androidadvplproject.activits;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.net.URLConnection;

import br.com.juliansantos.androidadvplproject.R;

public class SettingsServerActivity extends AppCompatActivity {

    private EditText edtSerer;
    private EditText edtPort;
    private TextView txvLabelURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_server);

        // load views to class.
        initViews();
    }

    public void initViews() {

        edtSerer = findViewById(R.id.edt_server);
        edtSerer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                updateLabelURL();
            }
        });

        edtPort = findViewById(R.id.edt_port);
        edtPort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                updateLabelURL();
            }
        });

        txvLabelURL = findViewById(R.id.txv_label_url_server);
    }

    public void updateLabelURL() {

        String edtServerValue;
        String edtPortValue;
        StringBuilder txtValue;

        edtServerValue = edtSerer.getText().toString().trim();
        edtPortValue = edtPort.getText().toString().trim();
        txtValue = new StringBuilder();

        if (edtServerValue.isEmpty()) {
            edtServerValue = "{server}";
        }

        if (edtPortValue.isEmpty()) {
            edtPortValue = "{port}";
        }

        txtValue.append("http://");
        txtValue.append(edtServerValue);
        txtValue.append(":");
        txtValue.append(edtPortValue);
        txtValue.append("/REST");

        txvLabelURL.setText(txtValue.toString());

    }

    public void btnSaveOnClick(View view) {

        final boolean[] isConnectedSuccessFully = new boolean[1];
        ProgressDialog progress;

        progress = new ProgressDialog(this);
        progress.setMessage("Testando conexão...");
        progress.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URLConnection connection = new URL(txvLabelURL.getText().toString()).openConnection();
                    connection.setConnectTimeout(5000);
                    connection.connect();
                    isConnectedSuccessFully[0] = true;
                } catch (Exception e) {
                    isConnectedSuccessFully[0] = false;
                }
            }
        }).run();

        progress.dismiss();

        if (isConnectedSuccessFully[0]) {
            Snackbar.make(view, "Sucesso ao conectar ao servidor!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            Snackbar.make(view, "Falha ao conectar ao servidor!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }

    public void btnCancelOnClick(View view) {
    }
}
