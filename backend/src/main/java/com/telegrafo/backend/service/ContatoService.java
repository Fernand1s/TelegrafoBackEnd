package com.telegrafo.backend.service;

import com.telegrafo.backend.dto.ContatoResponseDTO;
import com.telegrafo.backend.dto.UsuarioHomeDTO;
import com.telegrafo.backend.model.Contato;
import com.telegrafo.backend.model.Usuario;
import com.telegrafo.backend.repository.ContatoRepository;
import com.telegrafo.backend.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final UsuarioRepository usuarioRepository;

    public ContatoService( ContatoRepository contatoRepository, UsuarioRepository usuarioRepository){
        this.contatoRepository = contatoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void adicionarContato(Long usuarioId, Long amigoId){
        if(contatoRepository.existsByUsuarioIdAndAmigoId(usuarioId, amigoId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Contato já adicionado");
        }

        if (contatoRepository.existsByUsuarioIdAndAmigoId(usuarioId, amigoId) ||
                contatoRepository.existsByUsuarioIdAndAmigoId(amigoId, usuarioId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este usuário já é seu contato.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        Usuario amigo = usuarioRepository.findById(amigoId).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        Contato contato1 = new Contato();
        contato1.setUsuario(usuario);
        contato1.setAmigo(amigo);

        Contato contato2 = new Contato();
        contato2.setUsuario(amigo);
        contato2.setAmigo(usuario);

        contatoRepository.saveAll(List.of(contato1, contato2));
    }

    public List<UsuarioHomeDTO> listarContatos(Long usuarioId) {

        return contatoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(contato -> {
                    Usuario amigo = contato.getAmigo();
                    return new UsuarioHomeDTO(
                            amigo.getId(),
                            amigo.getNome(),
                            amigo.getOnline(),
                            amigo.getFotoPerfil(),
                            true
                    );
                })
                .toList();
    }

}