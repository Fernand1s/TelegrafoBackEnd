package com.telegrafo.backend.service;

import com.telegrafo.backend.dto.LoginRequestDTO;
import com.telegrafo.backend.dto.UsuarioCadastroDTO;
import com.telegrafo.backend.dto.UsuarioHomeDTO;
import com.telegrafo.backend.dto.UsuarioResponseDTO;
import com.telegrafo.backend.model.Usuario;
import com.telegrafo.backend.repository.ContatoRepository;
import com.telegrafo.backend.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final ImagemService imagemService;
    private final ContatoRepository contatoRepository;

    public UsuarioService(UsuarioRepository repository, ImagemService imagemService, ContatoRepository contatoRepository) {
        this.repository = repository;
        this.imagemService = imagemService;
        this.contatoRepository = contatoRepository;
    }

    public UsuarioResponseDTO cadastrar(UsuarioCadastroDTO dto) {

        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        if (!dto.getSenha().equals(dto.getSenhaConfirmacao())) {
            throw new RuntimeException("Senhas não coincidem");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(encoder.encode(dto.getSenha()));

        Usuario salvo = repository.save(usuario);

        usuario.setOnline(false);
        repository.save(usuario);

        return new UsuarioResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getFotoPerfil()
        );
    }

    public UsuarioResponseDTO login(LoginRequestDTO dto) {

        Usuario usuario = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        usuario.setOnline(true);
        repository.save(usuario);

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getFotoPerfil()
        );

    }

    public void logout(Long usuarioId) {
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setOnline(false);
        repository.save(usuario);
    }

    public List<UsuarioHomeDTO> listarUsuariosHome(Long usuarioId) {

        return repository.findByIdNot(usuarioId)

                .stream()
                .map(u -> {
                    boolean jaContato = contatoRepository.existsByUsuarioIdAndAmigoId(usuarioId, u.getId());

                    return new UsuarioHomeDTO(
                            u.getId(),
                            u.getNome(),
                            u.getOnline(),
                            u.getFotoPerfil(),
                            jaContato
                    );
                })
                .toList();

    }

    public UsuarioResponseDTO atualizarFotoPerfil(Long usuarioId, MultipartFile arquivo) {
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String base64 = imagemService.processarImagem(getInputStream(arquivo));

        usuario.setFotoPerfil(base64);
        Usuario salvo = repository.save(usuario);

        return new UsuarioResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getFotoPerfil()
        );
    }

    private InputStream getInputStream(MultipartFile file) {
        try {
            return file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo");
        }
    }
}