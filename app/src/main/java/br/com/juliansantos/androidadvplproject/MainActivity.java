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

import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.juliansantos.androidadvplproject.beans.CompanyProtheus;
import br.com.juliansantos.androidadvplproject.beans.UserProtheus;
import br.com.juliansantos.androidadvplproject.tasks.TaskLogin;

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
    private CompanyProtheus companyProtheus;


    /**
     * @since 04/03/2019
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load views to class.
        initViews();

        // If there is settings information, it loads the same.
        loadSettings();

        // If there is login information, it loads the same.
        loadLoginInfo();

    }

    /**
     * Method to load saved settings information.
     *
     * @since 05/03/2019
     */
    private void loadSettings() {
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
     * Method to load saved login information.
     *
     * @since 05/03/2019
     */
    private void loadLoginInfo() {

        File fileAuthentication;
        String pathAuthentication = getApplicationContext().getDir("serializeds", MODE_PRIVATE).getPath() + "/authentication";

        try {

            fileAuthentication = new File(pathAuthentication);

            if (fileAuthentication.exists()) {
                userProtheus = SerializationUtils.deserialize(new FileInputStream(fileAuthentication));

                edtUser.setText(userProtheus.getCode());
                edtPass.setText(userProtheus.getPassword());
                chkbRemember.setChecked(userProtheus.isSaveLoginInfo());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method for save UserProtheus created in login.
     */
    public void saveLoginInfo(UserProtheus userProtheus) {

        File fileAuthentication;
        String pathAuthentication = getApplicationContext().getDir("serializeds", MODE_PRIVATE).getPath() + "/authentication";

        try {

            fileAuthentication = new File(pathAuthentication);

            if (fileAuthentication.exists()) {
                fileAuthentication.delete();
            }

            fileAuthentication.createNewFile();

            SerializationUtils.serialize(userProtheus, new FileOutputStream(fileAuthentication));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}
