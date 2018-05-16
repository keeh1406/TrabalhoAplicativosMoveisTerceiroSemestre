package com.example.opet.aplicativomaiscadastros.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opet.aplicativomaiscadastros.Model.Setor;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class SetorAdapter extends ArrayAdapter {
    private int resource;
    private List<Setor> setors;

    public SetorAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Setor> objects) {
        super(context, resource, objects);
        this.resource = resource;
        setors = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Setor setor = setors.get(position);

        TextView textIdSetor = (TextView) mView.findViewById(R.id.textIdSetor);
        TextView textNomeSetor = (TextView) mView.findViewById(R.id.textNomeSetor);

        if(textIdSetor != null){
            textIdSetor.setText(String.valueOf(setor.getIdSetor()));
        }
        if(textNomeSetor != null){
            textNomeSetor.setText(setor.getNomeSetor());
        }
        return mView;
    }
}
