package com.example.opet.aplicativomaiscadastros.ActivityCadastrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarProduto_Venda;
import com.example.opet.aplicativomaiscadastros.Adapter.FornecedorAdapter;
import com.example.opet.aplicativomaiscadastros.Adapter.LojaAdapter;
import com.example.opet.aplicativomaiscadastros.Adapter.VendaAdapter;
import com.example.opet.aplicativomaiscadastros.Adapter.ProdutoAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.Produto_VendaDAO;
import com.example.opet.aplicativomaiscadastros.Model.Fornecedor;
import com.example.opet.aplicativomaiscadastros.Model.Loja;
import com.example.opet.aplicativomaiscadastros.Model.Venda;
import com.example.opet.aplicativomaiscadastros.Model.Produto_Venda;
import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityCadastrarProduto_Venda extends Activity {
    private EditText editQuantidadeProduto_Venda;
    private EditText valorUnitarioProduto_Venda;
    private Spinner spinnerProdutoProduto_Venda;
    private Spinner spinnerVendaProduto_Venda;
    private Venda venda;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto_venda);

        editQuantidadeProduto_Venda = (EditText) findViewById(R.id.textQuantidadeProdutoVenda);
        valorUnitarioProduto_Venda = (EditText) findViewById(R.id.textValorUnitarioVenda);
        spinnerProdutoProduto_Venda = findViewById(R.id.spinnerProduto);
        List<Produto> produtos = new ArrayList<>();
        final ProdutoAdapter produtoAdapter = new ProdutoAdapter(this,android.R.layout.simple_spinner_item,produtos);
        spinnerProdutoProduto_Venda.setAdapter(produtoAdapter);
        spinnerProdutoProduto_Venda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                produto = (Produto) produtoAdapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerVendaProduto_Venda = findViewById(R.id.spinnerVenda);
        List<Venda> vendas = new ArrayList<>();
        final VendaAdapter vendaAdapter = new VendaAdapter(this,android.R.layout.simple_spinner_item,vendas);
        spinnerVendaProduto_Venda.setAdapter(vendaAdapter);
        spinnerVendaProduto_Venda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                venda = (Venda) vendaAdapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void salvarProduto_Venda(View v) throws ParseException {
        Produto_VendaDAO produto_vendaDAO = new Produto_VendaDAO(this);
        Produto_Venda produto_venda = new Produto_Venda();
        produto_venda.setQuantidadeProduto(Integer.parseInt(editQuantidadeProduto_Venda.getText().toString()));
        produto_venda.setValorUnitario(Integer.parseInt(valorUnitarioProduto_Venda.getText().toString()));
        produto_venda.setId_Produto(produto.getIdProduto());
        produto_venda.setId_Venda(venda.getIdVenda());

        long resultado = produto_vendaDAO.insereDado(produto_venda);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarProduto_Vendas = new Intent(ActivityCadastrarProduto_Venda.this,ActivityListarProduto_Venda.class);
            startActivity(listarProduto_Vendas);
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