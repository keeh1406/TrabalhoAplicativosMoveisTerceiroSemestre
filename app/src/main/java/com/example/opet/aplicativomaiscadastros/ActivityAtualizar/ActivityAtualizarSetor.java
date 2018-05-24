package com.example.opet.aplicativomaiscadastros.ActivityAtualizar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.opet.aplicativomaiscadastros.DAO.SetorDAO;
import com.example.opet.aplicativomaiscadastros.MainActivity;
import com.example.opet.aplicativomaiscadastros.Model.Setor;
import com.example.opet.aplicativomaiscadastros.R;
import java.text.ParseException;

/**
 * Created by opet on 23/05/2018.
 */

public class ActivityAtualizarSetor extends Activity {
    private int ID_SETOR;
    private SetorDAO setorDAO;
    private Setor setor;
    private EditText editNomeSetor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_setores);
        setorDAO = new SetorDAO(this);
        Intent intent = getIntent();
        if(intent.hasExtra("ID_SETOR")){
            ID_SETOR = intent.getIntExtra("ID_SETOR",0);
        }
        try {
            setor = setorDAO.carregaSetorPorID(ID_SETOR);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editNomeSetor = (EditText) findViewById(R.id.editNomeSetorUpdate);

        editNomeSetor.setText(setor.getNomeSetor());
    }

    public void atualizarSetor(View v) throws ParseException {
        setor.setNomeSetor(editNomeSetor.getText().toString());

        if(setorDAO.atualizaSetor(setor))
            Toast.makeText(ActivityAtualizarSetor.this, "Setor atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ActivityAtualizarSetor.this, "Erro ao atualizar o setor.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerSetor(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setorDAO.deletaRegistro(ID_SETOR);
                Toast.makeText(ActivityAtualizarSetor.this, "Setor removido com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(ActivityAtualizarSetor.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }
}
