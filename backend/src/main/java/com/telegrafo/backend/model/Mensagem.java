package com.telegrafo.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


@Entity
@Table(name = "mensagens")
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private Usuario remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;

    @Column(columnDefinition = "TEXT")
    private String conteudo;
    private LocalTime dataEnvio = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

    public Mensagem() {}

    public void setId(Long id) { this.id = id; }
    public void setRemetente(Usuario remetente) { this.remetente = remetente; }
    public void setDestinatario(Usuario destinatario) { this.destinatario = destinatario; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public void setDataEnvio(LocalTime dataEnvio) { this.dataEnvio = dataEnvio; }

    public Usuario getRemetente() { return remetente; }
    public Usuario getDestinatario() { return destinatario; }
    public Long getId() { return id; }
    public String getConteudo() { return conteudo; }
    public LocalTime getDataEnvio() { return dataEnvio; }

}