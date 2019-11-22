package v1.ajude.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v1.ajude.models.Campanha;
import v1.ajude.models.Comentario;
import v1.ajude.services.CampanhaServices;
import v1.ajude.services.JWTService;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campanha")
public class CampanhaController {

    @Autowired
    private CampanhaServices campanhaServices;
    @Autowired
    private JWTService jwtService;

    public CampanhaController(CampanhaServices campanhaServices, JWTService jwtService) {
        super();
        this.campanhaServices = campanhaServices;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    public ResponseEntity<Campanha> criarCampanha(@RequestBody Campanha campanha, @RequestHeader("Authorization") String header) {
        try {
            if(jwtService.usuarioExiste(header)) {
                String email = jwtService.getSujeitoDoToken(header);
                return new ResponseEntity<Campanha>(campanhaServices.criarCampanha(email, campanha), HttpStatus.OK);
            }
        }catch(ServletException e){
            return new ResponseEntity<Campanha>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping("/{url}")
    public ResponseEntity<Campanha> getCampanha(@PathVariable String url, @RequestHeader("Authorization") String header) {
        try {
            if (jwtService.usuarioExiste(header)) {
                Campanha campanha = campanhaServices.getCampanhaUrl(url);
                if (campanha != null) {
                    return new ResponseEntity<Campanha>(campanha, HttpStatus.OK);
                }
                return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
            }
        } catch (ServletException e) {
            return new ResponseEntity<Campanha>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);

    }

    @RequestMapping("/{id}/status")
    public ResponseEntity<Campanha> setStatus(@PathVariable Long id, @RequestHeader("Authorization") String header) {
        try {
            if(jwtService.usuarioExiste(header)) {
                Optional<Campanha> campanha = campanhaServices.getCampanha(id);
                if (campanha.isPresent()) {
                    return new ResponseEntity<Campanha>(campanhaServices.setStatus(campanha.get()), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
                }
            }
        }catch(ServletException e){
            return new ResponseEntity<Campanha>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/busca/{substring}")
    public ResponseEntity<List<Campanha>> getCampanhasPorSubString(@PathVariable String substring) {
        return new ResponseEntity<List<Campanha>>(campanhaServices.getCampanhas(substring), HttpStatus.OK);
    }

}
