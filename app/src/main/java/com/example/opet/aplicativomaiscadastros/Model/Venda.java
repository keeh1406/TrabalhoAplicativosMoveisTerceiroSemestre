package com.example.opet.aplicativomaiscadastros.Model;

import java.util.Date;

/**
 * Created by opet on 25/04/2018.
 */

public class Venda {
    private int idVenda;
    private String descricaoVenda;
    private Long valorTotalVenda;
    private String formaPagamentoVenda;
    private Date dataVenda;
    private String fechado;
    private int idProdutoVenda;
    private int idClienteVenda;

    public Venda() {
    }

    public Venda(int idVenda, String descricaoVenda, Long valorTotalVenda, String fechado, Date dataVenda, String formaPagamentoVenda) {
        this.idVenda = idVenda;
        this.descricaoVenda = descricaoVenda;
        this.valorTotalVenda = valorTotalVenda;
        this.fechado = fechado;
        this.formaPagamentoVenda = formaPagamentoVenda;
        this.dataVenda = dataVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getDescricaoVenda() {
        return descricaoVenda;
    }

    public void setDescricaoVenda(String descricaoVenda) {
        this.descricaoVenda = descricaoVenda;
    }

    public Long getValorTotalVenda() {
        return valorTotalVenda;
    }

    public void setValorTotalVenda(Long valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
    }

    public String getFormaPagamentoVenda() {
        return formaPagamentoVenda;
    }

    public void setFormaPagamentoVenda(String formaPagamentoVenda) { this.formaPagamentoVenda = formaPagamentoVenda;}

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getIdProdutoVenda() {
        return idProdutoVenda;
    }

    public void setIdProdutoVenda(int idProdutoVenda) {
        this.idProdutoVenda = idProdutoVenda;
    }

    public int getIdClienteVenda() {
        return idClienteVenda;
    }

    public void setIdClienteVenda(int idClienteVenda) {
        this.idClienteVenda = idClienteVenda;
    }

    public String getFechado() {
        return fechado;
    }

    public void setFechado(String fechado) {
        this.fechado = fechado;
    }
}
