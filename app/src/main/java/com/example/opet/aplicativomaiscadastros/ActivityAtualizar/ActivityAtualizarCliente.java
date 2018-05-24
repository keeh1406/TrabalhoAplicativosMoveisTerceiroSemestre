package com.example.opet.aplicativomaiscadastros.ActivityAtualizar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.aplicativomaiscadastros.DAO.ClienteDAO;
import com.example.opet.aplicativomaiscadastros.MainActivity;
import com.example.opet.aplicativomaiscadastros.Model.Cliente;
import com.example.opet.aplicativomaiscadastros.R;
import com.example.opet.aplicativomaiscadastros.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by opet on 23/05/2018.
 */

public class ActivityAtualizarCliente extends Activity {
    private int ID_CLIENTE;
    private ClienteDAO clienteDAO;
    private Cliente cliente;
    private EditText editNomeCliente;
    private EditText editEmailCliente;
    private EditText editSenhaCliente;
    private EditText editNascimentoCliente;
    private EditText editCPFCliente;
    private EditText editEnderecoCliente;
    private EditText editSexoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_clientes);
        clienteDAO = new ClienteDAO(this);
        Intent intent = getIntent();
        if(intent.hasExtra("ID_CLIENTE")){
            ID_CLIENTE = intent.getIntExtra("ID_CLIENTE",0);
        }
        try {
            cliente = clienteDAO.carregaClientePorID(ID_CLIENTE);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editNomeCliente = (EditText) findViewById(R.id.editNomeClienteUpdate);
        editEmailCliente = (EditText) findViewById(R.id.editEmailClienteUpdate);
        editSenhaCliente = (EditText) findViewById(R.id.editSenhaClienteUpdate);
        editNascimentoCliente = (EditText) findViewById(R.id.editNascimentoClienteUpdate);
        editCPFCliente = (EditText) findViewById(R.id.editCPFClienteUpdate);
        editEnderecoCliente = (EditText) findViewById(R.id.editEmailClienteUpdate);
        editSexoCliente = (EditText) findViewById(R.id.editSexoClienteUpdate);

        editNomeCliente.setText(cliente.getNomeCliente());
        editEmailCliente.setText(cliente.getEmailCliente());
        editSenhaCliente.setText(cliente.getSenhaCliente());
        editNascimentoCliente.setText(Util.toString(cliente.getNascimentoCliente()));
        editCPFCliente.setText(cliente.getCPFCliente());
        editEnderecoCliente.setText(cliente.getEnderecoCliente());
        editSexoCliente.setText(cliente.getSexoCliente());
    }

    public void atualizarCliente(View v) throws ParseException {
        cliente.setNomeCliente(editNomeCliente.getText().toString());
        cliente.setEmailCliente(editEmailCliente.getText().toString());
        cliente.setSenhaCliente(editSenhaCliente.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        cliente.setNascimentoCliente(simpleDateFormat.parse(editNascimentoCliente.getText().toString()));
        cliente.setCPFCliente(editCPFCliente.getText().toString());
        cliente.setEnderecoCliente(editEnderecoCliente.getText().toString());
        cliente.setSexoCliente(editSexoCliente.getText().toString());

        if(clienteDAO.atualizaCliente(cliente))
            Toast.makeText(ActivityAtualizarCliente.this, "Cliente atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ActivityAtualizarCliente.this, "Erro ao atualizar o cliente.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerCliente(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clienteDAO.deletaRegistro(ID_CLIENTE);
                Toast.makeText(ActivityAtualizarCliente.this, "Cliente removido com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(ActivityAtualizarCliente.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }
}
