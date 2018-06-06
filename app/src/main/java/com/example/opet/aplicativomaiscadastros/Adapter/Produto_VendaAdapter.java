package com.example.opet.aplicativomaiscadastros.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.opet.aplicativomaiscadastros.Model.Produto_Venda;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 06/06/2018.
 */

public class Produto_VendaAdapter extends ArrayAdapter {
    private int resource;
    private List<Produto_Venda> produto_vendas;

    public Produto_VendaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Produto_Venda> objects) {
        super(context, resource, objects);
        this.resource = resource;
        produto_vendas = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Produto_Venda produto_venda = produto_vendas.get(position);

        TextView textIdProdutoVenda = (TextView) mView.findViewById(R.id.textIdProdutoVenda);
        TextView textValorUnitario = (TextView) mView.findViewById(R.id.textValorUnitarioVenda);
        TextView textQuantidadeProduto = (TextView) mView.findViewById(R.id.textQuantidadeProdutoVenda);
        TextView textIdProduto = (TextView) mView.findViewById(R.id.textIdProduto);
        TextView textIdVenda = (TextView) mView.findViewById(R.id.textIdVenda);

        if(textIdProdutoVenda != null){
            textIdProduto.setText(String.valueOf(produto_venda.getIdProdutoVenda()));
        }
        if(textValorUnitario != null){
            textValorUnitario.setText(produto_venda.getValorUnitario());
        }
        if(textQuantidadeProduto != null){
            textQuantidadeProduto.setText(produto_venda.getQuantidadeProduto());
        }
        if(textIdProduto != null){
            textIdProduto.setText(String.valueOf(produto_venda.getId_Produto()));
        }
        if(textIdVenda != null){
            textIdVenda.setText(String.valueOf(produto_venda.getId_Venda()));
        }
        return mView;
    }
}
