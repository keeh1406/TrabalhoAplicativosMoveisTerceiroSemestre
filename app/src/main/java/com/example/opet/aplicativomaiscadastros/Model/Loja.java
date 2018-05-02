package com.example.opet.aplicativomaiscadastros.Model;

/**
 * Created by opet on 25/04/2018.
 */

public class Loja {
    private int idLoja;
    private Long CNPJLoja;
    private String enderecoLoja;
    private String nomeLoja;
    private String emailLoja;
    private Long telefoneLoja;


    public Loja() {
    }

    public int getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }

    public Long getCNPJLoja() {
        return CNPJLoja;
    }

    public void setCNPJLoja(Long CNPJLoja) {
        this.CNPJLoja = CNPJLoja;
    }

    public String getEnderecoLoja() {
        return enderecoLoja;
    }

    public void setEnderecoLoja(String enderecoLoja) {
        this.enderecoLoja = enderecoLoja;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getEmailLoja() {
        return emailLoja;
    }

    public void setEmailLoja(String emailLoja) {
        this.emailLoja = emailLoja;
    }

    public Long getTelefoneLoja() {
        return telefoneLoja;
    }

    public void setTelefoneLoja(Long telefoneLoja) {
        this.telefoneLoja = telefoneLoja;
    }
}
