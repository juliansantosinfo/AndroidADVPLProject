package br.com.juliansantos.androidadvplproject.activits;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.juliansantos.androidadvplproject.R;
import br.com.juliansantos.androidadvplproject.beans.CompanyProtheus;
import br.com.juliansantos.androidadvplproject.beans.UserProtheus;

public class HomeActivity extends Activity {

    private Handler handler;
    private UserProtheus userProtheus;
    private CompanyProtheus company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // load views to class.
        initViews();

        // Initialize handler.
        initHandler();

        // Get extras via intent.
        getIntentExtras();

    }

    public void initViews() {
    }

    public void initHandler() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
    }

    public void getIntentExtras() {

        if (getIntent() == null) {
            return;
        }

        userProtheus = (UserProtheus) getIntent().getExtras().getSerializable("userProtheus");
        company = (CompanyProtheus) getIntent().getExtras().getSerializable("companyProtheus");

    }
}
