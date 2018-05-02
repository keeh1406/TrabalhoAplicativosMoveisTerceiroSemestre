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
    private Long CPFFuncionario;
    private String enderecoFuncionario;
    private Long telefoneFuncionario;
    private Boolean flGerente;
    private String sexoFuncionario;
    private int id_Loja;

    public Funcionario() {

    }

    public Funcionario(int idFuncionario, String nomeFuncionario, String emailFuncionario, String senhaFuncionario, Date nascimentoFuncionario, Long CPFFuncionario, String enderecoFuncionario, Long telefoneFuncionario, Boolean flGerente, String sexoFuncionario) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.emailFuncionario = emailFuncionario;
        this.senhaFuncionario = senhaFuncionario;
        this.nascimentoFuncionario = nascimentoFuncionario;
        this.CPFFuncionario = CPFFuncionario;
        this.enderecoFuncionario = enderecoFuncionario;
        this.telefoneFuncionario = telefoneFuncionario;
        this.flGerente = flGerente;
        this.sexoFuncionario = sexoFuncionario;
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

    public Long getCPFFuncionario() {
        return CPFFuncionario;
    }

    public void setCPFFuncionario(Long CPFFuncionario) {
        this.CPFFuncionario = CPFFuncionario;
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

    public String getSexoFuncionario() {
        return sexoFuncionario;
    }

    public void setSexoFuncionario(String sexoFuncionario) {
        this.sexoFuncionario = sexoFuncionario;
    }

    public long getId_Loja() {
        return id_Loja;
    }

    public void setId_Loja(int id_Loja) {
        this.id_Loja = id_Loja;
    }
}
