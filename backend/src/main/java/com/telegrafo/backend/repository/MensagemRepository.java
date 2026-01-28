package com.telegrafo.backend.repository;


import com.telegrafo.backend.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    @Query("SELECT m FROM Mensagem m WHERE " +
            "(m.remetente.id = :id1 AND m.destinatario.id = :id2) OR " +
            "(m.remetente.id = :id2 AND m.destinatario.id = :id1) " +
            "ORDER BY m.dataEnvio ASC")
    List<Mensagem> findChatHistory(@Param("id1") Long id1, @Param("id2") Long id2);
}