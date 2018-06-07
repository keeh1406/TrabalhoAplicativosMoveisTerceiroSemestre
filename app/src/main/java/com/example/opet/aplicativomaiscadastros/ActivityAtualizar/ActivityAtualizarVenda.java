package com.example.opet.aplicativomaiscadastros.ActivityAtualizar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opet.aplicativomaiscadastros.DAO.ProdutoDAO;
import com.example.opet.aplicativomaiscadastros.DAO.VendaDAO;
import com.example.opet.aplicativomaiscadastros.MainActivity;
import com.example.opet.aplicativomaiscadastros.Model.Cliente;
import com.example.opet.aplicativomaiscadastros.Model.Fornecedor;
import com.example.opet.aplicativomaiscadastros.Model.Marca;
import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.Model.Setor;
import com.example.opet.aplicativomaiscadastros.Model.Venda;
import com.example.opet.aplicativomaiscadastros.R;
import com.example.opet.aplicativomaiscadastros.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by opet on 23/05/2018.
 */

public class ActivityAtualizarVenda extends Activity {
    private int ID_VENDA;
    private VendaDAO vendaDAO;
    private Venda venda;
    private EditText editEditDescricaoVenda;
    private EditText editValorTotalVenda;
    private EditText editFormaPagamentoVenda;
    private EditText editDataVenda;
    private CheckBox editFechado;
    private EditText editIdProdutoVenda;
    private EditText editIdClienteVenda;
    private Produto produto;
    private Cliente cliente;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_vendas);
        vendaDAO = new VendaDAO(this);
        Intent intent = getIntent();
        if(intent.hasExtra("ID_VENDA")){
            ID_VENDA = intent.getIntExtra("ID_VENDA",0);
        }
        try {
            venda = vendaDAO.carregaVendaPorID(ID_VENDA);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editEditDescricaoVenda = (EditText) findViewById(R.id.editDescricaoVendaUpdate);
        editValorTotalVenda = (EditText) findViewById(R.id.editValorTotalVendaUpdate);
        editFormaPagamentoVenda = (EditText) findViewById(R.id.editFormaPagamentoVendaUpdate);
        editDataVenda = (EditText) findViewById(R.id.editDataVendaUpdate);
        editFechado = (CheckBox) findViewById(R.id.editFechadoUpdate);
        editIdProdutoVenda = (EditText) findViewById(R.id.editIdProdutoVendaUpdate);
        editIdClienteVenda = (EditText) findViewById(R.id.editIdClienteUpdate);

        editEditDescricaoVenda.setText(venda.getDescricaoVenda());
        editValorTotalVenda.setText(String.valueOf(venda.getValorTotalVenda()));
        editFormaPagamentoVenda.setText(venda.getFormaPagamentoVenda());
        editDataVenda.setText(Util.toString(venda.getDataVenda()));
        editFechado.setText(venda.getFechado());
        editIdProdutoVenda.setText(venda.getIdProdutoVenda());
        editIdClienteVenda.setText(venda.getIdClienteVenda());
    }

    public void atualizarProduto(View v) throws ParseException {
        venda.setDescricaoVenda(editEditDescricaoVenda.getText().toString());
        venda.setValorTotalVenda(Long.valueOf(String.valueOf(editValorTotalVenda.getText().toString())));
        venda.setFormaPagamentoVenda(editFormaPagamentoVenda.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        venda.setDataVenda(simpleDateFormat.parse(editDataVenda.getText().toString()));
        venda.setFechado(editFechado.getText().toString());
        venda.setIdProdutoVenda(Integer.parseInt(editFormaPagamentoVenda.getText().toString()));
        venda.setIdClienteVenda(Integer.parseInt(editDataVenda.getText().toString()));

        if(vendaDAO.atualizaVenda(venda))
            Toast.makeText(ActivityAtualizarVenda.this, "Venda atualizada com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ActivityAtualizarVenda.this, "Erro ao atualizar a venda.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerProduto(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vendaDAO.deletaRegistro(ID_VENDA);
                Toast.makeText(ActivityAtualizarVenda.this, "Venda removida com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(ActivityAtualizarVenda.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }