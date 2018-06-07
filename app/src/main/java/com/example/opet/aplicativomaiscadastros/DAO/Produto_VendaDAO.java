package com.example.opet.aplicativomaiscadastros.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.aplicativomaiscadastros.Factory.DataBaseFactory;
import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.Model.Produto_Venda;
import com.example.opet.aplicativomaiscadastros.Util.BancoUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 06/06/2018.
 */

public class Produto_VendaDAO {
    private SQLiteDatabase db;
    private DataBaseFactory banco;


    public Produto_VendaDAO(Context context) {
        banco = new DataBaseFactory(context);
    }

    public long insereDado(Produto_Venda produto_venda) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.VALOR_UNITARIO, String.valueOf(produto_venda.getValorUnitario()));
        valores.put(BancoUtil.QUANTIDADE_PRODUTO, produto_venda.getQuantidadeProduto());
        valores.put(BancoUtil.PRODUTO_VENDA_PRODUTO, produto_venda.getId_Produto());
        valores.put(BancoUtil.PRODUTO_VENDA_VENDA, produto_venda.getId_Venda());

        resultado = db.insert(BancoUtil.TABELA_PRODUTO_VENDA, null, valores);
        db.close();

        return resultado;

    }

    public Produto_Venda carregaProduto_VendaPorID(long id) throws ParseException {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_PRODUTO_VENDA, BancoUtil.NOME_PRODUTO, BancoUtil.DESCRICAO_PRODUTO,
                BancoUtil.PRODUTO_FORNECEDOR, BancoUtil.PRODUTO_MARCA, BancoUtil.PRODUTO_SETOR};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_PRODUTO_VENDA + " = " + id;

        cursor = db.query(BancoUtil.TABELA_PRODUTO, campos, where, null,
                null, null, null, null);

        Produto_Venda produto_venda = new Produto_Venda();
        if (cursor != null) {
            cursor.moveToFirst();

            int idProdutoVenda = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_PRODUTO_VENDA));
            int quantidadeProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.QUANTIDADE_PRODUTO));
            int valorUnitario = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.VALOR_UNITARIO));
            int idProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_VENDA_PRODUTO));
            int idVenda = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_VENDA_VENDA));

            produto_venda.setIdProdutoVenda(idProdutoVenda);
            produto_venda.setQuantidadeProduto(quantidadeProduto);
            produto_venda.setValorUnitario(valorUnitario);
            produto_venda.setId_Produto(idProduto);
            produto_venda.setId_Venda(idVenda);

        }
        db.close();
        return produto_venda;
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_PRODUTO_VENDA, BancoUtil.QUANTIDADE_PRODUTO, BancoUtil.VALOR_UNITARIO,
                BancoUtil.PRODUTO_VENDA_PRODUTO, BancoUtil.PRODUTO_VENDA_VENDA};
        db = banco.getReadableDatabase();

        String where = null;
        cursor = db.query(BancoUtil.TABELA_PRODUTO_VENDA, campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Produto_Venda> carregaDadosLista() {
        Cursor cursor = null;

        cursor = carregaDados();

        List<Produto_Venda> produto_vendas = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Produto_Venda produto_venda = new Produto_Venda();
                    int idProdutoVenda = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_PRODUTO_VENDA));
                    int quantidadeProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.QUANTIDADE_PRODUTO));
                    int valorUnitario = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.VALOR_UNITARIO));
                    int idProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_VENDA_PRODUTO));
                    int idVenda = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_VENDA_VENDA));

                    produto_venda.setIdProdutoVenda(idProdutoVenda);
                    produto_venda.setQuantidadeProduto(quantidadeProduto);
                    produto_venda.setValorUnitario(valorUnitario);
                    produto_venda.setId_Produto(idProduto);
                    produto_venda.setId_Venda(idVenda);

                    produto_vendas.add(produto_venda);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return produto_vendas;
    }

    public void deletaRegistro(int id) {
        String where = BancoUtil.ID_PRODUTO_VENDA + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_PRODUTO_VENDA, where, null);
        db.close();
    }

    public boolean atualizarProduto_Venda(Produto_Venda produto_venda) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_PRODUTO_VENDA + " = " + produto_venda.getIdProdutoVenda();
        where = BancoUtil.QUANTIDADE_PRODUTO + " = " + produto_venda.getQuantidadeProduto();
        where = BancoUtil.VALOR_UNITARIO + " = " + produto_venda.getValorUnitario();
        where = BancoUtil.PRODUTO_SETOR + " = " + produto_venda.getId_Produto();
        where = BancoUtil.PRODUTO_FORNECEDOR + " = " + produto_venda.getId_Venda();

        valores = new ContentValues();
        valores.put(BancoUtil.QUANTIDADE_PRODUTO, produto_venda.getQuantidadeProduto());
        valores.put(BancoUtil.VALOR_UNITARIO, String.valueOf(produto_venda.getValorUnitario()));
        valores.put(BancoUtil.PRODUTO_VENDA_PRODUTO, produto_venda.getId_Produto());
        valores.put(BancoUtil.PRODUTO_VENDA_VENDA, produto_venda.getId_Venda());


        int resultado = db.update(BancoUtil.TABELA_PRODUTO_VENDA, valores, where, null);
        db.close();
        if (resultado > 0)
            return true;
        else
            return false;
    }
}
