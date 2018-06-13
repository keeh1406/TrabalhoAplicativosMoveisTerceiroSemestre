package com.example.opet.aplicativomaiscadastros.ActivityCadastrar;

/**
 * Created by opet on 16/05/2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarClientes;
import com.example.opet.aplicativomaiscadastros.DAO.ClienteDAO;
import com.example.opet.aplicativomaiscadastros.Model.Cliente;
import com.example.opet.aplicativomaiscadastros.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityCadastrarCliente extends Activity {
    private EditText editNomeCliente;
    private EditText editEmailCliente;
    private EditText editSenhaCliente;
    private EditText editNascimentoCliente;
    private EditText editCPFCliente;
    private EditText editEnderecoCliente;
    private EditText editTelefoneCliente;
    private Spinner SpinnerSexoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        editNomeCliente = (EditText) findViewById(R.id.editNomeCliente);
        editEmailCliente = (EditText) findViewById(R.id.editEmailCliente);
        editSenhaCliente = (EditText) findViewById(R.id.editSenhaCliente);
        editNascimentoCliente = (EditText) findViewById(R.id.editNascimentoCliente);
        editCPFCliente = (EditText) findViewById(R.id.editCPFCliente);
        editEnderecoCliente = (EditText) findViewById(R.id.editEnderecoCliente);
        editTelefoneCliente = (EditText) findViewById(R.id.editTelefoneCliente);
        SpinnerSexoCliente = (Spinner) findViewById(R.id.SpinnerSexoCliente);
    }

    public void salvarCliente(View v) throws ParseException {
        ClienteDAO clienteDAO = new ClienteDAO(this);
        Cliente cliente = new Cliente();
        cliente.setNomeCliente(editNomeCliente.getText().toString());
        cliente.setEmailCliente(editEmailCliente.getText().toString());
        cliente.setSenhaCliente(editSenhaCliente.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        cliente.setNascimentoCliente(simpleDateFormat.parse(editNascimentoCliente.getText().toString()));
        cliente.setCPFCliente(Long.parseLong(editCPFCliente.getText().toString()));
        cliente.setEnderecoCliente(editEnderecoCliente.getText().toString());
        cliente.setTelefoneCliente(Long.parseLong(editTelefoneCliente.getText().toString()));
        cliente.setSexoCliente(SpinnerSexoCliente.toString());

        long resultado = clienteDAO.insereDado(cliente);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarClientes = new Intent(ActivityCadastrarCliente.this,ActivityListarClientes.class);
            startActivity(listarClientes);
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
