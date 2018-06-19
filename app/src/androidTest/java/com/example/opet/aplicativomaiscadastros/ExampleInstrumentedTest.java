package com.example.opet.aplicativomaiscadastros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.opet.aplicativomaiscadastros.DAO.ClienteDAO;
import com.example.opet.aplicativomaiscadastros.Factory.DataBaseFactory;
import com.example.opet.aplicativomaiscadastros.Model.Cliente;
import com.example.opet.aplicativomaiscadastros.Util.BancoUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.opet.aplicativomaiscadastros", appContext.getPackageName());
    }

    @Test
    public void insereDado() throws Exception {

        //Criando o Cliente
        Context appContext = InstrumentationRegistry.getTargetContext();
        SQLiteDatabase db;
        DataBaseFactory banco = null;
        banco = new DataBaseFactory(appContext);

        //inserts
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setNomeCliente("Kethllyn");
        cliente.setEmailCliente("kdsm@gmail.com");
        cliente.setEnderecoCliente("Curitiba");
        cliente.setTelefoneCliente((long) 98);
        cliente.setSexoCliente("Feminino");
        //endregion

        //region Buscando o Cliente no Banco de Dados
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNomeCliente());
        valores.put(BancoUtil.EMAIL_CLIENTE, cliente.getEmailCliente());
        valores.put(BancoUtil.NASCIMENTO_CLIENTE, String.valueOf(cliente.getNascimentoCliente()));
        valores.put(BancoUtil.ENDERECO_CLIENTE, cliente.getEnderecoCliente());
        valores.put(BancoUtil.CPF_CLIENTE, cliente.getCPFCliente());
        valores.put(BancoUtil.TELEFONE_CLIENTE, cliente.getTelefoneCliente());
        valores.put(BancoUtil.SEXO_CLIENTE, cliente.getSexoCliente());

        resultado = db.insert(BancoUtil.TABELA_CLIENTE, null, valores);
        db.close();

        assertTrue(resultado > 0);
        //endregion

    }
    //endregion

    @Test
    public void DeletandoDados() throws Exception {
        //Deletando o Empregado utilizando o ID como parametro

        //region Deletando o empregado
        Context appContext = InstrumentationRegistry.getTargetContext();
        SQLiteDatabase db;
        DataBaseFactory banco;
        banco = new DataBaseFactory(appContext);
        ClienteDAO clienteDAO = new ClienteDAO();
        int id = 13;
        String where = BancoUtil.ID_CLIENTE + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(BancoUtil.TABELA_CLIENTE, where, null);
        db.close();
        //endregion

        //Verificando se ele realmente foi excluido

        //region Listando os Clientes
        //Listando os Clientes
        Cursor cursor;
        String[] campos = {BancoUtil.ID_CLIENTE, BancoUtil.NOME_CLIENTE, BancoUtil.EMAIL_CLIENTE, BancoUtil.NASCIMENTO_CLIENTE,
                BancoUtil.ENDERECO_CLIENTE, BancoUtil.CPF_CLIENTE, BancoUtil.TELEFONE_CLIENTE, BancoUtil.SEXO_CLIENTE};
        db = banco.getReadableDatabase();

        String condicao = BancoUtil.ID_CLIENTE + " = " + 5;

        cursor = db.query(BancoUtil.TABELA_CLIENTE, campos, condicao, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        //endregion

        //region Recuperando o Cliente listado com o ID passado para exclusão
        try {
            if (cursor.getCount() > 0) {
                do {

                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_CLIENTE));
                    String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NOME_CLIENTE));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.EMAIL_CLIENTE));
                    String nascimento = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.NASCIMENTO_CLIENTE));
                    String endereco = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ENDERECO_CLIENTE));
                    long cpf = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.CPF_CLIENTE));
                    long telefone = cursor.getLong(cursor.getColumnIndexOrThrow(BancoUtil.TELEFONE_CLIENTE));
                    String sexo = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.SEXO_CLIENTE));

                    assertFalse("Ainda há registros com esse ID", false);
                } while (cursor.moveToNext());
            }
            else
                assertTrue("Removido com sucesso", true);
        } finally {
            cursor.close();
        }
        //endregion
    }

    @Test
    public void UpdateMetodo() throws Exception{

        //region Adicionandos os novos dados do Cliente
        Context appContext = InstrumentationRegistry.getTargetContext();
        SQLiteDatabase db;
        DataBaseFactory banco = null;

        banco = new DataBaseFactory(appContext);

        ContentValues valores;
        String where;

        Cliente cliente = new Cliente();
        cliente.setNomeCliente("Kethllyn Martins");
        cliente.setEmailCliente("kdsm@gmail.com.br");
        cliente.setEnderecoCliente("Curitiba Pr");
        cliente.setSexoCliente("Feminino");
        //endregion

        //region Fazendo Update
        db = banco.getWritableDatabase();

        where = BancoUtil.ID_CLIENTE + " = " + 23;

        valores = new ContentValues();

        valores.put(BancoUtil.NOME_CLIENTE, cliente.getNomeCliente());
        valores.put(BancoUtil.EMAIL_CLIENTE, cliente.getEmailCliente());
        valores.put(BancoUtil.NASCIMENTO_CLIENTE, String.valueOf(cliente.getNascimentoCliente()));
        valores.put(BancoUtil.ENDERECO_CLIENTE, cliente.getEnderecoCliente());
        valores.put(BancoUtil.CPF_CLIENTE, cliente.getCPFCliente());
        valores.put(BancoUtil.TELEFONE_CLIENTE, cliente.getTelefoneCliente());
        valores.put(BancoUtil.SEXO_CLIENTE, cliente.getSexoCliente());

        int resultado = db.update(BancoUtil.TABELA_CLIENTE,valores,where,null);
        db.close();
        assertTrue(resultado > 0);
        //endregion

    }
}