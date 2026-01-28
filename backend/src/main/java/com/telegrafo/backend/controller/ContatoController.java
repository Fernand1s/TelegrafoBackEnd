package com.telegrafo.backend.controller;

import com.telegrafo.backend.dto.AdicionarContatoDTO;
import com.telegrafo.backend.dto.UsuarioHomeDTO;
import com.telegrafo.backend.service.ContatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios/contatos") // Original "/contatos"
@CrossOrigin(origins = "*")
public class ContatoController {
    private final ContatoService service;

    public ContatoController(ContatoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody AdicionarContatoDTO dto){
        service.adicionarContato(dto.getUsuarioId(), dto.getAmigoId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<UsuarioHomeDTO>> listarContatos(@PathVariable Long usuarioId){
        return ResponseEntity.ok(service.listarContatos(usuarioId));
    }

}