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
        TextView textQuantidadeProduto = (TextView) mView.findViewById(R.id.textQuantidadeProdutoVenda);
        TextView textDescricaoVenda = (TextView) mView.findViewById(R.id.textDescricaoVenda);
        TextView textValorTotalVenda = (TextView) mView.findViewById(R.id.textValorTotalVenda);
        TextView textValorUnitarioVenda = (TextView) mView.findViewById(R.id.textValorUnitarioVenda);
        TextView textFormaPagamentoVenda = (TextView) mView.findViewById(R.id.textFormaPagamentoVenda);
        TextView textDataVenda = (TextView) mView.findViewById(R.id.textDataVenda);
        TextView textIdProdutoVenda = (TextView) mView.findViewById(R.id.textIdProdutoVenda);
        TextView textIdClienteVenda = (TextView) mView.findViewById(R.id.textIdClienteVenda);

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
        if(textValorTotalVenda != null){
            textValorTotalVenda.setText(String.valueOf(venda.getValorTotalVenda()));
        }
        if(textValorUnitarioVenda != null){
            textValorUnitarioVenda.setText(String.valueOf(venda.getValorUnitarioVenda()));
        }
        if(textFormaPagamentoVenda != null){
            textFormaPagamentoVenda.setText(String.valueOf(venda.getFormaPagamentoVenda()));
        }
        if(textIdProdutoVenda != null){
            textIdProdutoVenda.setText(String.valueOf(venda.getIdProdutoVenda()));
        }
        if(textIdClienteVenda != null){
            textIdClienteVenda.setText(String.valueOf(venda.getIdClienteVenda()));
        }
        return mView;
    }
}
