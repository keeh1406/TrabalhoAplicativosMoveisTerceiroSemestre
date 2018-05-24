package com.example.opet.aplicativomaiscadastros.ActivityListar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opet.aplicativomaiscadastros.ActivityAtualizar.ActivityAtualizarFuncionario;
import com.example.opet.aplicativomaiscadastros.Adapter.FuncionarioAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.FuncionarioDAO;
import com.example.opet.aplicativomaiscadastros.Model.Funcionario;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityListarFuncionarios extends Activity {

    private ListView listaFuncionarios;
    private FuncionarioAdapter myAdapter;
    FuncionarioDAO funcionarioDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_funcionarios);

        carregarElementos();
    }

    public void carregarElementos(){
        listaFuncionarios = (ListView) findViewById(R.id.listFuncionarios);
        funcionarioDAO = new FuncionarioDAO(this);
        List<Funcionario> funcionarios = funcionarioDAO.carregaDadosLista();
        myAdapter = new FuncionarioAdapter(this, R.layout.item_funcionario,funcionarios);
        listaFuncionarios.setAdapter(myAdapter);
        listaFuncionarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Funcionario funcionario = (Funcionario)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ActivityListarFuncionarios.this,ActivityAtualizarFuncionario.class);
                atualizarIntent.putExtra("ID_FUNCIONARIO",funcionario.getIdFuncionario());
                startActivity(atualizarIntent);
            }
        });
    }
}
