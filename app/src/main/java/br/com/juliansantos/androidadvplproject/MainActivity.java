package br.com.juliansantos.androidadvplproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.juliansantos.androidadvplproject.activits.CompanysActivity;
import br.com.juliansantos.androidadvplproject.activits.SettingsServerActivity;
import br.com.juliansantos.androidadvplproject.beans.CompanyProtheus;
import br.com.juliansantos.androidadvplproject.beans.UserProtheus;
import br.com.juliansantos.androidadvplproject.preferences.PreferencesApp;
import br.com.juliansantos.androidadvplproject.tasks.TaskLogin;

/**
 * Class that defines the main activity of the application.
 *
 * @author Julian de Almeida Santos
 * @since 04/03/2019
 */
public class MainActivity extends AppCompatActivity {

    // Global variables.
    private static boolean isFullscrrenLayout;

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
    private FloatingActionButton fabSettings;

    private UserProtheus userProtheus;
    private CompanyProtheus companyProtheus;

    /**
     * @author Julian de Almeida Santos
     * @since 04/03/2019
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load views to class.
        initViews();

        // If there is login information, it loads the same.
        loadLoginInfo();
    }

    /**
     * @author Julian de Almeida Santos
     * @since 09/03/2019
     */
    @Override
    protected void onStart() {
        super.onStart();

        // Initialize variable for fullscreenLayout.
        isFullscrrenLayout = true;
        hideNavegationBar();

    }

    /**
     * Method responsible for initializing objects views of the XML layout.
     *
     * @author Julian de Almeida Santos
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
        fabSettings = findViewById(R.id.fab_settings);


        //Views in 'layout2_bottom'.
        btnSingIn = findViewById(R.id.btn_singin);

    }

    /**
     * Method to load saved login information.
     *
     * @author Julian de Almeida Santos
     * @since 05/03/2019
     */
    private void loadLoginInfo() {

        // Get preferences in main activity.
        String userLogin = PreferencesApp.getSharedPreferences(this).getString("userLogin", "");
        String passLogin = PreferencesApp.getSharedPreferences(this).getString("passLogin", "");
        boolean saveLogin = PreferencesApp.getSharedPreferences(this).getBoolean("saveLogin", false);

        // Refresh views.
        txvUser.setText(userLogin);
        txvPass.setText(passLogin);
        chkbRemember.setChecked(saveLogin);

    }

    /**
     * Method for save UserProtheus created in login.
     *
     * @author Julian de Almeida Santos
     * @since 05/03/2019
     */
    public void saveLoginInfo(UserProtheus userProtheus) {

        // Put preferences in main activity to preferences app.
        PreferencesApp.getEditor(this).putString("userLogin", txvUser.getText().toString());
        PreferencesApp.getEditor(this).putString("passLogin", txvPass.getText().toString());
        PreferencesApp.getEditor(this).putBoolean("saveLogin", chkbRemember.isChecked());
        PreferencesApp.getEditor(this).commit();

    }

    /**
     * Method for change status fullscreenLayout.
     *
     * @author Julian de Almeida Santos
     * @since 05/03/2019
     */
    public void toggleFullscreenLayout(View view) {

        if (isFullscrrenLayout) {
            hideNavegationBar();
        } else {
            showNavegationBar();
        }

    }

    /**
     * Method for show navegation bar in layout.
     *
     * @author Julian de Almeida Santos
     * @since 05/03/2019
     */
    public void showNavegationBar() {

        // Define fulscrren activity.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_VISIBLE);

        // Delayed display of UI elements
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
        isFullscrrenLayout = true;
    }

    /**
     * Method for hide navigation bar in layout.
     *
     * @author Julian de Almeida Santos
     * @since 05/03/2019
     */
    public void hideNavegationBar() {
        // Define fulscrren activity.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        isFullscrrenLayout = false;
    }

    /**
     * Method for execute listener onClick in button btnSingIn.
     *
     * @author Julian de Almeida Santos
     * @since 05/03/2019
     * @param view
     */
    public void btnEntrarOnClick(View view) {

        // Local variables.
        String userCode = edtUser.getText().toString();
        String userPass = edtPass.getText().toString();

        // Initialize userProtheus.
        userProtheus = new UserProtheus();
        userProtheus.setCode(userCode);
        userProtheus.setPassword(userPass);
        userProtheus.setSaveLoginInfo(chkbRemember.isChecked());

        // Save login info case checkbox is selected.
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(chkbRemember.isChecked()) {
                    saveLoginInfo(userProtheus);
                }
            }
        }).start();

        // Get UserProtheus and Companys.
        new TaskLogin(this, userProtheus).execute();

    }

    /**
     * Method for executar listener onClick in button floatingActionButton Settings.
     *
     * @author Julian de Almeida Santos
     * @since 05/03/2019
     * @param view
     */
    public void btnSettingsOnClick(View view) {

        Intent intent = new Intent(this, SettingsServerActivity.class);
        startActivity(intent);

    }

}
