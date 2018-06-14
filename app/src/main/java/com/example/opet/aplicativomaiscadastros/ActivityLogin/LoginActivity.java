package com.example.opet.aplicativomaiscadastros.ActivityLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.aplicativomaiscadastros.ActivityCadastrar.ActivityCadastrarFuncionario;
import com.example.opet.aplicativomaiscadastros.DAO.FuncionarioDAO;
import com.example.opet.aplicativomaiscadastros.MainActivity;
import com.example.opet.aplicativomaiscadastros.R;
import com.example.opet.aplicativomaiscadastros.Util.Util;

/**
 * Created by opet on 14/06/2018.
 */

public class LoginActivity extends Activity {

        private EditText editEmail;
        private EditText editSenha;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            editEmail = (EditText) findViewById(R.id.editEmailUsuario);
            editSenha = (EditText) findViewById(R.id.editSenhaUsuario);
        }

    public void logarUsuario(View v){
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(this);
        long idUsuario = funcionarioDAO.validaUsuario(email, Util.toMD5(senha));
        if(idUsuario > 0){
            Toast.makeText(this, "Funcionário Logado com Sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("ID_FUNCIONARIO",idUsuario);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Funcionário não Cadastrado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void registrarUsuario(View v){
        Intent intent = new Intent(LoginActivity.this,ActivityCadastrarFuncionario.class);
        startActivity(intent);
    }
}
