package com.telegrafo.backend.dto;

public class UsuarioHomeDTO {
    private Long id;
    private String nome;
    private Boolean online;
    private String fotoPerfil;
    private Boolean jaContato;

    public UsuarioHomeDTO(Long id, String nome, Boolean online, String fotoPerfil, Boolean jaContato){
        this.id = id;
        this.nome = nome;
        this.online = online;
        this.fotoPerfil = fotoPerfil;
        this.jaContato = jaContato;
    }

    public Long getId(){ return id; }
    public String getNome(){ return nome; }
    public Boolean getOnline(){ return online; }
    public String getFotoPerfil() { return fotoPerfil; }
    public Boolean getJaContato() { return jaContato; }
}