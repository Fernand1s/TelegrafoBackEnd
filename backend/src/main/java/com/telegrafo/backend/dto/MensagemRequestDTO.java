package com.telegrafo.backend.dto;

public class MensagemRequestDTO {
    private Long remetenteId;
    private Long destinatarioId;
    private String conteudo;

    public void setRemetenteId(Long remetenteId){
        this.remetenteId = remetenteId;
    }

    public void setDestinatarioId(Long destinatarioId){
        this.destinatarioId = destinatarioId;
    }

    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }

    public Long getRemetenteId(){
        return remetenteId;
    }

    public Long getDestinatarioId(){
        return destinatarioId;
    }

    public String getConteudo(){
        return conteudo;
    }
}