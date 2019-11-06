package v1.ajude.controllers;

import v1.ajude.models.Usuario;
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

    @DeleteMapping("/delete")
    public ResponseEntity<Usuario> deleteUsuario(@RequestHeader ("Authorization") String header) {

        try {
            String email = jwtService.getSujeitoDoToken(header);
            System.out.println(email);
            if(jwtService.usuarioExiste(header)) {
                return new ResponseEntity<Usuario>(usuarioServices.remove(email).get(), HttpStatus.OK);
            }
        }catch(ServletException e){
            return new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
    }
}
