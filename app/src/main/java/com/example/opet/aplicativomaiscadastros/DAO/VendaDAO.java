package com.example.opet.aplicativomaiscadastros.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.aplicativomaiscadastros.Factory.DataBaseFactory;
import com.example.opet.aplicativomaiscadastros.Model.Venda;
import com.example.opet.aplicativomaiscadastros.Util.BancoUtil;
import com.example.opet.aplicativomaiscadastros.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class VendaDAO {
    private SQLiteDatabase db;
    private DataBaseFactory banco;


    public VendaDAO(Context context) {
        banco = new DataBaseFactory(context);
    }

    public long insereDado(Venda venda) {
        ContentValues valores;
        long resultado;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.QUANTIDADE_PRODUTO, venda.getQuantidadeProduto());
        valores.put(BancoUtil.DESCRICAO_VENDA, venda.getDescricaoVenda());
        valores.put(BancoUtil.VALOR_VENDA, venda.getValorVenda());
        valores.put(BancoUtil.DATA_VENDA, String.valueOf(venda.getDataVenda()));
        valores.put(BancoUtil.VENDA_CLIENTE, venda.getId_Cliente());
        valores.put(BancoUtil.VENDA_PRODUTO, venda.getId_Produto());

        resultado = db.insert(BancoUtil.TABELA_VENDA, null, valores);
        db.close();

        return resultado;

    }

    public Venda carregaVendaPorID(long id) throws ParseException {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_VENDA, BancoUtil.QUANTIDADE_PRODUTO, BancoUtil.DESCRICAO_VENDA,
                BancoUtil.DATA_VENDA, BancoUtil.VALOR_VENDA, BancoUtil.VENDA_PRODUTO, BancoUtil.VENDA_CLIENTE};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_VENDA + " = " + id;

        cursor = db.query(BancoUtil.TABELA_VENDA, campos, where, null,
                null, null, null, null);

        Venda venda = new Venda();
        if (cursor != null) {
            cursor.moveToFirst();

            int idVenda = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_VENDA));
            int quantidadeVenda = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.QUANTIDADE_PRODUTO));
            String descricaoVenda = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.DESCRICAO_VENDA));
            String validadeVenda = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.DATA_VENDA));
            long valorVenda = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.VALOR_VENDA));
            int produtoVenda = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.VENDA_PRODUTO));
            int clienteVenda = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.VENDA_CLIENTE));

            venda.setIdVenda(idVenda);
            venda.setQuantidadeProduto(quantidadeVenda);
            venda.setDescricaoVenda(descricaoVenda);
            venda.setDataVenda(Util.toDate(validadeVenda));
            venda.setValorVenda(valorVenda);
            venda.setId_Cliente(clienteVenda);
            venda.setId_Produto(produtoVenda);

        }
        db.close();
        return venda;
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_VENDA, BancoUtil.QUANTIDADE_PRODUTO, BancoUtil.DESCRICAO_VENDA,
                BancoUtil.DATA_VENDA, BancoUtil.VALOR_VENDA, BancoUtil.VENDA_PRODUTO, BancoUtil.VENDA_CLIENTE};
        db = banco.getReadableDatabase();

        String where = null;
        cursor = db.query(BancoUtil.TABELA_VENDA, campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Venda> carregaDadosLista() {
        Cursor cursor = null;

        cursor = carregaDados();

        List<Venda> vendas = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Venda venda = new Venda();
                    int idVenda = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_VENDA));
                    int quantidadeProduto = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.QUANTIDADE_PRODUTO));
                    String descricaoVenda = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.DESCRICAO_VENDA));
                    String validadeVenda = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.DATA_VENDA));
                    long valorVenda = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.VALOR_VENDA));
                    int produtoVenda = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.VENDA_PRODUTO));
                    int clienteVenda = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.VENDA_CLIENTE));

                    venda.setIdVenda(idVenda);
                    venda.setQuantidadeProduto(quantidadeProduto);
                    venda.setDescricaoVenda(descricaoVenda);
                    venda.setDataVenda(Util.toDate(validadeVenda));
                    venda.setValorVenda(valorVenda);
                    venda.setId_Cliente(clienteVenda);
                    venda.setId_Produto(produtoVenda);

                    vendas.add(venda);
                } while (cursor.moveToNext());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return vendas;
    }

    public void deletaRegistro(int id) {
        String where = BancoUtil.ID_VENDA + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_VENDA, where, null);
        db.close();
    }

    public boolean atualizaVenda(Venda venda) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_VENDA + " = " + venda.getIdVenda();
        where = BancoUtil.QUANTIDADE_PRODUTO + " = " + venda.getQuantidadeProduto();
        where = BancoUtil.DESCRICAO_VENDA + " = " + venda.getDescricaoVenda();
        where = BancoUtil.DATA_VENDA + " = " + venda.getDataVenda();
        where = BancoUtil.VENDA_CLIENTE + " = " + venda.getIdVenda();
        where = BancoUtil.VALOR_VENDA+ " = " + venda.getValorVenda();
        where = BancoUtil.VENDA_PRODUTO + " = " + venda.getId_Produto();

        valores = new ContentValues();
        valores.put(BancoUtil.QUANTIDADE_PRODUTO, venda.getQuantidadeProduto());
        valores.put(BancoUtil.DESCRICAO_VENDA, venda.getDescricaoVenda());
        valores.put(BancoUtil.VALOR_VENDA, venda.getValorVenda());
        valores.put(BancoUtil.DATA_VENDA, String.valueOf(venda.getDataVenda()));
        valores.put(BancoUtil.VENDA_CLIENTE, venda.getId_Cliente());
        valores.put(BancoUtil.VENDA_PRODUTO, venda.getId_Produto());


        int resultado = db.update(BancoUtil.TABELA_VENDA, valores, where, null);
        db.close();
        if (resultado > 0)
            return true;
        else
            return false;
    }
}
