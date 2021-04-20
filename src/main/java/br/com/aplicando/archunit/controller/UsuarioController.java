package br.com.aplicando.archunit.controller;

import br.com.aplicando.archunit.repository.UsuarioRepository;
import br.com.aplicando.archunit.service.UsuarioService;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
