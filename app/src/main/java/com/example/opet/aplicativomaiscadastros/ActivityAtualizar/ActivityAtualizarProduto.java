package com.example.opet.aplicativomaiscadastros.ActivityAtualizar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.aplicativomaiscadastros.DAO.ProdutoDAO;
import com.example.opet.aplicativomaiscadastros.MainActivity;
import com.example.opet.aplicativomaiscadastros.Model.Fornecedor;
import com.example.opet.aplicativomaiscadastros.Model.Marca;
import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.Model.Setor;
import com.example.opet.aplicativomaiscadastros.R;
import com.example.opet.aplicativomaiscadastros.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by opet on 23/05/2018.
 */

public class ActivityAtualizarProduto extends Activity {
    private int ID_PRODUTO;
    private ProdutoDAO produtoDAO;
    private Produto produto;
    private EditText editNomeProduto;
    private EditText editDescricaoProduto;
    private EditText editSetorProduto;
    private EditText editMarcaProduto;
    private EditText editFornecedorProduto;
    private Marca marca;
    private Setor setor;
    private Fornecedor fornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_produtos);
        produtoDAO = new ProdutoDAO(this);
        Intent intent = getIntent();
        if(intent.hasExtra("ID_PRODUTO")){
            ID_PRODUTO = intent.getIntExtra("ID_PRODUTO",0);
        }
        try {
            produto = produtoDAO.carregaProdutoPorID(ID_PRODUTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editNomeProduto = (EditText) findViewById(R.id.editNomeProdutoUpdate);
        editDescricaoProduto = (EditText) findViewById(R.id.editDescricaoProdutoUpdate);
        editSetorProduto = (EditText) findViewById(R.id.editSetorProdutoUpdate);
        editMarcaProduto = (EditText) findViewById(R.id.editMarcaProdutoUpdate);
        editFornecedorProduto = (EditText) findViewById(R.id.editFornecedorProdutoUpdate);

        editNomeProduto.setText(produto.getNomeProduto());
        editDescricaoProduto.setText(produto.getDescricaoProduto());
        editSetorProduto.setText(produto.getId_Setor());
        editMarcaProduto.setText(produto.getId_Marca());
        editFornecedorProduto.setText(produto.getId_Fornecedor());
    }

    public void atualizarProduto(View v) throws ParseException {
        produto.setNomeProduto(editNomeProduto.getText().toString());
        produto.setDescricaoProduto(editDescricaoProduto.getText().toString());
        produto.setId_Setor(Integer.parseInt(editSetorProduto.getText().toString()));
        produto.setId_Marca(Integer.parseInt(editMarcaProduto.getText().toString()));
        produto.setId_Fornecedor(Integer.parseInt(editFornecedorProduto.getText().toString()));

        if(produtoDAO.atualizaProduto(produto))
            Toast.makeText(ActivityAtualizarProduto.this, "Produto atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ActivityAtualizarProduto.this, "Erro ao atualizar o produto.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerProduto(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                produtoDAO.deletaRegistro(ID_PRODUTO);
                Toast.makeText(ActivityAtualizarProduto.this, "Produto removido com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(ActivityAtualizarProduto.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }
}