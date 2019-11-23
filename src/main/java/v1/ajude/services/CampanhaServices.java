package v1.ajude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v1.ajude.daos.CampanhaRepository;
import v1.ajude.daos.ComentarioRepository;
import v1.ajude.daos.LikesRepository;
import v1.ajude.daos.RespostaRepository;
import v1.ajude.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampanhaServices {

    @Autowired
    private CampanhaRepository<Campanha, Integer> campanhasDAO;
    @Autowired
    private ComentarioRepository<Comentario, Integer> comentariosDAO;
    @Autowired
    private RespostaRepository<Resposta, Integer> respostasDAO;
    @Autowired
    private LikesRepository<Likes, Integer> likesDAO;

    @Autowired
    private UsuarioServices usuarioServices;

    public CampanhaServices(CampanhaRepository<Campanha, Integer> campanhasDAO) {
        this.campanhasDAO = campanhasDAO;
    }

    public Campanha criarCampanha(String email, Campanha campanha) {
        Campanha campanhaSalva = recuperaCampanha(campanha);

        if (campanhaSalva == null) {
            Campanha campanhaConstruct = new Campanha(campanha.getNomeCurto(), campanha.getDescricao(),
                    campanha.getDeadLineString(), campanha.getURL(), campanha.getMeta(), usuarioServices.getUsuario(email).get());
            return campanhasDAO.save(campanhaConstruct);
        }
        return null;
    }

    public Optional<Campanha> getCampanha(Long id) {
        Campanha campanhaSalva = recuperaCampanha(id);

        if (campanhaSalva != null) {
            // Atualiza Status Campanha, sem encerrar
            campanhaSalva.setStatus(false);
            campanhasDAO.save(campanhaSalva);
            return campanhasDAO.findById(id);
        }
        return null;
    }

    public Optional<Campanha> getCampanha(String url) {
        Optional<Campanha> campanha = campanhasDAO.findByURL(url);
        if (campanha.isPresent()) {
            return campanha;
        }
        return null;
    }

    public List<Campanha> getCampanhas(String subString) {
        List<Campanha> result = new ArrayList<>();
        List<Campanha> campanhas = campanhasDAO.findAll();

        for (Campanha campanha : campanhas) {
            if (campanha.getNomeCurto().contains(subString)) {
                result.add(campanha);
            }
        }
        return result;
    }

    public Campanha setStatus(Campanha campanha) {
        Campanha campanhaSalva = recuperaCampanha(campanha);

        if (campanhaSalva != null) {
            // Encerra Campanha
            campanhaSalva.setStatus(true);
            campanhasDAO.save(campanhaSalva);
            return campanhaSalva;
        }
        return null;
    }

    public Comentario addComentario(Campanha campanha, Comentario comentario, String email) {
        Campanha campanhaSalva = recuperaCampanha(campanha);
        Usuario usuarioSalvo = recuperaUsuario(email);

        if (campanhaSalva != null && usuarioSalvo != null) {
            
            Comentario novoComentario = new Comentario(campanhaSalva, usuarioSalvo, comentario.getTextoComentario());
            campanhaSalva.addComentario(novoComentario);

            comentariosDAO.save(novoComentario);
            campanhasDAO.save(campanhaSalva);
            return novoComentario;
        }
        return null;
    }

    public Comentario addResposta(Campanha campanha, Long idComentario, Resposta resposta, String email) {
        Campanha campanhaSalva = recuperaCampanha(campanha);
        Usuario usuarioSalvo = recuperaUsuario(email);
        Comentario comentarioSalvo = recuperaComentario(idComentario);

        if (campanhaSalva != null && usuarioSalvo != null && comentarioSalvo != null) {

            Resposta novaResposta = new Resposta(comentarioSalvo, usuarioSalvo, resposta.getTextoResposta());

            comentarioSalvo.addResposta(usuarioSalvo, novaResposta);

            respostasDAO.save(novaResposta);
            comentariosDAO.save(comentarioSalvo);
            campanhasDAO.save(campanhaSalva);
            return comentarioSalvo;
        }
        return null;
    }

    public Campanha addLike(Campanha campanha, String email) {
        Campanha campanhaSalva = recuperaCampanha(campanha);
        Usuario usuarioSalvo = recuperaUsuario(email);
        Likes likeSalvo = recuperaLike(campanhaSalva, usuarioSalvo);
        Likes thatLikes = null;

        if (campanhaSalva != null && usuarioSalvo != null) {

            if (likeSalvo != null) {
                thatLikes = likeSalvo;

                if (thatLikes.getLikeMode()) {
                    thatLikes.setLikeMode(false);
                } else {
                    thatLikes.setLikeMode(true);
                }

            } else {
                thatLikes = new Likes(usuarioSalvo, true, campanhaSalva);
            }

            campanhaSalva.setContLike(thatLikes);
            likesDAO.save(thatLikes);
            campanhasDAO.save(campanhaSalva);
            return campanhaSalva;
        }
        return null;
    }

    private Campanha recuperaCampanha(Campanha campanha) {
        Optional<Campanha> campanhaSalva = this.campanhasDAO.findById(campanha.getId());
        if (campanhaSalva.isPresent()) {
            return campanhaSalva.get();
        }
        return null;
    }
    private Campanha recuperaCampanha(Long id) {
        Optional<Campanha> campanhaSalva =  this.campanhasDAO.findById(id);
        if (campanhaSalva.isPresent()) {
            return campanhaSalva.get();
        }
        return null;
    }
    private Usuario recuperaUsuario(String email) {
        Optional<Usuario> usuarioSalvo =  usuarioServices.getUsuario(email);
        if (usuarioSalvo.isPresent()) {
            return usuarioSalvo.get();
        }
        return null;
    }
    private UsuarioDTO recuperaUsuarioDTO(String email) {
        return usuarioServices.getUsuarioDTO(email);
    }
    private Comentario recuperaComentario(Long idComentario) {
        Optional<Comentario> comentarioSalvo = this.comentariosDAO.findById(idComentario);
        if (comentarioSalvo.isPresent()) {
            return comentarioSalvo.get();
        }
        return null;
    }

    private Likes recuperaLike(Campanha campanha, Usuario usuario) {
        Optional<Likes> like = this.likesDAO.findByURL(campanha.getURL());
        if (like.isPresent()) {
            if (like.get().getLikeUsuario().equals(usuario)) {
                return like.get();
            }
        }
        return null;
    }

}
