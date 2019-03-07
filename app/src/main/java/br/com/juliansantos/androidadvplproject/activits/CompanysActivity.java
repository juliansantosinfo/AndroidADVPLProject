package br.com.juliansantos.androidadvplproject.activits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import br.com.juliansantos.androidadvplproject.R;

public class CompanysActivity extends AppCompatActivity {

    private ListView listViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companys);

        // load views to class.
        initViews();

        listViewMain.addView(new TextView(this));
        listViewMain.addView(new TextView(this));
        listViewMain.addView(new TextView(this));
        listViewMain.addView(new TextView(this));
        listViewMain.addView(new TextView(this));
        listViewMain.addView(new TextView(this));
        listViewMain.addView(new TextView(this));
        listViewMain.addView(new TextView(this));
        listViewMain.addView(new TextView(this));

    }

    /**
     * Method responsible for initializing objects views of the XML layout.
     *
     * @since 04/03/2019
     */
    private void initViews() {

        listViewMain = findViewById(R.id.listview_main);

    }



}

