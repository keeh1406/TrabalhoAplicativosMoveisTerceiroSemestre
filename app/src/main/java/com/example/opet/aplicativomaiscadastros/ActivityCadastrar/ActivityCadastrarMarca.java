package com.example.opet.aplicativomaiscadastros.ActivityCadastrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarMarcas;
import com.example.opet.aplicativomaiscadastros.DAO.MarcaDAO;
import com.example.opet.aplicativomaiscadastros.Model.Marca;
import com.example.opet.aplicativomaiscadastros.R;

import java.text.ParseException;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityCadastrarMarca extends Activity {
    private EditText editNomeMarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_marca);

        editNomeMarca = (EditText) findViewById(R.id.editNomeMarca);

    }

    public void salvarMarca(View v) throws ParseException {
        MarcaDAO marcaDAO = new MarcaDAO(this);
        Marca marca = new Marca();
        marca.setNomeMarca(editNomeMarca.getText().toString());

        long resultado = marcaDAO.insereDado(marca);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarMarcaes = new Intent(ActivityCadastrarMarca.this,ActivityListarMarcas.class);
            startActivity(listarMarcaes);
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
