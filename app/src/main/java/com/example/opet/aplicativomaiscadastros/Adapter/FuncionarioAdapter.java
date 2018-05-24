package com.example.opet.aplicativomaiscadastros.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opet.aplicativomaiscadastros.Model.Funcionario;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class FuncionarioAdapter extends ArrayAdapter{
    private int resource;
    private List<Funcionario> funcionarios;

    public FuncionarioAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Funcionario> objects) {
        super(context, resource, objects);
        this.resource = resource;
        funcionarios = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Funcionario funcionario = funcionarios.get(position);

        TextView textIdFuncionario = (TextView) mView.findViewById(R.id.textIdFuncionario);
        TextView textNomeFuncionario = (TextView) mView.findViewById(R.id.textNomeFuncionario);
        TextView textEmailFuncionario = (TextView) mView.findViewById(R.id.textEmailFuncionario);
        TextView textSenhaFuncionario = (TextView) mView.findViewById(R.id.textSenhaFuncionario);
        TextView textNascimentoFuncionario = (TextView) mView.findViewById(R.id.textNascimentoFuncionario);
        TextView textCPFFuncionario = (TextView) mView.findViewById(R.id.textCPFFuncionario);
        TextView textEnderecoFuncionario = (TextView) mView.findViewById(R.id.textEnderecoFuncionario);
        TextView textTelefoneFuncionario = (TextView) mView.findViewById(R.id.textTelefoneFuncionario);
        TextView textSexoFuncionario = (TextView) mView.findViewById(R.id.spinnerSexoFuncionario);
        TextView textFlGerenteFuncionario = (TextView) mView.findViewById(R.id.textFlGerenteFuncionario);
        TextView textIdLojaFuncionario = (TextView) mView.findViewById(R.id.textIdLojaFuncionario);

        if(textIdFuncionario != null){
            textIdFuncionario.setText(String.valueOf(funcionario.getIdFuncionario()));
        }
        if(textNomeFuncionario != null){
            textNomeFuncionario.setText(funcionario.getNomeFuncionario());
        }
        if(textEmailFuncionario != null){
            textEmailFuncionario.setText(funcionario.getEmailFuncionario());
        }
        if(textSenhaFuncionario != null){
            textSenhaFuncionario.setText(funcionario.getSenhaFuncionario());
        }
        if(textNascimentoFuncionario != null){
            textNascimentoFuncionario.setText(String.valueOf(funcionario.getNascimentoFuncionario()));
        }
        if(textCPFFuncionario != null){
            textCPFFuncionario.setText(String.valueOf(funcionario.getCPFFuncionario()));
        }
        if(textEnderecoFuncionario != null){
            textEnderecoFuncionario.setText(String.valueOf(funcionario.getEnderecoFuncionario()));
        }
        if(textTelefoneFuncionario != null){
            textTelefoneFuncionario.setText(String.valueOf(funcionario.getTelefoneFuncionario()));
        }
        if(textSexoFuncionario != null){
            textSexoFuncionario.setText(String.valueOf(funcionario.getSexoFuncionario()));
        }
        if(textFlGerenteFuncionario != null){
            textFlGerenteFuncionario.setText(String.valueOf(funcionario.getFlGerente()));
        }
        if(textIdLojaFuncionario != null){
            textIdLojaFuncionario.setText(String.valueOf(funcionario.getId_Loja()));
        }
        return mView;
    }
}
