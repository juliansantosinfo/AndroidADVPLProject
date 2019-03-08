package br.com.juliansantos.androidadvplproject.activits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;

import br.com.juliansantos.androidadvplproject.R;
import br.com.juliansantos.androidadvplproject.adapters.AdapterCompanys;
import br.com.juliansantos.androidadvplproject.beans.CompanyProtheus;
import br.com.juliansantos.androidadvplproject.beans.UserProtheus;

public class CompanysActivity extends AppCompatActivity {

    private ListView listViewMain;
    private ArrayList<CompanyProtheus> listCompanys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companys);

        UserProtheus userProtheus = SerializationUtils.deserialize((byte[]) getIntent().getSerializableExtra("userProtheus"));
        listCompanys = userProtheus.getListCompanys();

        // Remove branch.
        for (int i=0; i < listCompanys.size(); i++){
            if (listCompanys.get(i).getBranch() != "01") {
                listCompanys.remove(i);
            }
        }

        // load views to class.
        initViews();

        // Define list layout with adapterCompany.
        listViewMain.setAdapter(new AdapterCompanys(this, listCompanys));
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

