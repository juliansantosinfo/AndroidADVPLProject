package br.com.juliansantos.androidadvplproject.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.juliansantos.androidadvplproject.R;
import br.com.juliansantos.androidadvplproject.activits.CompanysActivity;
import br.com.juliansantos.androidadvplproject.beans.CompanyProtheus;

public class AdapterCompanys extends BaseAdapter {

    private ArrayList<CompanyProtheus> companysProtheus;
    private CompanysActivity activity;

    public AdapterCompanys(CompanysActivity activity, ArrayList<CompanyProtheus> companysProtheus) {
        this.activity = activity;
        this.companysProtheus = companysProtheus;
    }

    @Override
    public int getCount() {
        return companysProtheus.size();
    }

    @Override
    public Object getItem(int position) {
        return companysProtheus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.listview_companys, parent, false);
        CompanyProtheus company = companysProtheus.get(position);

        ImageView imgView = view.findViewById(R.id.listview_companys_image);
        TextView txvCode = view.findViewById(R.id.listview_companys_code);
        TextView txvNome = view.findViewById(R.id.listview_companys_nome);

        txvCode.setText("Empresa: "
                + company.getCode().trim()
                + "/" + company.getBranch().trim());
        txvNome.setText(company.getFullName().trim());

        return view;
    }

    public void updateAdpter(ArrayList<CompanyProtheus> companysProtheus) {
        this.companysProtheus = companysProtheus;
        notifyDataSetChanged();
    }

    public ArrayList<CompanyProtheus> getListCompany() {
        return companysProtheus;
    }

}
