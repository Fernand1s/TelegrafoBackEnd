package com.telegrafo.backend.repository;

import com.telegrafo.backend.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    Boolean existsByDestinatarioIdAndRemetenteIdAndLidaFalse(
            Long destinatarioId,
            Long remetenteId
    );

    List<Notificacao> findByDestinatarioIdAndLidaFalse(Long destinatarioId);
}
