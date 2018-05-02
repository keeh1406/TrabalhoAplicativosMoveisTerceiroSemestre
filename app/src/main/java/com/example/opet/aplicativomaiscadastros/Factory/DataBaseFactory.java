package com.example.opet.aplicativomaiscadastros.Factory;

/**
 * Created by opet on 02/05/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.opet.aplicativomaiscadastros.Util.BancoUtil;

public class DataBaseFactory extends SQLiteOpenHelper {

    public DataBaseFactory(Context context){
        super(context, BancoUtil.NOME_BANCO,null,BancoUtil.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ BancoUtil.TABELA_PRODUTO+"("
                + BancoUtil.ID_PRODUTO+ " integer primary key autoincrement,"
                + BancoUtil.NOME_PRODUTO + " text,"
                + BancoUtil.DESCRICAO_PRODUTO + " text,"
                + BancoUtil.VALIDADE_PRODUTO + " text,"
                + BancoUtil.PRODUTO_FORNECEDOR + " text,"
                + BancoUtil.PRODUTO_SETOR + " text,"
                + BancoUtil.PRODUTO_MARCA + " text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_PRODUTO);
        onCreate(db);
    }
}
