package com.example.opet.aplicativomaiscadastros.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.aplicativomaiscadastros.Factory.DataBaseFactory;
import com.example.opet.aplicativomaiscadastros.Model.Marca;
import com.example.opet.aplicativomaiscadastros.Util.BancoUtil;
import com.example.opet.aplicativomaiscadastros.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class MarcaDAO {
    private SQLiteDatabase db;
    private DataBaseFactory banco;


    public MarcaDAO(Context context) {
        banco = new DataBaseFactory(context);
    }

    public long insereDado(Marca marca) {
        ContentValues valores;
        long resultado;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.NOME_MARCA, marca.getNomeMarca());

        resultado = db.insert(BancoUtil.TABELA_MARCA, null, valores);
        db.close();

        return resultado;

    }

    public Marca carregaMarcaPorID(long id) throws ParseException {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_MARCA, BancoUtil.NOME_MARCA};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_MARCA + " = " + id;

        cursor = db.query(BancoUtil.TABELA_MARCA, campos, where, null,
                null, null, null, null);

        Marca marca = new Marca();
        if (cursor != null) {
            cursor.moveToFirst();

            int idMarca = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_MARCA));
            String nomeMarca = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_MARCA));

            marca.setIdMarca(idMarca);
            marca.setNomeMarca(nomeMarca);

        }
        db.close();
        return marca;
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_MARCA, BancoUtil.NOME_MARCA};
        db = banco.getReadableDatabase();

        String where = null;
        cursor = db.query(BancoUtil.TABELA_MARCA, campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Marca> carregaDadosLista() {
        Cursor cursor = null;

        cursor = carregaDados();

        List<Marca> marcas = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Marca marca = new Marca();
                    int idMarca = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_MARCA));
                    String nomeMarca = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_MARCA));

                    marca.setIdMarca(idMarca);
                    marca.setNomeMarca(nomeMarca);

                    marcas.add(marca);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return marcas;
    }

    public void deletaRegistro(int id) {
        String where = BancoUtil.ID_MARCA + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_MARCA, where, null);
        db.close();
    }

    public boolean atualizaMarca(Marca marca) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_MARCA + " = " + marca.getIdMarca();
        where = BancoUtil.NOME_MARCA + " = " + marca.getNomeMarca();

        valores = new ContentValues();
        valores.put(BancoUtil.NOME_MARCA, marca.getNomeMarca());

        int resultado = db.update(BancoUtil.TABELA_MARCA, valores, where, null);
        db.close();
        if (resultado > 0)
            return true;
        else
            return false;
    }
}
