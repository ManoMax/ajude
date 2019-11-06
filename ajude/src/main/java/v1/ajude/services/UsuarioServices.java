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
        Optional<Usuario> searchUser = this.usuariosDAO.findByEmail(usuario.getEmail());
        System.out.println(searchUser);
        if (!searchUser.isPresent()) {
            return usuariosDAO.save(usuario);
        }
        return null;
    }

    public Optional<Usuario> getUsuario(String email) {
        return this.usuariosDAO.findByEmail(email);
    }

    public Collection<Usuario> getUsuarios() {
        return usuariosDAO.findAll();
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


    public Optional<Usuario> remove(String email) {
        Optional<Usuario> user = getUsuario(email);
        usuariosDAO.deleteById(email);
        return user;
    }
}
