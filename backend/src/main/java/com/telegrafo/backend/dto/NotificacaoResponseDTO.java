package com.telegrafo.backend.dto;

public class NotificacaoResponseDTO {
    private Long id;
    private Long remetenteId;
    private String remetenteNome;
    private String mensagem;
    private Boolean lida;

    public NotificacaoResponseDTO(Long id, Long remetenteId,  String remetenteNome, String mensagem, Boolean lida){
        this.id = id;
        this.remetenteId = remetenteId;
        this.remetenteNome = remetenteNome;
        this.mensagem = mensagem;
        this.lida = lida;
    }

    public Long getId(){ return id; }
    public Long getRemetenteId(){ return remetenteId; }
    public String getRemetenteNome(){ return remetenteNome; }
    public String getMensagem(){ return mensagem; }
    public Boolean getLida(){ return lida; }

}