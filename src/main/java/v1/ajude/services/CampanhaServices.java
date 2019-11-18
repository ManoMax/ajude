package v1.ajude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v1.ajude.daos.CampanhaRepository;
import v1.ajude.models.Campanha;
import v1.ajude.models.Comentario;
import v1.ajude.models.Usuario;

import java.util.Optional;

@Service
public class CampanhaServices {

    @Autowired
    private CampanhaRepository<Campanha, Integer> campanhasDAO;

    @Autowired
    private UsuarioServices usuarioServices;

    public CampanhaServices(CampanhaRepository<Campanha, Integer> campanhasDAO) {
        this.campanhasDAO = campanhasDAO;
    }

    public Campanha criarCampanha(String email, Campanha campanha) {
        Optional<Campanha> searchCampanha = this.campanhasDAO.findById(campanha.getId());
        Campanha campanhaConstruct = new Campanha(campanha.getNomeCurto(), campanha.getDescricao(),
                campanha.getDeadLineToString(), campanha.getURL(), campanha.getMeta(), usuarioServices.getUsuario(email).get());
        return campanhasDAO.save(campanhaConstruct);
    }

    public Campanha setStatus(Campanha campanha) {
        Optional<Campanha> searchCampanha = this.campanhasDAO.findById(campanha.getId());
        // Encerra Campanha
        searchCampanha.get().setStatus(true);
        campanhasDAO.save(searchCampanha.get());
        return searchCampanha.get();
    }

    public Optional<Campanha> getCampanha(Long id) {
        // Atualiza Status Campanha, sem encerrar
        campanhasDAO.findById(id).get().setStatus(false);
        return campanhasDAO.findById(id);
    }

    public Campanha addComentario(Campanha campanha, Comentario comentario, String email) {
        Optional<Campanha> searchCampanha = this.campanhasDAO.findById(campanha.getId());
        Usuario usuario = usuarioServices.getUsuario(email).get();
        searchCampanha.get().addComentario(comentario, usuario);
        campanhasDAO.save(searchCampanha.get());
        return searchCampanha.get();
    }
}
