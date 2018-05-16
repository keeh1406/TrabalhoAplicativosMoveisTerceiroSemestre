package com.example.opet.aplicativomaiscadastros.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opet.aplicativomaiscadastros.Factory.DataBaseFactory;
import com.example.opet.aplicativomaiscadastros.Model.Cliente;
import com.example.opet.aplicativomaiscadastros.Util.BancoUtil;
import com.example.opet.aplicativomaiscadastros.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ClienteDAO {
    private SQLiteDatabase db;
    private DataBaseFactory banco;


    public ClienteDAO(Context context) {
        banco = new DataBaseFactory(context);
    }

    public long insereDado(Cliente cliente) {
        ContentValues valores;
        long resultado;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNomeCliente());
        valores.put(BancoUtil.CPF_CLIENTE, cliente.getCPFCliente());
        valores.put(BancoUtil.NASCIMENTO_CLIENTE, format.format(cliente.getNascimentoCliente()));
        valores.put(BancoUtil.ENDERECO_CLIENTE, cliente.getEnderecoCliente());
        valores.put(BancoUtil.EMAIL_CLIENTE, cliente.getEmailCliente());
        valores.put(BancoUtil.SENHA_CLIENTE, cliente.getSenhaCliente());
        valores.put(BancoUtil.TELEFONE_CLIENTE, cliente.getTelefoneCliente());
        valores.put(BancoUtil.SEXO_CLIENTE, cliente.getSexoCliente());

        resultado = db.insert(BancoUtil.TABELA_CLIENTE, null, valores);
        db.close();

        return resultado;

    }

    public Cliente carregaClientePorID(long id) throws ParseException {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_CLIENTE, BancoUtil.NOME_CLIENTE, BancoUtil.CPF_CLIENTE,
                BancoUtil.NASCIMENTO_CLIENTE, BancoUtil.ENDERECO_CLIENTE, BancoUtil.EMAIL_CLIENTE,
                BancoUtil.SENHA_CLIENTE, BancoUtil.TELEFONE_CLIENTE, BancoUtil.SEXO_CLIENTE};
        db = banco.getReadableDatabase();

        String where = BancoUtil.ID_CLIENTE + " = " + id;

        cursor = db.query(BancoUtil.TABELA_CLIENTE, campos, where, null,
                null, null, null, null);

        Cliente cliente = new Cliente();
        if (cursor != null) {
            cursor.moveToFirst();

            int idCliente = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_CLIENTE));
            String nomeCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_CLIENTE));
            long cpfCliente = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CPF_CLIENTE));
            String nascimentoCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NASCIMENTO_CLIENTE));
            String enderecoCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ENDERECO_CLIENTE));
            String emailCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.EMAIL_CLIENTE));
            String senhaCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SENHA_CLIENTE));
            long telefoneCliente = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.TELEFONE_CLIENTE));
            String sexoCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SEXO_CLIENTE));

            cliente.setIdCliente(idCliente);
            cliente.setNomeCliente(nomeCliente);
            cliente.setCPFCliente(cpfCliente);
            cliente.setNascimentoCliente(Util.toDate(nascimentoCliente));
            cliente.setEnderecoCliente(enderecoCliente);
            cliente.setEmailCliente(emailCliente);
            cliente.setSenhaCliente(senhaCliente);
            cliente.setTelefoneCliente(telefoneCliente);
            cliente.setSexoCliente(sexoCliente);

        }
        db.close();
        return cliente;
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_CLIENTE, BancoUtil.NOME_CLIENTE, BancoUtil.CPF_CLIENTE,
                BancoUtil.NASCIMENTO_CLIENTE, BancoUtil.ENDERECO_CLIENTE, BancoUtil.EMAIL_CLIENTE,
                BancoUtil.SENHA_CLIENTE, BancoUtil.TELEFONE_CLIENTE, BancoUtil.SEXO_CLIENTE};
        db = banco.getReadableDatabase();

        String where = null;
        cursor = db.query(BancoUtil.TABELA_CLIENTE, campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Cliente> carregaDadosLista() {
        Cursor cursor = null;

        cursor = carregaDados();

        List<Cliente> clientes = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Cliente cliente = new Cliente();
                    int idCliente = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_CLIENTE));
                    String nomeCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_CLIENTE));
                    long cpfCliente = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CPF_CLIENTE));
                    String nascimentoCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NASCIMENTO_CLIENTE));
                    String enderecoCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ENDERECO_CLIENTE));
                    String emailCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.EMAIL_CLIENTE));
                    String senhaCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SENHA_CLIENTE));
                    long telefoneCliente = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.TELEFONE_CLIENTE));
                    String sexoCliente = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SEXO_CLIENTE));

                    cliente.setIdCliente(idCliente);
                    cliente.setNomeCliente(nomeCliente);
                    cliente.setCPFCliente(cpfCliente);
                    cliente.setNascimentoCliente(Util.toDate(nascimentoCliente));
                    cliente.setEnderecoCliente(enderecoCliente);
                    cliente.setEmailCliente(emailCliente);
                    cliente.setSenhaCliente(senhaCliente);
                    cliente.setTelefoneCliente(telefoneCliente);
                    cliente.setSexoCliente(sexoCliente);

                    clientes.add(cliente);
                } while (cursor.moveToNext());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return clientes;
    }

    public void deletaRegistro(int id) {
        String where = BancoUtil.ID_CLIENTE + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_CLIENTE, where, null);
        db.close();
    }

    public boolean atualizaCliente(Cliente cliente) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_CLIENTE + " = " + cliente.getIdCliente();
        where = BancoUtil.NOME_CLIENTE + " = " + cliente.getNomeCliente();
        where = BancoUtil.CPF_CLIENTE + " = " + cliente.getCPFCliente();
        where = BancoUtil.NASCIMENTO_CLIENTE + " = " + cliente.getNascimentoCliente();
        where = BancoUtil.ENDERECO_CLIENTE + " = " + cliente.getEnderecoCliente();
        where = BancoUtil.EMAIL_CLIENTE + " = " + cliente.getEmailCliente();
        where = BancoUtil.SENHA_CLIENTE + " = " + cliente.getSenhaCliente();
        where = BancoUtil.TELEFONE_CLIENTE + " = " + cliente.getTelefoneCliente();
        where = BancoUtil.SEXO_CLIENTE + " = " + cliente.getSexoCliente();


        valores = new ContentValues();
        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNomeCliente());
        valores.put(BancoUtil.CPF_CLIENTE, cliente.getCPFCliente());
        valores.put(BancoUtil.NASCIMENTO_CLIENTE, String.valueOf(cliente.getNascimentoCliente()));
        valores.put(BancoUtil.ENDERECO_CLIENTE, cliente.getEnderecoCliente());
        valores.put(BancoUtil.EMAIL_CLIENTE, cliente.getEmailCliente());
        valores.put(BancoUtil.SENHA_CLIENTE, cliente.getSenhaCliente());
        valores.put(BancoUtil.TELEFONE_CLIENTE, cliente.getTelefoneCliente());
        valores.put(BancoUtil.SEXO_CLIENTE, cliente.getSexoCliente());


        int resultado = db.update(BancoUtil.TABELA_CLIENTE, valores, where, null);
        db.close();
        if (resultado > 0)
            return true;
        else
            return false;
    }
}
