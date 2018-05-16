package com.example.opet.aplicativomaiscadastros.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opet.aplicativomaiscadastros.Model.Venda;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class VendaAdapter extends ArrayAdapter {
    private int resource;
    private List<Venda> vendas;

    public VendaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Venda> objects) {
        super(context, resource, objects);
        this.resource = resource;
        vendas = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Venda venda = vendas.get(position);

        TextView textIdVenda = (TextView) mView.findViewById(R.id.textIdVenda);
        TextView textQuantidadeProduto = (TextView) mView.findViewById(R.id.textQuantidadeProduto);
        TextView textDescricaoVenda = (TextView) mView.findViewById(R.id.textDescricaoVenda);
        TextView textDataVenda = (TextView) mView.findViewById(R.id.textDataVenda);
        TextView textValorVenda = (TextView) mView.findViewById(R.id.textValorVenda);
        TextView textId_Produto = (TextView) mView.findViewById(R.id.textId_Produto);
        TextView textId_Cliente = (TextView) mView.findViewById(R.id.textId_Cliente);

        if(textIdVenda != null){
            textIdVenda.setText(String.valueOf(venda.getIdVenda()));
        }
        if(textQuantidadeProduto != null){
            textQuantidadeProduto.setText(venda.getQuantidadeProduto());
        }
        if(textDescricaoVenda != null){
            textDescricaoVenda.setText(venda.getDescricaoVenda());
        }
        if(textDataVenda != null){
            textDataVenda.setText(String.valueOf(venda.getDataVenda()));
        }
        if(textValorVenda != null){
            textValorVenda.setText(String.valueOf(venda.getValorVenda()));
        }
        if(textId_Produto != null){
            textId_Produto.setText(String.valueOf(venda.getId_Produto()));
        }
        if(textId_Cliente != null){
            textId_Cliente.setText(String.valueOf(venda.getId_Cliente()));
        }
        return mView;
    }
}
