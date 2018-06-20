package com.example.opet.aplicativomaiscadastros.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.aplicativomaiscadastros.Factory.DataBaseFactory;
import com.example.opet.aplicativomaiscadastros.Model.Fornecedor;
import com.example.opet.aplicativomaiscadastros.Util.BancoUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class FornecedorDAO {
    private SQLiteDatabase db;
    private DataBaseFactory banco;


    public FornecedorDAO(Context context) {
        banco = new DataBaseFactory(context);
    }

    public long insereDado(Fornecedor fornecedor) {
        ContentValues valores;
        long resultado;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.NOME_FORNECEDOR, fornecedor.getNomeFornecedor());
        valores.put(BancoUtil.CNPJ_FORNECEDOR, fornecedor.getCNPJ());

        resultado = db.insert(BancoUtil.TABELA_FORNECEDOR, null, valores);
        db.close();

        return resultado;

    }

    public Fornecedor carregaFornecedorPorID(long id) throws ParseException {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_FORNECEDOR, BancoUtil.NOME_FORNECEDOR, BancoUtil.CNPJ_FORNECEDOR};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_FORNECEDOR + " = " + id;

        cursor = db.query(BancoUtil.TABELA_FORNECEDOR, campos, where, null,
                null, null, null, null);

        Fornecedor fornecedor = new Fornecedor();
        if (cursor != null) {
            cursor.moveToFirst();

            int idFornecedor = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_FORNECEDOR));
            String nomeFornecedor = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_FORNECEDOR));
            long cpfFornecedor = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CNPJ_FORNECEDOR));

            fornecedor.setIdFornecedor(idFornecedor);
            fornecedor.setNomeFornecedor(nomeFornecedor);
            fornecedor.setCNPJ(cpfFornecedor);

        }
        db.close();
        return fornecedor;
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_FORNECEDOR, BancoUtil.NOME_FORNECEDOR, BancoUtil.CNPJ_FORNECEDOR};
        db = banco.getReadableDatabase();

        String where = null;
        cursor = db.query(BancoUtil.TABELA_FORNECEDOR, campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Fornecedor> carregaDadosLista() {
        Cursor cursor = null;

        cursor = carregaDados();

        List<Fornecedor> fornecedores = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Fornecedor fornecedor = new Fornecedor();
                    int idFornecedor = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_FORNECEDOR));
                    String nomeFornecedor = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_FORNECEDOR));
                    long cpfFornecedor = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CNPJ_FORNECEDOR));

                    fornecedor.setIdFornecedor(idFornecedor);
                    fornecedor.setNomeFornecedor(nomeFornecedor);
                    fornecedor.setCNPJ(cpfFornecedor);

                    fornecedores.add(fornecedor);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return fornecedores;
    }

    public void deletaRegistro(int id) {
        String where = BancoUtil.ID_FORNECEDOR + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_FORNECEDOR, where, null);
        db.close();
    }

    public boolean atualizaFornecedor(Fornecedor fornecedor) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_FORNECEDOR + " = " + fornecedor.getIdFornecedor();
        where = BancoUtil.NOME_FORNECEDOR + " = " + fornecedor.getNomeFornecedor();
        where = BancoUtil.CNPJ_FORNECEDOR + " = " + fornecedor.getCNPJ();


        valores = new ContentValues();
        valores.put(BancoUtil.NOME_FORNECEDOR, fornecedor.getNomeFornecedor());
        valores.put(BancoUtil.CNPJ_FORNECEDOR, fornecedor.getCNPJ());


        int resultado = db.update(BancoUtil.TABELA_FORNECEDOR, valores, where, null);
        db.close();
        if (resultado > 0)
            return true;
        else
            return false;
    }
}
