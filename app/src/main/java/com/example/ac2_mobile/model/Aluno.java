package com.example.ac2_mobile.model;


public class Aluno {

    private int ra;
    private String nome;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;


    public Aluno(int ra, String nome, String cep, String logradouro, String complemento, String bairro, String cidade, String uf) {
        this.ra = ra;
        this.nome = nome;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }
}
