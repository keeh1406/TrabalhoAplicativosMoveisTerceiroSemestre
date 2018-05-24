package com.example.opet.aplicativomaiscadastros.ActivityAtualizar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.opet.aplicativomaiscadastros.DAO.FuncionarioDAO;
import com.example.opet.aplicativomaiscadastros.MainActivity;
import com.example.opet.aplicativomaiscadastros.Model.Funcionario;
import com.example.opet.aplicativomaiscadastros.Model.Loja;
import com.example.opet.aplicativomaiscadastros.R;
import com.example.opet.aplicativomaiscadastros.Util.Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by opet on 23/05/2018.
 */

public class ActivityAtualizarFuncionario extends Activity {
    private int ID_FUNCIONARIO;
    private FuncionarioDAO funcionarioDAO;
    private Funcionario funcionario;
    private EditText editNomeFuncionario;
    private EditText editEmailFuncionario;
    private EditText editSenhaFuncionario;
    private EditText editNascimentoFuncionario;
    private EditText editCPFFuncionario;
    private EditText editEnderecoFuncionario;
    private EditText editSexoFuncionario;
    private EditText editFlGerenteFuncionario;
    private EditText editIdLojaFuncionario;
    private Loja loja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_funcionarios);
        funcionarioDAO = new FuncionarioDAO(this);
        Intent intent = getIntent();
        if(intent.hasExtra("ID_FUNCIONARIO")){
            ID_FUNCIONARIO = intent.getIntExtra("ID_FUNCIONARIO",0);
        }
        try {
            funcionario = funcionarioDAO.carregaFuncionarioPorID(ID_FUNCIONARIO);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editNomeFuncionario = (EditText) findViewById(R.id.editNomeFuncionarioUpdate);
        editEmailFuncionario = (EditText) findViewById(R.id.editEmailFuncionarioUpdate);
        editSenhaFuncionario = (EditText) findViewById(R.id.editSenhaFuncionarioUpdate);
        editNascimentoFuncionario = (EditText) findViewById(R.id.editNascimentoFuncionarioUpdate);
        editCPFFuncionario = (EditText) findViewById(R.id.editCPFFuncionarioUpdate);
        editEnderecoFuncionario = (EditText) findViewById(R.id.editEmailFuncionarioUpdate);
        editSexoFuncionario = (EditText) findViewById(R.id.editSexoFuncionarioUpdate);
        editFlGerenteFuncionario = (EditText) findViewById(R.id.editFlGerenteFuncionarioUpdate);
        editIdLojaFuncionario = (EditText) findViewById(R.id.editIdLojaFuncionarioUpdate);

        editNomeFuncionario.setText(funcionario.getNomeFuncionario());
        editEmailFuncionario.setText(funcionario.getEmailFuncionario());
        editSenhaFuncionario.setText(funcionario.getSenhaFuncionario());
        editNascimentoFuncionario.setText(Util.toString(funcionario.getNascimentoFuncionario()));
        editCPFFuncionario.setText(funcionario.getCPFFuncionario());
        editEnderecoFuncionario.setText(funcionario.getEnderecoFuncionario());
        editSexoFuncionario.setText(funcionario.getSexoFuncionario());
        editFlGerenteFuncionario.setText(funcionario.getFlGerente());
        editIdLojaFuncionario.setText(loja.getIdLoja());
    }

    public void atualizarFuncionario(View v) throws ParseException {
        funcionario.setNomeFuncionario(editNomeFuncionario.getText().toString());
        funcionario.setEmailFuncionario(editEmailFuncionario.getText().toString());
        funcionario.setSenhaFuncionario(editSenhaFuncionario.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        funcionario.setNascimentoFuncionario(simpleDateFormat.parse(editNascimentoFuncionario.getText().toString()));
        funcionario.setCPFFuncionario(editCPFFuncionario.getText().toString());
        funcionario.setEnderecoFuncionario(editEnderecoFuncionario.getText().toString());
        funcionario.setSexoFuncionario(editSexoFuncionario.getText().toString());
        funcionario.setFlGerente(editFlGerenteFuncionario.getText().toString());
        funcionario.setId_Loja(editIdLojaFuncionario.getText().toString());

        if(funcionarioDAO.atualizaFuncionario(funcionario))
            Toast.makeText(ActivityAtualizarFuncionario.this, "Funcionario atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ActivityAtualizarFuncionario.this, "Erro ao atualizar o funcionario.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerFuncionario(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                funcionarioDAO.deletaRegistro(ID_FUNCIONARIO);
                Toast.makeText(ActivityAtualizarFuncionario.this, "Funcionario removido com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(ActivityAtualizarFuncionario.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }
}