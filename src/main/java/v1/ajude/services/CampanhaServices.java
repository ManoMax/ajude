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

    public CampanhaServices(CampanhaRepository<Campanha, Integer> campanhasDAO) {
        super();
        this.campanhasDAO = campanhasDAO;
    }

    public Optional<Campanha> criarCampanha(String email, Campanha campanha) {
        return null;
    }
}
