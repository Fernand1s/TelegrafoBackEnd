package com.telegrafo.backend.controller;

import com.telegrafo.backend.dto.MensagemRequestDTO;
import com.telegrafo.backend.dto.NotificacaoResponseDTO;
import com.telegrafo.backend.model.Mensagem;
import com.telegrafo.backend.model.Usuario;
import com.telegrafo.backend.repository.MensagemRepository;
import com.telegrafo.backend.repository.UsuarioRepository;
import com.telegrafo.backend.model.Notificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.telegrafo.backend.repository.NotificacaoRepository;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @GetMapping("/historico/{id1}/{id2}")
    public List<Mensagem> getHistorico(@PathVariable Long id1, @PathVariable Long id2) {
        return mensagemRepository.findChatHistory(id1, id2);
    }

    @MessageMapping("/chat")
    public void receberMensagem(@Payload MensagemRequestDTO dto) {

        Usuario remetente = usuarioRepository.findById(dto.getRemetenteId()).orElseThrow(() -> new RuntimeException("Remetente não encontrado"));
        Usuario destinatario = usuarioRepository.findById(dto.getDestinatarioId()).orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(remetente);
        mensagem.setDestinatario(destinatario);
        mensagem.setConteudo(dto.getConteudo());

        Mensagem salva = mensagemRepository.save(mensagem);

        boolean jaExisteNotificacao =
                notificacaoRepository.existsByDestinatarioIdAndRemetenteIdAndLidaFalse(
                         dto.getDestinatarioId(),
                         dto.getRemetenteId()
                );

        if (!jaExisteNotificacao) {
            Notificacao notificacao = new Notificacao();
            notificacao.setDestinatario(destinatario);
            notificacao.setRemetente(remetente);
            notificacao.setMensagem(
                    remetente.getNome() + " te enviou uma mensagem"
            );

            Notificacao notificacaoSalva = notificacaoRepository.save(notificacao);

            messagingTemplate.convertAndSend(
                    "/queue/notificacoes/" + dto.getDestinatarioId(),
                    new NotificacaoResponseDTO(
                            notificacaoSalva.getId(),
                            notificacaoSalva.getRemetente().getId(),
                            notificacaoSalva.getRemetente().getNome(),
                            notificacaoSalva.getMensagem(),
                            notificacaoSalva.getLida()
                    )
            );
        }

        messagingTemplate.convertAndSend(
                "/queue/mensagens/" + destinatario.getId(),
                salva
        );


        // para realizar testes
        messagingTemplate.convertAndSend(
                "/queue/mensagens/" + remetente.getId(),
                salva
        );
    }

    @GetMapping("/notificacoes/{usuarioId}")
    public List<NotificacaoResponseDTO> listar(@PathVariable Long usuarioId) {
        return notificacaoRepository
                .findByDestinatarioIdAndLidaFalse(usuarioId)
                .stream()
                .map(n -> new NotificacaoResponseDTO(
                        n.getId(),
                        n.getRemetente().getId(),
                        n.getRemetente().getNome(),
                        n.getMensagem(),
                        n.getLida()
                ))
                .toList();
    }

    @PutMapping("/notificacoes/{id}/ler")
    public void marcarComoLida(@PathVariable Long id) {
        Notificacao n = notificacaoRepository.findById(id)
                .orElseThrow();
        n.setLida(true);
        notificacaoRepository.save(n);
    }
}