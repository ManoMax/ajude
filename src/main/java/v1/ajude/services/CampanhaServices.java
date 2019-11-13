package v1.ajude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v1.ajude.daos.CampanhaRepository;
import v1.ajude.models.Campanha;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                    campanha.getStringDeadLine(), campanha.getStatus(), campanha.getMeta(), usuarioServices.getUsuario(email).get());
            return campanhasDAO.save(campanhaConstruct);
        }
        return null;
    }
}
