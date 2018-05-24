package com.example.opet.aplicativomaiscadastros.ActivityListar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opet.aplicativomaiscadastros.ActivityAtualizar.ActivityAtualizarSetor;
import com.example.opet.aplicativomaiscadastros.Adapter.SetorAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.SetorDAO;
import com.example.opet.aplicativomaiscadastros.Model.Setor;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityListarSetores extends Activity {

    private ListView listaSetors;
    private SetorAdapter myAdapter;
    SetorDAO setorDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_setores);

        carregarElementos();
    }

    public void carregarElementos(){
        listaSetors = (ListView) findViewById(R.id.listSetores);
        setorDAO = new SetorDAO(this);
        List<Setor> setores = setorDAO.carregaDadosLista();
        myAdapter = new SetorAdapter(this, R.layout.item_setor,setores);
        listaSetors.setAdapter(myAdapter);
        listaSetors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Setor setor = (Setor)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ActivityListarSetores.this,ActivityAtualizarSetor.class);
                atualizarIntent.putExtra("ID_SETOR",setor.getIdSetor());
                startActivity(atualizarIntent);
            }
        });
    }
}
