package com.example.opet.aplicativomaiscadastros.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opet.aplicativomaiscadastros.Model.Loja;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class LojaAdapter extends ArrayAdapter{
    private int resource;
    private List<Loja> lojas;

    public LojaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Loja> objects) {
        super(context, resource, objects);
        this.resource = resource;
        lojas = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Loja loja = lojas.get(position);

        TextView textIdLoja = (TextView) mView.findViewById(R.id.textIdLoja);
        TextView textNomeLoja = (TextView) mView.findViewById(R.id.textNomeLoja);
        TextView textEmailLoja = (TextView) mView.findViewById(R.id.textEmailLoja);
        TextView textCNPJLoja = (TextView) mView.findViewById(R.id.textCNPJLoja);
        TextView textEnderecoLoja = (TextView) mView.findViewById(R.id.textEnderecoLoja);
        TextView textTelefoneLoja = (TextView) mView.findViewById(R.id.textTelefoneLoja);

        if(textIdLoja != null){
            textIdLoja.setText(String.valueOf(loja.getIdLoja()));
        }
        if(textNomeLoja != null){
            textNomeLoja.setText(loja.getNomeLoja());
        }
        if(textEmailLoja != null){
            textEmailLoja.setText(loja.getEmailLoja());
        }
        if(textCNPJLoja != null){
            textCNPJLoja.setText(String.valueOf(loja.getCNPJLoja()));
        }
        if(textEnderecoLoja != null){
            textEnderecoLoja.setText(String.valueOf(loja.getEnderecoLoja()));
        }
        if(textTelefoneLoja != null){
            textTelefoneLoja.setText(String.valueOf(loja.getTelefoneLoja()));
        }
        return mView;
    }
}
