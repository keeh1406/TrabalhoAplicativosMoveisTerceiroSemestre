package com.example.opet.aplicativomaiscadastros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.opet.aplicativomaiscadastros.ActivityCadastrar.ActivityCadastrarCliente;
import com.example.opet.aplicativomaiscadastros.ActivityCadastrar.ActivityCadastrarFornecedor;
import com.example.opet.aplicativomaiscadastros.ActivityCadastrar.ActivityCadastrarFuncionario;
import com.example.opet.aplicativomaiscadastros.ActivityCadastrar.ActivityCadastrarLoja;
import com.example.opet.aplicativomaiscadastros.ActivityCadastrar.ActivityCadastrarMarca;
import com.example.opet.aplicativomaiscadastros.ActivityCadastrar.ActivityCadastrarProduto;
import com.example.opet.aplicativomaiscadastros.ActivityCadastrar.ActivityCadastrarSetor;
import com.example.opet.aplicativomaiscadastros.ActivityCadastrar.ActivityCadastrarVenda;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarClientes;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarFornecedores;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarFuncionarios;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarLojas;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarMarcas;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarProdutos;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarSetores;
import com.example.opet.aplicativomaiscadastros.ActivityListar.ActivityListarVendas;
import com.example.opet.aplicativomaiscadastros.ActivityLogin.LoginActivity;
import com.example.opet.aplicativomaiscadastros.DAO.FuncionarioDAO;
import com.example.opet.aplicativomaiscadastros.Model.Funcionario;

public class MainActivity extends Activity {

    private static Funcionario usuarioLogado;
    private TextView textWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textWelcome = (TextView) findViewById(R.id.textWelcome);

        Intent resultado = getIntent();
        int id = resultado.getIntExtra("ID_FUNCIONARIO",0);
        if(usuarioLogado == null)
            usuarioLogado = new FuncionarioDAO(this).carregaFuncionarioPorIDLogin(id);

        textWelcome.setText("Olá! " + usuarioLogado.getNomeFuncionario());
    }

    public void carregaItemMenu(View v){
        switch (v.getId()) {
            case R.id.btnInserirCliente:
                carregarIntent(ActivityCadastrarCliente.class);
                break;
            case R.id.btnInserirFuncionario:
                carregarIntent(ActivityCadastrarFuncionario.class);
                break;
            case R.id.btnInserirFornecedor:
                carregarIntent(ActivityCadastrarFornecedor.class);
                break;
            case R.id.btnInserirLoja:
                carregarIntent(ActivityCadastrarLoja.class);
                break;
            case R.id.btnInserirMarca:
                carregarIntent(ActivityCadastrarMarca.class);
                break;
            case R.id.btnInserirSetor:
                carregarIntent(ActivityCadastrarSetor.class);
                break;
            case R.id.btnInserirProduto:
                carregarIntent(ActivityCadastrarProduto.class);
                break;
            case R.id.btnInserirVenda:
                carregarIntent(ActivityCadastrarVenda.class);
                break;
            case R.id.btnListarClientes:
                carregarIntent(ActivityListarClientes.class);
                break;
            case R.id.btnListarFuncionarios:
                carregarIntent(ActivityListarFuncionarios.class);
                break;
            case R.id.btnListarFornecedores:
                carregarIntent(ActivityListarFornecedores.class);
                break;
            case R.id.btnListarLojas:
                carregarIntent(ActivityListarLojas.class);
                break;
            case R.id.btnListarMarcas:
                carregarIntent(ActivityListarMarcas.class);
                break;
            case R.id.btnListarSetores:
                carregarIntent(ActivityListarSetores.class);
                break;
            case R.id.btnListarProdutos:
                carregarIntent(ActivityListarProdutos.class);
                break;
            case R.id.btnListarVendas:
                carregarIntent(ActivityListarVendas.class);
                break;
        }
    }

    public void deslogar(View v){
        usuarioLogado = null;
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void carregarIntent(Class classe){
        Intent intent = new Intent(MainActivity.this,classe);
        startActivity(intent);
    }
}
