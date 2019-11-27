package v1.ajude.controllers;

import v1.ajude.models.Usuario;
import v1.ajude.dtos.UsuarioDTO;
import v1.ajude.dtos.UsuarioDTOPagina;
import v1.ajude.services.JWTService;
import v1.ajude.services.UsuarioServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import java.util.Collection;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;
    @Autowired
    private JWTService jwtService;

    public UsuarioController(UsuarioServices usuarioServices, JWTService jwtService) {
        super();
        this.usuarioServices = usuarioServices;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<UsuarioDTO>(usuarioServices.addUsuario(usuario), HttpStatus.OK);
    }

    @GetMapping("/{urlUser}")
    public ResponseEntity<UsuarioDTOPagina> getUsuario(@PathVariable String urlUser) {
        return new ResponseEntity<UsuarioDTOPagina>(usuarioServices.getUsuarioDTOPagina(urlUser), HttpStatus.OK);
    }

    @RequestMapping("/list")
    public ResponseEntity<Collection<UsuarioDTO>>  getUsuarios(){
        return  new ResponseEntity<Collection<UsuarioDTO>>(usuarioServices.getUsuarios(),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UsuarioDTO> deleteUsuario(@RequestHeader ("Authorization") String header) {
        try {
            String email = jwtService.getSujeitoDoToken(header);
            if(jwtService.usuarioExiste(header)) {
                return new ResponseEntity<UsuarioDTO>(usuarioServices.remove(email), HttpStatus.OK);
            }
        }catch(ServletException e){
            return new ResponseEntity<UsuarioDTO>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<UsuarioDTO>(HttpStatus.UNAUTHORIZED);
    }
}
