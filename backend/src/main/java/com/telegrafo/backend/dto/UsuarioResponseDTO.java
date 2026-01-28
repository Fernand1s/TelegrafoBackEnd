package com.telegrafo.backend.dto;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String fotoPerfil;

    public UsuarioResponseDTO(Long id, String nome, String email, String fotoPerfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getFotoPerfil() { return fotoPerfil; }
}