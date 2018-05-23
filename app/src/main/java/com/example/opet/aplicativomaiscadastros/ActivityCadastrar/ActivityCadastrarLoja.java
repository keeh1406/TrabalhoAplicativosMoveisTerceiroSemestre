package com.example.opet.aplicativomaiscadastros.ActivityCadastrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarLojas;
import com.example.opet.aplicativomaiscadastros.DAO.LojaDAO;
import com.example.opet.aplicativomaiscadastros.Model.Loja;
import com.example.opet.aplicativomaiscadastros.R;
import java.text.ParseException;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityCadastrarLoja extends Activity {
    private EditText editNomeLoja;
    private EditText editEmailLoja;
    private EditText editCNPJLoja;
    private EditText editEnderecoLoja;
    private EditText editTelefoneLoja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_loja);

        editNomeLoja = (EditText) findViewById(R.id.editNomeLoja);
        editEmailLoja = (EditText) findViewById(R.id.editEmailLoja);
        editCNPJLoja = (EditText) findViewById(R.id.editCNPJLoja);
        editEnderecoLoja = (EditText) findViewById(R.id.editEnderecoLoja);
        editTelefoneLoja = (EditText) findViewById(R.id.editTelefoneLoja);
    }

    public void salvarLoja(View v) throws ParseException {
        LojaDAO lojaDAO = new LojaDAO(this);
        Loja loja = new Loja();
        loja.setNomeLoja(editNomeLoja.getText().toString());
        loja.setEmailLoja(editEmailLoja.getText().toString());
        loja.setCNPJLoja(Long.parseLong(editCNPJLoja.getText().toString()));
        loja.setEnderecoLoja(editEnderecoLoja.getText().toString());
        loja.setEnderecoLoja(editTelefoneLoja.getText().toString());

        long resultado = lojaDAO.insereDado(loja);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarLojas = new Intent(ActivityCadastrarLoja.this,ActivityListarLojas.class);
            startActivity(listarLojas);
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