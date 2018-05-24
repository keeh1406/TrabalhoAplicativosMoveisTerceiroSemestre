package com.example.opet.aplicativomaiscadastros.ActivityAtualizar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.aplicativomaiscadastros.DAO.MarcaDAO;
import com.example.opet.aplicativomaiscadastros.MainActivity;
import com.example.opet.aplicativomaiscadastros.Model.Marca;
import com.example.opet.aplicativomaiscadastros.R;

import java.text.ParseException;

/**
 * Created by opet on 23/05/2018.
 */

public class ActivityAtualizarMarca extends Activity {
    private int ID_MARCA;
    private MarcaDAO marcaDAO;
    private Marca marca;
    private EditText editNomeMarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_marcas);
        marcaDAO = new MarcaDAO(this);
        Intent intent = getIntent();
        if(intent.hasExtra("ID_MARCA")){
            ID_MARCA = intent.getIntExtra("ID_MARCA",0);
        }
        try {
            marca = marcaDAO.carregaMarcaPorID(ID_MARCA);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editNomeMarca = (EditText) findViewById(R.id.editNomeMarcaUpdate);

        editNomeMarca.setText(marca.getNomeMarca());
    }

    public void atualizarCliente(View v) throws ParseException {
        marca.setNomeMarca(editNomeMarca.getText().toString());

        if(marcaDAO.atualizaMarca(marca))
            Toast.makeText(ActivityAtualizarMarca.this, "Marca atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ActivityAtualizarMarca.this, "Erro ao atualizar o marca.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerMarca(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                marcaDAO.deletaRegistro(ID_MARCA);
                Toast.makeText(ActivityAtualizarMarca.this, "Marca removida com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(ActivityAtualizarMarca.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }
}
