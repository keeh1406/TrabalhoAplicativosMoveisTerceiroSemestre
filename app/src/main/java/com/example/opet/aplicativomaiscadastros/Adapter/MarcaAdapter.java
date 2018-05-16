package com.example.opet.aplicativomaiscadastros.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opet.aplicativomaiscadastros.Model.Marca;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class MarcaAdapter extends ArrayAdapter {
    private int resource;
    private List<Marca> marcas;

    public MarcaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Marca> objects) {
        super(context, resource, objects);
        this.resource = resource;
        marcas = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Marca marca = marcas.get(position);

        TextView textIdMarca = (TextView) mView.findViewById(R.id.textIdMarca);
        TextView textNomeMarca = (TextView) mView.findViewById(R.id.textNomeMarca);

        if(textIdMarca != null){
            textIdMarca.setText(String.valueOf(marca.getIdMarca()));
        }
        if(textNomeMarca != null){
            textNomeMarca.setText(marca.getNomeMarca());
        }
        return mView;
    }
}
