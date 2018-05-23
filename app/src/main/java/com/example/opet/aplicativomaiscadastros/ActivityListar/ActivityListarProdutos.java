package com.example.opet.aplicativomaiscadastros.ActivityListar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opet.aplicativomaiscadastros.ActivityAtualizar.ActivityAtualizarProduto;
import com.example.opet.aplicativomaiscadastros.Adapter.ProdutoAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.ProdutoDAO;
import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityListarProdutos extends Activity {

    private ListView listaProdutos;
    private ProdutoAdapter myAdapter;
    ProdutoDAO produtoDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);

        carregarElementos();
    }

    public void carregarElementos(){
        listaProdutos = (ListView) findViewById(R.id.listProdutos);
        produtoDAO = new ProdutoDAO(this);
        List<Produto> produtos = produtoDAO.carregaDadosLista();
        myAdapter = new ProdutoAdapter(this, R.layout.item_produto,produtos);
        listaProdutos.setAdapter(myAdapter);
        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produto = (Produto)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ActivityListarProdutos.this,ActivityAtualizarProduto.class);
                atualizarIntent.putExtra("ID_PRODUTO",produto.getIdProduto());
                startActivity(atualizarIntent);
            }
        });
    }
}