package com.example.opet.aplicativomaiscadastros.Model;

import java.util.Date;

/**
 * Created by opet on 25/04/2018.
 */

public class Produto {
    private int idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private int valor;
    private int id_Setor;
    private int id_Marca;
    private int id_Fornecedor;

    public Produto() {
    }

    public int getIdProduto() {
        return idProduto;
    }

    public Produto(int idProduto, String nomeProduto, String descricaoProduto, int valor) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.valor = valor;
        this.id_Setor = id_Setor;
        this.id_Marca = id_Marca;
        this.id_Fornecedor = id_Fornecedor;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getId_Setor() {
        return id_Setor;
    }

    public void setId_Setor(int id_Setor) {
        this.id_Setor = id_Setor;
    }

    public int getId_Marca() {
        return id_Marca;
    }

    public void setId_Marca(int id_Marca) {
        this.id_Marca = id_Marca;
    }

    public int getId_Fornecedor() {
        return id_Fornecedor;
    }

    public void setId_Fornecedor(int id_Fornecedor) {
        this.id_Fornecedor = id_Fornecedor;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
