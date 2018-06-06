package com.example.opet.aplicativomaiscadastros.Model;

import java.math.BigDecimal;

/**
 * Created by opet on 06/06/2018.
 */

public class Produto_Venda {
    private int idProdutoVenda;
    private int valorUnitario;
    private int quantidadeProduto;
    private int id_Produto;
    private int id_Venda;

    public Produto_Venda() {
    }

    public Produto_Venda(int idProdutoVenda, int valorUnitario, int quantidadeProduto, int id_Produto, int id_Venda) {
        this.idProdutoVenda = idProdutoVenda;
        this.valorUnitario = valorUnitario;
        this.quantidadeProduto = quantidadeProduto;
        this.id_Produto = id_Produto;
        this.id_Venda = id_Venda;
    }

    public int getIdProdutoVenda() {
        return idProdutoVenda;
    }

    public void setIdProdutoVenda(int idProdutoVenda) {
        this.idProdutoVenda = idProdutoVenda;
    }

    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public int getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(int id_Produto) {
        this.id_Produto = id_Produto;
    }

    public int getId_Venda() {
        return id_Venda;
    }

    public void setId_Venda(int id_Venda) {
        this.id_Venda = id_Venda;
    }
}
