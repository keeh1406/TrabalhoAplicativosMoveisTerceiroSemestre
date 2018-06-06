package com.example.opet.aplicativomaiscadastros.ActivityListar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opet.aplicativomaiscadastros.ActivityAtualizar.ActivityAtualizarProduto_Venda;
import com.example.opet.aplicativomaiscadastros.Adapter.Produto_VendaAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.Produto_VendaDAO;
import com.example.opet.aplicativomaiscadastros.Model.Produto_Venda;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 06/06/2018.
 */

public class ActivityListarProduto_Venda extends Activity {
    private ListView listaProduto_Vendas;
    private Produto_VendaAdapter myAdapter;
    Produto_VendaDAO produto_vendaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produto_venda);

        carregarElementos();
    }

    public void carregarElementos(){
        listaProduto_Vendas = (ListView) findViewById(R.id.listProduto_Vendas);
        produto_vendaDAO = new Produto_VendaDAO(this);
        List<Produto_Venda> produto_vendas = produto_vendaDAO.carregaDadosLista();
        myAdapter = new Produto_VendaAdapter(this, R.layout.item_produto_venda,produto_vendas);
        listaProduto_Vendas.setAdapter(myAdapter);
        listaProduto_Vendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto_Venda produto_venda = (Produto_Venda)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ActivityListarProduto_Venda.this,ActivityAtualizarProduto_Venda.class);
                atualizarIntent.putExtra("ID_PRODUTO_VENDA",produto_venda.getIdProdutoVenda());
                startActivity(atualizarIntent);
            }
        });
    }
}
