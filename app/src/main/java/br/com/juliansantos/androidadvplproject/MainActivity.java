package br.com.juliansantos.androidadvplproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.juliansantos.androidadvplproject.activits.PreferenceActivity;
import br.com.juliansantos.androidadvplproject.beans.CompanyProtheus;
import br.com.juliansantos.androidadvplproject.beans.UserProtheus;
import br.com.juliansantos.androidadvplproject.tasks.TaskLogin;

/**
 * Class that defines the main activity of the application.
 *
 * @author Julian de Almeida Santos
 * @since 04/03/2019
 */
public class MainActivity extends Activity {

    // Global variables.
    private Handler handler;

    private LinearLayout layoutMain;
    private LinearLayout layoutTop;
    private TextView txvTitle;
    private LinearLayout layoutCenter;
    private TextView txvUser;
    private EditText edtUser;
    private TextView txvPass;
    private EditText edtPass;
    private CheckBox chkbRemember;
    private LinearLayout layoutBottom;
    private Button btnSingIn;
    private Button btnSettings;

    private UserProtheus userProtheus;
    private CompanyProtheus companyProtheus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views.
        initViews();

        // Initialize handler.
        handler = new Handler();
    }

    private void initViews() {

        // Layouts.
        layoutMain = findViewById(R.id.layout_main);
        layoutTop = findViewById(R.id.layout2_top);
        layoutCenter = findViewById(R.id.layout2_center);

        // Views in 'layout2_top'
        txvTitle = findViewById(R.id.txv_login);

        // Views in 'layout2_center'
        txvUser = findViewById(R.id.txv_user);
        txvPass = findViewById(R.id.txv_pass);
        edtUser = findViewById(R.id.edt_user);
        edtPass = findViewById(R.id.edt_pass);
        chkbRemember = findViewById(R.id.chkb_remember_login);


        //Views in 'layout2_bottom'.
        btnSingIn = findViewById(R.id.btn_singin);
        btnSettings = findViewById(R.id.btn_settings);

    }

    public void btnEntrarOnClick(View view) {

        // Local variables.
        String userCode = edtUser.getText().toString();
        String userPass = edtPass.getText().toString();

        // Initialize userProtheus.
        userProtheus = new UserProtheus();
        userProtheus.setCode(userCode);
        userProtheus.setPassword(userPass);
        userProtheus.setSaveLoginInfo(chkbRemember.isChecked());

        // Get UserProtheus and Companys.
        new TaskLogin(handler,this, userProtheus).execute();

    }

    public void btnSettingsOnClick(View view) {

        Intent intent = new Intent(this, PreferenceActivity.class);
        startActivity(intent);

    }

}
