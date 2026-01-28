package com.telegrafo.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificacoes")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario destinatario;

    @ManyToOne
    private Usuario remetente;

    private String mensagem;

    private Boolean lida = false;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Notificacao(){}

    public void setId(Long id){
        this.id = id;
    }
    public void setDestinatario(Usuario destinatario){
        this.destinatario = destinatario;
    }
    public void setRemetente(Usuario remetente){
        this.remetente = remetente;
    }
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
    public void setLida(Boolean lida){
        this.lida = lida;
    }
    public void setDataCriacao(LocalDateTime dataCriacao){
        this.dataCriacao = dataCriacao;
    }

    public Long getId(){
        return id;
    }
    public Usuario getDestinatario(){
        return destinatario;
    }
    public Usuario getRemetente(){
        return remetente;
    }
    public String getMensagem(){
        return mensagem;
    }
    public Boolean getLida(){
        return lida;
    }
    public LocalDateTime getDataCriacao(){
        return dataCriacao;
    }

}
