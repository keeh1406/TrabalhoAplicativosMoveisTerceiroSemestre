package com.example.opet.aplicativomaiscadastros.ActivityListar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opet.aplicativomaiscadastros.ActivityAtualizar.ActivityAtualizarVenda;
import com.example.opet.aplicativomaiscadastros.Adapter.VendaAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.VendaDAO;
import com.example.opet.aplicativomaiscadastros.Model.Venda;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 23/05/2018.
 */

public class ActivityListarVendas extends Activity {

    private ListView listaVendas;
    private VendaAdapter myAdapter;
    VendaDAO vendaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_vendas);

        carregarElementos();
    }

    public void carregarElementos(){
        listaVendas = (ListView) findViewById(R.id.listVendas);
        vendaDAO = new VendaDAO(this);
        List<Venda> vendas = vendaDAO.carregaDadosLista();
        myAdapter = new VendaAdapter(this, R.layout.item_venda,vendas);
        listaVendas.setAdapter(myAdapter);
        listaVendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Venda venda = (Venda)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ActivityListarVendas.this,ActivityAtualizarVenda.class);
                atualizarIntent.putExtra("ID_VENDA",venda.getIdVenda());
                startActivity(atualizarIntent);
            }
        });
    }
}