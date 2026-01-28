package com.telegrafo.backend.controller;

import com.telegrafo.backend.dto.*;
import com.telegrafo.backend.model.Usuario;
import com.telegrafo.backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/usuarios") //originalmente "/usuarios" em caso de erro de '404 Not Found'
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(
            @RequestBody @Valid UsuarioCadastroDTO dto
    ) {
        return ResponseEntity.ok(service.cadastrar(dto));
    }

    @PostMapping("/auth")
    public ResponseEntity<UsuarioResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO dto
    ) {
        return ResponseEntity.ok(service.login(dto));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO dto) {
        service.logout(dto.getUserId());
        return ResponseEntity.ok().build();
    }

    List<Usuario> findByIdNot(Long id) {
        return null;
    }

    @GetMapping("/home/{usuarioId}")
    public ResponseEntity<List<UsuarioHomeDTO>> listarUsuariosHome(
            @PathVariable Long usuarioId
    ) {
        return ResponseEntity.ok(service.listarUsuariosHome(usuarioId));
    }

    @PostMapping("/foto-perfil")
    public ResponseEntity<UsuarioResponseDTO> uploadFotoPerfil(
        @RequestParam("usuarioId") Long usuarioId,
        @RequestParam("foto") MultipartFile foto
    ) {
        return ResponseEntity.ok(service.atualizarFotoPerfil(usuarioId, foto));
    }


    /*
    @PostMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable Long id) {
    service.logout(id);
    return ResponseEntity.ok().build();
    }
     */

    /*
    @GetMapping("/online")
    public List<UsuarioResponseDTO> usuariosOnline() {
        return service.listarOnline();
    }

     */




}