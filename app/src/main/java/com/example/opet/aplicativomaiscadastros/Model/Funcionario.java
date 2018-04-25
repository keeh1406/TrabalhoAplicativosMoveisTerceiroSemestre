package com.example.opet.aplicativomaiscadastros.Model;

import java.util.Date;

/**
 * Created by opet on 25/04/2018.
 */

public class Funcionario {
    private int idFuncionario;
    private String nomeFuncionario;
    private String emailFuncionario;
    private String senhaFuncionario;
    private Date nascimentoFuncionario;
    private Long cpfFuncionario;
    private String enderecoFuncionario;
    private Long telefoneFuncionario;
    private Boolean flGerente;

    public Funcionario() {

    }

    public Funcionario(int idFuncionario, String nomeFuncionario, String emailFuncionario, String senhaFuncionario, Date nascimentoFuncionario, Long cpfFuncionario, String enderecoFuncionario, Long telefoneFuncionario, Boolean flGerente) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.emailFuncionario = emailFuncionario;
        this.senhaFuncionario = senhaFuncionario;
        this.nascimentoFuncionario = nascimentoFuncionario;
        this.cpfFuncionario = cpfFuncionario;
        this.enderecoFuncionario = enderecoFuncionario;
        this.telefoneFuncionario = telefoneFuncionario;
        this.flGerente = flGerente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getEmailFuncionario() {
        return emailFuncionario;
    }

    public void setEmailFuncionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public String getSenhaFuncionario() {
        return senhaFuncionario;
    }

    public void setSenhaFuncionario(String senhaFuncionario) {
        this.senhaFuncionario = senhaFuncionario;
    }

    public Date getNascimentoFuncionario() {
        return nascimentoFuncionario;
    }

    public void setNascimentoFuncionario(Date nascimentoFuncionario) {
        this.nascimentoFuncionario = nascimentoFuncionario;
    }

    public Long getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(Long cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getEnderecoFuncionario() {
        return enderecoFuncionario;
    }

    public void setEnderecoFuncionario(String enderecoFuncionario) {
        this.enderecoFuncionario = enderecoFuncionario;
    }

    public Long getTelefoneFuncionario() {
        return telefoneFuncionario;
    }

    public void setTelefoneFuncionario(Long telefoneFuncionario) {
        this.telefoneFuncionario = telefoneFuncionario;
    }

    public Boolean getFlGerente() {
        return flGerente;
    }

    public void setFlGerente(Boolean flGerente) {
        this.flGerente = flGerente;
    }
}
