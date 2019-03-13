package br.com.juliansantos.androidadvplproject.activits;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.juliansantos.androidadvplproject.R;
import br.com.juliansantos.androidadvplproject.adapters.AdapterCompanys;
import br.com.juliansantos.androidadvplproject.beans.CompanyProtheus;
import br.com.juliansantos.androidadvplproject.beans.UserProtheus;

public class CompanysActivity extends AppCompatActivity {

    private String filterBranch = "01";
    private ListView listViewMain;
    private ArrayList<CompanyProtheus> listCompanysFull;
    private ArrayList<CompanyProtheus> listCompanys;
    private AdapterCompanys adapterCompanys;
    private EditText edtSearch;
    private Handler handler;
    private UserProtheus userProtheus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companys);

        // load views to class.
        initViews();

        // Initialize handler.
        initHandler();

        // Get extras via intent.
        getIntentExtras();

        // Remove branch.
        filterBranch();

        // Define list layout with adapterCompany.
        adapterCompanys= new AdapterCompanys(this, listCompanys);
        listViewMain.setAdapter(adapterCompanys);
    }

    private void initViews() {

        listViewMain = findViewById(R.id.listview_main);
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CompanyProtheus company = adapterCompanys.getListCompany().get(position);

                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                intent.putExtra("userProtheus", userProtheus);
                intent.putExtra("companyProtheus", company);

                startActivity(intent);
            }
        });

        edtSearch = findViewById(R.id.edt_search_companys);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                ArrayList<CompanyProtheus> listSearch = new ArrayList<CompanyProtheus>();

                if(edtSearch.getText().toString().isEmpty()) {

                    // Define list layout with adapterCompany.
                    adapterCompanys.updateAdpter(listCompanys);

                } else {

                    for (int i = 0; i < listCompanys.size();  i++){
                        if(listCompanys.get(i).getName().toLowerCase().contains(edtSearch.getText().toString().toLowerCase())) {
                            listSearch.add(listCompanys.get(i));
                        }
                    }

                    // Define list layout with adapterCompany.
                    adapterCompanys.updateAdpter(listSearch);
                }
            }
        });

    }

    public void initHandler() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
    }

    private void getIntentExtras() {

        if (getIntent() == null) {
            return;
        }

        userProtheus = (UserProtheus) getIntent().getSerializableExtra("userProtheus");
        listCompanysFull = userProtheus.getListCompanys();
    }

    private void filterBranch() {

        listCompanys = new ArrayList<CompanyProtheus>();
        for(CompanyProtheus company : listCompanysFull){
            if( company.getBranch().equals(filterBranch) ) {
                listCompanys.add(company);
            }
        }

    }
}

