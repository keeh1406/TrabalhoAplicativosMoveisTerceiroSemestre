package com.example.opet.aplicativomaiscadastros.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.aplicativomaiscadastros.Factory.DataBaseFactory;
import com.example.opet.aplicativomaiscadastros.Model.Produto;
import com.example.opet.aplicativomaiscadastros.Util.BancoUtil;
import com.example.opet.aplicativomaiscadastros.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ProdutoDAO {
    private SQLiteDatabase db;
    private DataBaseFactory banco;


    public ProdutoDAO(Context context) {
        banco = new DataBaseFactory(context);
    }

    public long insereDado(Produto produto) {
        ContentValues valores;
        long resultado;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.NOME_PRODUTO, produto.getNomeProduto());
        valores.put(BancoUtil.DESCRICAO_PRODUTO, produto.getDescricaoProduto());
        valores.put(BancoUtil.VALIDADE_PRODUTO, format.format(produto.getDataValidadeProduto()));
        valores.put(BancoUtil.PRODUTO_FORNECEDOR, produto.getId_Fornecedor());
        valores.put(BancoUtil.PRODUTO_MARCA, produto.getId_Marca());
        valores.put(BancoUtil.PRODUTO_SETOR, produto.getId_Setor());

        resultado = db.insert(BancoUtil.TABELA_PRODUTO, null, valores);
        db.close();

        return resultado;

    }

    public Produto carregaProdutoPorID(long id) throws ParseException {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_PRODUTO, BancoUtil.NOME_PRODUTO, BancoUtil.DESCRICAO_PRODUTO,
                BancoUtil.VALIDADE_PRODUTO, BancoUtil.PRODUTO_FORNECEDOR, BancoUtil.PRODUTO_MARCA, BancoUtil.PRODUTO_SETOR};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_PRODUTO + " = " + id;

        cursor = db.query(BancoUtil.TABELA_PRODUTO, campos, where, null,
                null, null, null, null);

        Produto produto = new Produto();
        if (cursor != null) {
            cursor.moveToFirst();

            int idProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_PRODUTO));
            String nomeProduto = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_PRODUTO));
            String descricaoProduto = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.DESCRICAO_PRODUTO));
            String validadeProduto = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.VALIDADE_PRODUTO));
            int fornecedorProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_FORNECEDOR));
            int marcaProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_MARCA));
            int setorProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_SETOR));

            produto.setIdProduto(idProduto);
            produto.setNomeProduto(nomeProduto);
            produto.setDescricaoProduto(descricaoProduto);
            produto.setDataValidadeProduto(Util.toDate(validadeProduto));
            produto.setId_Fornecedor(fornecedorProduto);
            produto.setId_Setor(setorProduto);
            produto.setId_Marca(marcaProduto);

        }
        db.close();
        return produto;
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_PRODUTO, BancoUtil.NOME_PRODUTO, BancoUtil.DESCRICAO_PRODUTO,
                BancoUtil.VALIDADE_PRODUTO, BancoUtil.PRODUTO_SETOR, BancoUtil.PRODUTO_MARCA, BancoUtil.PRODUTO_FORNECEDOR};
        db = banco.getReadableDatabase();

        String where = null;
        cursor = db.query(BancoUtil.TABELA_PRODUTO, campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Produto> carregaDadosLista() {
        Cursor cursor = null;

        cursor = carregaDados();

        List<Produto> produtos = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Produto produto = new Produto();
                    int idProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_PRODUTO));
                    String nomeProduto = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_PRODUTO));
                    String descricaoProduto = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.DESCRICAO_PRODUTO));
                    String validadeProduto = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.VALIDADE_PRODUTO));
                    int fornecedorProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_FORNECEDOR));
                    int marcaProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_MARCA));
                    int setorProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.PRODUTO_SETOR));

                    produto.setIdProduto(idProduto);
                    produto.setNomeProduto(nomeProduto);
                    produto.setDescricaoProduto(descricaoProduto);
                    produto.setDataValidadeProduto(Util.toDate(validadeProduto));
                    produto.setId_Fornecedor(fornecedorProduto);
                    produto.setId_Setor(setorProduto);
                    produto.setId_Marca(marcaProduto);

                    produtos.add(produto);
                } while (cursor.moveToNext());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return produtos;
    }

    public void deletaRegistro(int id) {
        String where = BancoUtil.ID_PRODUTO + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_PRODUTO, where, null);
        db.close();
    }

    public boolean atualizaProduto(Produto produto) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_PRODUTO + " = " + produto.getIdProduto();
        where = BancoUtil.NOME_PRODUTO + " = " + produto.getNomeProduto();
        where = BancoUtil.DESCRICAO_PRODUTO + " = " + produto.getDescricaoProduto();
        where = BancoUtil.VALIDADE_PRODUTO + " = " + produto.getDataValidadeProduto();
        where = BancoUtil.PRODUTO_SETOR + " = " + produto.getIdProduto();
        where = BancoUtil.PRODUTO_FORNECEDOR + " = " + produto.getId_Fornecedor();
        where = BancoUtil.PRODUTO_MARCA + " = " + produto.getId_Marca();

        valores = new ContentValues();
        valores.put(BancoUtil.NOME_PRODUTO, produto.getNomeProduto());
        valores.put(BancoUtil.DESCRICAO_PRODUTO, produto.getDescricaoProduto());
        valores.put(BancoUtil.VALIDADE_PRODUTO, String.valueOf(produto.getDataValidadeProduto()));
        valores.put(BancoUtil.PRODUTO_SETOR, produto.getId_Setor());
        valores.put(BancoUtil.PRODUTO_MARCA, produto.getId_Marca());
        valores.put(BancoUtil.PRODUTO_FORNECEDOR, produto.getId_Fornecedor());


        int resultado = db.update(BancoUtil.TABELA_PRODUTO, valores, where, null);
        db.close();
        if (resultado > 0)
            return true;
        else
            return false;
    }
}
