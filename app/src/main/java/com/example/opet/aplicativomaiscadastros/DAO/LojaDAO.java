package com.example.opet.aplicativomaiscadastros.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.aplicativomaiscadastros.Factory.DataBaseFactory;
import com.example.opet.aplicativomaiscadastros.Model.Loja;
import com.example.opet.aplicativomaiscadastros.Util.BancoUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class LojaDAO {
    private SQLiteDatabase db;
    private DataBaseFactory banco;


    public LojaDAO(Context context) {
        banco = new DataBaseFactory(context);
    }

    public long insereDado(Loja loja) {
        ContentValues valores;
        long resultado;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.NOME_LOJA, loja.getNomeLoja());
        valores.put(BancoUtil.CNPJ_LOJA, loja.getCNPJLoja());
        valores.put(BancoUtil.ENDERECO_LOJA, loja.getEnderecoLoja());
        valores.put(BancoUtil.EMAIL_LOJA, loja.getEmailLoja());
        valores.put(BancoUtil.TELEFONE_lOJA, loja.getTelefoneLoja());

        resultado = db.insert(BancoUtil.TABELA_LOJA, null, valores);
        db.close();

        return resultado;

    }

    public Loja carregaLojaPorID(long id) throws ParseException {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_LOJA, BancoUtil.NOME_LOJA, BancoUtil.CNPJ_LOJA,
                BancoUtil.ENDERECO_LOJA, BancoUtil.EMAIL_LOJA, BancoUtil.TELEFONE_lOJA};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_LOJA + " = " + id;

        cursor = db.query(BancoUtil.TABELA_LOJA, campos, where, null,
                null, null, null, null);

        Loja loja = new Loja();
        if (cursor != null) {
            cursor.moveToFirst();

            int idLoja = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_LOJA));
            String nomeLoja = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_LOJA));
            long cpfLoja = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CNPJ_LOJA));
            String enderecoLoja = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ENDERECO_LOJA));
            String emailLoja = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.EMAIL_LOJA));
            long telefoneLoja = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.TELEFONE_lOJA));;

            loja.setIdLoja(idLoja);
            loja.setNomeLoja(nomeLoja);
            loja.setCNPJLoja(cpfLoja);
            loja.setEnderecoLoja(enderecoLoja);
            loja.setEmailLoja(emailLoja);
            loja.setTelefoneLoja(telefoneLoja);

        }
        db.close();
        return loja;
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_LOJA, BancoUtil.NOME_LOJA, BancoUtil.CNPJ_LOJA,
             BancoUtil.ENDERECO_LOJA, BancoUtil.EMAIL_LOJA,BancoUtil.TELEFONE_lOJA};
        db = banco.getReadableDatabase();

        String where = null;
        cursor = db.query(BancoUtil.TABELA_LOJA, campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Loja> carregaDadosLista() {
        Cursor cursor = null;

        cursor = carregaDados();

        List<Loja> lojas = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Loja loja = new Loja();
                    int idLoja = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_LOJA));
                    String nomeLoja = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_LOJA));
                    long cpfLoja = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CNPJ_LOJA));
                    String enderecoLoja = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ENDERECO_LOJA));
                    String emailLoja = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.EMAIL_LOJA));
                    long telefoneLoja = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.TELEFONE_lOJA));

                    loja.setIdLoja(idLoja);
                    loja.setNomeLoja(nomeLoja);
                    loja.setCNPJLoja(cpfLoja);
                    loja.setEnderecoLoja(enderecoLoja);
                    loja.setEmailLoja(emailLoja);
                    loja.setTelefoneLoja(telefoneLoja);

                    lojas.add(loja);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return lojas;
    }

    public void deletaRegistro(int id) {
        String where = BancoUtil.ID_LOJA + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_LOJA, where, null);
        db.close();
    }

    public boolean atualizaLoja(Loja loja) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_LOJA + " = " + loja.getIdLoja();
        where = BancoUtil.NOME_LOJA + " = " + loja.getNomeLoja();
        where = BancoUtil.CNPJ_LOJA + " = " + loja.getCNPJLoja();
        where = BancoUtil.ENDERECO_LOJA + " = " + loja.getEnderecoLoja();
        where = BancoUtil.EMAIL_LOJA + " = " + loja.getEmailLoja();
        where = BancoUtil.TELEFONE_lOJA + " = " + loja.getTelefoneLoja();


        valores = new ContentValues();
        valores.put(BancoUtil.NOME_LOJA, loja.getNomeLoja());
        valores.put(BancoUtil.CNPJ_LOJA, loja.getCNPJLoja());
        valores.put(BancoUtil.ENDERECO_LOJA, loja.getEnderecoLoja());
        valores.put(BancoUtil.EMAIL_LOJA, loja.getEmailLoja());
        valores.put(BancoUtil.TELEFONE_lOJA, loja.getTelefoneLoja());


        int resultado = db.update(BancoUtil.TABELA_LOJA, valores, where, null);
        db.close();
        if (resultado > 0)
            return true;
        else
            return false;
    }
}
