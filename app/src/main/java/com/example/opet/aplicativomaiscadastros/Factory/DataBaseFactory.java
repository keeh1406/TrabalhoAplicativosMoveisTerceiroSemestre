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
        String sql = "CREATE TABLE "+ BancoUtil.TABELA_LOJA +"("
                + BancoUtil.ID_LOJA + " integer primary key autoincrement,"
                + BancoUtil.NOME_LOJA + " text,"
                + BancoUtil.CNPJ_LOJA + " text,"
                + BancoUtil.ENDERECO_LOJA + " text,"
                + BancoUtil.EMAIL_LOJA + " text,"
                + BancoUtil.TELEFONE_lOJA + " text"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+ BancoUtil.TABELA_FORNECEDOR +"("
                + BancoUtil.ID_FORNECEDOR + " integer primary key autoincrement,"
                + BancoUtil.NOME_FORNECEDOR + " text,"
                + BancoUtil.CNPJ_FORNECEDOR + " text"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+ BancoUtil.TABELA_CLIENTE +"("
                + BancoUtil.ID_CLIENTE + " integer primary key autoincrement,"
                + BancoUtil.NOME_CLIENTE + " text,"
                + BancoUtil.CPF_CLIENTE + " text,"
                + BancoUtil.NASCIMENTO_CLIENTE + " text,"
                + BancoUtil.ENDERECO_CLIENTE + " text,"
                + BancoUtil.EMAIL_CLIENTE + " text,"
                + BancoUtil.SENHA_CLIENTE + " text,"
                + BancoUtil.TELEFONE_CLIENTE + " text,"
                + BancoUtil.SEXO_CLIENTE + " text"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+ BancoUtil.TABELA_FUNCIONARIO +"("
                + BancoUtil.ID_FUNCIONARIO + " integer primary key autoincrement,"
                + BancoUtil.NOME_FUNCIONARIO + " text,"
                + BancoUtil.CPF_FUNCIONARIO + " text,"
                + BancoUtil.NASCIMENTO_FUNCIONARIO + " text,"
                + BancoUtil.ENDERECO_FUNCIONARIO + " text,"
                + BancoUtil.EMAIL_FUNCIONARIO + " text,"
                + BancoUtil.TELEFONE_FUNCIONARIO + " text,"
                + BancoUtil.SEXO_FUNCIONARIO + " text,"
                + BancoUtil.SENHA_FUNCIONARIO + " text,"
                + BancoUtil.FL_GERENTE + " text, "
                + BancoUtil.FUNCIONARIO_LOJA + " text "
                + ")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+ BancoUtil.TABELA_MARCA +"("
                + BancoUtil.ID_MARCA + " integer primary key autoincrement,"
                + BancoUtil.NOME_MARCA + " text"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+ BancoUtil.TABELA_SETOR +"("
                + BancoUtil.ID_SETOR + " integer primary key autoincrement,"
                + BancoUtil.NOME_SETOR + " text"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+ BancoUtil.TABELA_PRODUTO +"("
                + BancoUtil.ID_PRODUTO + " integer primary key autoincrement,"
                + BancoUtil.NOME_PRODUTO + " text,"
                + BancoUtil.DESCRICAO_PRODUTO + " text,"
                + BancoUtil.PRODUTO_FORNECEDOR + " text,"
                + BancoUtil.PRODUTO_SETOR + " text,"
                + BancoUtil.PRODUTO_MARCA + " text,"
                + " FOREIGN KEY (" + BancoUtil.PRODUTO_FORNECEDOR + ") REFERENCES " + BancoUtil.TABELA_FORNECEDOR + "(" + BancoUtil.ID_FORNECEDOR + "),"
                + " FOREIGN KEY (" + BancoUtil.PRODUTO_SETOR + ") REFERENCES " + BancoUtil.TABELA_SETOR + "(" + BancoUtil.ID_SETOR + "),"
                + " FOREIGN KEY (" + BancoUtil.PRODUTO_MARCA + ") REFERENCES " + BancoUtil.TABELA_MARCA + "(" + BancoUtil.ID_MARCA + ")"
                + ")";;
        db.execSQL(sql);

        sql = "CREATE TABLE "+ BancoUtil.TABELA_PRODUTO_VENDA +"("
                + BancoUtil.ID_PRODUTO_VENDA + " integer primary key autoincrement,"
                + BancoUtil.VALOR_UNITARIO + " text,"
                + BancoUtil.PRODUTO_VENDA_PRODUTO + " text,"
                + BancoUtil.PRODUTO_VENDA_VENDA + " text,"
                + " FOREIGN KEY (" + BancoUtil.PRODUTO_VENDA_PRODUTO + ") REFERENCES " + BancoUtil.TABELA_PRODUTO + "(" + BancoUtil.ID_PRODUTO + "),"
                + " FOREIGN KEY (" + BancoUtil.PRODUTO_VENDA_VENDA + ") REFERENCES " + BancoUtil.TABELA_VENDA + "(" + BancoUtil.ID_VENDA + ")"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+ BancoUtil.TABELA_VENDA +"("
                + BancoUtil.ID_VENDA + " integer primary key autoincrement,"
                + BancoUtil.DESCRICAO_VENDA + " text,"
                + BancoUtil.VALOR_TOTAL_VENDA + " text,"
                + BancoUtil.FORMA_PAGAMENTO_VENDA + " text,"
                + BancoUtil.DATA_VENDA + " text,"
                + BancoUtil.FECHADO + " text,"
                + BancoUtil.VENDA_CLIENTE + " text,"
                + BancoUtil.VENDA_PRODUTO + " text,"
                + " FOREIGN KEY (" + BancoUtil.VENDA_CLIENTE + ") REFERENCES " + BancoUtil.TABELA_CLIENTE + "(" + BancoUtil.ID_CLIENTE + "),"
                + " FOREIGN KEY (" + BancoUtil.VENDA_PRODUTO + ") REFERENCES " + BancoUtil.TABELA_PRODUTO + "(" + BancoUtil.ID_PRODUTO + ")"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_PRODUTO);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_PRODUTO_VENDA);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_FORNECEDOR);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_FUNCIONARIO);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_CLIENTE);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_LOJA);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_MARCA);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_SETOR);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_VENDA);

        onCreate(db);
    }
}
