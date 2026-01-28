package com.telegrafo.backend.dto;

public class AdicionarContatoDTO {
    private Long usuarioId;
    private Long amigoId;

    public AdicionarContatoDTO(Long usuarioId, Long amigoId){
        this.usuarioId = usuarioId;
        this.amigoId = amigoId;
    }

    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public void setAmigoId(Long amigoId) { this.amigoId = amigoId; }

    public Long getUsuarioId(){ return usuarioId; }
    public Long getAmigoId(){ return amigoId; }
}