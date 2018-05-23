package com.example.opet.aplicativomaiscadastros.ActivityCadastrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarProdutos;
import com.example.opet.aplicativomaiscadastros.Adapter.FornecedorAdapter;
import com.example.opet.aplicativomaiscadastros.Adapter.LojaAdapter;
import com.example.opet.aplicativomaiscadastros.Adapter.MarcaAdapter;
import com.example.opet.aplicativomaiscadastros.Adapter.SetorAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.ProdutoDAO;
import com.example.opet.aplicativomaiscadastros.Model.Fornecedor;
import com.example.opet.aplicativomaiscadastros.Model.Loja;
import com.example.opet.aplicativomaiscadastros.Model.Marca;
import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.Model.Setor;
import com.example.opet.aplicativomaiscadastros.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityCadastrarProduto extends Activity {
    private EditText editNomeProduto;
    private EditText editDescricaoProduto;
    private EditText editValidadeProduto;
    private Spinner spinnerSetorProduto;
    private Spinner spinnerMarcaProduto;
    private Spinner spinnerFornecedorProduto;
    private Marca marca;
    private Setor setor;
    private Fornecedor fornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        editNomeProduto = (EditText) findViewById(R.id.editNomeProduto);
        editDescricaoProduto = (EditText) findViewById(R.id.editDescricaoProduto);
        editValidadeProduto = (EditText) findViewById(R.id.editValidadeProduto);

        spinnerSetorProduto = findViewById(R.id.spinnerSetorProduto);
        List<Setor> setores = new ArrayList<>();
        final SetorAdapter setorAdapter = new SetorAdapter(this,android.R.layout.simple_spinner_item,setores);
        spinnerSetorProduto.setAdapter(setorAdapter);
        spinnerSetorProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setor = (Setor) setorAdapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMarcaProduto = findViewById(R.id.spinnerMarcaProduto);
        List<Marca> marcas = new ArrayList<>();
        final MarcaAdapter marcaAdapter = new MarcaAdapter(this,android.R.layout.simple_spinner_item,marcas);
        spinnerMarcaProduto.setAdapter(marcaAdapter);
        spinnerMarcaProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                marca = (Marca) marcaAdapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerFornecedorProduto = findViewById(R.id.spinnerFornecedorProduto);
        List<Fornecedor> fornecedores = new ArrayList<>();
        final FornecedorAdapter fornecedorAdapter = new FornecedorAdapter(this,android.R.layout.simple_spinner_item,fornecedores);
        spinnerFornecedorProduto.setAdapter(fornecedorAdapter);
        spinnerFornecedorProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fornecedor = (Fornecedor) fornecedorAdapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void salvarProduto(View v) throws ParseException {
        ProdutoDAO produtoDAO = new ProdutoDAO(this);
        Produto produto = new Produto();
        produto.setNomeProduto(editNomeProduto.getText().toString());
        produto.setDescricaoProduto(editDescricaoProduto.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        produto.setDataValidadeProduto(simpleDateFormat.parse(editValidadeProduto.getText().toString()));
        produto.setId_Setor(setor.getIdSetor());
        produto.setId_Marca(marca.getIdMarca());
        produto.setId_Fornecedor(fornecedor.getIdFornecedor());

        long resultado = produtoDAO.insereDado(produto);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarProdutos = new Intent(ActivityCadastrarProduto.this,ActivityListarProdutos.class);
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