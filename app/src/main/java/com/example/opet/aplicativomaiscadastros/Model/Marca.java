package com.example.opet.aplicativomaiscadastros.Model;

/**
 * Created by opet on 02/05/2018.
 */

public class Marca {
    private int idMarca;
    private String nomeMarca;

    public Marca() {
    }

    public Marca(int idMarca, String nomeMarca) {
        this.idMarca = idMarca;
        this.nomeMarca = nomeMarca;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }
}
