package com.example.opet.aplicativomaiscadastros.ActivityListar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opet.aplicativomaiscadastros.ActivityAtualizar.ActivityAtualizarCliente;
import com.example.opet.aplicativomaiscadastros.Adapter.ClienteAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.ClienteDAO;
import com.example.opet.aplicativomaiscadastros.Model.Cliente;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityListarClientes extends Activity {

    private ListView listaClientes;
    private ClienteAdapter myAdapter;
    ClienteDAO clienteDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clientes);

        carregarElementos();
    }

    public void carregarElementos(){
        listaClientes = (ListView) findViewById(R.id.listClientes);
        clienteDAO = new ClienteDAO(this);
        List<Cliente> clientes = clienteDAO.carregaDadosLista();
        myAdapter = new ClienteAdapter(this, R.layout.item_cliente,clientes);
        listaClientes.setAdapter(myAdapter);
        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cliente = (Cliente)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ActivityListarClientes.this,ActivityAtualizarCliente.class);
                atualizarIntent.putExtra("ID_CLIENTE",cliente.getIdCliente());
                startActivity(atualizarIntent);
            }
        });
    }
}