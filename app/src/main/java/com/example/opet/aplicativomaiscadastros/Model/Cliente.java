package com.example.opet.aplicativomaiscadastros.Model;

import java.util.Date;

/**
 * Created by opet on 25/04/2018.
 */

public class Cliente {
    private int idCliente;
    private String nomeCliente;
    private String emailCliente;
    private String senhaCliente;
    private Date nascimentoCliente;
    private Long cpfCliente;
    private String enderecoCliente;
    private Long telefoneCliente;
    private String sexoCliente;


    public Cliente() {
    }

    public Cliente(int idCliente, String nomeCliente, String emailCliente, String senhaCliente, Date nascimentoCliente, Long cpfCliente, String enderecoCliente, Long telefoneCliente, String sexoCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.senhaCliente = senhaCliente;
        this.nascimentoCliente = nascimentoCliente;
        this.cpfCliente = cpfCliente;
        this.enderecoCliente = enderecoCliente;
        this.telefoneCliente = telefoneCliente;
        this.sexoCliente = sexoCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    public Date getNascimentoCliente() {
        return nascimentoCliente;
    }

    public void setNascimentoCliente(Date nascimentoCliente) {
        this.nascimentoCliente = nascimentoCliente;
    }

    public Long getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(Long cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public Long getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(Long telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getSexoCliente() {
        return sexoCliente;
    }

    public void setSexoCliente(String sexoCliente) {
        this.sexoCliente = sexoCliente;
    }
}
