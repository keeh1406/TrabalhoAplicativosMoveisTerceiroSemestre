package com.example.opet.aplicativomaiscadastros.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ProdutoAdapter extends ArrayAdapter{
    private int resource;
    private List<Produto> produtos;

    public ProdutoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Produto> objects) {
        super(context, resource, objects);
        this.resource = resource;
        produtos = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Produto produto = produtos.get(position);

        TextView textIdProduto = (TextView) mView.findViewById(R.id.textIdProduto);
        TextView textNomeProduto = (TextView) mView.findViewById(R.id.textNomeProduto);
        TextView textDescricaoProduto = (TextView) mView.findViewById(R.id.textDescricaoProduto);
        TextView textSetorProduto = (TextView) mView.findViewById(R.id.textSetorProduto);
        TextView textMarcaProduto = (TextView) mView.findViewById(R.id.textMarcaProduto);
        TextView textFornecedorProduto = (TextView) mView.findViewById(R.id.textFornecedorProduto);

        if(textIdProduto != null){
            textIdProduto.setText(String.valueOf(produto.getIdProduto()));
        }
        if(textNomeProduto != null){
            textNomeProduto.setText(produto.getNomeProduto());
        }
        if(textDescricaoProduto != null){
            textDescricaoProduto.setText(produto.getDescricaoProduto());
        }
        if(textSetorProduto != null){
            textSetorProduto.setText(String.valueOf(produto.getId_Setor()));
        }
        if(textMarcaProduto != null){
            textMarcaProduto.setText(String.valueOf(produto.getId_Marca()));
        }
        if(textMarcaProduto != null){
            textFornecedorProduto.setText(String.valueOf(produto.getId_Fornecedor()));
        }
        return mView;
    }
}
