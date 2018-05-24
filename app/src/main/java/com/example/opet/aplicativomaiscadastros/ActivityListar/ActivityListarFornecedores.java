package com.example.opet.aplicativomaiscadastros.ActivityListar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opet.aplicativomaiscadastros.ActivityAtualizar.ActivityAtualizarFornecedor;
import com.example.opet.aplicativomaiscadastros.Adapter.FornecedorAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.FornecedorDAO;
import com.example.opet.aplicativomaiscadastros.Model.Fornecedor;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 23/05/2018.
 */

public class ActivityListarFornecedores extends Activity {

    private ListView listaFornecedors;
    private FornecedorAdapter myAdapter;
    FornecedorDAO fornecedorDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_fornecedores);

        carregarElementos();
    }

    public void carregarElementos(){
        listaFornecedors = (ListView) findViewById(R.id.listFornecedores);
        fornecedorDAO = new FornecedorDAO(this);
        List<Fornecedor> fornecedores = fornecedorDAO.carregaDadosLista();
        myAdapter = new FornecedorAdapter(this, R.layout.item_fornecedor,fornecedores);
        listaFornecedors.setAdapter(myAdapter);
        listaFornecedors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fornecedor fornecedor = (Fornecedor)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ActivityListarFornecedores.this,ActivityAtualizarFornecedor.class);
                atualizarIntent.putExtra("ID_FORNECEDOR",fornecedor.getIdFornecedor());
                startActivity(atualizarIntent);
            }
        });
    }
}
