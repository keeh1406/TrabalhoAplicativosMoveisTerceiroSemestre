package com.example.opet.aplicativomaiscadastros.Model;

/**
 * Created by opet on 25/04/2018.
 */

public class Fornecedor {
    private int idFornecedor;
    private String nomeFornecedor;
    private Long CNPJFornecedor;
    private Long telefoneFornecedor;

    public Fornecedor() {
    }

    public Fornecedor(int idFornecedor, String nomeFornecedor, Long CNPJ, Long telefoneFornecedor) {
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        this.CNPJFornecedor = CNPJ;
        this.telefoneFornecedor = telefoneFornecedor;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public Long getCNPJ() {
        return CNPJFornecedor;
    }

    public void setCNPJ(Long CNPJ) {
        this.CNPJFornecedor = CNPJ;
    }

    public Long getTelefoneFornecedor() {
        return telefoneFornecedor;
    }

    public void setTelefoneFornecedor(Long telefoneFornecedor) {
        this.telefoneFornecedor = telefoneFornecedor;
    }
}
