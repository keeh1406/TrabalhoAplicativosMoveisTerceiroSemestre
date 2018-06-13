package com.example.opet.aplicativomaiscadastros;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.opet.aplicativomaiscadastros.DAO.ClienteDAO;
import com.example.opet.aplicativomaiscadastros.Factory.DataBaseFactory;
import com.example.opet.aplicativomaiscadastros.Model.Cliente;

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
    public void testeInsertBanco() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        ClienteDAO clienteDAO = new ClienteDAO(appContext);
        Cliente cliente = new Cliente();
        cliente.setNomeCliente("Kethllyn");
        cliente.setCPFCliente(06111821903);
        assertTrue(clienteDAO.insereDado(cliente) > 0);
        Cliente resultado = clienteDAO.carregaClientePorID(3);
        assertEquals(cliente, cliente);
        resultado.setNomeCliente(String.valueOf(clienteDAO));
        assertTrue(clienteDAO.atualizaCliente(resultado) > 0);
        assertTrue(clienteDAO.deletaRegistro(resultado) > 0);
    }
}