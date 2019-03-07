package br.com.juliansantos.androidadvplproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.juliansantos.androidadvplproject.beans.UserProtheus;
import br.com.juliansantos.androidadvplproject.tasks.TaskLogin;
import br.com.juliansantos.androidadvplproject.webservices.WSUserProtheus;

/**
 * Class that defines the main activity of the application.
 *
 * @author Julian de Almeida Santos
 * @since 04/03/2019
 */
public class MainActivity extends AppCompatActivity {

    // Global variables.
    private LinearLayout layoutMain;
    public  ProgressBar progressBarLogin;
    private LinearLayout layoutTop;
    private TextView txvTitle;
    private LinearLayout layoutCenter;
    private TextView txvUser;
    private EditText edtUser;
    private TextView txvPass;
    private EditText edtPass;
    private CheckBox chkbRemember;
    private LinearLayout layoutBottom;
    private TextView txvFooter;
    private Button btnSingIn;

    private UserProtheus userProtheus;

    /**
     * @since 04/03/2019
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If there is settings information, it loads the same.
        loadSettings();

        // If there is login information, it loads the same.
        loadLoginInfo();

        // load views to class.
        initViews();

    }

    /**
     * Method to load saved settings information.
     *
     * @since 05/03/2019
     */
    private void loadSettings() {
    }

    /**
     * Method to load saved login information.
     *
     * @since 05/03/2019
     */
    private void loadLoginInfo() {
    }

    /**
     * Method responsible for initializing objects views of the XML layout.
     *
     * @since 04/03/2019
     */
    private void initViews() {

        // Layouts.
        layoutMain = findViewById(R.id.layout_main);
        layoutTop = findViewById(R.id.layout2_top);
        layoutCenter = findViewById(R.id.layout2_center);
        layoutBottom = findViewById(R.id.layout2_bottom);

        // Views in 'layout2_top'
        txvTitle = findViewById(R.id.txv_login);

        // Views in 'layout2_center'
        progressBarLogin = findViewById(R.id.pgrb_login);
        txvUser = findViewById(R.id.txv_user);
        txvPass = findViewById(R.id.txv_pass);
        edtUser = findViewById(R.id.edt_user);
        edtPass = findViewById(R.id.edt_pass);
        chkbRemember = findViewById(R.id.chkb_remember_login);
        txvFooter = findViewById(R.id.txv_by);

        //Views in 'layout2_bottom'.
        btnSingIn = findViewById(R.id.btn_singin);

    }

    /**
     * Method for executar listener onClick in button btnSingIn.
     *
     * @since 04/03/2019
     */
    public void btnEntrarOnClick(View view) {

        // Local variables.
        String userCode = edtUser.getText().toString();
        String userPass = edtPass.getText().toString();

        // Initialize userProtheus.
        userProtheus = new UserProtheus();
        userProtheus.setCode(userCode);
        userProtheus.setPassword(userPass);

        // Get UserProtheus and Companys.
        new TaskLogin(this, userProtheus).execute();

    }
}
