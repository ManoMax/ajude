package v1.ajude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v1.ajude.daos.CampanhaRepository;
import v1.ajude.daos.DoacaoRepository;
import v1.ajude.daos.UsuariosRepository;
import v1.ajude.models.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServices {

    @Autowired
    private UsuariosRepository<Usuario, String> usuariosDAO;
    @Autowired
    private DoacaoRepository<Doacao, Integer> doacoesDAO;
    @Autowired
    private CampanhaRepository<Campanha, Integer> campanhasDAO;
    @Autowired
    private EmailService emailService;

    public UsuarioServices(UsuariosRepository<Usuario, String> usuariosDAO) {
        super();
        this.usuariosDAO = usuariosDAO;
    }

    public UsuarioDTO addUsuario(Usuario usuario) {
        Optional<Usuario> searchUser = this.usuariosDAO.findByEmail(usuario.getEmail());
        if (!searchUser.isPresent()) {
            Usuario novoUsuario = new Usuario(usuario.getUrlUser(), usuario.getPrimeiroNome(), usuario.getUltimoNome(), usuario.getEmail(), usuario.getNumCartao(), usuario.getSenha());
            usuariosDAO.save(novoUsuario);
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getUrlUser(), usuario.getPrimeiroNome(),
                    usuario.getUltimoNome(), usuario.getEmail());
            emailService.boasVindas(novoUsuario);
            return usuarioDTO;
        }
        return null;
    }

    public Optional<Usuario> getUsuario(String email) {
        Optional<Usuario> usuario = usuariosDAO.findByEmail(email);
        if (usuario.isPresent()) {
            return usuario;
        }
        return null;
    }

    public UsuarioDTO getUsuarioDTO(String urlUser) {
        Optional<Usuario> usuarioSalvo = usuariosDAO.findByUrlUser(urlUser);
        if (usuarioSalvo.isPresent()) {
            Usuario usuario = usuarioSalvo.get();
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getUrlUser(), usuario.getPrimeiroNome(),
                    usuario.getUltimoNome(), usuario.getEmail());
            return usuarioDTO;
        }
        return null;
    }

    public Collection<UsuarioDTO> getUsuarios() {
        List<Usuario> usuarios = usuariosDAO.findAll();
        List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
        for (Usuario usuario : usuarios) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getUrlUser(), usuario.getPrimeiroNome(),
                    usuario.getUltimoNome(), usuario.getEmail());
            usuariosDTO.add(usuarioDTO);
        }
        return usuariosDTO;
    }

    public boolean exist(Usuario usuario) {
        Optional<Usuario> user = usuariosDAO.findById(usuario.getEmail());
        return user.isPresent();
    }

    public boolean exist(String emailUsuario) {
        Optional<Usuario> user = usuariosDAO.findById(emailUsuario);
        return user.isPresent();
    }

    public boolean verificaSenha(Usuario usuario) {
        if (usuario.getEmail() != null) {
            Optional<Usuario> user = usuariosDAO.findById(usuario.getEmail());
            return user.isPresent() && user.get().getSenha().equals(usuario.getSenha());
        }
        return false;
    }

    public UsuarioDTO remove(String email) {
        UsuarioDTO user = getUsuarioDTO(email);
        usuariosDAO.deleteById(email);
        return user;
    }

    public void addDoacao(Doacao novaDoacao) {
        this.doacoesDAO.save(novaDoacao);
    }

    public void addCampanha(Campanha novaCampanha) {
        this.campanhasDAO.save(novaCampanha);
    }

    public UsuarioDTOPagina getUsuarioDTOPagina(String urlUser) {
        Optional<Usuario> usuarioSalvo = usuariosDAO.findByUrlUser(urlUser);
        if (usuarioSalvo.isPresent()) {
            Usuario usuario = usuarioSalvo.get();
            UsuarioDTOPagina usuarioDTOPagina = new UsuarioDTOPagina(usuario.getUrlUser(), usuario.getPrimeiroNome(),
                    usuario.getUltimoNome(), usuario.getEmail(), usuario.getCampanhasCriadasDTO(), usuario.getDoacoesFeitasDTO());
            return usuarioDTOPagina;
        }
        return null;
    }
}
