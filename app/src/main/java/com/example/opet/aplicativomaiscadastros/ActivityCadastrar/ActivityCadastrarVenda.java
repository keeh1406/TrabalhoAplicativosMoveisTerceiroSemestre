package com.example.opet.aplicativomaiscadastros.ActivityCadastrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarProdutos;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarVendas;
import com.example.opet.aplicativomaiscadastros.Adapter.ClienteAdapter;
import com.example.opet.aplicativomaiscadastros.Adapter.FornecedorAdapter;
import com.example.opet.aplicativomaiscadastros.Adapter.MarcaAdapter;
import com.example.opet.aplicativomaiscadastros.Adapter.ProdutoAdapter;
import com.example.opet.aplicativomaiscadastros.Adapter.SetorAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.ProdutoDAO;
import com.example.opet.aplicativomaiscadastros.DAO.VendaDAO;
import com.example.opet.aplicativomaiscadastros.Model.Cliente;
import com.example.opet.aplicativomaiscadastros.Model.Fornecedor;
import com.example.opet.aplicativomaiscadastros.Model.Marca;
import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.Model.Setor;
import com.example.opet.aplicativomaiscadastros.Model.Venda;
import com.example.opet.aplicativomaiscadastros.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityCadastrarVenda extends Activity {
    private EditText editDescricaoVenda;
    private EditText editValorTotalVenda;
    private EditText editFormaPagamentoVenda;
    private EditText editDataVenda;
    private CheckBox checkboxFechado;
    private Spinner spinnerIdProdutoVenda;
    private Spinner spinnerIdClienteVenda;
    private Produto produto;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        editDescricaoVenda = (EditText) findViewById(R.id.editDescricaoVenda);
        editValorTotalVenda = (EditText) findViewById(R.id.editValorTotalVenda);
        editFormaPagamentoVenda = (EditText) findViewById(R.id.editFormaPagamentoVenda);
        checkboxFechado = (CheckBox) findViewById(R.id.checkboxFechado);
        editDataVenda = (EditText) findViewById(R.id.editDataVenda);

        spinnerIdClienteVenda = findViewById(R.id.spinnerIdClienteVenda);
        List<Cliente> clientes = new ArrayList<>();
        final ClienteAdapter clienteAdapter = new ClienteAdapter(this,android.R.layout.simple_spinner_item,clientes);
        spinnerIdClienteVenda.setAdapter(clienteAdapter);
        spinnerIdClienteVenda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cliente = (Cliente) clienteAdapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerIdProdutoVenda = findViewById(R.id.spinnerIdProdutoVenda);
        List<Produto> produtos = new ArrayList<>();
        final ProdutoAdapter produtoAdapter = new ProdutoAdapter(this,android.R.layout.simple_spinner_item,produtos);
        spinnerIdProdutoVenda.setAdapter(produtoAdapter);
        spinnerIdProdutoVenda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                produto = (Produto) produtoAdapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void salvarVenda(View v) throws ParseException {
        VendaDAO vendaDAO = new VendaDAO(this);
        Venda venda = new Venda();
        venda.setDescricaoVenda(editDescricaoVenda.getText().toString());
        venda.setValorTotalVenda(Long.valueOf(editValorTotalVenda.getText().toString()));
        venda.setFormaPagamentoVenda(editFormaPagamentoVenda.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        venda.setDataVenda(simpleDateFormat.parse(editDataVenda.getText().toString()));
        venda.setFechado(checkboxFechado.toString());
        venda.setIdProdutoVenda(produto.getIdProduto());
        venda.setIdVenda(cliente.getIdCliente());

        long resultado = vendaDAO.insereDado(venda);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarProdutos = new Intent(ActivityCadastrarVenda.this,ActivityListarVendas.class);
            startActivity(listarProdutos);
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
