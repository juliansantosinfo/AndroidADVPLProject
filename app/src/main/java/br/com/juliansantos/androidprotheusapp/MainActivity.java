package br.com.juliansantos.androidprotheusapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import br.com.juliansantos.totvsprotheuslogin.R;
import br.com.juliansantos.totvsprotheuslogin.beans.CompanyProtheus;
import br.com.juliansantos.totvsprotheuslogin.beans.UserProtheus;
import br.com.juliansantos.totvsprotheuslogin.webservices.WSAuthenticationProtheus;
import br.com.juliansantos.totvsprotheuslogin.webservices.WSUserProtheus;

/**
 * Class that defines the main activity of the application.
 *
 * @author Julian de Almeida Santos
 * @since 04/03/2019
 */
public class MainActivity extends AppCompatActivity {

    // Global variables.
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
    private TextView txvFooter;
    private Button btnSingIn;

    private boolean isAuthorized;
    private UserProtheus user;
    private ArrayList<CompanyProtheus> companysProtheus;

    /**
     *
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

        // Initialize listener for buttons.
        initButtonListener();

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

        layoutMain = findViewById(R.id.layout_main);

        layoutTop = findViewById(R.id.layout2_top);
        txvTitle = findViewById(R.id.txv_login);

        layoutCenter = findViewById(R.id.layout2_center);
        txvUser = findViewById(R.id.txv_user);
        edtUser = findViewById(R.id.edt_user);
        txvPass = findViewById(R.id.txv_pass);
        edtPass = findViewById(R.id.edt_pass);
        chkbRemember = findViewById(R.id.chkb_remember_login);

        layoutBottom = findViewById(R.id.layout2_bottom);
        txvFooter = findViewById(R.id.txv_by);

        btnSingIn = findViewById(R.id.btn_singin);

    }

    /**
     * Method for initialize listener in buttons.
     *
     * @since 04/03/2019
     */
    private void initButtonListener() {

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Local variables.
                String userCode = "mas800109";
                String pass = "Cecor@2009";

                // Initializes user and password
                userCode = edtUser.getText().toString();
                pass = edtPass.getText().toString();
                pass = Base64.encodeToString(pass.getBytes(), Base64.DEFAULT);

                try {

                    // Performs authentication for user and password.
                    isAuthorized = (boolean) new WSAuthenticationProtheus(userCode, pass).execute().get();

                    // Is successfully logged, fetches user information.
                    if (isAuthorized) {
                        user = (UserProtheus) new WSUserProtheus("cod", userCode).execute().get();

                        // If not load user information.
                        if (user.getId().isEmpty()) {
                            return;
                        }

                        // Get available company`s for protheus user.
                        companysProtheus = new ArrayList<CompanyProtheus>(Arrays.asList((CompanyProtheus[]) new WSUserProtheus("emp", userCode).execute().get()));

                        //Next
                        Toast toast = Toast.makeText(getApplicationContext(), "Olâ " + user.getName(), Toast.LENGTH_LONG);
                        toast.show();

                    } else {
                        return;
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
