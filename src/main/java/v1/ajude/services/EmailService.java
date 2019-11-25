package v1.ajude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import v1.ajude.models.Usuario;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public String boasVindas(Usuario usuario) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Seja Bem-vindo(a) ao AJuDE");
        message.setText("Olá, " + usuario.getPrimeiroNome() + " " + usuario.getUltimoNome() + "." +
                System.lineSeparator() +
                "É uma horna poder contar com sua presença." +
                System.lineSeparator() +
                "Estamos ansiosos por sua Contribuição. " +
                System.lineSeparator() +
                "Busque Campanhas e Contribua de algumas forma positiva em nossa plataforma." +
                System.lineSeparator() +
                "Ou comece, agora mesmo, a promover sua própria campanha." +
                System.lineSeparator() +
                "Sua Página: " + usuario.getUrlUser() + System.lineSeparator() +
                "Aqui Juntos Doando Esperança!");
        message.setTo(usuario.getEmail().toLowerCase());
        message.setFrom("projeto.trampo2019@gmail.com");


        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
