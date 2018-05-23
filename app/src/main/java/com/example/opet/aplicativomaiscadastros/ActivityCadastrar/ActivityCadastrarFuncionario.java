package com.example.opet.aplicativomaiscadastros.ActivityCadastrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarFuncionarios;
import com.example.opet.aplicativomaiscadastros.Adapter.LojaAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.FuncionarioDAO;
import com.example.opet.aplicativomaiscadastros.DAO.LojaDAO;
import com.example.opet.aplicativomaiscadastros.Model.Funcionario;
import com.example.opet.aplicativomaiscadastros.Model.Loja;
import com.example.opet.aplicativomaiscadastros.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityCadastrarFuncionario extends Activity {
    private EditText editNomeFuncionario;
    private EditText editEmailFuncionario;
    private EditText editSenhaFuncionario;
    private EditText editNascimentoFuncionario;
    private EditText editCPFFuncionario;
    private EditText editEnderecoFuncionario;
    private EditText editTelefoneFuncionario;
    private CheckBox checkboxFlGerenteFuncionario;
    private Spinner spinnerSexoFuncionario;
    private Spinner spinnerIdLojaFuncionario;
    private Loja loja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_funcionario);

        editNomeFuncionario = (EditText) findViewById(R.id.editNomeFuncionario);
        editEmailFuncionario = (EditText) findViewById(R.id.editEmailFuncionario);
        editSenhaFuncionario = (EditText) findViewById(R.id.editSenhaFuncionario);
        editNascimentoFuncionario = (EditText) findViewById(R.id.editNascimentoFuncionario);
        editCPFFuncionario = (EditText) findViewById(R.id.editCPFFuncionario);
        editEnderecoFuncionario = (EditText) findViewById(R.id.editEnderecoFuncionario);
        editTelefoneFuncionario = (EditText) findViewById(R.id.editTelefoneFuncionario);
        checkboxFlGerenteFuncionario = (CheckBox) findViewById(R.id.checkboxFlGerenteFuncionario);
        spinnerSexoFuncionario = (Spinner) findViewById(R.id.SpinnerSexoFuncionario);

        spinnerIdLojaFuncionario = findViewById(R.id.spinnerIdLojaFuncionario);
        List<Loja> lojas = new ArrayList<>();
        final LojaAdapter lojaAdapter = new LojaAdapter(this,android.R.layout.simple_spinner_item,lojas);
        spinnerIdLojaFuncionario.setAdapter(lojaAdapter);
        spinnerIdLojaFuncionario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loja = (Loja) lojaAdapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void salvarFuncionario(View v) throws ParseException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(this);
        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFuncionario(editNomeFuncionario.getText().toString());
        funcionario.setEmailFuncionario(editEmailFuncionario.getText().toString());
        funcionario.setSenhaFuncionario(editSenhaFuncionario.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        funcionario.setNascimentoFuncionario(simpleDateFormat.parse(editNascimentoFuncionario.getText().toString()));
        funcionario.setCPFFuncionario(Long.parseLong(editCPFFuncionario.getText().toString()));
        funcionario.setEnderecoFuncionario(editEnderecoFuncionario.getText().toString());
        funcionario.setEnderecoFuncionario(editTelefoneFuncionario.getText().toString());
        funcionario.setFlGerente(checkboxFlGerenteFuncionario.getText().toString());
        funcionario.setSexoFuncionario((spinnerSexoFuncionario.toString()));
        funcionario.setId_Loja(loja.getIdLoja());

        long resultado = funcionarioDAO.insereDado(funcionario);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarFuncionarios = new Intent(ActivityCadastrarFuncionario.this,ActivityListarFuncionarios.class);
            startActivity(listarFuncionarios);
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
