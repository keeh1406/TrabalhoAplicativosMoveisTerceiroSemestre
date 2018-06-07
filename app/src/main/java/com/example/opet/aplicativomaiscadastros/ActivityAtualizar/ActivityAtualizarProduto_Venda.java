package com.example.opet.aplicativomaiscadastros.ActivityAtualizar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.opet.aplicativomaiscadastros.DAO.ProdutoDAO;
import com.example.opet.aplicativomaiscadastros.DAO.Produto_VendaDAO;
import com.example.opet.aplicativomaiscadastros.MainActivity;
import com.example.opet.aplicativomaiscadastros.Model.Fornecedor;
import com.example.opet.aplicativomaiscadastros.Model.Marca;
import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.Model.Produto_Venda;
import com.example.opet.aplicativomaiscadastros.Model.Setor;
import com.example.opet.aplicativomaiscadastros.Model.Venda;
import com.example.opet.aplicativomaiscadastros.R;

import java.text.ParseException;

/**
 * Created by opet on 06/06/2018.
 */

public class ActivityAtualizarProduto_Venda extends Activity {
    private int ID_PRODUTO_VENDA;
    private Produto_VendaDAO produto_vendaDAO;
    private Produto_Venda produto_venda;
    private EditText editValorUnitario;
    private EditText editQuantidadeProduto;
    private EditText editIdProduto;
    private EditText editIdVenda;
    private Produto produto;
    private Venda venda;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_produto_venda);
        produto_vendaDAO = new Produto_VendaDAO(this);
        Intent intent = getIntent();
        if(intent.hasExtra("ID_PRODUTO_VENDA")){
            ID_PRODUTO_VENDA = intent.getIntExtra("ID_PRODUTO_VENDA",0);
        }
        try {
            produto_venda = produto_vendaDAO.carregaProduto_VendaPorID(ID_PRODUTO_VENDA);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editValorUnitario = (EditText) findViewById(R.id.editValorUnitarioProduto_Venda);
        editQuantidadeProduto = (EditText) findViewById(R.id.editQuantidadeProduto);
        editIdProduto = (EditText) findViewById(R.id.spinnerProduto);
        editIdVenda = (EditText) findViewById(R.id.spinnerVenda);

        editValorUnitario.setText(produto_venda.getValorUnitario());
        editQuantidadeProduto.setText(produto_venda.getQuantidadeProduto());
        editIdProduto.setText(produto_venda.getId_Produto());
        editIdVenda.setText(produto_venda.getId_Venda());
    }

    public void atualizarProduto_Venda(View v) throws ParseException {
        produto_venda.setValorUnitario(Integer.parseInt(editValorUnitario.getText().toString()));
        produto_venda.setQuantidadeProduto(Integer.parseInt(editQuantidadeProduto.getText().toString()));
        produto_venda.setId_Produto(Integer.parseInt(editIdProduto.getText().toString()));
        produto_venda.setId_Venda(Integer.parseInt(editIdVenda.getText().toString()));

        if(produto_vendaDAO.atualizarProduto_Venda((produto_venda))) {
            Toast.makeText(ActivityAtualizarProduto_Venda.this, "Produto venda atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(ActivityAtualizarProduto_Venda.this, "Erro ao atualizar o produto venda.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerProduto_Venda(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                produto_vendaDAO.deletaRegistro(ID_PRODUTO_VENDA);
                Toast.makeText(ActivityAtualizarProduto_Venda.this, "Produto venda removido com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(ActivityAtualizarProduto_Venda.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }

}
