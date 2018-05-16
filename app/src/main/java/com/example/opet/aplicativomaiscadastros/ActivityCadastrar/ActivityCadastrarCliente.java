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
    private EditText editDescricaoCliente;
    private EditText editValidadeCliente;
    private Spinner editSetorCliente;
    private Spinner editMarcaCliente;
    private Spinner editFornecedorCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        editNomeCliente = (EditText) findViewById(R.id.editNomeCliente);
        editDescricaoCliente = (EditText) findViewById(R.id.editDescricaoCliente);
        editValidadeCliente = (EditText) findViewById(R.id.editValidadeCliente);
        editSetorCliente = (Spinner) findViewById(R.id.editSpinnerSetorCliente);
        editMarcaCliente = (Spinner) findViewById(R.id.editSpinnerMarcaCliente);
        editFornecedorCliente = (Spinner) findViewById(R.id.editSpinnerFornecedorCliente);
    }

    public void salvarCliente(View v) throws ParseException {
        ClienteDAO clienteDAO = new ClienteDAO(this);
        Cliente cliente = new Cliente();
        cliente.setNomeCliente(editNomeCliente.getText().toString());
        cliente.setDescricaoCliente(editDescricaoCliente.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        cliente.setDataValidadeCliente(simpleDateFormat.parse(editValidadeCliente.getText().toString()));
        cliente.setId_Setor(editSetorCliente.getText().toString());
        cliente.setId_Marca(editMarcaCliente.getText().toString());
        cliente.setId_Fornecedor(editFornecedorCliente.getText().toString());

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
