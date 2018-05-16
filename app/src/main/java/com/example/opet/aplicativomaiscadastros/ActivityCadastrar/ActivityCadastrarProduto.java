package com.example.opet.aplicativomaiscadastros.ActivityCadastrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarProdutos;
import com.example.opet.aplicativomaiscadastros.DAO.ProdutoDAO;
import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityCadastrarProduto extends Activity {
    private EditText editNomeProduto;
    private EditText editDescricaoProduto;
    private EditText editValidadeProduto;
    private Spinner editSetorProduto;
    private Spinner editMarcaProduto;
    private Spinner editFornecedorProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        editNomeProduto = (EditText) findViewById(R.id.editNomeProduto);
        editDescricaoProduto = (EditText) findViewById(R.id.editDescricaoProduto);
        editValidadeProduto = (EditText) findViewById(R.id.editValidadeProduto);
        editSetorProduto = (Spinner) findViewById(R.id.editSpinnerSetorProduto);
        editMarcaProduto = (Spinner) findViewById(R.id.editSpinnerMarcaProduto);
        editFornecedorProduto = (Spinner) findViewById(R.id.editSpinnerFornecedorProduto);
    }

    public void salvarProduto(View v) throws ParseException {
        ProdutoDAO produtoDAO = new ProdutoDAO(this);
        Produto produto = new Produto();
        produto.setNomeProduto(editNomeProduto.getText().toString());
        produto.setDescricaoProduto(editDescricaoProduto.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        produto.setDataValidadeProduto(simpleDateFormat.parse(editValidadeProduto.getText().toString()));
        produto.setId_Setor(editSetorProduto.getText().toString());
        produto.setId_Marca(editMarcaProduto.getText().toString());
        produto.setId_Fornecedor(editFornecedorProduto.getText().toString());

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