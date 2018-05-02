package com.example.opet.aplicativomaiscadastros.Model;

import java.util.Date;

/**
 * Created by opet on 25/04/2018.
 */

public class Venda {
    private int idVenda;
    private int quantidadeProduto;
    private String descricaoVenda;
    private Long valorVenda;
    private Date dataVenda;
    private int id_Produto;
    private int id_Cliente;

    public Venda() {
    }

    public Venda(int idVenda, int quantidadeProduto, String descricaoVenda, Long valorVenda, Date dataVenda) {
        this.idVenda = idVenda;
        this.quantidadeProduto = quantidadeProduto;
        this.descricaoVenda = descricaoVenda;
        this.valorVenda = valorVenda;
        this.dataVenda = dataVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public String getDescricaoVenda() {
        return descricaoVenda;
    }

    public void setDescricaoVenda(String descricaoVenda) {
        this.descricaoVenda = descricaoVenda;
    }

    public Long getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Long valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(int id_Produto) {
        this.id_Produto = id_Produto;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }
}
