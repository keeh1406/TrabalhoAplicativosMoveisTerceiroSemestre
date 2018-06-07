package com.example.opet.aplicativomaiscadastros.ActivityAtualizar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.opet.aplicativomaiscadastros.DAO.LojaDAO;
import com.example.opet.aplicativomaiscadastros.MainActivity;
import com.example.opet.aplicativomaiscadastros.Model.Loja;
import com.example.opet.aplicativomaiscadastros.R;
import java.text.ParseException;

/**
 * Created by opet on 23/05/2018.
 */

public class ActivityAtualizarLoja extends Activity {
    private int ID_LOJA;
    private LojaDAO lojaDAO;
    private Loja loja;
    private EditText ediEnderecoLoja;
    private EditText editNomeLoja;
    private EditText editEmailLoja;
    private EditText editTelefoneLoja;
    private EditText editCNPJLoja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_lojas);
        lojaDAO = new LojaDAO(this);
        Intent intent = getIntent();
        if(intent.hasExtra("ID_LOJA")){
            ID_LOJA = intent.getIntExtra("ID_LOJA",0);
        }
        try {
            loja = lojaDAO.carregaLojaPorID(ID_LOJA);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ediEnderecoLoja = findViewById(R.id.editEnderecoLojaUpdate);
        editNomeLoja = findViewById(R.id.editNomeLojaUpdate);
        editEmailLoja = findViewById(R.id.editEmailLojaUpdate);
        editTelefoneLoja = findViewById(R.id.editTelefoneLojaUpdate);
        editCNPJLoja = findViewById(R.id.editCNPJLojaUpdate);

        ediEnderecoLoja.setText(loja.getEnderecoLoja());
        editNomeLoja.setText(loja.getNomeLoja());
        editEmailLoja.setText(loja.getEmailLoja());
        editTelefoneLoja.setText(String.valueOf(loja.getTelefoneLoja()));
        editCNPJLoja.setText(String.valueOf(loja.getCNPJLoja()));
    }

    public void atualizarLoja(View v) throws ParseException {
        loja.setEnderecoLoja(ediEnderecoLoja.getText().toString());
        loja.setNomeLoja(editNomeLoja.getText().toString());
        loja.setEmailLoja(editEmailLoja.getText().toString());
        loja.setCNPJLoja(Long.valueOf(String.valueOf(editCNPJLoja.getText().toString())));
        loja.setTelefoneLoja(Long.valueOf(editTelefoneLoja.getText().toString()));

        if(lojaDAO.atualizaLoja(loja))
            Toast.makeText(ActivityAtualizarLoja.this, "Loja atualizada com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ActivityAtualizarLoja.this, "Erro ao atualizar a loja.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerCliente(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lojaDAO.deletaRegistro(ID_LOJA);
                Toast.makeText(ActivityAtualizarLoja.this, "Loja removida com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(ActivityAtualizarLoja.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }
}