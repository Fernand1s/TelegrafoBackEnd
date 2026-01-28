package com.telegrafo.backend.repository;

import com.telegrafo.backend.model.Contato;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends  JpaRepository<Contato, Long>{

    List<Contato> findByUsuarioId(Long usuarioId);

    boolean existsByUsuarioIdAndAmigoId(Long usuarioId, Long amigoId);

}
