package com.example.opet.aplicativomaiscadastros.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.aplicativomaiscadastros.Factory.DataBaseFactory;
import com.example.opet.aplicativomaiscadastros.Model.Setor;
import com.example.opet.aplicativomaiscadastros.Util.BancoUtil;
import com.example.opet.aplicativomaiscadastros.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class SetorDAO {
    private SQLiteDatabase db;
    private DataBaseFactory banco;


    public SetorDAO(Context context) {
        banco = new DataBaseFactory(context);
    }

    public long insereDado(Setor setor) {
        ContentValues valores;
        long resultado;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.NOME_SETOR, setor.getNomeSetor());

        resultado = db.insert(BancoUtil.TABELA_SETOR, null, valores);
        db.close();

        return resultado;

    }

    public Setor carregaSetorPorID(long id) throws ParseException {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_SETOR, BancoUtil.NOME_SETOR};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_SETOR + " = " + id;

        cursor = db.query(BancoUtil.TABELA_SETOR, campos, where, null,
                null, null, null, null);

        Setor setor = new Setor();
        if (cursor != null) {
            cursor.moveToFirst();

            int idSetor = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_SETOR));
            String nomeSetor = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_SETOR));

            setor.setIdSetor(idSetor);
            setor.setNomeSetor(nomeSetor);

        }
        db.close();
        return setor;
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_SETOR, BancoUtil.NOME_SETOR};
        db = banco.getReadableDatabase();

        String where = null;
        cursor = db.query(BancoUtil.TABELA_SETOR, campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Setor> carregaDadosLista() {
        Cursor cursor = null;

        cursor = carregaDados();

        List<Setor> setores = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Setor setor = new Setor();
                    int idSetor = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_SETOR));
                    String nomeSetor = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_SETOR));

                    setor.setIdSetor(idSetor);
                    setor.setNomeSetor(nomeSetor);

                    setores.add(setor);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return setores;
    }

    public void deletaRegistro(int id) {
        String where = BancoUtil.ID_SETOR + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_SETOR, where, null);
        db.close();
    }

    public boolean atualizaSetor(Setor setor) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_SETOR + " = " + setor.getIdSetor();
        where = BancoUtil.NOME_SETOR + " = " + setor.getNomeSetor();

        valores = new ContentValues();
        valores.put(BancoUtil.NOME_SETOR, setor.getNomeSetor());

        int resultado = db.update(BancoUtil.TABELA_SETOR, valores, where, null);
        db.close();
        if (resultado > 0)
            return true;
        else
            return false;
    }
}
