package v1.ajude.controllers;

import v1.ajude.models.Usuario;
import v1.ajude.services.JWTService;
import v1.ajude.services.UsuarioServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;
    @SuppressWarnings("unused")
    private JWTService jwtService;

    public UsuarioController(UsuarioServices usuarioServices, JWTService jwtService) {
        super();
        this.usuarioServices = usuarioServices;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<Usuario>(usuarioServices.addUsuario(usuario), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String email) {
        return new ResponseEntity<Usuario>(usuarioServices.getUsuario(email).get(), HttpStatus.OK);
    }

    @RequestMapping("/list")
    public ResponseEntity<Collection<Usuario>>  getUsuarios(){
        return  new ResponseEntity<Collection<Usuario>>(usuarioServices.getUsuarios(),HttpStatus.OK);
    }
}
