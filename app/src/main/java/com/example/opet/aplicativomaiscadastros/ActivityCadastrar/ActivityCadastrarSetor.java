package com.example.opet.aplicativomaiscadastros.ActivityCadastrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarSetores;
import com.example.opet.aplicativomaiscadastros.DAO.SetorDAO;
import com.example.opet.aplicativomaiscadastros.Model.Setor;
import com.example.opet.aplicativomaiscadastros.R;

import java.text.ParseException;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityCadastrarSetor extends Activity {
    private EditText editNomeSetor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_setor);

        editNomeSetor = (EditText) findViewById(R.id.editNomeSetor);

    }

    public void salvarSetor(View v) throws ParseException {
        SetorDAO setorDAO = new SetorDAO(this);
        Setor setor = new Setor();
        setor.setNomeSetor(editNomeSetor.getText().toString());

        long resultado = setorDAO.insereDado(setor);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarSetores = new Intent(ActivityCadastrarSetor.this,ActivityListarSetores.class);
            startActivity(listarSetores);
            finish();
        }
        else{
            exibirMensagem("Erro ao cadastrar o item.");
        }
    }

    private void exibirMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}