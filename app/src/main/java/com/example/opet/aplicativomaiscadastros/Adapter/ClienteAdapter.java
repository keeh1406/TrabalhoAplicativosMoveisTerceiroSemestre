package com.example.opet.aplicativomaiscadastros.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opet.aplicativomaiscadastros.Model.Cliente;
import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ClienteAdapter extends ArrayAdapter {
    private int resource;
    private List<Cliente> clientes;

    public ClienteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Cliente> objects) {
        super(context, resource, objects);
        this.resource = resource;
        clientes = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Cliente cliente = clientes.get(position);

        TextView textIdCliente = (TextView) mView.findViewById(R.id.textIdCliente);
        TextView textNomeCliente = (TextView) mView.findViewById(R.id.textNomeCliente);
        TextView textEmailCliente = (TextView) mView.findViewById(R.id.textEmailCliente);
        TextView textSenhaCliente = (TextView) mView.findViewById(R.id.textSenhaCliente);
        TextView textNascimentoCliente = (TextView) mView.findViewById(R.id.textNascimentoCliente);
        TextView textCPFCliente = (TextView) mView.findViewById(R.id.textCPFCliente);
        TextView textEnderecoCliente = (TextView) mView.findViewById(R.id.textEnderecoCliente);
        TextView textTelefoneCliente = (TextView) mView.findViewById(R.id.textTelefoneCliente);
        TextView textSexoCliente = (TextView) mView.findViewById(R.id.textSexoCliente);

        if(textIdCliente != null){
            textIdCliente.setText(String.valueOf(cliente.getIdCliente()));
        }
        if(textNomeCliente != null){
            textNomeCliente.setText(cliente.getNomeCliente());
        }
        if(textEmailCliente != null){
            textEmailCliente.setText(cliente.getEmailCliente());
        }
        if(textSenhaCliente != null){
            textSenhaCliente.setText(cliente.getSenhaCliente());
        }
        if(textNascimentoCliente != null){
            textNascimentoCliente.setText(String.valueOf(cliente.getNascimentoCliente()));
        }
        if(textCPFCliente != null){
            textCPFCliente.setText(String.valueOf(cliente.getCPFCliente()));
        }
        if(textEnderecoCliente != null){
            textEnderecoCliente.setText(String.valueOf(cliente.getEnderecoCliente()));
        }
        if(textTelefoneCliente != null){
            textTelefoneCliente.setText(String.valueOf(cliente.getTelefoneCliente()));
        }
        if(textSexoCliente != null){
            textSexoCliente.setText(String.valueOf(cliente.getSexoCliente()));
        }
        return mView;
    }
}
