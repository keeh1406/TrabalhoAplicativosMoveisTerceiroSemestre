package com.example.opet.aplicativomaiscadastros.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.aplicativomaiscadastros.Factory.DataBaseFactory;
import com.example.opet.aplicativomaiscadastros.Model.Funcionario;
import com.example.opet.aplicativomaiscadastros.Util.BancoUtil;
import com.example.opet.aplicativomaiscadastros.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class FuncionarioDAO {
    private SQLiteDatabase db;
    private DataBaseFactory banco;


    public FuncionarioDAO(Context context) {
        banco = new DataBaseFactory(context);
    }

    public long insereDado(Funcionario funcionario) {
        ContentValues valores;
        long resultado;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.NOME_FUNCIONARIO, funcionario.getNomeFuncionario());
        valores.put(BancoUtil.CPF_FUNCIONARIO, funcionario.getCPFFuncionario());
        valores.put(BancoUtil.NASCIMENTO_FUNCIONARIO, format.format(funcionario.getNascimentoFuncionario()));
        valores.put(BancoUtil.ENDERECO_FUNCIONARIO, funcionario.getEnderecoFuncionario());
        valores.put(BancoUtil.EMAIL_FUNCIONARIO, funcionario.getEmailFuncionario());
        valores.put(BancoUtil.SENHA_FUNCIONARIO, funcionario.getSenhaFuncionario());
        valores.put(BancoUtil.TELEFONE_FUNCIONARIO, funcionario.getTelefoneFuncionario());
        valores.put(BancoUtil.SEXO_FUNCIONARIO, funcionario.getSexoFuncionario());
        valores.put(BancoUtil.FL_GERENTE, funcionario.getFlGerente());
        valores.put(BancoUtil.FUNCIONARIO_LOJA, funcionario.getId_Loja());

        resultado = db.insert(BancoUtil.TABELA_FUNCIONARIO, null, valores);
        db.close();

        return resultado;

    }

    public Funcionario carregaFuncionarioPorID(long id) throws ParseException {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_FUNCIONARIO, BancoUtil.NOME_FUNCIONARIO, BancoUtil.CPF_FUNCIONARIO,
                BancoUtil.NASCIMENTO_FUNCIONARIO, BancoUtil.ENDERECO_FUNCIONARIO, BancoUtil.EMAIL_FUNCIONARIO,
                BancoUtil.SENHA_FUNCIONARIO, BancoUtil.TELEFONE_FUNCIONARIO, BancoUtil.SEXO_FUNCIONARIO,
                BancoUtil.FL_GERENTE, BancoUtil.FUNCIONARIO_LOJA};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_FUNCIONARIO + " = " + id;

        cursor = db.query(BancoUtil.TABELA_FUNCIONARIO, campos, where, null,
                null, null, null, null);

        Funcionario funcionario = new Funcionario();
        if (cursor != null) {
            cursor.moveToFirst();

            int idFuncionario = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_FUNCIONARIO));
            String nomeFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_FUNCIONARIO));
            long cpfFuncionario = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CPF_FUNCIONARIO));
            String nascimentoFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NASCIMENTO_FUNCIONARIO));
            String enderecoFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ENDERECO_FUNCIONARIO));
            String emailFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.EMAIL_FUNCIONARIO));
            String senhaFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SENHA_FUNCIONARIO));
            long telefoneFuncionario = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.TELEFONE_FUNCIONARIO));
            String sexoFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SEXO_FUNCIONARIO));
            String flGerente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.FL_GERENTE));
            int lojaFuncionario = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.FUNCIONARIO_LOJA));

            funcionario.setIdFuncionario(idFuncionario);
            funcionario.setNomeFuncionario(nomeFuncionario);
            funcionario.setCPFFuncionario(cpfFuncionario);
            funcionario.setNascimentoFuncionario(Util.toDate(nascimentoFuncionario));
            funcionario.setEnderecoFuncionario(enderecoFuncionario);
            funcionario.setEmailFuncionario(emailFuncionario);
            funcionario.setSenhaFuncionario(senhaFuncionario);
            funcionario.setTelefoneFuncionario(telefoneFuncionario);
            funcionario.setSexoFuncionario(sexoFuncionario);
            funcionario.setFlGerente(flGerente);
            funcionario.setId_Loja(lojaFuncionario);

        }
        db.close();
        return funcionario;
    }

    public Funcionario carregaFuncionarioPorIDLogin(int id){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_FUNCIONARIO, BancoUtil.EMAIL_FUNCIONARIO, BancoUtil.SENHA_FUNCIONARIO};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_FUNCIONARIO + " = " + id;

        cursor = db.query(BancoUtil.TABELA_FUNCIONARIO, campos, where, null, null, null, null, null);

        Funcionario funcionario = new Funcionario();
        if (cursor != null) {
            cursor.moveToFirst();

            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_FUNCIONARIO));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.EMAIL_FUNCIONARIO));
            String senha = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SENHA_FUNCIONARIO));

            funcionario.setIdFuncionario(ID);
            funcionario.setEmailFuncionario(email);
            funcionario.setSenhaFuncionario(senha);

        }
        db.close();
        return funcionario;
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_FUNCIONARIO, BancoUtil.NOME_FUNCIONARIO, BancoUtil.CPF_FUNCIONARIO,
                BancoUtil.NASCIMENTO_FUNCIONARIO, BancoUtil.ENDERECO_FUNCIONARIO, BancoUtil.EMAIL_FUNCIONARIO,
                BancoUtil.SENHA_FUNCIONARIO, BancoUtil.TELEFONE_FUNCIONARIO, BancoUtil.SEXO_FUNCIONARIO,
                BancoUtil.FL_GERENTE, BancoUtil.FUNCIONARIO_LOJA};
        db = banco.getReadableDatabase();

        String where = null;
        cursor = db.query(BancoUtil.TABELA_FUNCIONARIO, campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Funcionario> carregaDadosLista() {
        Cursor cursor = null;

        cursor = carregaDados();

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Funcionario funcionario = new Funcionario();
                    int idFuncionario = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_FUNCIONARIO));
                    String nomeFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_FUNCIONARIO));
                    long cpfFuncionario = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CPF_FUNCIONARIO));
                    String nascimentoFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NASCIMENTO_FUNCIONARIO));
                    String enderecoFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ENDERECO_FUNCIONARIO));
                    String emailFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.EMAIL_FUNCIONARIO));
                    String senhaFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SENHA_FUNCIONARIO));
                    long telefoneFuncionario = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.TELEFONE_FUNCIONARIO));
                    String sexoFuncionario = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SEXO_FUNCIONARIO));
                    String flGerente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.FL_GERENTE));
                    int lojaFuncionario = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.FUNCIONARIO_LOJA));

                    funcionario.setIdFuncionario(idFuncionario);
                    funcionario.setNomeFuncionario(nomeFuncionario);
                    funcionario.setCPFFuncionario(cpfFuncionario);
                    funcionario.setNascimentoFuncionario(Util.toDate(nascimentoFuncionario));
                    funcionario.setEnderecoFuncionario(enderecoFuncionario);
                    funcionario.setEmailFuncionario(emailFuncionario);
                    funcionario.setSenhaFuncionario(senhaFuncionario);
                    funcionario.setTelefoneFuncionario(telefoneFuncionario);
                    funcionario.setSexoFuncionario(sexoFuncionario);
                    funcionario.setFlGerente(flGerente);
                    funcionario.setId_Loja(lojaFuncionario);

                    funcionarios.add(funcionario);
                } while (cursor.moveToNext());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return funcionarios;
    }

    public void deletaRegistro(int id) {
        String where = BancoUtil.ID_FUNCIONARIO + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_FUNCIONARIO, where, null);
        db.close();
    }

    public boolean atualizaFuncionario(Funcionario funcionario) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_FUNCIONARIO + " = " + funcionario.getIdFuncionario();
        where = BancoUtil.NOME_FUNCIONARIO + " = " + funcionario.getNomeFuncionario();
        where = BancoUtil.CPF_FUNCIONARIO + " = " + funcionario.getCPFFuncionario();
        where = BancoUtil.NASCIMENTO_FUNCIONARIO + " = " + funcionario.getNascimentoFuncionario();
        where = BancoUtil.ENDERECO_FUNCIONARIO + " = " + funcionario.getEnderecoFuncionario();
        where = BancoUtil.EMAIL_FUNCIONARIO + " = " + funcionario.getEmailFuncionario();
        where = BancoUtil.SENHA_FUNCIONARIO + " = " + funcionario.getSenhaFuncionario();
        where = BancoUtil.TELEFONE_FUNCIONARIO + " = " + funcionario.getTelefoneFuncionario();
        where = BancoUtil.SEXO_FUNCIONARIO + " = " + funcionario.getSexoFuncionario();
        where = BancoUtil.FL_GERENTE + " = " + funcionario.getFlGerente();
        where = BancoUtil.FUNCIONARIO_LOJA + " = " + funcionario.getId_Loja();


        valores = new ContentValues();
        valores.put(BancoUtil.NOME_FUNCIONARIO, funcionario.getNomeFuncionario());
        valores.put(BancoUtil.CPF_FUNCIONARIO, funcionario.getCPFFuncionario());
        valores.put(BancoUtil.NASCIMENTO_FUNCIONARIO, String.valueOf(funcionario.getNascimentoFuncionario()));
        valores.put(BancoUtil.ENDERECO_FUNCIONARIO, funcionario.getEnderecoFuncionario());
        valores.put(BancoUtil.EMAIL_FUNCIONARIO, funcionario.getEmailFuncionario());
        valores.put(BancoUtil.SENHA_FUNCIONARIO, funcionario.getSenhaFuncionario());
        valores.put(BancoUtil.TELEFONE_FUNCIONARIO, funcionario.getTelefoneFuncionario());
        valores.put(BancoUtil.SEXO_FUNCIONARIO, funcionario.getSexoFuncionario());
        valores.put(BancoUtil.FL_GERENTE, funcionario.getFlGerente());
        valores.put(BancoUtil.FUNCIONARIO_LOJA, funcionario.getId_Loja());


        int resultado = db.update(BancoUtil.TABELA_FUNCIONARIO, valores, where, null);
        db.close();
        if (resultado > 0)
            return true;
        else
            return false;
    }

    public long validaUsuario(String email, String senha){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_FUNCIONARIO, BancoUtil.EMAIL_FUNCIONARIO, BancoUtil.SENHA_FUNCIONARIO};
        db = banco.getReadableDatabase();

        String where = BancoUtil.EMAIL_FUNCIONARIO + " = " + "'" + email + "'";
        where += " and " + BancoUtil.SENHA_FUNCIONARIO + " = " + "'" + senha + "'";

        cursor = db.query(BancoUtil.TABELA_FUNCIONARIO, campos, where, null, null, null, null, null);

        cursor.moveToFirst();

        db.close();

        if(cursor.getCount() > 0)
            return cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_FUNCIONARIO));
        return -1;
    }
}
