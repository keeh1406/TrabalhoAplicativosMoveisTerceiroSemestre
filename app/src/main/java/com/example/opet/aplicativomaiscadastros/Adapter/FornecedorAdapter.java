package com.example.opet.aplicativomaiscadastros.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opet.aplicativomaiscadastros.Model.Fornecedor;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class FornecedorAdapter extends ArrayAdapter {
    private int resource;
    private List<Fornecedor> fornecedores;

    public FornecedorAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Fornecedor> objects) {
        super(context, resource, objects);
        this.resource = resource;
        fornecedores = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Fornecedor fornecedor = fornecedores.get(position);

        TextView textIdFornecedor = (TextView) mView.findViewById(R.id.textIdFornecedor);
        TextView textNomeFornecedor = (TextView) mView.findViewById(R.id.textNomeFornecedor);
        TextView textCNPJFornecedor = (TextView) mView.findViewById(R.id.textCNPJFornecedor);
        TextView textTelefoneFornecedor = (TextView) mView.findViewById(R.id.textTelefoneFornecedor);

        if(textIdFornecedor != null){
            textIdFornecedor.setText(String.valueOf(fornecedor.getIdFornecedor()));
        }
        if(textNomeFornecedor != null){
            textNomeFornecedor.setText(fornecedor.getNomeFornecedor());
        }
        if(textCNPJFornecedor != null){
            textCNPJFornecedor.setText(String.valueOf(fornecedor.getCNPJ()));
        }
        if(textTelefoneFornecedor != null){
            textTelefoneFornecedor.setText(String.valueOf(fornecedor.getTelefoneFornecedor()));
        }
        return mView;
    }
}
