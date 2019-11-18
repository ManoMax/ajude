package v1.ajude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v1.ajude.daos.CampanhaRepository;
import v1.ajude.models.Campanha;

import java.util.Optional;

@Service
public class CampanhaServices {

    @Autowired
    private CampanhaRepository<Campanha, Integer> campanhasDAO;

    @Autowired
    private UsuarioServices usuarioServices;

    public CampanhaServices(CampanhaRepository<Campanha, Integer> campanhasDAO) {
        super();
        this.campanhasDAO = campanhasDAO;
    }

    public Campanha criarCampanha(String email, Campanha campanha) {
        Optional<Campanha> searchCampanha = this.campanhasDAO.findById(campanha.getId());
        if (!searchCampanha.isPresent()) {
            Campanha campanhaConstruct = new Campanha(campanha.getNomeCurto(), campanha.getDescricao(),
                    campanha.getDeadLineToString(), campanha.getURL(), campanha.getMeta(), usuarioServices.getUsuario(email).get());
            return campanhasDAO.save(campanhaConstruct);
        }
        return null;
    }

    // Encerra Campanha
    public Campanha setStatus(Campanha campanha) {
        Optional<Campanha> searchCampanha = this.campanhasDAO.findById(campanha.getId());
        if (searchCampanha.isPresent()) {
            searchCampanha.get().setStatus(true);
            campanhasDAO.save(searchCampanha.get());
            return searchCampanha.get();
        }
        return null;
    }

    public Optional<Campanha> getCampanha(Long id) {
        // Atualiza Campanha, sem encerrar
        campanhasDAO.findById(id).get().setStatus(false);
        return campanhasDAO.findById(id);
    }
}
