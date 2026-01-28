package com.telegrafo.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contatos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "amigo_id"})
)
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "amigo_id")
    private Usuario amigo;

    public Contato() {}

    public void setId(Long id) { this.id = id; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setAmigo(Usuario amigo) { this.amigo = amigo; }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Usuario getAmigo() { return amigo; }

}