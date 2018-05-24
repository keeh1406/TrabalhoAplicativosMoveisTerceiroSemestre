package com.example.opet.aplicativomaiscadastros.ActivityListar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.opet.aplicativomaiscadastros.ActivityAtualizar.ActivityAtualizarMarca;
import com.example.opet.aplicativomaiscadastros.Adapter.MarcaAdapter;
import com.example.opet.aplicativomaiscadastros.DAO.MarcaDAO;
import com.example.opet.aplicativomaiscadastros.Model.Marca;
import com.example.opet.aplicativomaiscadastros.R;

import java.util.List;

/**
 * Created by opet on 16/05/2018.
 */

public class ActivityListarMarcas extends Activity {

    private ListView listaMarcas;
    private MarcaAdapter myAdapter;
    MarcaDAO marcaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_marcas);

        carregarElementos();
    }

    public void carregarElementos(){
        listaMarcas = (ListView) findViewById(R.id.listMarcas);
        marcaDAO = new MarcaDAO(this);
        List<Marca> marcas = marcaDAO.carregaDadosLista();
        myAdapter = new MarcaAdapter(this, R.layout.item_marca,marcas);
        listaMarcas.setAdapter(myAdapter);
        listaMarcas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Marca marca = (Marca)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ActivityListarMarcas.this,ActivityAtualizarMarca.class);
                atualizarIntent.putExtra("ID_MARCA",marca.getIdMarca());
                startActivity(atualizarIntent);
            }
        });
    }
}
