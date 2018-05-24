package com.example.opet.aplicativomaiscadastros.ActivityListar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opet.aplicativomaiscadastros.ActivityAtualizar.ActivityAtualizarLoja;
import com.example.opet.aplicativomaiscadastros.Adapter.LojaAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.LojaDAO;
import com.example.opet.aplicativomaiscadastros.Model.Loja;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityListarLojas extends Activity {

    private ListView listaLojas;
    private LojaAdapter myAdapter;
    LojaDAO lojaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_lojas);

        carregarElementos();
    }

    public void carregarElementos(){
        listaLojas = (ListView) findViewById(R.id.listLojas);
        lojaDAO = new LojaDAO(this);
        List<Loja> lojas = lojaDAO.carregaDadosLista();
        myAdapter = new LojaAdapter(this, R.layout.item_loja,lojas);
        listaLojas.setAdapter(myAdapter);
        listaLojas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Loja loja = (Loja)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ActivityListarLojas.this,ActivityAtualizarLoja.class);
                atualizarIntent.putExtra("ID_LOJA",loja.getIdLoja());
                startActivity(atualizarIntent);
            }
        });
    }
}
