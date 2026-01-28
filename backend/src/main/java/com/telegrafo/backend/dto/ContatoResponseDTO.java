package com.telegrafo.backend.dto;

public class ContatoResponseDTO {
    private Long id;
    private String nome;
    private Boolean online;
    private String fotoPerfil;

    public ContatoResponseDTO(Long id, String nome, Boolean online, String fotoPerfil){
        this.id = id;
        this.nome = nome;
        this.online = online;
        this.fotoPerfil = fotoPerfil;
    }

    public Long getId(){ return id; }

    public String getNome(){ return nome; }

    public Boolean getOnline(){ return online; }

    public String getFotoPerfil(){ return fotoPerfil; }
}