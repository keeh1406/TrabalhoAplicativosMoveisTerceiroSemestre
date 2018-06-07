package com.example.opet.aplicativomaiscadastros.ActivityAtualizar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.aplicativomaiscadastros.DAO.FornecedorDAO;
import com.example.opet.aplicativomaiscadastros.MainActivity;
import com.example.opet.aplicativomaiscadastros.Model.Fornecedor;
import com.example.opet.aplicativomaiscadastros.R;
import com.example.opet.aplicativomaiscadastros.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by opet on 23/05/2018.
 */

public class ActivityAtualizarFornecedor extends Activity {
    private int ID_FORNECEDOR;
    private FornecedorDAO fornecedorDAO;
    private Fornecedor fornecedor;
    private EditText editNomeFornecedor;
    private EditText editCNPJFornecedor;
    private EditText editTelefoneFornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_fornecedores);
        fornecedorDAO = new FornecedorDAO(this);
        Intent intent = getIntent();
        if(intent.hasExtra("ID_FORNECEDOR")){
            ID_FORNECEDOR = intent.getIntExtra("ID_FORNECEDOR",0);
        }
        try {
            fornecedor = fornecedorDAO.carregaFornecedorPorID(ID_FORNECEDOR);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editNomeFornecedor = (EditText) findViewById(R.id.editNomeFornecedorUpdate);
        editCNPJFornecedor = (EditText) findViewById(R.id.editCNPJFornecedorUpdate);
        editTelefoneFornecedor = (EditText) findViewById(R.id.editTelefoneFornecedorUpdate);

        editNomeFornecedor.setText(fornecedor.getNomeFornecedor());
        editCNPJFornecedor.setText(String.valueOf(fornecedor.getCNPJ()));
        editTelefoneFornecedor.setText(String.valueOf(fornecedor.getTelefoneFornecedor()));
    }

    public void atualizarCliente(View v) throws ParseException {
        fornecedor.setNomeFornecedor(editNomeFornecedor.getText().toString());
        fornecedor.setCNPJ(Long.valueOf(String.valueOf(editCNPJFornecedor.getText().toString())));
        fornecedor.setTelefoneFornecedor(Long.valueOf(editTelefoneFornecedor.getText().toString()));

        if(fornecedorDAO.atualizaFornecedor(fornecedor))
            Toast.makeText(ActivityAtualizarFornecedor.this, "Fornecedor atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ActivityAtualizarFornecedor.this, "Erro ao atualizar o fornecedor.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerCliente(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fornecedorDAO.deletaRegistro(ID_FORNECEDOR);
                Toast.makeText(ActivityAtualizarFornecedor.this, "Fornecedor removido com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(ActivityAtualizarFornecedor.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }
}