package v1.ajude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v1.ajude.daos.UsuariosRepository;
import v1.ajude.models.Usuario;

import java.util.Collection;
import java.util.Optional;

@Service
public class UsuarioServices {

    @Autowired
    private UsuariosRepository<Usuario, String> usuariosDAO;

    public UsuarioServices(UsuariosRepository<Usuario, String> usuariosDAO) {
        super();
        this.usuariosDAO = usuariosDAO;
    }

    public Usuario addUsuario(Usuario usuario) {
        return usuariosDAO.save(usuario);
    }

    public Optional<Usuario> getUsuario(String email) {
        return this.usuariosDAO.findByEmail(email);
    }

    public Collection<Usuario> getUsuarios() {
        return usuariosDAO.findAll();
    }
}
